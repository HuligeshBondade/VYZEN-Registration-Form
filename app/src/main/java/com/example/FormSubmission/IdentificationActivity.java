package com.example.FormSubmission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IdentificationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FILE = 1;

    private RadioGroup passportRadioGroup;
    private EditText passportNumber;
    private EditText aadhaarNumber;
    private EditText panNumber;
    private EditText drivingLicense;
    private EditText voterId;
    private Uri passportUri;
    private Uri aadhaarUri;
    private Uri panUri;
    private Uri licenseUri;
    private Uri voterIdUri;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        passportRadioGroup = findViewById(R.id.passport_radio_group);
        passportNumber = findViewById(R.id.passport_number);
        aadhaarNumber = findViewById(R.id.aadhaar_number);
        panNumber = findViewById(R.id.pan_number);
        drivingLicense = findViewById(R.id.driving_license);
        voterId = findViewById(R.id.voter_id);

        loadPreviousInputs();

        Button uploadPassportButton = findViewById(R.id.upload_passport_copy);
        Button uploadAadhaarButton = findViewById(R.id.upload_aadhaar_copy);
        Button uploadPanButton = findViewById(R.id.upload_pan_copy);
        Button uploadLicenseButton = findViewById(R.id.upload_license_copy);
        Button uploadVoterIdButton = findViewById(R.id.upload_voter_id_copy);

        uploadPassportButton.setOnClickListener(v -> openFileChooser(REQUEST_CODE_FILE, "passport"));
        uploadAadhaarButton.setOnClickListener(v -> openFileChooser(REQUEST_CODE_FILE, "aadhaar"));
        uploadPanButton.setOnClickListener(v -> openFileChooser(REQUEST_CODE_FILE, "pan"));
        uploadLicenseButton.setOnClickListener(v -> openFileChooser(REQUEST_CODE_FILE, "license"));
        uploadVoterIdButton.setOnClickListener(v -> openFileChooser(REQUEST_CODE_FILE, "voterId"));

        Button btnSave = findViewById(R.id.btnNext);
        btnSave.setOnClickListener(v -> {
            saveInputs();
            Intent intent = new Intent(IdentificationActivity.this, ProfessionActivity.class);
            startActivity(intent);
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    private void openFileChooser(int requestCode, String type) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select a file"), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            switch (requestCode) {
                case REQUEST_CODE_FILE:
                    switch (requestCode) {
                        case 1:
                            passportUri = uri;
                            break;
                        case 2:
                            aadhaarUri = uri;
                            break;
                        case 3:
                            panUri = uri;
                            break;
                        case 4:
                            licenseUri = uri;
                            break;
                        case 5:
                            voterIdUri = uri;
                            break;
                    }
                    break;
            }
        }
    }

    private void saveInputs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("passport", passportRadioGroup.getCheckedRadioButtonId() == R.id.passport_yes ? "YES" : "NO");
        editor.putString("passportNumber", passportNumber.getText().toString());
        editor.putString("aadhaarNumber", aadhaarNumber.getText().toString());
        editor.putString("panNumber", panNumber.getText().toString());
        editor.putString("drivingLicense", drivingLicense.getText().toString());
        editor.putString("voterId", voterId.getText().toString());

        if (passportUri != null) {
            editor.putString("passportUri", passportUri.toString());
        }
        if (aadhaarUri != null) {
            editor.putString("aadhaarUri", aadhaarUri.toString());
        }
        if (panUri != null) {
            editor.putString("panUri", panUri.toString());
        }
        if (licenseUri != null) {
            editor.putString("licenseUri", licenseUri.toString());
        }
        if (voterIdUri != null) {
            editor.putString("voterIdUri", voterIdUri.toString());
        }
        editor.apply();
    }

    private void loadPreviousInputs() {
        String passport = sharedPreferences.getString("passport", "NO");
        if ("YES".equals(passport)) {
            passportRadioGroup.check(R.id.passport_yes);
        } else {
            passportRadioGroup.check(R.id.passport_no);
        }
        passportNumber.setText(sharedPreferences.getString("passportNumber", ""));
        aadhaarNumber.setText(sharedPreferences.getString("aadhaarNumber", ""));
        panNumber.setText(sharedPreferences.getString("panNumber", ""));
        drivingLicense.setText(sharedPreferences.getString("drivingLicense", ""));
        voterId.setText(sharedPreferences.getString("voterId", ""));

        passportUri = Uri.parse(sharedPreferences.getString("passportUri", ""));
        aadhaarUri = Uri.parse(sharedPreferences.getString("aadhaarUri", ""));
        panUri = Uri.parse(sharedPreferences.getString("panUri", ""));
        licenseUri = Uri.parse(sharedPreferences.getString("licenseUri", ""));
        voterIdUri = Uri.parse(sharedPreferences.getString("voterIdUri", ""));
    }
}
