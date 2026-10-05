package com.vansh.labour_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JobPosted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_posted); // Match your XML file name

        // 1. "View My Jobs" Button Click Logic
        Button btnViewMyJobs = findViewById(R.id.btnViewMyJobs);
        btnViewMyJobs.setOnClickListener(v -> {
            Intent intent = new Intent(JobPosted.this, EmployerDashboardActivity.class);
            // Clear activity stack so users don't loop back to the success screen on back press
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // 2. "Back to Dashboard" Text Link Click Logic
        TextView tvBackToDashboard = findViewById(R.id.tvBackToDashboard);
        tvBackToDashboard.setOnClickListener(v -> {
            Intent intent = new Intent(JobPosted.this, DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}