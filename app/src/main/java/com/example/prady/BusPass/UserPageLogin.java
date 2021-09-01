package com.example.prady.BusPass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.util.Date;
import java.util.jar.Attributes;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class UserPageLogin extends AppCompatActivity {

    String TAG = "GenerateQRCode";
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    Bitmap bitmap;
    DatabaseHelper db;
    QRGEncoder qrgEncoder;
    ImageView qrImage;
    Button bHome;
    DatabaseHelper databaseHelper;
    //String name="", phone="", adharno="";
    String inputValue="";
    static int count =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_login);
        db = new DatabaseHelper(this);
        bHome = findViewById(R.id.bHome);
        bHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserPageLogin.this, Login.class);
                startActivity(i);
            }
        });


        SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
        String username = sp.getString("user", "");
        //  String value1 = sp.getString("item","");
        String password = sp.getString("pass", "");

        Log.e(TAG, "onClick: " + username + password);



         SQLiteDatabase db1=db.getReadableDatabase();
        Cursor cursor=db1.rawQuery("select * from registerShop where Username=? and Password=?", new String[]{username,password});

        cursor.moveToFirst();
        int cnt= cursor.getCount();
        if(cnt > 0) {

            Date d = new Date();
            CharSequence cdate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
            String s=String.valueOf(cdate);
            //Toast.makeText(UserPageLogin.this, "Fields cant be empty"+s, Toast.LENGTH_LONG).show();

            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String phone = cursor.getString(cursor.getColumnIndex("Phone"));
            String adharno = cursor.getString(cursor.getColumnIndex("AdharNo"));

            String output = "Name=" + name + "\nPhone=" + phone + "\nAadhar No=" + adharno +"\nDate=" +s;
            inputValue = output;

            Toast.makeText(UserPageLogin.this, "text: " + output, Toast.LENGTH_SHORT).show();


        SharedPreferences sp2 = getSharedPreferences("key2", Context.MODE_PRIVATE);
        String user = sp2.getString("usern", "");

    }

/*
       Cursor cursor;


        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Toast.makeText(UserPageLogin.this,"User name is :  " +user ,Toast.LENGTH_SHORT).show();
        cursor = db.rawQuery("select name,phone,adhar from " + databaseHelper.TABLE_NAME + " where Username=?", new String[]{user});

        cursor.moveToFirst();
        int cnt= cursor.getCount();
        if(cnt > 0) {
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String phone = cursor.getString(cursor.getColumnIndex("Phone"));
            String adharno = cursor.getString(cursor.getColumnIndex("AdharNo"));

            String output = "Name=" + name + "\nPhone=" + phone + "\nAadhar No=" + adharno;
            inputValue = output;

            Toast.makeText(UserPageLogin.this, "text: " + output, Toast.LENGTH_SHORT).show();
*/


        qrImage = (ImageView) findViewById(R.id.QR_Image);
        Log.e(TAG, "onClick: "+inputValue );
if(count<=2){
    if (inputValue.length() > 0) {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        qrgEncoder = new QRGEncoder(
                inputValue, null,
                QRGContents.Type.TEXT,
                smallerDimension);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v(TAG, e.toString());
        }
    } else {
        // Registration.etName.setError("Required");
    }

count++;
}else {
    Toast.makeText(UserPageLogin.this,"Your already scan QR code Two Times",Toast.LENGTH_LONG).show();




}



        }

}
