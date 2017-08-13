package com.android.elliotmiller.week5appem197.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.elliotmiller.week5appem197.R;
import com.android.elliotmiller.week5appem197.model.DBHelper;
import com.android.elliotmiller.week5appem197.recyclerviewAdapters.StudentRecordAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentRecord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentRecord extends Fragment {
    // the fragment initialization parameters
    private static final String STUDENT_ID = "param1";
    private static final String STUDENT_NAME = "param2";

    private String studentId;
    private String studentName;
    private RecyclerView recyclerView;

    public StudentRecord() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sId Parameter 1.
     * @param sName Parameter 2.
     * @return A new instance of fragment StudentRecord.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentRecord newInstance(String sId, String sName) {
        StudentRecord fragment = new StudentRecord();
        Bundle args = new Bundle();
        args.putString(STUDENT_ID, sId);
        args.putString(STUDENT_NAME, sName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentId = getArguments().getString(STUDENT_ID);
            studentName = getArguments().getString(STUDENT_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_record, container, false);
        ((TextView)view.findViewById(R.id.tv_student_details)).setText(this.studentName);
        recyclerView = view.findViewById(R.id.rv_student_details);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor cursor = new DBHelper(getContext()).getStudentById(this.studentId);
        recyclerView.setAdapter(new StudentRecordAdapter(getContext(), cursor));
    }
}
