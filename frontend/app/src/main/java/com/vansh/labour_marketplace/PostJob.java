package com.vansh.labour_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PostJob extends AppCompatActivity {

    private EditText etJobTitle, etDescription, etLocation, etDuration, etWorkersNeeded, etDailyWage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_a_job); // Ensure filename matches your XML layout file

        // 1. Initialize Form Inputs (EditTexts)
        etJobTitle = findViewById(R.id.etJobTitle);
        etDescription = findViewById(R.id.etDescription);
        etLocation = findViewById(R.id.etLocation);
        etDuration = findViewById(R.id.etDuration);
        etWorkersNeeded = findViewById(R.id.etWorkersNeeded);
        etDailyWage = findViewById(R.id.etDailyWage);

        // 2. Header Back Button
        FrameLayout backButton = findViewById(R.id.backButton);
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // 3. Header Preview Button
        FrameLayout previewButton = findViewById(R.id.previewButton);
        if (previewButton != null) {
            previewButton.setOnClickListener(v ->
                    Toast.makeText(PostJob.this, "Previewing job layout...", Toast.LENGTH_SHORT).show()
            );
        }

        // 4. Post Job Now Button
        Button btnPostJobNow = findViewById(R.id.btnPostJobNow);
        if (btnPostJobNow != null) {
            btnPostJobNow.setOnClickListener(v -> validateAndPostJob());
        }
    }

    private void validateAndPostJob() {
        String title = etJobTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String duration = etDuration.getText().toString().trim();
        String workers = etWorkersNeeded.getText().toString().trim();
        String wage = etDailyWage.getText().toString().trim();

        // Basic form validation check
        if (title.isEmpty() || location.isEmpty() || wage.isEmpty()) {
            Toast.makeText(this, "Please fill in at least Title, Location, and Wage.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Success Feedback
        Toast.makeText(this, "Job posted successfully!", Toast.LENGTH_SHORT).show();

        // Navigate to Job Success Screen
        Intent intent = new Intent(PostJob.this, JobPosted.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}