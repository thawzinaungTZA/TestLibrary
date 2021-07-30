package jp.co.hivelocity.hvcnetwork.auth;

import android.util.Log;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by sugiyama on 2016/06/02.
 */
public class HvcApiAuth extends BaseApiAuth {
    private String apiKey;
    private String apiSecret;
    private String headerNameForAPIKey;
    private String headerNameForTimestamp;
    private String headerNameForSignature;

    //private HashMap<String, String> extraHeaders = new HashMap<String, String>();

    private String algorithm = "HmacSHA1";

    public HvcApiAuth() {
    }

    public HvcApiAuth(
            String apiKey,
            String apiSecret,
            String headerNameForAPIKey,
            String headerNameForTimestamp,
            String headerNameForSignature
    ) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.headerNameForAPIKey = headerNameForAPIKey;
        this.headerNameForTimestamp = headerNameForTimestamp;
        this.headerNameForSignature = headerNameForSignature;
    }

    public String timestamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

    public String signature(String timestamp) {

        SecretKeySpec secretKey = new SecretKeySpec((this.apiSecret).getBytes(), this.algorithm);

        Mac mac = null;
        try {
            mac = Mac.getInstance(this.algorithm);
            mac.init(secretKey);
        } catch (NoSuchAlgorithmException e) {
            Log.i("Exception", "NoSuchAlgorithmException");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            Log.i("Exception", "InvalidKeyException");
            e.printStackTrace();
        }

        String s = timestamp + this.apiKey;

        byte[] bytes = mac.doFinal(s.getBytes());

        return byteArrayToHex(bytes);

    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }

    public String signature() {
        return signature(this.timestamp());
    }

    @Override
    protected HashMap<String, String> getHeaders() {
        String timestamp = this.timestamp();
        HashMap<String, String> dic = new HashMap<String, String>();
        dic.put(this.headerNameForAPIKey, this.apiKey);
        dic.put(this.headerNameForTimestamp, timestamp);
        dic.put(this.headerNameForSignature, this.signature(timestamp));
        return dic;
    }

}
