package com.example.myapplication;

import android.app.SharedElementCallback;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etEmail;
    Button btnSubmit;
    //Getting the database

    private DatabaseAdapter adapter;
    private SQLiteDatabase writeDB;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relocated Database instance
        CustomDB customDB = new CustomDB(this);
        writeDB = customDB.getWritableDatabase();

        //instantiate the recycler
        RecyclerView recyclerView = findViewById(R.id.recycler_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DatabaseAdapter(this,getAllUsers());
        recyclerView.setAdapter(adapter);

        //binding
        etUsername = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveValuesDB();
            }
        });
    }

    public void saveValuesDB(){
        if(etUsername.getText().toString().trim().isEmpty()||
        etEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"You must fill all inputs",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String name = etUsername.getText().toString();
        String email = etEmail.getText().toString();

        Log.d(TAG, "saveValuesDB: "+name+", "+email);

        //Adding data to the corresponding place
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableConstants.UserDetails.NAME_COLUMN,
                name);
        contentValues.put(TableConstants.UserDetails.EMAIL_COLUMN,
                email);
        contentValues.put(TableConstants.UserDetails.ADDRESS_COLUMN,
                "452 street");
        contentValues.put(TableConstants.UserDetails.BIRTHDAY_COLUMN,
                "12/12/2014");
        contentValues.put(TableConstants.UserDetails.COUNTRY_COLUMN,
                "USA");
        contentValues.put(TableConstants.UserDetails.GENDER_COLUMN,
                "male");
        contentValues.put(TableConstants.UserDetails.PASS_COLUMN,
                "4324davdw");

        writeDB.insert(TableConstants.UserDetails.TABLE_NAME,null,contentValues);

        //cursor swap for each new item
        adapter.swapCursor(getAllUsers());

        etUsername.getText().clear();
        etEmail.getText().clear();
    }

    @Override
    public void setEnterSharedElementCallback(SharedElementCallback callback) {
        super.setEnterSharedElementCallback(callback);
    }



//    public void readFromDB(){
//        SQLiteDatabase readDB = customDB.getReadableDatabase();
//
//        String[] colums = {TableConstants.UserDetails.NAME_COLUMN,
//        TableConstants.UserDetails.ADDRESS_COLUMN
//        };
//
//        String selection = "WHERE "+
//                TableConstants.UserDetails.COUNTRY_COLUMN+
//                " LIKE ";
//
//        String[] selectionArgs = {"MEXICO", "US","UK","CHINA","KOREA", "PUERTO RICO"};
//
//        String groupBy = TableConstants.UserDetails.GENDER_COLUMN;
//
//        String orderBy = TableConstants.UserDetails.BIRTHDAY_COLUMN;
//
//        readDB.query(
//                TableConstants.UserDetails.TABLE_NAME,
//                colums,
//                selection,
//                selectionArgs,
//                groupBy,
//                null,
//                orderBy
//        );
//        Cursor cursor;
//        cursor = readDB.query(TableConstants.UserDetails.TABLE_NAME,
//                colums,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        while (cursor.moveToNext()){
//            try {
//                String name = cursor
//                        .getString(
//                                cursor.getColumnIndexOrThrow(
//                                        TableConstants.
//                                                UserDetails.
//                                                NAME_COLUMN));
//            }catch (Exception err){
//                err.printStackTrace();
//            }
//        }
//    }

    private Cursor getAllUsers(){
        String[] colums = {TableConstants.UserDetails.NAME_COLUMN,
                TableConstants.UserDetails.ADDRESS_COLUMN
        };

        return writeDB.query(
                TableConstants.UserDetails.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                TableConstants.UserDetails.NAME_COLUMN
        );
    }
}
/*
* name
* birday
* email
* pswrd
* country
* address
* gender*/
