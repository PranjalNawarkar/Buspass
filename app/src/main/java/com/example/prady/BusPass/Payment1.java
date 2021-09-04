package com.example.prady.BusPass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Payment1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvamount;
    Spinner spinner1;
    EditText etCard_number,OTP;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment1);

        tvamount=findViewById(R.id.tvAmount);
        spinner1=findViewById(R.id.spinner);
        etCard_number=findViewById(R.id.etCardNo);
        pay=findViewById(R.id.btnPay);
        OTP=findViewById(R.id.etOTP);



        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String otp_number=OTP.getText().toString();
                String number = etCard_number.getText().toString();
                if(number.length()==16 && !otp_number.isEmpty()){
                    SharedPreferences sp1 = getSharedPreferences("key1", Context.MODE_PRIVATE);
                    String value_otp = sp1.getString("otp_no", "");
                    // Toast.makeText(Payment.this, "otp : "+value_otp, Toast.LENGTH_SHORT).show();

                    if(value_otp.equals(otp_number)){
                        Toast.makeText(Payment1.this, "Payment Done Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Payment1.this,UserPage1.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter valid OTP",Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(Payment.this, "Payment done successfully", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Payment1.this, "Enter 16 digit valid Number", Toast.LENGTH_SHORT).show();

                }


            }
        });




        SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        String name = sp.getString("item","");

        if(name.equals("Student"))
        {
            String cost= "Rs. 1200";
            tvamount.setText(cost);

        }else if(name.equals("Adult")){
            String cost= "Rs. 1400";
            tvamount.setText(cost);
        }else if(name.equals("Senior Citizen")){
            String cost= "Rs. 750";
            tvamount.setText(cost);
        }

        spinner1.setOnItemSelectedListener(this);//spinner.setOnItemSelectedListener(this);
        List<String> list1=new ArrayList<String>();
        list1.add("select");
        list1.add("Debit Card");
        list1.add("Credit Card");


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

        String item=adapterView.getItemAtPosition(i).toString();
        SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("item",item);
        ed.commit();

       /* if(i>0){
           // Toast.makeText(MainActivity.this,"Hello "+item,Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
