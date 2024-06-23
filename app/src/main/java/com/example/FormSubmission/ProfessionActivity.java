package com.example.FormSubmission;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfessionActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_REQUEST = 1;
    private static final int PICK_RESUME_REQUEST = 2;

    private EditText workLink, socialMediaLinks, onlinePortfolio, skills, languages;
    private Uri photoUri, resumeUri;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);

        workLink = findViewById(R.id.work_link);
        socialMediaLinks = findViewById(R.id.social_media_links);
        onlinePortfolio = findViewById(R.id.online_portfolio);
        skills = findViewById(R.id.skills);
        languages = findViewById(R.id.languages);

        Button btnNext = findViewById(R.id.btnNext);
        Button btnBack = findViewById(R.id.btnBack);
        Button uploadPhoto = findViewById(R.id.upload_photo);
        Button uploadResume = findViewById(R.id.upload_resume);

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_PHOTO_REQUEST);
            }
        });

        uploadResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_RESUME_REQUEST);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    saveInputs();
                    Intent intent = new Intent(ProfessionActivity.this, EducationActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInputs();
                Intent intent = new Intent(ProfessionActivity.this, IdentificationActivity.class);
                startActivity(intent);
            }
        });

        loadPreviousInputs();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_PHOTO_REQUEST) {
                photoUri = data.getData();
                Toast.makeText(this, "Photo selected", Toast.LENGTH_SHORT).show();
            } else if (requestCode == PICK_RESUME_REQUEST) {
                resumeUri = data.getData();
                Toast.makeText(this, "Resume selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateFields() {
        if (photoUri == null) {
            Toast.makeText(this, "Please upload your photo", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(workLink.getText())) {
            workLink.setError("Required");
            return false;
        }
        if (resumeUri == null) {
            Toast.makeText(this, "Please upload your resume", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(socialMediaLinks.getText())) {
            socialMediaLinks.setError("Required");
            return false;
        }
        if (TextUtils.isEmpty(onlinePortfolio.getText())) {
            onlinePortfolio.setError("Required");
            return false;
        }
        if (TextUtils.isEmpty(skills.getText())) {
            skills.setError("Required");
            return false;
        }
        if (TextUtils.isEmpty(languages.getText())) {
            languages.setError("Required");
            return false;
        }
        return true;
    }

    private void saveInputs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("workLink", workLink.getText().toString());
        editor.putString("socialMediaLinks", socialMediaLinks.getText().toString());
        editor.putString("onlinePortfolio", onlinePortfolio.getText().toString());
        editor.putString("skills", skills.getText().toString());
        editor.putString("languages", languages.getText().toString());
        if (photoUri != null) {
            editor.putString("photoUri", photoUri.toString());
        }
        if (resumeUri != null) {
            editor.putString("resumeUri", resumeUri.toString());
        }
        editor.apply();
    }

    private void loadPreviousInputs() {
        workLink.setText(sharedPreferences.getString("workLink", ""));
        socialMediaLinks.setText(sharedPreferences.getString("socialMediaLinks", ""));
        onlinePortfolio.setText(sharedPreferences.getString("onlinePortfolio", ""));
        skills.setText(sharedPreferences.getString("skills", ""));
        languages.setText(sharedPreferences.getString("languages", ""));
        String photoUriString = sharedPreferences.getString("photoUri", null);
        if (photoUriString != null) {
            photoUri = Uri.parse(photoUriString);
        }
        String resumeUriString = sharedPreferences.getString("resumeUri", null);
        if (resumeUriString != null) {
            resumeUri = Uri.parse(resumeUriString);
        }
    }
}
