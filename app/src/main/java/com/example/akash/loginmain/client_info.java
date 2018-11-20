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
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class client_info extends AppCompatActivity {

    private static  final String TAG = client_info.class.getSimpleName();
    private TextView cView;
    private EditText email, name, address, mobile;
    private Button info_submit, signout1;
    private DatabaseReference mRef;
    private FirebaseDatabase mFirebaseInstance;
    FirebaseAuth auth;

    private String clientId;
  //c  public String activeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);

        cView = (TextView) findViewById(R.id.client_view);
        email = (EditText) findViewById(R.id.client_email);
        name = (EditText) findViewById(R.id.client_name);
        address = (EditText) findViewById(R.id.client_address);
        mobile = (EditText) findViewById(R.id.client_mobile);
        info_submit = (Button) findViewById(R.id.client_info_submit);
        signout1 = (Button)findViewById(R.id.singOut);


        mFirebaseInstance = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        signout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                FirebaseUser user = auth.getCurrentUser();
                if (user == null) {
                    Intent intent = new Intent(client_info.this, entry.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                   // finish();
                }

              /*  final FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            Intent intent = new Intent(client_info.this, entry.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                };
                if (authListener == null) {
                    FirebaseAuth.getInstance().addAuthStateListener(authListener);
                }
                else {
                    FirebaseAuth.getInstance().removeAuthStateListener(authListener);
                } */
            }
        });


        //get reference to client+info node in json.

        mRef = mFirebaseInstance.getReference("client_info");

        //store app title to 'app_name' node;

        mFirebaseInstance.getReference("app_name").setValue("Location based service provider");

        // app_name change listener

        mFirebaseInstance.getReference("app_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e(TAG, "App name updated");

                //update toolbar title.
                //getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //Failed to read value
                Log.e(TAG, "Failed to read app title.", databaseError.toException());
            }
        });
        //save or update user

        info_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String client_email = email.getText().toString();
                String client_name = name.getText().toString();
                String client_address = address.getText().toString();
                String client_mobile = mobile.getText().toString();

                //check for already existed userId
                if (TextUtils.isEmpty(clientId)){
                    createClient(client_email, client_name, client_address, client_mobile);
                }

            }
        });


    }



        //creating new client under node 'client_info'

        private void createClient(String client_email, String client_name, String client_address, String client_mobile) {
            //TODO
            //in the app this clientId should be fetched by implementing firebase auth

          /*  if (TextUtils.isEmpty(clientId)) {
                //push is used to generate unique key , not by default key accepted
                clientId = mRef.push().getKey();
            }*/
            clientInfo cInfo = new clientInfo(client_email, client_name, client_address, client_mobile);

            cInfo.activeId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            clientId = cInfo.activeId;

            mRef.child(clientId).setValue(cInfo);

            addClientChangeListener();

        }

        //user data vhange listener for the updated data
        private void addClientChangeListener(){

            mRef.child(clientId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    clientInfo cInfo = dataSnapshot.getValue(clientInfo.class);

                    //check for null

                    if (cInfo == null) {
                        Log.e(TAG, "client data is null");
                        return;

                    }
                    Log.e(TAG, "Client data is inserted " + cInfo.client_email + "," + cInfo.client_name + "," + cInfo.client_address + "," + cInfo.client_mobile);
                    //Displaying newly updated data

                    Intent intent1 = new Intent(client_info.this, c_success.class);
                    startActivity(intent1);

                //    cView.setText(cInfo.client_email + " ," + cInfo.client_name + " ," + cInfo.client_address + ", " + cInfo.client_mobile);

                    //clear edit text

                    email.setText("");
                    name.setText("");
                    address.setText("");
                    mobile.setText("");



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                    //Faled to read data
                    Log.e(TAG, "Failed to read user", databaseError.toException());

                }
            });
        }
}
