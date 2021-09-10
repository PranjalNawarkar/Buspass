package com.example.prady.BusPass;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PopWindow extends Activity {

    Button confirm;
    EditText otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);

        otp=findViewById(R.id.OTP);
        confirm=findViewById(R.id.confirm);



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                onBackPressed();

               // Toast.makeText(PopWindow.this,"otp is: " +otp,Toast.LENGTH_SHORT).show();//System.out.println(otp);// no. of zeros depends on the OTP digit
                //send that otp to number // (otp);


            }
        });
    }
}
