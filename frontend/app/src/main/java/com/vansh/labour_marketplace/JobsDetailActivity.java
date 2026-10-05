package com.vansh.labour_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JobsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobs_detail); // Replace with your exact XML layout filename if different

        // 1. Header Back Button
        FrameLayout backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // 2. Header Preview Button
        FrameLayout previewButton = findViewById(R.id.previewButton);
        if (previewButton != null) {
            previewButton.setOnClickListener(v ->
                    Toast.makeText(this, "Previewing job layout...", Toast.LENGTH_SHORT).show()
            );
        }

        // 3. Bottom Action Button ("Post Job Now") Lookup & Click Handler
        View bottomActionLayout = findViewById(R.id.bottomActionLayout);
        if (bottomActionLayout instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) bottomActionLayout;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                if (child instanceof Button) {
                    child.setOnClickListener(v -> {
                        Toast.makeText(this, "Job posted successfully!", Toast.LENGTH_SHORT).show();

                        // Navigate to Success Screen
                        Intent intent = new Intent(JobsDetailActivity.this, JobPosted.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    });
                    break;
                }
            }
        }
    }
}