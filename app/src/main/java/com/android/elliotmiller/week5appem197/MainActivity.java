package com.android.elliotmiller.week5appem197;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.elliotmiller.week5appem197.fragments.Home;
import com.android.elliotmiller.week5appem197.fragments.Record;

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
    public void onStudentSelected(String id) {
        Log.d(TAG, "Student with id " + id + " selected.");
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
    public void onRecordAdded() {
        Log.d(TAG, "New Record Added");
    }
}
