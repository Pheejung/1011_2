package com.example.user.a1011_2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private Button bt2;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        bt2 = (Button) findViewById(R.id.bt2);
        context = this;

        bt2.setOnClickListener(aci);

        final SQLiteDatabase sqlitedb2 = openOrCreateDatabase("samples2.db", MODE_PRIVATE, null);
        sqlitedb2.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT, " +
                "email TEXT);");

        TextView c_name = (TextView)findViewById(R.id.tv2);
        TextView c_phone = (TextView)findViewById(R.id.tv3);
        TextView c_eamil = (TextView)findViewById(R.id.tv4);


        Intent fromMain = getIntent();
        String id = fromMain.getStringExtra("id");

        Cursor cursor = sqlitedb2.query("users", new String[] {"_id", "name", "phone","email"},
                "_id = " + id, null, null, null, null);

        cursor.moveToFirst();
        int _id = cursor.getInt(cursor.getColumnIndex("_id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String phone = cursor.getString(cursor.getColumnIndex("phone"));
        String email = cursor.getString(cursor.getColumnIndex("email"));

    c_name.setText(name);
        c_phone.setText(phone);
        c_eamil.setText(email);

        final Button button = (Button) findViewById(R.id.bt1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }


    View.OnClickListener aci = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, Main4Activity.class);
            startActivity(i);
            finish();
        }
    };
}
