package com.example.user.a1011_2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private Button bt1;
    private Button bt2;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent myIntent = new Intent();
        sendBroadcast(myIntent);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        context = this;

        bt1.setOnClickListener(oc);


        final SQLiteDatabase sqlitedb2 = openOrCreateDatabase("samples2.db", MODE_APPEND, null);
        sqlitedb2.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT, " +
                "email TEXT);");

        final Button button = (Button) findViewById(R.id.bt2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText e_name = (EditText) findViewById(R.id.et1);
                EditText e_phone = (EditText) findViewById(R.id.et2);
                EditText e_email = (EditText) findViewById(R.id.et3);

                ContentValues values = new ContentValues();
                values.put("name", e_name.getText().toString());
                values.put("phone", e_phone.getText().toString());
                values.put("email", e_email.getText().toString());



                if (et1.getText().toString().equals("")) {
                    Toast.makeText(context, "이름을 입력하세요", Toast.LENGTH_LONG).show();
                }else if (et2.getText().toString().equals("")) {
                    Toast.makeText(context, "전화번호를 입력하세요", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(intent);
                    sqlitedb2.insert("users",null, values);
                    sqlitedb2.close();

                }

            }
        });
    }

    View.OnClickListener oc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, MainActivity.class);
            startActivity(i);
            finish();
        }
    };
        }
