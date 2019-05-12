package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {
    private Context context;
    private Cursor cursor;

    private static final String TAG = "DatabaseAdapter";

    public DatabaseAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public class DatabaseViewHolder extends RecyclerView.ViewHolder{
        public TextView tvUsername, tvEmail;

        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout,viewGroup,false);

        return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder databaseViewHolder, int i) {
        if(!cursor.moveToPosition(i)){
            return;
        }
        Log.d(TAG, "onBindViewHolder: "+cursor.getColumnIndex(TableConstants.UserDetails.NAME_COLUMN)+
                ", "+cursor.getColumnIndex(TableConstants.UserDetails.EMAIL_COLUMN));


        String userName = cursor.getString(cursor.getColumnIndex(TableConstants.UserDetails.NAME_COLUMN));
        databaseViewHolder.tvUsername.setText(userName);


        String email = cursor.getString(cursor.getColumnIndex(TableConstants.UserDetails.EMAIL_COLUMN));
        databaseViewHolder.tvEmail.setText(email);


        Log.d(TAG, "onBindViewHolder: "+userName+", "+email);

    }



    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if(cursor != null){
            cursor.close();
        }
        cursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
