package common.networkrequestlibrary.interceptor;

import java.io.IOException;

import common.networkrequestlibrary.util.HttpBuilder;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chasen on 2018/3/13.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String cache = request.header("Cache-Time");
        if (!HttpBuilder.checkNULL(cache)) {
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
                    .header("Cache-Control", "max-age=" + cache)
                    .build();
            return response1;
        } else {
            return response;
        }
    }
}