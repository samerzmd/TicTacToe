package com.doski.moski.tictactoe;

public class ResultBuilder {
    private String mApi_dev_key;
    private String mApi_paste_code;
    private String mApi_paste_private;
    private String mApi_paste_name;
    private String mApi_paste_expire_date;
    private String mApi_paste_format;
    private String mApi_user_key;

    public ResultBuilder setApi_dev_key(String api_dev_key) {
        mApi_dev_key = api_dev_key;
        return this;
    }

    public ResultBuilder setApi_paste_code(String api_paste_code) {
        mApi_paste_code = api_paste_code;
        return this;
    }

    public ResultBuilder setApi_paste_private(String api_paste_private) {
        mApi_paste_private = api_paste_private;
        return this;
    }

    public ResultBuilder setApi_paste_name(String api_paste_name) {
        mApi_paste_name = api_paste_name;
        return this;
    }

    public ResultBuilder setApi_paste_expire_date(String api_paste_expire_date) {
        mApi_paste_expire_date = api_paste_expire_date;
        return this;
    }

    public ResultBuilder setApi_paste_format(String api_paste_format) {
        mApi_paste_format = api_paste_format;
        return this;
    }

    public ResultBuilder setApi_user_key(String api_user_key) {
        mApi_user_key = api_user_key;
        return this;
    }

    public Result createResult() {
        return new Result(mApi_dev_key, mApi_paste_code, mApi_paste_private, mApi_paste_name, mApi_paste_expire_date, mApi_paste_format, mApi_user_key);
    }
}