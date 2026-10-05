package com.vansh.labour_marketplace;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EmployerDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_dashboard); // Ensure this matches your XML file name

        // 1. Add New Job Button
        FrameLayout btnAddJob = findViewById(R.id.btnAddJob);
        btnAddJob.setOnClickListener(v ->
                Toast.makeText(EmployerDashboardActivity.this, "Opening Post New Job screen...", Toast.LENGTH_SHORT).show()
        );

        // 2. Tab Switching Logic (Active vs Completed)
        // Note: Give your tab TextViews IDs in XML if you want to reference them explicitly,
        // e.g., tvTabActive and tvTabCompleted.

        // 3. Job Card Clicks (Viewing Applicants)
        // If you added IDs to your CardViews:
        // CardView cardMason = findViewById(R.id.cardMason);
        // cardMason.setOnClickListener(v ->
        //     Toast.makeText(EmployerDashboardActivity.this, "Viewing 12 applicants for Mason Helper", Toast.LENGTH_SHORT).show()
        // );

        // CardView cardElectrician = findViewById(R.id.cardElectrician);
        // cardElectrician.setOnClickListener(v ->
        //     Toast.makeText(EmployerDashboardActivity.this, "Viewing 8 applicants for Electrician", Toast.LENGTH_SHORT).show()
        // );

        // CardView cardPainter = findViewById(R.id.cardPainter);
        // cardPainter.setOnClickListener(v ->
        //     Toast.makeText(EmployerDashboardActivity.this, "Viewing 5 applicants for Painter", Toast.LENGTH_SHORT).show()
        // );
    }
}