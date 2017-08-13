package com.android.elliotmiller.week5appem197.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.elliotmiller.week5appem197.R;
import com.android.elliotmiller.week5appem197.model.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecordInterface} interface
 * to handle interaction events.
 * Use the {@link Record#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Record extends Fragment implements View.OnClickListener {

    private RecordInterface mListener;
    private EditText etId, etFirstName, etLastName, etClassId, etClassName, etClassScore;

    public Record() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Record.
     */
    public static Record newInstance() {
        Record fragment = new Record();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        etId = view.findViewById(R.id.et_id);
        etId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DBHelper db = new DBHelper(getContext());
                Cursor cursor = db.getStudentById(charSequence.toString());
                if (cursor.moveToNext()) {
                    etFirstName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_FIRST_NAME)));
                    etLastName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LAST_NAME)));
                    etFirstName.setEnabled(false);
                    etLastName.setEnabled(false);
                } else {
                    etFirstName.setText("");
                    etLastName.setText("");
                    etFirstName.setEnabled(true);
                    etLastName.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        etFirstName = view.findViewById(R.id.et_first_name);
        etLastName = view.findViewById(R.id.et_last_name);
        etClassId = view.findViewById(R.id.et_class_id);
        etClassId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DBHelper db = new DBHelper(getContext());
                Cursor cursor = db.getClassById(charSequence.toString());
                if (cursor.moveToNext()) {
                    etClassName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_NAME)));
                    etClassName.setEnabled(false);
                } else {
                    etClassName.setText("");
                    etClassName.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        etClassName = view.findViewById(R.id.et_class_name);
        etClassScore = view.findViewById(R.id.et_score);
        view.findViewById(R.id.btn_save).setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecordInterface) {
            mListener = (RecordInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RecordInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save: {
                // check that all data are available
                if (!this.isFormDataValid()) {
                    return;
                }
                ContentValues cv = new ContentValues();
                String classId = etClassId.getText().toString();
                String className = etClassName.getText().toString();
                cv.put(DBHelper.COLUMN_CLASS_ID, classId);
                cv.put(DBHelper.COLUMN_CLASS_NAME, className);
                cv.put(DBHelper.COLUMN_STUDENT_ID, etId.getText().toString());
                cv.put(DBHelper.COLUMN_FIRST_NAME, etFirstName.getText().toString());
                cv.put(DBHelper.COLUMN_LAST_NAME, etLastName.getText().toString());
                cv.put(DBHelper.COLUMN_CLASS_SCORE, etClassScore.getText().toString());
                cv.put(DBHelper.COLUMN_CLASS_NAME, etClassName.getText().toString());

                long result = new DBHelper(getContext()).addStudent(cv);
                //make the save
                if (result > 0) {
                    mListener.onRecordAdded(classId, className);
                }
                // if save is successful tell mListener else tell mListener
                break;
            }
            default: {
                // Do Nada :)
            }
        }
    }

    private boolean isFormDataValid() {
        boolean valid = true;
        if(TextUtils.isEmpty(etClassId.getText().toString())) {
            ((TextInputLayout)getView().getRootView().findViewById(R.id.ti_id)).setError(getString(R.string.required));
            valid = false;
        }
        if (TextUtils.isEmpty(etFirstName.getText().toString())) {
            ((TextInputLayout)getView().getRootView().findViewById(R.id.ti_fn)).setError(getString(R.string.required));
            valid = false;
        }
        if (TextUtils.isEmpty(etLastName.getText().toString())) {
            ((TextInputLayout)getView().getRootView().findViewById(R.id.ti_ln)).setError(getString(R.string.required));
            valid = false;
        }
        if (TextUtils.isEmpty(etClassId.getText().toString())) {
            ((TextInputLayout)getView().getRootView().findViewById(R.id.ti_c_id)).setError(getString(R.string.required));
            valid = false;
        }
        if (TextUtils.isEmpty(etClassName.getText().toString())) {
            ((TextInputLayout)getView().getRootView().findViewById(R.id.ti_c_n)).setError(getString(R.string.required));
            valid = false;
        }
        if (TextUtils.isEmpty(etClassScore.getText().toString())) {
            ((TextInputLayout)getView().getRootView().findViewById(R.id.ti_c_s)).setError(getString(R.string.required));
            valid = false;
        }
        return valid;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface RecordInterface {
        void onRecordAdded(String classId, String className);
    }
}

// TODO: Handle on enter press for each edittext since we want only single lines
