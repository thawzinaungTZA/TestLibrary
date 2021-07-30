package jp.co.hivelocity.hvcnetwork.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kyawsanwin on 11/21/16.
 */

public abstract class BaseApiAuth {

    protected abstract HashMap<String, String> getHeaders();

    private HashMap<String, String> extraHeaders = new HashMap<String, String>();

    public HashMap<String, String> headers() {
        HashMap<String, String> dic = this.getHeaders();
        for (Map.Entry<String, String> entry : extraHeaders.entrySet()) {
            dic.put(entry.getKey(), entry.getValue());
        }

        return dic;
    }

    public void setExtraHeader(String key, String value) {
        extraHeaders.put(key, value);
    }

    public void removeExtraHeader(String key) {
        if (extraHeaders.containsKey(key)) {
            extraHeaders.remove(key);
        }
    }
}
