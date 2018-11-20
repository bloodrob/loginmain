package com.example.akash.loginmain;

/**
 * Created by akash on 10/26/2018.
 */

public class spInfoInsert {

    public String activeId;
    public String sp_name;
    public String sp_address;
    public String sp_state;
    public String sp_district;
    public String sp_pin;

    //default constructor required for calls to DataSnapshot.getValue(clientInfo.class)
    public spInfoInsert() {

    }

    public spInfoInsert(String sp_name, String sp_address, String sp_state, String sp_district, String sp_pin) {

        this.sp_name = sp_name;
        this.sp_address = sp_address;
        this.sp_state = sp_state;
        this.sp_district = sp_district;
        this.sp_pin = sp_pin;
    }

}
