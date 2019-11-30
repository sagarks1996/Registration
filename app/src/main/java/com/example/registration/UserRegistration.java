package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity {

    AppCompatEditText name,email,password;
    Button submit;
    DbHelper mydb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        name = findViewById(R.id.etfirstname);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etnpassword);
        submit = findViewById(R.id.btnsubmit);

        mydb = new DbHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String iname = name.getText().toString().trim();
                String iemail = email.getText().toString().trim();
                String ipass = password.getText().toString().trim();


                if (iname.isEmpty() || iemail.isEmpty() || ipass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Missing data",Toast.LENGTH_SHORT).show();


                }else {

                    boolean isinserted = mydb.insertdata(iname,iemail,ipass);
                    if (isinserted){
                        Log.d("debugg","data inserted");
                        Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        email.setText("");
                        password.setText("");

                        Intent lgn = new Intent(UserRegistration.this,MainActivity.class);
                        startActivity(lgn);

                    }else {
                        Log.d("debugg","data not inserted");
                        Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        email.setText("");
                        password.setText("");
                    }

                }

            }
        });
    }
}
