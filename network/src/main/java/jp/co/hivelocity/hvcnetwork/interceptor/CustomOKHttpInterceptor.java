package jp.co.hivelocity.hvcnetwork.interceptor;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.co.hivelocity.hvcnetwork.auth.BaseApiAuth;
import jp.co.hivelocity.hvcnetwork.auth.HvcApiAuth;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.password;

/**
 * Created by kyawsanwin on 6/22/16.
 */
public class CustomOKHttpInterceptor implements Interceptor {

    private BaseApiAuth auth;

    public CustomOKHttpInterceptor(BaseApiAuth auth) {
        this.auth = auth;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (auth != null) {
            HashMap<String, String> headers = auth.headers();

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
                Log.d(entry.getKey(), entry.getValue());
            }
        }

        Request request = builder.build();
        return chain.proceed(request);
    }
}
