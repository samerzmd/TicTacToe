package com.doski.moski.tictactoe;

import android.content.Intent;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SamerGigaByte on 4/8/2017.
 */

class Log {
    private static ArrayList sLogObjects = new ArrayList();
    private static boolean needReport = true;

    static void i(String name, String param) {
        boolean has = false;
        LogObject currentLogObject = null;
        for (Object logObject : sLogObjects) {

            if (((LogObject) logObject).MethodName.equals(name)) {
                currentLogObject = (LogObject) logObject;
                has = true;
                break;
            }
        }
        if (has) {
            if (!param.contains("Start")) {
                if (currentLogObject.ExecutionTime.contains("Start")) {
                    Long diffTime = Long.parseLong(param.split("End ")[1]) - Long.parseLong(currentLogObject.ExecutionTime.split("Start ")[1]);
                    currentLogObject.ExecutionTime = diffTime.toString();
                }
            }
        } else {
            sLogObjects.add(new LogObject(name, param));
        }
        if (sLogObjects.size() == 17 && needReport) {
            needReport = false;

            final long googleTime = System.nanoTime();
            Retrofit retrofitGoogle = new Retrofit.Builder()
                    .baseUrl("https://google.com/")
                    .build();

            Apiable apiable = retrofitGoogle.create(Apiable.class);
            apiable.googleIt("speedtest").enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call1, Response<ResponseBody> response) {
                    try {

                        long finalGoogleTime = (System.nanoTime() - googleTime) / response.body().string().length() * 2;
                        for (Object logObject : sLogObjects) {
                            ((LogObject) logObject).Throughput = String.valueOf(finalGoogleTime);
                        }

                        Gson gson = new Gson();

                        String listString = gson.toJson(
                                sLogObjects,
                                new TypeToken<ArrayList<LogObject>>() {
                                }.getType());
                        JSONArray json = new JSONArray(listString);
                        Result result = new ResultBuilder().setApi_dev_key("YOUR_API_KEY").setApi_paste_code(json.toString()).setApi_paste_expire_date("10M").setApi_paste_format("php").setApi_paste_name("one").setApi_paste_private("0").createResult();
                        Gson gson2 = new GsonBuilder()
                                .setLenient()
                                .create();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://pastebin.com/api/")
                                .addConverterFactory(GsonConverterFactory.create(gson2))
                                .build();

                        Apiable apiable = retrofit.create(Apiable.class);

                        Call<ResponseBody> call = apiable.postResult(result.api_option, result.api_paste_private, result.api_paste_name, result.api_paste_expire_date, result.api_paste_format, result.api_dev_key, result.api_paste_code);

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String url=response.body().string();
                                    android.util.Log.d("call", url);
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                    App.getApplication().startActivity(browserIntent);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }
}
