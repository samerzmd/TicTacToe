package com.doski.moski.tictactoe;

/**
 * Created by SamerGigaByte on 4/8/2017.
 */

public class Result {
    String api_dev_key;
    String api_paste_code;
    String api_paste_private;
    String api_paste_name;
    String api_paste_expire_date;
    String api_paste_format;
    String api_user_key;
    String api_option="paste";



    public Result(String api_dev_key, String api_paste_code, String api_paste_private, String api_paste_name, String api_paste_expire_date, String api_paste_format, String api_user_key) {
        this.api_dev_key = api_dev_key;
        this.api_paste_code = api_paste_code;
        this.api_paste_private = api_paste_private;
        this.api_paste_name = api_paste_name;
        this.api_paste_expire_date = api_paste_expire_date;
        this.api_paste_format = api_paste_format;
        this.api_user_key = api_user_key;
    }


}
