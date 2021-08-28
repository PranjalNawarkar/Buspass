package com.example.prady.BusPass;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity{


    DatabaseHelper db;
   List<User> userlist;
   ListView listView;

    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db=new DatabaseHelper(this);
        userlist=new ArrayList<>();
        listView=findViewById(R.id.listUser);

        getAllEntries();
    }

    private void getAllEntries() {
        Cursor cursor=db.getAllEntries();
        if(cursor.moveToFirst())
        {
            do{
                userlist.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)

                        ));

            }while(cursor.moveToNext());

            UserAdapter adapter= new UserAdapter(this, R.layout.itemlayout, userlist, db);
            listView.setAdapter(adapter);
        }

    }

}
