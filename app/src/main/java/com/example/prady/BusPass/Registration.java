package com.example.prady.BusPass;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
     DatabaseHelper db;
    Button bRegister;
    public static EditText etName, etDOB, etPass, etPass1,etCity, etUser, etPhone, etAdharNo;
    //TextView tvUploadDocLink;
    Spinner spinner;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    DatePickerDialog picker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);
        etName = (EditText) findViewById(R.id.etName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        etCity = (EditText) findViewById(R.id.etCity);
        etPass1 = (EditText) findViewById(R.id.etPass1);
        etAdharNo = (EditText) findViewById(R.id.etAdharNo);
        bRegister = (Button) findViewById(R.id.bRegister);
        spinner = findViewById(R.id.spAgeGroup);

        //tvUploadDocLink = (TextView) findViewById(R.id.tvUploadDocLink);

        etDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Registration.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                etDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
                picker.show();
            }
        });


      //  Date d = new Date();
       // CharSequence cdate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        //String s=String.valueOf(cdate);


        //  Toast.makeText(Registration.this, "Fields cant be empty"+s, Toast.LENGTH_LONG).show();



        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date d = new Date();
                CharSequence cdate  = DateFormat.format("MMMM d, yyyy ", d.getTime());
                 String s=String.valueOf(cdate);






              //  Toast.makeText(Registration.this, "Fields cant be empty"+s, Toast.LENGTH_LONG).show();
                // db=openHelper.getWritableDatabase();
                String name = etName.getText().toString();
                String dob= String.valueOf(etDOB.getText());

                String phone = etPhone.getText().toString();
                String adhar = etAdharNo.getText().toString();
                String city = etCity.getText().toString();
                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();
                String pass1 = etPass1.getText().toString();

                SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
                String value = sp.getString("item", "");


                if (name.equals("") || name == null || etDOB.getText().equals("") || etDOB.getText() == null || phone.equals("") || phone == null || adhar.equals("") || adhar == null
                        ||city.equals("") || city == null|| user.equals("") || user == null || pass.equals("") || pass == null || pass1.equals("") || pass1 == null) {

                    Toast.makeText(Registration.this, "Fields cant be empty", Toast.LENGTH_LONG).show();
                } else {
                    if(phone.length()==10){
                        if(adhar.length()==12){
                            if (pass.equals(pass1)) {
                                if (db.insertData(name, dob, phone, adhar,city, user, pass, value)) {

                                    try {

                                        java.util.Random r=new java.util.Random();

                                        int otp = r.nextInt(1000000);
                                        String otp_no=String.valueOf(otp);
                                        SharedPreferences sp1 = getSharedPreferences("key1", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor ed1 = sp1.edit();
                                        ed1.putString("otp_no", otp_no);
                                        ed1.commit();

                                        String otp_msg="Here is the OTP: " +otp;
                                        SmsManager smsManager = SmsManager.getDefault();
                                        smsManager.sendTextMessage(phone, null, otp_msg, null, null);
                                        Toast.makeText(getApplicationContext(), "OTP Sent!",Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(Registration.this, Payment.class);
                                        startActivity(i);

                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(),
                                                "OTP Sending faild, please try again later!",
                                                Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Could not register", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Registration.this, "Password don't match!", Toast.LENGTH_LONG).show();

                            }
                        }else{
                            Toast.makeText(Registration.this, "Enter valid Adhar Number!", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Toast.makeText(Registration.this, "Enter valid Phone number!", Toast.LENGTH_LONG).show();

                    }

                        }
                }

        });


      /*  tvUploadDocLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, UploadDocument.class));
            }
        });
*/
        spinner.setOnItemSelectedListener(this);//spinner.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Select");
        list.add("Student");
        list.add("Adult");
        list.add("Senior Citizen");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

        String item = adapterView.getItemAtPosition(i).toString();
        SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("item", item);
        ed.commit();

        //  Intent z = new Intent(this, Payment.class);
        //  z.putExtra("item_name", item);
        //  startActivity(z);
       /* if(i>0){
           // Toast.makeText(MainActivity.this,"Hello "+item,Toast.LENGTH_SHORT).show();
        }*/
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
