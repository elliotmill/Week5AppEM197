package com.android.elliotmiller.week5appem197;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.elliotmiller.week5appem197.fragments.Home;
import com.android.elliotmiller.week5appem197.fragments.Record;
import com.android.elliotmiller.week5appem197.fragments.StudentRecord;

public class MainActivity extends AppCompatActivity implements Home.HomeInterface, Record.RecordInterface {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_holder, new Home());
        ft.commit();
    }

    @Override
    public void onStudentSelected(String studentId, String fName, String lName) {
        Log.d(TAG, "Select student: " + studentId + " | " + fName + " | " + lName );
        StudentRecord sR = StudentRecord.newInstance(studentId, fName + " " + lName);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_holder, sR);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void addNewStudentRecord() {
        Log.d(TAG, "Add new student record");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_holder, Record.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onRecordAdded(String classId, String className) {
        Snackbar.make(findViewById(R.id.fragment_holder), classId + " (" + className + ") Saved", Snackbar.LENGTH_SHORT).show();
        getSupportFragmentManager().popBackStack();
        Log.d(TAG, "New Record Added: " + classId + " - " + className);
    }
}
