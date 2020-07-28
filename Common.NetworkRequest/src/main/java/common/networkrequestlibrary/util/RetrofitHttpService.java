package common.networkrequestlibrary.util;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by chasen on 2018/3/13.
 */

public interface RetrofitHttpService {
    @GET()
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params, @HeaderMap Map<String, String> headers);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST()
    Call<String> postWithJson(@Url String url, @Body RequestBody body);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @PUT()
    Call<String> putWithJson(@Url String url, @Body RequestBody body);

    @Headers({"Content-Type: application/text", "Accept: application/json"})
    @POST()
    Call<String> postWithText(@Url String url, @Body RequestBody body);

    @Streaming
    @GET()
    Call<ResponseBody> download(@HeaderMap Map<String, String> headers, @Url String url, @QueryMap Map<String, Object> params);

    @Multipart
    @POST
    Call<String> uploadFile(@Url String url, @PartMap Map<String, Object> params, @PartMap Map<String, RequestBody> maps, @HeaderMap Map<String, String> headers);

    @Streaming
    @GET
    Call<ResponseBody> downloadWithPause(@HeaderMap Map<String, String> headers, @Header("RANGE") String range, @Url String url);
}
