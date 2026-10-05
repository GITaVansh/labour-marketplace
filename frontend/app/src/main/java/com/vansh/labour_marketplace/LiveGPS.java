package com.vansh.labour_marketplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LiveGPS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_gps_tracking); // Match your XML layout filename

        // 1. Back Navigation Button
        TextView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // 2. SOS Emergency Button Logic
        TextView btnSos = findViewById(R.id.btnSos);
        if (btnSos != null) {
            btnSos.setOnClickListener(v -> showSosConfirmationDialog());
        }

        // 3. Map Container Click
        ConstraintLayout mapContainer = findViewById(R.id.mapContainer);
        if (mapContainer != null) {
            mapContainer.setOnClickListener(v ->
                    Toast.makeText(LiveGPS.this, "Centering map to worker's live location...", Toast.LENGTH_SHORT).show()
            );
        }

        // 4. End Job & Complete Button
        Button btnEndJob = findViewById(R.id.btnEndJob);
        if (btnEndJob != null) {
            btnEndJob.setOnClickListener(v -> {
                Toast.makeText(LiveGPS.this, "Job ended successfully!", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }

    private void showSosConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Emergency SOS")
                .setMessage("Are you sure you want to trigger an emergency alert?")
                .setPositiveButton("Yes, Send SOS", (dialog, which) ->
                        Toast.makeText(LiveGPS.this, "SOS alert sent to support team!", Toast.LENGTH_LONG).show()
                )
                .setNegativeButton("Cancel", null)
                .show();
    }
}