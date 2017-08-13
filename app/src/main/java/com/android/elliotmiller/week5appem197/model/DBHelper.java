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
