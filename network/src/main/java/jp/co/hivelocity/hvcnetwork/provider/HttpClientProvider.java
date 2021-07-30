package jp.co.hivelocity.hvcnetwork.provider;

import java.util.concurrent.TimeUnit;

import jp.co.hivelocity.hvcnetwork.BuildConfig;
import jp.co.hivelocity.hvcnetwork.auth.BaseApiAuth;
import jp.co.hivelocity.hvcnetwork.auth.HvcApiAuth;
import jp.co.hivelocity.hvcnetwork.interceptor.CustomOKHttpInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by kyawsanwin on 9/22/16.
 */

public class HttpClientProvider {

    private static OkHttpClient okHttpClient;
    private static CustomOKHttpInterceptor customOKHttpInterceptor;

    public static OkHttpClient provideOkHttpClient(BaseApiAuth auth) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);

        if (true) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.addInterceptor(provideCustomOkhttpInterceptor(auth));
        okHttpClient = builder.build();
        return okHttpClient;
    }

    private static CustomOKHttpInterceptor provideCustomOkhttpInterceptor(BaseApiAuth auth) {
        customOKHttpInterceptor = new CustomOKHttpInterceptor(auth);
        return customOKHttpInterceptor;
    }
}
