package com.android.elliotmiller.week5appem197.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by azeezolaniran on 13/08/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "week5appem197db";
    private static final String TABLE_NAME = "students_records";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_CLASS_ID = "class_id";
    public static final String COLUMN_CLASS_NAME = "class_name";
    public static final String COLUMN_CLASS_SCORE = "class_score";
    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_STUDENT_ID,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME,
            COLUMN_CLASS_ID,
            COLUMN_CLASS_NAME,
            COLUMN_CLASS_SCORE
    };

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_STUDENT_ID + " TEXT, "
                + COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, "
                + COLUMN_CLASS_ID + " TEXT, "
                + COLUMN_CLASS_NAME + " TEXT,"
                + COLUMN_CLASS_SCORE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
        insertDefaultUsers(sqLiteDatabase);
    }

    private void insertDefaultUsers(SQLiteDatabase db) {
        ContentValues cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "100");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "Biology");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "1");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Sally");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Smith");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "70");
        db.insert(TABLE_NAME, null, cv1);

        ContentValues cv2 = new ContentValues();
        cv2.put(DBHelper.COLUMN_CLASS_ID, "110");
        cv2.put(DBHelper.COLUMN_CLASS_NAME, "Gym");
        cv2.put(DBHelper.COLUMN_STUDENT_ID, "1");
        cv2.put(DBHelper.COLUMN_FIRST_NAME, "Sally");
        cv2.put(DBHelper.COLUMN_LAST_NAME, "Smith");
        cv2.put(DBHelper.COLUMN_CLASS_SCORE, "90");
        db.insert(TABLE_NAME, null, cv2);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "120");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "English");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "2");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Mark");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Front");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "40");
        db.insert(TABLE_NAME, null, cv1);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "110");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "Gym");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "2");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Mark");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Front");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "10");
        db.insert(TABLE_NAME, null, cv1);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "130");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "Science");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "3");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Rick");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Hart");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "85");
        db.insert(TABLE_NAME, null, cv1);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "100");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "Biology");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "3");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Rick");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Hart");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "90");
        db.insert(TABLE_NAME, null, cv1);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "110");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "Gym");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "3");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Rick");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Hart");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "45");
        db.insert(TABLE_NAME, null, cv1);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "140");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "Computers");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "4");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Justin");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Jones");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "45");
        db.insert(TABLE_NAME, null, cv1);

        cv1 = new ContentValues();
        cv1.put(DBHelper.COLUMN_CLASS_ID, "120");
        cv1.put(DBHelper.COLUMN_CLASS_NAME, "English");
        cv1.put(DBHelper.COLUMN_STUDENT_ID, "4");
        cv1.put(DBHelper.COLUMN_FIRST_NAME, "Justin");
        cv1.put(DBHelper.COLUMN_LAST_NAME, "Jones");
        cv1.put(DBHelper.COLUMN_CLASS_SCORE, "70");
        db.insert(TABLE_NAME, null, cv1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }

    public synchronized long addStudent(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, cv);
    }

    public synchronized Cursor getStudentById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, ALL_COLUMNS, COLUMN_STUDENT_ID + " = ?", new String[]{id}, null, null, null);
    }
    public synchronized Cursor getClassById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, ALL_COLUMNS, COLUMN_CLASS_ID + " = ?", new String[]{id}, null, null, null);
    }

    public synchronized Cursor getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public static String getGradeFromScore(String scr) {
        String grade;
        Double score = Double.parseDouble(scr);
        if (score < 60.0) {
            grade = "F";
        } else if (score < 70.0 && score >= 60.0) {
            grade = "D";
        } else if (score < 80.0 && score >= 70.0) {
            grade = "C";
        } else if (score < 90.0 && score >= 80.0) {
            grade = "B";
        } else if (score <= 100.0 && score >= 90.0) {
            grade = "A";
        } else {
            grade = "N/A";
        }
        return grade;
    }
}
