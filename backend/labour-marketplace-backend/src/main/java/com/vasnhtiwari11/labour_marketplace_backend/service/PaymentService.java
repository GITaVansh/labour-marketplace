package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.PaymentNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.PaymentVerificationException;
import com.vasnhtiwari11.labour_marketplace_backend.model.Application;
import com.vasnhtiwari11.labour_marketplace_backend.model.Payment;
import com.vasnhtiwari11.labour_marketplace_backend.model.PaymentStatus;
import com.vasnhtiwari11.labour_marketplace_backend.repository.ApplicationRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.PaymentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public Payment createPaymentOrder(Long applicationId, Double amount) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", Math.round(amount * 100));
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "application_" + applicationId);

            com.razorpay.Order razorpayOrder = razorpayClient.orders.create(orderRequest);
            String orderId = razorpayOrder.get("id");

            Payment payment = new Payment(application, amount, orderId);
            return paymentRepository.save(payment);

        } catch (Exception e) {
            throw new PaymentVerificationException("Failed to create Razorpay order: " + e.getMessage());
        }
    }

    public Payment verifyAndHoldPayment(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
        Payment payment = paymentRepository.findByRazorpayOrderId(razorpayOrderId)
                .orElseThrow(() -> new PaymentNotFoundException(razorpayOrderId));

        try {
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", razorpayOrderId);
            options.put("razorpay_payment_id", razorpayPaymentId);
            options.put("razorpay_signature", razorpaySignature);

            boolean isValid = Utils.verifyPaymentSignature(options, razorpayKeySecret);

            if (!isValid) {
                throw new PaymentVerificationException("Payment signature verification failed.");
            }

            payment.setRazorpayPaymentId(razorpayPaymentId);
            payment.setStatus(PaymentStatus.HELD);
            return paymentRepository.save(payment);

        } catch (PaymentVerificationException e) {
            throw e;
        } catch (Exception e) {
            throw new PaymentVerificationException("Error verifying payment: " + e.getMessage());
        }
    }

    public Payment releasePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("id " + paymentId));

        if (payment.getStatus() != PaymentStatus.HELD) {
            throw new PaymentVerificationException("Only HELD payments can be released.");
        }

        payment.setStatus(PaymentStatus.RELEASED);
        return paymentRepository.save(payment);
    }
}