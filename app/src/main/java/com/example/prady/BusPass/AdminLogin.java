package com.example.prady.BusPass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    Button admin;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin = findViewById(R.id.Alogin);
        user = findViewById(R.id.Auser);
        pass = findViewById(R.id.Apass);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().equals("admin") && pass.getText().toString().equals("admin")) {
                    Intent i=new Intent(AdminLogin.this, Admin.class);
                    startActivity(i);
                }else{
                    Toast.makeText(AdminLogin.this,"Enter valid details",Toast.LENGTH_SHORT).show();
                }

            }
        });
        }
}
