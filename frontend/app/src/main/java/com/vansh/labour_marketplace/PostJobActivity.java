package com.vansh.labour_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PostJobActivity extends AppCompatActivity {

    private String selectedSkill = "Masonry"; // Default selected skill
    private boolean isFoodProvided = false;
    private boolean isAccommodationProvided = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_posted); // Make sure this matches your XML filename

        // 1. Header Navigation Buttons
        FrameLayout backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish()); // Go back to the previous screen
        }

        FrameLayout previewButton = findViewById(R.id.previewButton);
        if (previewButton != null) {
            previewButton.setOnClickListener(v ->
                    Toast.makeText(PostJobActivity.this, "Previewing job layout...", Toast.LENGTH_SHORT).show()
            );
        }

        // 2. Bottom Action: Post Job Now Button (Found dynamically inside the layout container)
        View postButtonView = findViewById(R.id.bottomActionLayout);
        if (postButtonView instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) postButtonView;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                if (child instanceof Button) {
                    child.setOnClickListener(v -> handlePostJob());
                    break;
                }
            }
        }
    }

    private void handlePostJob() {
        Toast.makeText(this, "Job posted successfully with skill: " + selectedSkill, Toast.LENGTH_SHORT).show();

        // Navigate to Job Success Screen
        Intent intent = new Intent(PostJobActivity.this, JobPosted.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}