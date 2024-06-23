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

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    private EditText educationCourse, educationSpecialization, educationInstitution, educationYearOfCompletion, educationPassPercentage,
            certificationsObtained, certificationIssuingAuthority, certificationCompletionDate, tentativeJoiningDate;
    private RadioGroup certifications, fresher;
    private Button btnBack, btnNext, upload_edu_certificates, upload_certificates;
    private SharedPreferences sharedPreferences;
    private static final int PICK_CERTIFICATION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);

        educationCourse = findViewById(R.id.education_course);
        educationSpecialization = findViewById(R.id.education_specialization);
        educationInstitution = findViewById(R.id.education_institution);
        educationYearOfCompletion = findViewById(R.id.education_year_of_completion);
        educationPassPercentage = findViewById(R.id.education_pass_percentage);
        certifications = findViewById(R.id.certifications);
        certificationsObtained = findViewById(R.id.certifications_obtained);
        certificationIssuingAuthority = findViewById(R.id.certification_issuing_authority);
        certificationCompletionDate = findViewById(R.id.certification_completion_date);
        tentativeJoiningDate = findViewById(R.id.tentative_joining_date);
        fresher = findViewById(R.id.fresher);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        upload_edu_certificates = findViewById(R.id.upload_edu_certificates);
        upload_certificates = findViewById(R.id.upload_certificates);

        loadPreviousData();

        upload_certificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_CERTIFICATION_REQUEST);
            }
        });

        upload_edu_certificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_CERTIFICATION_REQUEST);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrentData();
                Intent intent = new Intent(EducationActivity.this, ProfessionActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    saveCurrentData();
                    Intent intent = new Intent(EducationActivity.this, AcknowledgmentActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadPreviousData() {
        educationCourse.setText(sharedPreferences.getString("educationCourse", ""));
        educationSpecialization.setText(sharedPreferences.getString("educationSpecialization", ""));
        educationInstitution.setText(sharedPreferences.getString("educationInstitution", ""));
        educationYearOfCompletion.setText(sharedPreferences.getString("educationYearOfCompletion", ""));
        educationPassPercentage.setText(sharedPreferences.getString("educationPassPercentage", ""));

        String certificationsValue = sharedPreferences.getString("certifications", "");
        if (certificationsValue.equals("Yes")) {
            certifications.check(R.id.certifications_yes);
        } else {
            certifications.check(R.id.certifications_no);
        }

        certificationsObtained.setText(sharedPreferences.getString("certificationsObtained", ""));
        certificationIssuingAuthority.setText(sharedPreferences.getString("certificationIssuingAuthority", ""));
        certificationCompletionDate.setText(sharedPreferences.getString("certificationCompletionDate", ""));
        tentativeJoiningDate.setText(sharedPreferences.getString("tentativeJoiningDate", ""));

        String fresherValue = sharedPreferences.getString("fresher", "");
        if (fresherValue.equals("Yes")) {
            fresher.check(R.id.fresher_yes);
        } else {
            fresher.check(R.id.fresher_no);
        }
    }

    private void saveCurrentData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("educationCourse", educationCourse.getText().toString());
        editor.putString("educationSpecialization", educationSpecialization.getText().toString());
        editor.putString("educationInstitution", educationInstitution.getText().toString());
        editor.putString("educationYearOfCompletion", educationYearOfCompletion.getText().toString());
        editor.putString("educationPassPercentage", educationPassPercentage.getText().toString());

        int selectedCertificationId = certifications.getCheckedRadioButtonId();
        RadioButton selectedCertification = findViewById(selectedCertificationId);
        editor.putString("certifications", selectedCertification.getText().toString());

        editor.putString("certificationsObtained", certificationsObtained.getText().toString());
        editor.putString("certificationIssuingAuthority", certificationIssuingAuthority.getText().toString());
        editor.putString("certificationCompletionDate", certificationCompletionDate.getText().toString());
        editor.putString("tentativeJoiningDate", tentativeJoiningDate.getText().toString());

        int selectedFresherId = fresher.getCheckedRadioButtonId();
        RadioButton selectedFresher = findViewById(selectedFresherId);
        editor.putString("fresher", selectedFresher.getText().toString());

        editor.apply();
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(educationCourse.getText().toString())) {
            educationCourse.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(educationSpecialization.getText().toString())) {
            educationSpecialization.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(educationInstitution.getText().toString())) {
            educationInstitution.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(educationYearOfCompletion.getText().toString())) {
            educationYearOfCompletion.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(educationPassPercentage.getText().toString())) {
            educationPassPercentage.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(certificationsObtained.getText().toString())) {
            certificationsObtained.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(certificationIssuingAuthority.getText().toString())) {
            certificationIssuingAuthority.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(certificationCompletionDate.getText().toString())) {
            certificationCompletionDate.setError("This field is required");
            return false;
        }
        if (TextUtils.isEmpty(tentativeJoiningDate.getText().toString())) {
            tentativeJoiningDate.setError("This field is required");
            return false;
        }
        return true;
    }
}
