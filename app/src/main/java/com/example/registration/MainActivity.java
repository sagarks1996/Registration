package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    TextView newuser;
    DbHelper mydb;
    String iusername;
    String ipass;


    public void showmessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etusername);
        password = findViewById(R.id.edpassword);
        login = findViewById(R.id.btnlogin);
        newuser = findViewById(R.id.newregister);

        mydb = new DbHelper(this);

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent register = new Intent(MainActivity.this,UserRegistration.class);
                startActivity(register);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cusername = username.getText().toString().trim();
                String cpassword = password.getText().toString().trim();


                if (cusername.isEmpty() || cpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Data",Toast.LENGTH_SHORT).show();
                }
                else{



                    Cursor cursor = mydb.getdata(cusername,cpassword);
                    if(cursor.getCount()==0)
                    {
                        Toast.makeText(getApplicationContext(),"enter valid data",Toast.LENGTH_SHORT).show();

                        //Log.d("debugg","cursor null");
                    }
                    else
                    {
                        while (cursor.moveToNext())
                        {
                            Log.d("debugg","cursor not null");
                            iusername = cursor.getString(2);
                            ipass = cursor.getString(3);
                            Log.d("debugg","user:"+iusername);
                            Log.d("debugg","pass:"+ipass);
                        }


                        if (cusername.equals(iusername) && cpassword.equals(ipass)){
                            Log.d("debugg","user present");
                            Intent u = new Intent(MainActivity.this,UserPage.class);
                            startActivity(u);
                            Toast.makeText(getApplicationContext(),"Validated",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("debugg","user not present");
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }



                }


            }
        });

    }
}
