package com.doski.moski.tictactoe;

import java.util.HashMap;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by SamerGigaByte on 4/8/2017.
 */

public interface Apiable {
    @POST("api_post.php")
    @FormUrlEncoded
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> postResult(@Field("api_option") String api_option, @Field("api_paste_private") String api_paste_private,
                                  @Field("api_paste_name") String api_paste_name, @Field("api_paste_expire_date") String api_paste_expire_date,
                                  @Field("api_paste_format") String api_paste_format, @Field("api_dev_key") String api_dev_key,
                                  @Field("api_paste_code") String api_paste_code);

    @GET("search")
    Call<ResponseBody>googleIt( @Query("search") String search);
}
