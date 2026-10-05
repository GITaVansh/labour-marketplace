package com.vansh.labour_marketplace;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WorkerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worker_profile); // Match your XML filename

        // 1. Back Navigation Button
        TextView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // 2. Edit Profile Button
        TextView btnEdit = findViewById(R.id.btnEdit);
        if (btnEdit != null) {
            btnEdit.setOnClickListener(v ->
                    Toast.makeText(WorkerProfileActivity.this, "Opening edit profile screen...", Toast.LENGTH_SHORT).show()
            );
        }
    }
}