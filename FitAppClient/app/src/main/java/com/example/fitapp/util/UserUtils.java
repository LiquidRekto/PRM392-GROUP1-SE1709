package com.example.fitapp.util;

import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.fitapp.model.User;
import com.example.fitapp.model.UserToken;

import java.util.Map;

public class UserUtils {
    public static UserToken getUserToken(SharedPreferences sharedPref) {
        String token = SharedPrefUtils.retrieveKeyValue(sharedPref, "jwt_token");
        Log.d("TOKEN", "token: " + token);
        if (token != "") {
            JWT parsedJWT = new JWT(token);
            // Get all claims as a JMap
            Map<String, Claim> claims = parsedJWT.getClaims();
            UserToken ut = new UserToken();
            for (Map.Entry<String, Claim> entry : claims.entrySet()) {
                String key = entry.getKey();
                Claim claim = entry.getValue();
                Log.d("KEY", "KEY: " + key);
                if (key.endsWith("nameidentifier")) {
                    ut.setId(claim.asString());
                }
                if (key.endsWith("name")) {
                    ut.setName(claim.asString());
                }

                if (key.endsWith("role")) {
                    ut.setRoleName(claim.asString());
                }
                // Do something with 'key' and/or 'tab'
            }
            Log.d("UT", "id: " + ut.getId());
            Log.d("UT", "name: " + ut.getName());
            Log.d("UT", "role: " + ut.getRoleName());
            return ut;

        }
        return null;
    }
}
