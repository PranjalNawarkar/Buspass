package com.example.prady.BusPass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final long ONE_DAY = 24 * 60 * 60 * 1000;

    DatabaseHelper db;
    Button bLogin;
    EditText etUser,etPass;
    TextView tvRegisterLink,tvReRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=new DatabaseHelper(this);
        etUser=(EditText)findViewById(R.id.etUser);
        etPass=(EditText)findViewById(R.id.etPass);
        tvRegisterLink=(TextView) findViewById(R.id.tvRegisterLink);
        bLogin=(Button)findViewById(R.id.bLogin);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
        tvReRegisterLink=findViewById(R.id.tvReRegisterLink);
        tvReRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Login.this, ReRegistration.class);
                startActivity(i);


            }
        });


    }

    public void onClick(View v) {


        try {

            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            String installDate = preferences.getString("InstallDate", null);
            if (installDate == null) {
                // First run, so save the current date
                SharedPreferences.Editor editor = preferences.edit();
                Date now = new Date();
                String dateString = formatter.format(now);
                editor.putString("InstallDate", dateString);
                // Commit the edits!
                editor.commit();
            } else {
                // This is not the 1st run, check install date
                Date before = (Date) formatter.parse(installDate);
                Date now = new Date();
                long diff = now.getTime() - before.getTime();
                long days = diff / ONE_DAY;




                if (days == 5) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 25 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }

                else if (days == 6) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 24 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 7) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 23 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                } else if (days == 8) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 22 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 9) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 21 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 10) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 20 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }

                else if (days == 11) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 19 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 12) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 18 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                } else if (days == 13) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 18 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 14) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 17 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 15) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 16 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }

                else if (days == 16) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 15 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 17) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 14 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                } else if (days == 18) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 13 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 19) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 12 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 20) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 11 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }

                else if (days == 21) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 10 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 22) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 9 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                } else if (days == 23) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 8 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 24) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 7 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 25) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 6 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }


                else if (days == 26) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 5 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 27) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 4 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                } else if (days == 28) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 3 Days are Remmaing to expire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 29) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 2 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }else if (days == 30) { // More than 30 days?
                    Toast.makeText(Login.this, "Only 1 Days are Remmaing to ecxpire your Pass", Toast.LENGTH_LONG).show();
                }else if (days > 31) { // More than 30 days?
                     Toast.makeText(Login.this, "Your Pass is Expire!!!", Toast.LENGTH_LONG).show();
System.exit(0);
                    Intent intent =new Intent(Login.this, HomePage.class);
                    startActivity(intent);

                }



            }
        }catch (Exception e){
            e.printStackTrace();
        }

        switch (v.getId()) {
            case R.id.bLogin:
                //displayData(etUser,etPass);
                String a = etUser.getText().toString();
                String b = etPass.getText().toString();


                SharedPreferences sp=getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("user", a);
                ed.commit();

                SharedPreferences sp1=getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed1=sp.edit();
                ed.putString("pass", a);
                ed.commit();


                String usern=a;
             //  Toast.makeText(getApplicationContext(), "username: " +a + "and " +usern, Toast.LENGTH_LONG).show();
               SharedPreferences sp3 = getSharedPreferences("key3", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed3 = sp3.edit();
                ed3.putString("usern", usern);
                 ed3.commit();


                Boolean check=db.validation(a,b);
                if(check==true){
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Login.this,UserPageLogin.class);
                    startActivity(i);

                }else
                {
                    Toast.makeText(Login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();

                }
                break;

            case R.id.tvRegisterLink:
                Intent i=new Intent(Login.this, Registration.class);
                startActivity(i);
                break;









        }
    }


}
