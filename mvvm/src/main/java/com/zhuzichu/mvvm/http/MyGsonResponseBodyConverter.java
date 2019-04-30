package com.zhuzichu.mvvm.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final Type type;

    MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        try {
            verify(json);
            return gson.fromJson(json, type);
        } finally {
            value.close();
        }
    }

    private void verify(String json) {
        Result result = gson.fromJson(json, Result.class);
        int code = result.getCode();
        if (code != 200) {
            switch (code) {
                case ExceptionHandle.ERROR.PASSWORD_ERROR:
                    throw new ResponseThrowable(result.getMessage());
                default:
                    throw new ResponseThrowable("不清楚什么原因！");
            }
        }
    }
}