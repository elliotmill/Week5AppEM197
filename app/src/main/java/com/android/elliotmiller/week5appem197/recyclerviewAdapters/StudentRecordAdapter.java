package com.android.elliotmiller.week5appem197.recyclerviewAdapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.elliotmiller.week5appem197.R;
import com.android.elliotmiller.week5appem197.fragments.Home;
import com.android.elliotmiller.week5appem197.model.DBHelper;

/**
 * Created by azeezolaniran on 13/08/2017.
 */

public class StudentRecordAdapter extends RecyclerView.Adapter<StudentRecordAdapter.HomeViewHolder> {

    private Cursor cursor;
    private Context context;

    public StudentRecordAdapter(Context ctx, Cursor cs) {
        this.context = ctx;
        this.cursor = cs;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.student_records_item, null);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.tvId.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_ID)));
        holder.tvClassName.setText(
                cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_NAME))
        );
        holder.tvClassScore.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_SCORE)));
        holder.tvClassGrade.setText(
                DBHelper.getGradeFromScore(
                        cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_SCORE))
                )
        );
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0: cursor.getCount();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvClassName, tvClassScore, tvClassGrade;

        public HomeViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvClassName = itemView.findViewById(R.id.tv_class_name);
            tvClassScore = itemView.findViewById(R.id.tv_score);
            tvClassGrade = itemView.findViewById(R.id.tv_grade);
        }
    }
}
