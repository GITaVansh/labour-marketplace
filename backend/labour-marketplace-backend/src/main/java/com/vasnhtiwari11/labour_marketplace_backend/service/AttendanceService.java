package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.AlreadyCheckedInException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.NoActiveCheckInException;
import com.vasnhtiwari11.labour_marketplace_backend.model.Application;
import com.vasnhtiwari11.labour_marketplace_backend.model.Attendance;
import com.vasnhtiwari11.labour_marketplace_backend.exception.AttendanceNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.repository.ApplicationRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Attendance checkIn(Long applicationId, Double latitude, Double longitude) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        attendanceRepository
                .findTopByApplicationIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(applicationId)
                .ifPresent(existing -> {
                    throw new AlreadyCheckedInException(applicationId);
                });

        Attendance attendance = new Attendance(application, LocalDateTime.now(), latitude, longitude);
        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(Long applicationId) {
        Attendance activeAttendance = attendanceRepository
                .findTopByApplicationIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(applicationId)
                .orElseThrow(() -> new NoActiveCheckInException(applicationId));

        activeAttendance.setCheckOutTime(LocalDateTime.now());
        return attendanceRepository.save(activeAttendance);
    }

    public List<Attendance> getAttendanceHistory(Long applicationId) {
        return attendanceRepository.findByApplicationIdOrderByCheckInTimeDesc(applicationId);
    }

    public java.util.Map<String, Object> calculatePay(Long attendanceId) {
    Attendance attendance = attendanceRepository.findById(attendanceId)
            .orElseThrow(() -> new AttendanceNotFoundException(attendanceId));

    if (attendance.getCheckOutTime() == null) {
        throw new NoActiveCheckInException(attendance.getApplication().getId());
    }

    java.time.Duration workedDuration = java.time.Duration.between(
            attendance.getCheckInTime(), attendance.getCheckOutTime());
    double hoursWorked = workedDuration.toMinutes() / 60.0;

    double standardHours = 8.0;
    double overtimeMultiplier = 1.5;

    double regularHours = Math.min(hoursWorked, standardHours);
    double overtimeHours = Math.max(0, hoursWorked - standardHours);

    Double dailyWage = attendance.getApplication().getJob().getDailyWage();
    double hourlyRate = dailyWage / standardHours;

    double regularPay = regularHours * hourlyRate;
    double overtimePay = overtimeHours * hourlyRate * overtimeMultiplier;
    double totalPay = regularPay + overtimePay;

    java.util.Map<String, Object> result = new java.util.LinkedHashMap<>();
    result.put("hoursWorked", Math.round(hoursWorked * 100.0) / 100.0);
    result.put("regularHours", Math.round(regularHours * 100.0) / 100.0);
    result.put("overtimeHours", Math.round(overtimeHours * 100.0) / 100.0);
    result.put("hourlyRate", Math.round(hourlyRate * 100.0) / 100.0);
    result.put("regularPay", Math.round(regularPay * 100.0) / 100.0);
    result.put("overtimePay", Math.round(overtimePay * 100.0) / 100.0);
    result.put("totalPay", Math.round(totalPay * 100.0) / 100.0);

    return result;
}
}