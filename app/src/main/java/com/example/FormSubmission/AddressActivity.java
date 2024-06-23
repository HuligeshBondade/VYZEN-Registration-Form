package com.example.FormSubmission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddressActivity extends AppCompatActivity {

    private EditText currentAddress, permanentAddress, currentLocation, preferredLocation;
    private Button btnBack, btnNext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);

        currentAddress = findViewById(R.id.current_address);
        permanentAddress = findViewById(R.id.permanent_address);
        currentLocation = findViewById(R.id.current_location);
        preferredLocation = findViewById(R.id.preferred_location);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        loadPreviousInputs();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInputs();
                Intent intent = new Intent(AddressActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInputs();
                Intent intent = new Intent(AddressActivity.this, IdentificationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveInputs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("currentAddress", currentAddress.getText().toString());
        editor.putString("permanentAddress", permanentAddress.getText().toString());
        editor.putString("currentLocation", currentLocation.getText().toString());
        editor.putString("preferredLocation", preferredLocation.getText().toString());
        editor.apply();
    }

    private void loadPreviousInputs() {
        currentAddress.setText(sharedPreferences.getString("currentAddress", ""));
        permanentAddress.setText(sharedPreferences.getString("permanentAddress", ""));
        currentLocation.setText(sharedPreferences.getString("currentLocation", ""));
        preferredLocation.setText(sharedPreferences.getString("preferredLocation", ""));
    }
}
