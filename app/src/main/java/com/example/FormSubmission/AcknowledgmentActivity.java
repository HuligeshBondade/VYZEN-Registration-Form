package com.example.FormSubmission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AcknowledgmentActivity extends AppCompatActivity {

    private RadioGroup radioGroupBackgroundCheckConsent;
    private RadioGroup radioGroupDrugTestConsent;
    private RadioGroup radioGroupCriminalDisclosure;
    private EditText editTextCriminalDetails;
    private RadioGroup radioGroupAcknowledge;

    private Button btnBack;
    private Button btnSubmit;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgement);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);

        radioGroupBackgroundCheckConsent = findViewById(R.id.radioGroupBackgroundCheckConsent);
        radioGroupDrugTestConsent = findViewById(R.id.radioGroupDrugTestConsent);
        radioGroupCriminalDisclosure = findViewById(R.id.radioGroupCriminalDisclosure);
        editTextCriminalDetails = findViewById(R.id.editTextCriminalDetails);
        radioGroupAcknowledge = findViewById(R.id.radioGroupAcknowledge);

        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);

        loadPreviousInputs();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInputs();
                Intent intent = new Intent(AcknowledgmentActivity.this, EducationActivity.class);
                startActivity(intent);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveInputs();
                    // Show a success message
                    Toast.makeText(AcknowledgmentActivity.this, "Submitted successfully", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadPreviousInputs() {
        String backgroundCheckConsent = sharedPreferences.getString("backgroundCheckConsent", "");
        if (backgroundCheckConsent.equals("Yes")) {
            radioGroupBackgroundCheckConsent.check(R.id.radioBackgroundCheckYes);
        } else if (backgroundCheckConsent.equals("No")) {
            radioGroupBackgroundCheckConsent.check(R.id.radioBackgroundCheckNo);
        } else if (backgroundCheckConsent.equals("Maybe")) {
            radioGroupBackgroundCheckConsent.check(R.id.radioBackgroundCheckMaybe);
        }

        String drugTestConsent = sharedPreferences.getString("drugTestConsent", "");
        if (drugTestConsent.equals("Yes")) {
            radioGroupDrugTestConsent.check(R.id.radioDrugTestYes);
        } else if (drugTestConsent.equals("No")) {
            radioGroupDrugTestConsent.check(R.id.radioDrugTestNo);
        } else if (drugTestConsent.equals("Maybe")) {
            radioGroupDrugTestConsent.check(R.id.radioDrugTestMaybe);
        }

        String criminalDisclosure = sharedPreferences.getString("criminalDisclosure", "");
        if (criminalDisclosure.equals("Yes")) {
            radioGroupCriminalDisclosure.check(R.id.radioCriminalYes);
            editTextCriminalDetails.setText(sharedPreferences.getString("criminalDetails", ""));
        } else if (criminalDisclosure.equals("No")) {
            radioGroupCriminalDisclosure.check(R.id.radioCriminalNo);
            editTextCriminalDetails.setText("");
        }

        String acknowledge = sharedPreferences.getString("acknowledge", "");
        if (acknowledge.equals("Yes")) {
            radioGroupAcknowledge.check(R.id.radioAcknowledgeYes);
        } else if (acknowledge.equals("No")) {
            radioGroupAcknowledge.check(R.id.radioAcknowledgeNo);
        }
    }

    private void saveInputs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int selectedBackgroundCheckConsentId = radioGroupBackgroundCheckConsent.getCheckedRadioButtonId();
        if (selectedBackgroundCheckConsentId != -1) {
            RadioButton selectedBackgroundCheckConsent = findViewById(selectedBackgroundCheckConsentId);
            editor.putString("backgroundCheckConsent", selectedBackgroundCheckConsent.getText().toString());
        }

        int selectedDrugTestConsentId = radioGroupDrugTestConsent.getCheckedRadioButtonId();
        if (selectedDrugTestConsentId != -1) {
            RadioButton selectedDrugTestConsent = findViewById(selectedDrugTestConsentId);
            editor.putString("drugTestConsent", selectedDrugTestConsent.getText().toString());
        }

        int selectedCriminalDisclosureId = radioGroupCriminalDisclosure.getCheckedRadioButtonId();
        if (selectedCriminalDisclosureId != -1) {
            RadioButton selectedCriminalDisclosure = findViewById(selectedCriminalDisclosureId);
            editor.putString("criminalDisclosure", selectedCriminalDisclosure.getText().toString());

            if (selectedCriminalDisclosure.getText().toString().equals("Yes")) {
                editor.putString("criminalDetails", editTextCriminalDetails.getText().toString());
            } else {
                editor.putString("criminalDetails", "");
            }
        }

        int selectedAcknowledgeId = radioGroupAcknowledge.getCheckedRadioButtonId();
        if (selectedAcknowledgeId != -1) {
            RadioButton selectedAcknowledge = findViewById(selectedAcknowledgeId);
            editor.putString("acknowledge", selectedAcknowledge.getText().toString());
        }

        editor.apply();
    }

    private boolean validateFields() {
        if (radioGroupBackgroundCheckConsent.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select an option for background check consent", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupDrugTestConsent.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select an option for drug test consent", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupCriminalDisclosure.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select an option for criminal disclosure", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupCriminalDisclosure.getCheckedRadioButtonId() == R.id.radioCriminalYes &&
                TextUtils.isEmpty(editTextCriminalDetails.getText().toString())) {
            editTextCriminalDetails.setError("Please provide details of your criminal record");
            return false;
        }

        if (radioGroupAcknowledge.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please acknowledge to proceed", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
