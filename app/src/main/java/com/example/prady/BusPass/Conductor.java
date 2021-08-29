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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Conductor extends AppCompatActivity {
Spinner s1,s2;
    EditText start_point,end_point;
    Button proceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);

        //start_point=findViewById(R.id.source_location);
       // end_point=findViewById(R.id.dest_location);
s1=findViewById(R.id.spinner1);
s2=findViewById(R.id.spinner2);

        proceed=findViewById(R.id.Conductor_submit);


        List<String> list1=new ArrayList<>();
        list1.add("Select Source");
        list1.add("Nashik");
        list1.add("Malegaon");
        list1.add("Sinnar");
        list1.add("Ozar");
        list1.add("Sangamner");
        list1.add("Chandwad");


        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item1 = adapterView.getItemAtPosition(i).toString();
                SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("source", item1);
                ed.commit();
                if (i > 0)
                {
                    Toast.makeText(Conductor.this, "Selected Source is " + item1, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        List<String> list=new ArrayList<>();
        list.add("Select Destination");
        list.add("Nashik");
        list.add("Malegaon");
        list.add("Sinner");
        list.add("Ozar");
        list.add("Sangmner");
        list.add("Chandwad");


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s2.setAdapter(adapter);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("dest", item );
                ed.commit();
                if (i > 0)
                {
                    Toast.makeText(Conductor.this, "Selected Destination is " + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
                String src = sp.getString("source","");
                //  String value1 = sp.getString("item","");
                String destn = sp.getString("dest","");
                if(src.equals("Nashik")&&destn.equals("Nashik")){

                    Toast.makeText(Conductor.this, "Select Proper Route" , Toast.LENGTH_SHORT).show();

                }
                else if(src.equals("Malegaon")&&destn.equals("Malegaon")){
                    Toast.makeText(Conductor.this, "Select Proper Route" , Toast.LENGTH_SHORT).show();


                }
                else if(src.equals("Sinner")&&destn.equals("Sinner")){
                    Toast.makeText(Conductor.this, "Select Proper Route" , Toast.LENGTH_SHORT).show();


                }

                else if(src.equals("Ozar")&&destn.equals("Ozar")){
                    Toast.makeText(Conductor.this, "Select Proper Route" , Toast.LENGTH_SHORT).show();


                }    else if(src.equals("Sangmner")&&destn.equals("Sangmner")){
                    Toast.makeText(Conductor.this, "Select Proper Route" , Toast.LENGTH_SHORT).show();


                }    else if(src.equals("Chandwad")&&destn.equals("Chandwad")){
                    Toast.makeText(Conductor.this, "Select Proper Route" , Toast.LENGTH_SHORT).show();


                }
                else if(src.equals("Select Source")&&destn.equals("Select Destination")){
                    Toast.makeText(Conductor.this, "Select Proper Root" , Toast.LENGTH_SHORT).show();


                }else {

                    Intent i=new Intent(Conductor.this,QRcodeScan.class);
                    startActivity(i);
                }






            }
        });


    }
}
