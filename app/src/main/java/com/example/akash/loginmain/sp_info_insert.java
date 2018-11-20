package com.example.akash.loginmain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.akash.loginmain.R.id.sp_name;

public class sp_info_insert extends AppCompatActivity {

    private static final String TAG = sp_info_insert.class.getSimpleName();
    private EditText name, address, state, district, pin;
    private Button submit;

    private String spId;

    FirebaseDatabase mFirebaseIntance;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_info_insert);

        name = (EditText) findViewById(sp_name);
        address = (EditText) findViewById(R.id.sp_address);
        state = (EditText) findViewById(R.id.sp_state);
        district = (EditText) findViewById(R.id.sp_district);
        pin = (EditText) findViewById(R.id.sp_pin);
        submit = (Button) findViewById(R.id.submit_sp);

        mFirebaseIntance = FirebaseDatabase.getInstance();

        //get reference to the sp_insert node;

        mRef = mFirebaseIntance.getReference("sp_info");

        mFirebaseIntance.getReference("app_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "Found the app");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e(TAG, "Failed to read the app name", databaseError.toException());
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sp_name = name.getText().toString().trim();
                String sp_address = address.getText().toString().trim();
                String sp_state = state.getText().toString().trim();
                String sp_district = district.getText().toString().trim();
                String sp_pin = pin.getText().toString().trim();

                if (TextUtils.isEmpty(spId)) {
                    spCreate(sp_name, sp_address, sp_district, sp_state, sp_pin);
                }
              /*  else {
                    spUpdate(sp_name, sp_address, sp_district, sp_state, sp_pin);
                } */
            }
        });
    }

        public void spCreate(String sp_name, String sp_address, String sp_district, String sp_state, String sp_pin) {

            spInfoInsert spInfo = new spInfoInsert(sp_name, sp_address, sp_district, sp_state, sp_pin);

            spInfo.activeId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            spId = spInfo.activeId;

            mRef.child(spId).setValue(spInfo);

            addSpChangeListener();
        }

            //change listener to insert data;
        public void addSpChangeListener() {
        mRef.child(spId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                spInfoInsert spInfo = dataSnapshot.getValue(spInfoInsert.class);
                if (spInfo == null) {
                    Log.e(TAG, " No such data is found, you are not an authorised user to do so");
                }

                Log.e(TAG, " Your data is inserted successfully" + spInfo.sp_name + ", " + spInfo.sp_address + ", " + spInfo.sp_district + ", " + spInfo.sp_state + ", " + spInfo.sp_pin);
                Intent intent = new Intent(sp_info_insert.this, SP_success.class);
                startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e(TAG, "Failed to inser your data", databaseError.toException());
            }
        });
    }

}

