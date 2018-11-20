package com.example.akash.loginmain;
//package info.adroidhive.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    EditText Uemail1, Upassword1;
    Button UlogIn1;
    FirebaseAuth auth1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth1 = FirebaseAuth.getInstance();
        Button btn1 = (Button) findViewById(R.id.UlogIn);
        Button btn2 = (Button) findViewById(R.id.button4);
        Uemail1 = (EditText) findViewById(R.id.Uemail);
        Upassword1 = (EditText) findViewById(R.id.Upassword);
        UlogIn1 = (Button) findViewById(R.id.UlogIn);

       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this, register.class);
               startActivity(intent);
           }
       });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

        UlogIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Uemail = Uemail1.getText().toString().trim();
                String Upassword = Upassword1.getText().toString().trim();

                if(TextUtils.isEmpty(Uemail)) {
                    Toast.makeText(MainActivity.this, "No email id", Toast.LENGTH_LONG).show();
                }

                if(TextUtils.isEmpty(Upassword)) {
                    Toast.makeText(MainActivity.this, "No password", Toast.LENGTH_LONG).show();
                }
                auth1.signInWithEmailAndPassword(Uemail,Upassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful())    {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, client_info.class);
                                    startActivity(intent);
                                }

                            }
                        });

            }
        });
    }
}
