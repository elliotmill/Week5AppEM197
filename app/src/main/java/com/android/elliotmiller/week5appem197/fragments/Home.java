package com.android.elliotmiller.week5appem197.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.elliotmiller.week5appem197.R;
import com.android.elliotmiller.week5appem197.model.DBHelper;
import com.android.elliotmiller.week5appem197.recyclerviewAdapters.HomeAdapter;
import com.android.elliotmiller.week5appem197.recyclerviewAdapters.StudentRecordAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeInterface} interface
 * to handle interaction events.
 */
public class Home extends Fragment {

    private HomeInterface mListener;
    private RecyclerView recyclerView;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home, container, false);
        view.findViewById(R.id.btn_add_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addNewStudentRecord();
            }
        });
        recyclerView = view.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        Cursor cursor = new DBHelper(getContext()).getAllRecords();
        if( cursor.getCount() > 0) {
            recyclerView.setAdapter(new HomeAdapter(getContext(), cursor, mListener));
            getView().getRootView().findViewById(R.id.tv_no_records).setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeInterface) {
            mListener = (HomeInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement HomeInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface HomeInterface {
        void onStudentSelected(String studentId, String fName, String lName);
        void addNewStudentRecord();
    }
}
