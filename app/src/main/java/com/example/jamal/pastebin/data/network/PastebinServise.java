package com.example.jamal.pastebin.data.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PastebinServise {

    @FormUrlEncoded
    @POST("api_login.php")
    Call<ResponseBody> login(
            @Field("api_dev_key") String apiDevKey,
            @Field("api_user_name") String apiUserName,
            @Field("api_user_password") String apiUserPassword);

    @FormUrlEncoded
    @POST("api_post.php")
    Call<ResponseBody> getListPasteByUser(
            @Field("api_dev_key") String apiDevKey,
            @Field("api_user_key") String apiUserKey,
            @Field("api_results_limit") int apiResultsLimit,
            @Field("api_option") String apiOption);

    @FormUrlEncoded
    @POST("api_post.php")
    Call<ResponseBody> getListTrendingPaste(
            @Field("api_dev_key") String apiDevKey,
            @Field("api_option") String apiOption);
}