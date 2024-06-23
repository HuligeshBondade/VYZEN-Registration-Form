package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user_data.db";
    private static final int DATABASE_VERSION = 1;

    // Identification table
    private static final String TABLE_IDENTIFICATION = "identification";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";

    // Profession table
    private static final String TABLE_PROFESSION = "profession";
    private static final String COLUMN_WORK_LINK = "work_link";
    private static final String COLUMN_SOCIAL_MEDIA = "social_media_links";
    private static final String COLUMN_PORTFOLIO = "online_portfolio";
    private static final String COLUMN_SKILLS = "skills";
    private static final String COLUMN_LANGUAGES = "languages";
    private static final String COLUMN_PHOTO_URI = "photo_uri";
    private static final String COLUMN_RESUME_URI = "resume_uri";

    // Address table
    private static final String TABLE_ADDRESS = "address";
    private static final String COLUMN_CURRENT_ADDRESS = "current_address";
    private static final String COLUMN_PERMANENT_ADDRESS = "permanent_address";
    private static final String COLUMN_CURRENT_LOCATION = "current_location";
    private static final String COLUMN_PREFERRED_LOCATION = "preferred_location";

    // Education table
    private static final String TABLE_EDUCATION = "education";
    private static final String COLUMN_EDUCATION_COURSE = "education_course";
    private static final String COLUMN_EDUCATION_SPECIALIZATION = "education_specialization";
    private static final String COLUMN_EDUCATION_INSTITUTION = "education_institution";
    private static final String COLUMN_EDUCATION_YEAR_COMPLETION = "education_year_completion";
    private static final String COLUMN_EDUCATION_PASS_PERCENTAGE = "education_pass_percentage";
    private static final String COLUMN_CERTIFICATIONS = "certifications";
    private static final String COLUMN_CERTIFICATIONS_OBTAINED = "certifications_obtained";
    private static final String COLUMN_CERTIFICATION_ISSUING_AUTHORITY = "certification_issuing_authority";
    private static final String COLUMN_CERTIFICATION_COMPLETION_DATE = "certification_completion_date";
    private static final String COLUMN_TENTATIVE_JOINING_DATE = "tentative_joining_date";
    private static final String COLUMN_IS_FRESHER = "is_fresher";

    // Acknowledgment table
    private static final String TABLE_ACKNOWLEDGMENT = "acknowledgment";
    private static final String COLUMN_BACKGROUND_CHECK = "background_check";
    private static final String COLUMN_DRUG_TEST = "drug_test";
    private static final String COLUMN_CRIMINAL_DISCLOSURE = "criminal_disclosure";
    private static final String COLUMN_CRIMINAL_DETAILS = "criminal_details";
    private static final String COLUMN_ACKNOWLEDGE_TERMS = "acknowledge_terms";

    // Create table statements
    private static final String CREATE_TABLE_IDENTIFICATION = "CREATE TABLE " + TABLE_IDENTIFICATION +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_FIRST_NAME + " TEXT," +
            COLUMN_LAST_NAME + " TEXT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_PHONE + " TEXT," +
            COLUMN_DATE_OF_BIRTH + " TEXT)";

    private static final String CREATE_TABLE_PROFESSION = "CREATE TABLE " + TABLE_PROFESSION +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_WORK_LINK + " TEXT," +
            COLUMN_SOCIAL_MEDIA + " TEXT," +
            COLUMN_PORTFOLIO + " TEXT," +
            COLUMN_SKILLS + " TEXT," +
            COLUMN_LANGUAGES + " TEXT," +
            COLUMN_PHOTO_URI + " TEXT," +
            COLUMN_RESUME_URI + " TEXT)";

    private static final String CREATE_TABLE_ADDRESS = "CREATE TABLE " + TABLE_ADDRESS +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_CURRENT_ADDRESS + " TEXT," +
            COLUMN_PERMANENT_ADDRESS + " TEXT," +
            COLUMN_CURRENT_LOCATION + " TEXT," +
            COLUMN_PREFERRED_LOCATION + " TEXT)";

    private static final String CREATE_TABLE_EDUCATION = "CREATE TABLE " + TABLE_EDUCATION +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_EDUCATION_COURSE + " TEXT," +
            COLUMN_EDUCATION_SPECIALIZATION + " TEXT," +
            COLUMN_EDUCATION_INSTITUTION + " TEXT," +
            COLUMN_EDUCATION_YEAR_COMPLETION + " TEXT," +
            COLUMN_EDUCATION_PASS_PERCENTAGE + " TEXT," +
            COLUMN_CERTIFICATIONS + " TEXT," +
            COLUMN_CERTIFICATIONS_OBTAINED + " TEXT," +
            COLUMN_CERTIFICATION_ISSUING_AUTHORITY + " TEXT," +
            COLUMN_CERTIFICATION_COMPLETION_DATE + " TEXT," +
            COLUMN_TENTATIVE_JOINING_DATE + " TEXT," +
            COLUMN_IS_FRESHER + " INTEGER)";

    private static final String CREATE_TABLE_ACKNOWLEDGMENT = "CREATE TABLE " + TABLE_ACKNOWLEDGMENT +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_BACKGROUND_CHECK + " INTEGER," +
            COLUMN_DRUG_TEST + " INTEGER," +
            COLUMN_CRIMINAL_DISCLOSURE + " INTEGER," +
            COLUMN_CRIMINAL_DETAILS + " TEXT," +
            COLUMN_ACKNOWLEDGE_TERMS + " INTEGER)";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IDENTIFICATION);
        db.execSQL(CREATE_TABLE_PROFESSION);
        db.execSQL(CREATE_TABLE_ADDRESS);
        db.execSQL(CREATE_TABLE_EDUCATION);
        db.execSQL(CREATE_TABLE_ACKNOWLEDGMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IDENTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EDUCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACKNOWLEDGMENT);
        onCreate(db);
    }

    // Insert methods for each table
    public long insertIdentificationData(String firstName, String lastName, String email,
                                         String phone, String dateOfBirth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DATE_OF_BIRTH, dateOfBirth);
        return db.insert(TABLE_IDENTIFICATION, null, values);
    }

    public long insertProfessionData(String workLink, String socialMedia, String portfolio,
                                     String skills, String languages, String photoUri, String resumeUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORK_LINK, workLink);
        values.put(COLUMN_SOCIAL_MEDIA, socialMedia);
        values.put(COLUMN_PORTFOLIO, portfolio);
        values.put(COLUMN_SKILLS, skills);
        values.put(COLUMN_LANGUAGES, languages);
        values.put(COLUMN_PHOTO_URI, photoUri);
        values.put(COLUMN_RESUME_URI, resumeUri);
        return db.insert(TABLE_PROFESSION, null, values);
    }

    public long insertAddressData(String currentAddress, String permanentAddress,
                                  String currentLocation, String preferredLocation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CURRENT_ADDRESS, currentAddress);
        values.put(COLUMN_PERMANENT_ADDRESS, permanentAddress);
        values.put(COLUMN_CURRENT_LOCATION, currentLocation);
        values.put(COLUMN_PREFERRED_LOCATION, preferredLocation);
        return db.insert(TABLE_ADDRESS, null, values);
    }

    public long insertEducationData(String course, String specialization, String institution,
                                    String year, String percentage, String certs, String certsObtained,
                                    String certAuthority, String certDate, String joiningDate, boolean isFresher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EDUCATION_COURSE, course);
        values.put(COLUMN_EDUCATION_SPECIALIZATION, specialization);
        values.put(COLUMN_EDUCATION_INSTITUTION, institution);
        values.put(COLUMN_EDUCATION_YEAR_COMPLETION, year);
        values.put(COLUMN_EDUCATION_PASS_PERCENTAGE, percentage);
        values.put(COLUMN_CERTIFICATIONS, certs);
        values.put(COLUMN_CERTIFICATIONS_OBTAINED, certsObtained);
        values.put(COLUMN_CERTIFICATION_ISSUING_AUTHORITY, certAuthority);
        values.put(COLUMN_CERTIFICATION_COMPLETION_DATE, certDate);
        values.put(COLUMN_TENTATIVE_JOINING_DATE, joiningDate);
        values.put(COLUMN_IS_FRESHER, isFresher ? 1 : 0);
        return db.insert(TABLE_EDUCATION, null, values);
    }

    public long insertAcknowledgmentData(boolean backgroundCheck, boolean drugTest,
                                         boolean criminalDisclosure, String criminalDetails, boolean acknowledge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BACKGROUND_CHECK, backgroundCheck ? 1 : 0);
        values.put(COLUMN_DRUG_TEST, drugTest ? 1 : 0);
        values.put(COLUMN_CRIMINAL_DISCLOSURE, criminalDisclosure ? 1 : 0);
        values.put(COLUMN_CRIMINAL_DETAILS, criminalDetails);
        values.put(COLUMN_ACKNOWLEDGE_TERMS, acknowledge ? 1 : 0);
        return db.insert(TABLE_ACKNOWLEDGMENT, null, values);
    }
}
