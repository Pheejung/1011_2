package com.example.user.a1011_2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button bt1;
    private Context context;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.bt1);
        context = this;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        selectListDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectListDB();
    }

    protected void selectListDB() {

        final SQLiteDatabase sqlitedb2 = openOrCreateDatabase("samples2.db", MODE_PRIVATE, null);
        sqlitedb2.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone TEXT, " +
                "email TEXT);");

        final Cursor cursor = sqlitedb2.query("users", null, null, null, null, null, null, String.valueOf(5));

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.getCount() == 5) {
                    Toast.makeText(context, "더 이상 추가할 수 없습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(context, Main2Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        list = (ListView) findViewById(R.id.list);

        String[] data_columns = new String[]{"name", "phone", "email"};
        int[] widgets = new int[]{R.id.tv1, R.id.tv2, R.id.tv3};

        if (cursor != null) {
            startManagingCursor(cursor);

            ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.dbview, cursor, data_columns, widgets);

            list.setAdapter(adapter);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor item = (Cursor) list.getAdapter().getItem(position);
                int _id = item.getInt(item.getColumnIndex("_id"));
                String name = item.getString(item.getColumnIndex("name"));
                String phone = item.getString(item.getColumnIndex("phone"));
                String email = item.getString(item.getColumnIndex("email"));

                Intent toContents = new Intent(MainActivity.this, Main3Activity.class);
                toContents.putExtra("id", new Integer(_id).toString());
                startActivity(toContents);
                finish();

            }

        });

        sqlitedb2.close();

    }

}
