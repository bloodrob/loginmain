package com.example.akash.loginmain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akash on 10/31/2018.
 */

public class ResultRsc {

    public String client_name;
    public String client_address;
    public String client_email;
    public String client_mobile;


    public ResultRsc() {

    }

    public ResultRsc(String client_email, String client_name, String client_address, String client_mobile) {
        this.client_email = client_email;
        this.client_name = client_name;
        this.client_address = client_address;
        this.client_mobile = client_mobile;
    }

     /*public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("client_name", client_name);
        result.put("client_address", client_address);
        result.put("client_email", client_email);
        result.put("client_mobile", client_mobile);

        return result;
    }*/


}
