package com.example.prady.BusPass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConductorLogin extends AppCompatActivity {
    Button cLogin;
    EditText cUser,cPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor_login);

        cUser=findViewById(R.id.Conductor_user);
        cPass=findViewById(R.id.Conductor_pass);
        cLogin=findViewById(R.id.Coductor_login);

        cLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cUser.getText().toString().equals("123") && cPass.getText().toString().equals("123")) {
                    Intent i=new Intent(ConductorLogin.this, Conductor.class);
                    startActivity(i);
                }
            }
        });
    }
}
