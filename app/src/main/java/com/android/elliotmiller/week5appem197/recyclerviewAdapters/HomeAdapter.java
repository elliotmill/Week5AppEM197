package com.android.elliotmiller.week5appem197.recyclerviewAdapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.elliotmiller.week5appem197.R;
import com.android.elliotmiller.week5appem197.fragments.Home;
import com.android.elliotmiller.week5appem197.model.DBHelper;

/**
 * Created by azeezolaniran on 13/08/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private final Home.HomeInterface mListener;
    private Cursor cursor;
    private Context context;

    public HomeAdapter(Context ctx, Cursor cs, Home.HomeInterface mLstnr) {
        this.context = ctx;
        this.cursor = cs;
        this.mListener = mLstnr;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.records_item, null);
        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.tvId.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ID)));
        holder.tvName.setText(
                cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_FIRST_NAME))
                + " "
                + cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LAST_NAME))
        );
        holder.tvClassId.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_ID)));
        holder.tvClassName.setText(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_CLASS_NAME)));
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

        TextView tvId, tvName, tvClassId, tvClassName, tvClassScore, tvClassGrade;

        public HomeViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvClassId = itemView.findViewById(R.id.tv_class_id);
            tvClassName = itemView.findViewById(R.id.tv_class_name);
            tvClassScore = itemView.findViewById(R.id.tv_score);
            tvClassGrade = itemView.findViewById(R.id.tv_grade);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cursor.moveToPosition(getAdapterPosition());
                    mListener.onStudentSelected(
                            cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_STUDENT_ID)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_FIRST_NAME)),
                            cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_LAST_NAME))
                    );
                }
            });
        }
    }
}
