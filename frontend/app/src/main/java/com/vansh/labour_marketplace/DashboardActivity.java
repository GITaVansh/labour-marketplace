package com.vansh.labour_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard); // Loads your dashboard XML layout

        // 0. Profile Navigation (Good Morning, Ramesh click listener)
        TextView tvGreeting = findViewById(R.id.tvGreeting);
        if (tvGreeting != null) {
            tvGreeting.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, WorkerProfileActivity.class);
                startActivity(intent);
            });
        }

        // 1. Notification Button Click
        MaterialCardView btnNotification = findViewById(R.id.btnNotification);
        if (btnNotification != null) {
            btnNotification.setOnClickListener(v ->
                    Toast.makeText(DashboardActivity.this, "Notifications clicked", Toast.LENGTH_SHORT).show()
            );
        }

        // 2. Map View & Map Search Button Clicks
        MaterialCardView cardMapView = findViewById(R.id.cardMapView);
        if (cardMapView != null) {
            cardMapView.setOnClickListener(v ->
                    Toast.makeText(DashboardActivity.this, "Opening Map View...", Toast.LENGTH_SHORT).show()
            );
        }

        MaterialCardView btnMapSearch = findViewById(R.id.btnMapSearch);
        if (btnMapSearch != null) {
            btnMapSearch.setOnClickListener(v -> {
                if (v.getParent() != null) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                Toast.makeText(DashboardActivity.this, "Searching nearby jobs...", Toast.LENGTH_SHORT).show();
            });
        }

        // 3. Job 1 Buttons (Mason Helper) -> Navigates to Job Details
        MaterialButton btnAcceptJob1 = findViewById(R.id.btnAcceptJob1);
        if (btnAcceptJob1 != null) {
            btnAcceptJob1.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, JobsDetailActivity.class);
                intent.putExtra("JOB_TITLE", "Mason Helper");
                intent.putExtra("JOB_WAGE", "900");
                startActivity(intent);
            });
        }

        MaterialButton btnCounterJob1 = findViewById(R.id.btnCounterJob1);
        if (btnCounterJob1 != null) {
            btnCounterJob1.setOnClickListener(v ->
                    Toast.makeText(DashboardActivity.this, "Counter offer sent: ₹950 for Mason Helper", Toast.LENGTH_SHORT).show()
            );
        }

        // 4. Job 2 Buttons (Electrician) -> Navigates to Job Details
        MaterialButton btnAcceptJob2 = findViewById(R.id.btnAcceptJob2);
        if (btnAcceptJob2 != null) {
            btnAcceptJob2.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, JobsDetailActivity.class);
                intent.putExtra("JOB_TITLE", "Electrician");
                intent.putExtra("JOB_WAGE", "1200");
                startActivity(intent);
            });
        }

        MaterialButton btnCounterJob2 = findViewById(R.id.btnCounterJob2);
        if (btnCounterJob2 != null) {
            btnCounterJob2.setOnClickListener(v ->
                    Toast.makeText(DashboardActivity.this, "Counter offer sent: ₹950 for Electrician", Toast.LENGTH_SHORT).show()
            );
        }
    }
}