package jp.co.hivelocity.hvcnetwork.auth;

import androidx.annotation.NonNull;
import android.util.Base64;

import java.util.HashMap;

/**
 * Created by kyawsanwin on 11/21/16.
 */

public class BasicApiAuth extends BaseApiAuth {
    private String username;
    private String password;

    public BasicApiAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        final String basic = getBasicAuthorization();
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", basic);
        return headers;
    }

    @NonNull
    private String getBasicAuthorization() {
        String credentials = this.username + ":" + this.password;
        // create Base64 encodet string
        return "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
    }
}
