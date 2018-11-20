package com.example.akash.loginmain;


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



public class register extends AppCompatActivity {

     public static final String TAG = "register";
     EditText inputEmail1, inputPass1;
     Button btnRegister1;
     FirebaseAuth auth;
    // FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

      //  if(auth.getCurrentUser() != null)
      //  {
       //     finish();

           // Intent myintent = new Intent(v.getContext(),register.class);
            //startActivity(myintent);
      //  }

        inputEmail1 = (EditText) findViewById(R.id.inputEmail);
        inputPass1  =  (EditText) findViewById(R.id.inputPass);
        btnRegister1 =  (Button) findViewById(R.id.btnRegister);


        btnRegister1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String inputEmail = inputEmail1.getText().toString().trim();
                String inputPass  = inputPass1.getText().toString().trim();

                if (TextUtils.isEmpty(inputEmail)) {
                    Toast.makeText(register.this, " Email required !!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(inputPass))  {
                    Toast.makeText(register.this, "Password Required !!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (inputPass.length() <8) {
                    Toast.makeText(register.this, "Can not be shorter then 8", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(inputEmail,inputPass)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {


                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                             //   Toast.makeText(register.this, "createUsersWithEmail:onComplete:" + task.isSuccessful(),Toast.LENGTH_SHORT).show();

                                if(!task.isSuccessful()) {
                                    Toast.makeText(register.this, " Registration Failed. " + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Intent intent = new Intent(register.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                               // if(task.isSuccessful()){
                                   // Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    //Toast.makeText(register.this, "sucess", Toast.LENGTH_LONG).show();
                                   // FirebaseUser user = auth.getCurrentUser();
                                  //  if(user != null) {
                                    //    updateUI(user);
                                   // }
                                 //   if(!task.isSuccessful()){
                                 //   Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                  //  Toast.makeText(register.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                               // }
                            }
                        });

            }
        });

    }
}
