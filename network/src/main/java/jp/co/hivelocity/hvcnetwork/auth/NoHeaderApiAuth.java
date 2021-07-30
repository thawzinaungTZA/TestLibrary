package jp.co.hivelocity.hvcnetwork.auth;

import java.util.HashMap;

/**
 * Created by hivelocity on 5/26/17.
 */

public class NoHeaderApiAuth extends BaseApiAuth {

    public NoHeaderApiAuth() {
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        return new HashMap<>();
    }
}
