package com.example.prady.BusPass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME="registerShopping.db";
        public static final String TABLE_NAME="registerShop";
        public static final String COL_1="ID";
        public static final String COL_2="Name";
        public static final String COL_3="DOB";
        public static final String COL_4="Phone";
        public static final String COL_5="AdharNo";
        public static final String COL_6="City";
        public static final String COL_7="Username";
        public static final String COL_8="Password";
        public static final String COL_9="AgeGroup";
      //  public static final String COL_10="Cdate";

        //public static final String COL_7="Password1";
        public static final String TABLE_NAME1="QRCode";

        public static final String C_1="Name";
        public static final String C_2="Phone";
        public static final String C_3="AdharNo";
        private Context context;
        SQLiteDatabase db;


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,DOB TEXT,Phone TEXT,AdharNo TEXT,City TEXT, Username TEXT UNIQUE, Password TEXT,AgeGroup TEXT)");
            db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(ID1 INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Phone TEXT,AdharNo TEXT)");
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
            onCreate(db);
        }


        boolean insertData(String name,String dob,String phone,String adhar,String city,String user,String pass, String Agegroup )
        {
            ContentValues contentValues=new ContentValues();

            contentValues.put(COL_2,name);
            contentValues.put(COL_3,dob);
            contentValues.put(COL_4,phone);
            contentValues.put(COL_5,adhar);
            contentValues.put(COL_6,city);
            contentValues.put(COL_7,user);
            contentValues.put(COL_8,pass);
            contentValues.put(COL_9,Agegroup);


            SQLiteDatabase db=getWritableDatabase();
            return db.insert(TABLE_NAME,null,contentValues)!=-1;
            // SQLiteDatabase db=getWritableDatabase();
            //return db.insert(TABLE_NAME, null,contentValues)!=-1;

        }
        public boolean validation(String username,String password){


            SQLiteDatabase db=getReadableDatabase();
            Cursor cursor=db.rawQuery("select * from registerShop where Username=? and Password=?", new String[]{username,password});
            if(cursor.getCount()<=0) {
               cursor.close();
               db.close();
                return false;

            }else{
                cursor.close();
                db.close();

                return  true;
            }
        }

        public Cursor getAllEntries()
        {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor res=db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            return res;
        }


    }

