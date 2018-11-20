package com.example.akash.loginmain;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class sp_login extends AppCompatActivity {

    EditText email, password;
    Button submit, reg_go1, forgot_pass1;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_login);

        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.sp_email);
        password = (EditText) findViewById(R.id.sp_password);
        submit = (Button) findViewById(R.id.sp_login);
        reg_go1 = (Button) findViewById(R.id.reg_go);
        forgot_pass1 = (Button)findViewById(R.id.Forgot_pass);

        reg_go1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sp_login.this, SP_register.class);
                startActivity(intent);
            }
        });

        forgot_pass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sp_login.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sp_email = email.getText().toString().trim();
                String sp_password = password.getText().toString().trim();

                if (TextUtils.isEmpty(sp_email)) {
                    Toast.makeText(sp_login.this, " Pleasse enter your email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(sp_password)) {
                    Toast.makeText(sp_login.this, " Please enter your password", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(sp_email, sp_password)
                        .addOnCompleteListener(sp_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (! task.isSuccessful()) {
                                    Toast.makeText(sp_login.this, " Failed to log in to the account", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                else {
                                    Toast.makeText(sp_login.this, "Successfully enter your account ", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(sp_login.this, sp_info_insert.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });


    }
}
