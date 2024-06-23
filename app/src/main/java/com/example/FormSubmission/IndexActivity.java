package com.example.FormSubmission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IndexActivity extends AppCompatActivity {

    private static final String TAG = "IndexActivity";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);

        EditText firstName = findViewById(R.id.first_name);
        EditText lastName = findViewById(R.id.last_name);
        EditText contactNumber = findViewById(R.id.contact_number);
        EditText alternateContactNumber = findViewById(R.id.alternate_contact_number);
        EditText emailId = findViewById(R.id.email_id);
        EditText dateOfBirth = findViewById(R.id.date_of_birth);
        EditText age = findViewById(R.id.age);
        EditText nationality = findViewById(R.id.nationality);
        EditText bloodGroup = findViewById(R.id.blood_group);
        RadioGroup gender = findViewById(R.id.gender);
        RadioGroup maritalStatus = findViewById(R.id.marital_status);
        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            Log.d(TAG, "Next button clicked");

            // Validate user inputs
            if (validateInputs(firstName, lastName, contactNumber, alternateContactNumber, emailId, dateOfBirth, age, nationality, bloodGroup, gender, maritalStatus)) {
                Log.d(TAG, "Inputs validated successfully");

                // Save inputs to SharedPreferences
                saveInputs(firstName, lastName, contactNumber, alternateContactNumber, emailId, dateOfBirth, age, nationality, bloodGroup, gender, maritalStatus);

                // Start AddressActivity
                Intent intent = new Intent(IndexActivity.this, AddressActivity.class);
                startActivity(intent);
                Log.d(TAG, "AddressActivity started");
            } else {
                Log.d(TAG, "Input validation failed");
            }
        });
    }

    private boolean validateInputs(EditText firstName, EditText lastName, EditText contactNumber, EditText alternateContactNumber, EditText emailId, EditText dateOfBirth, EditText age, EditText nationality, EditText bloodGroup, RadioGroup gender, RadioGroup maritalStatus) {
        if (TextUtils.isEmpty(firstName.getText().toString().trim())) {
            firstName.setError("First Name is required");
            Log.d(TAG, "First Name is empty");
            return false;
        }
        if (TextUtils.isEmpty(lastName.getText().toString().trim())) {
            lastName.setError("Last Name is required");
            Log.d(TAG, "Last Name is empty");
            return false;
        }
        if (TextUtils.isEmpty(contactNumber.getText().toString().trim())) {
            contactNumber.setError("Contact Number is required");
            Log.d(TAG, "Contact Number is empty");
            return false;
        }
        if (TextUtils.isEmpty(alternateContactNumber.getText().toString().trim())) {
            alternateContactNumber.setError("Alternate Contact Number is required");
            Log.d(TAG, "Alternate Contact Number is empty");
            return false;
        }
        if (TextUtils.isEmpty(emailId.getText().toString().trim())) {
            emailId.setError("Email ID is required");
            Log.d(TAG, "Email ID is empty");
            return false;
        }
        if (TextUtils.isEmpty(dateOfBirth.getText().toString().trim())) {
            dateOfBirth.setError("Date of Birth is required");
            Log.d(TAG, "Date of Birth is empty");
            return false;
        }
        if (TextUtils.isEmpty(age.getText().toString().trim())) {
            age.setError("Age is required");
            Log.d(TAG, "Age is empty");
            return false;
        }
        if (gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Gender is required", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Gender not selected");
            return false;
        }
        if (maritalStatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Marital Status is required", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Marital Status not selected");
            return false;
        }
        if (TextUtils.isEmpty(nationality.getText().toString().trim())) {
            nationality.setError("Nationality is required");
            Log.d(TAG, "Nationality is empty");
            return false;
        }
        if (TextUtils.isEmpty(bloodGroup.getText().toString().trim())) {
            bloodGroup.setError("Blood Group is required");
            Log.d(TAG, "Blood Group is empty");
            return false;
        }
        return true;
    }

    private void saveInputs(EditText firstName, EditText lastName, EditText contactNumber, EditText alternateContactNumber, EditText emailId, EditText dateOfBirth, EditText age, EditText nationality, EditText bloodGroup, RadioGroup gender, RadioGroup maritalStatus) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName", firstName.getText().toString());
        editor.putString("lastName", lastName.getText().toString());
        editor.putString("contactNumber", contactNumber.getText().toString());
        editor.putString("alternateContactNumber", alternateContactNumber.getText().toString());
        editor.putString("emailId", emailId.getText().toString());
        editor.putString("dateOfBirth", dateOfBirth.getText().toString());
        editor.putString("age", age.getText().toString());
        editor.putString("nationality", nationality.getText().toString());
        editor.putString("bloodGroup", bloodGroup.getText().toString());

        int selectedGenderId = gender.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        editor.putString("gender", selectedGender.getText().toString());

        int selectedMaritalStatusId = maritalStatus.getCheckedRadioButtonId();
        RadioButton selectedMaritalStatus = findViewById(selectedMaritalStatusId);
        editor.putString("maritalStatus", selectedMaritalStatus.getText().toString());

        editor.apply();
    }
}
