package com.bwie.yuemonth.utils;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpUtils {
    private static OKHttpUtils okHttpUtils;
    private static Handler handler = new Handler();


    //私有化构造方法
    public OKHttpUtils() {


    }


    /**
     * 获取OKhttpClient实例化
     */
    public static OKHttpUtils getInstance() {
        if (null == okHttpUtils) {
            synchronized (OKHttpUtils.class) {
                if (null == okHttpUtils) {
                    okHttpUtils = new OKHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }


    /**
     * 封装的异步Get请求
     */
    public void doGet(String path, Map<String, String> map, final IOkHttpUtils okHttpCallBack) {
        //创建一个字符串容器
        StringBuilder sb = null;
        if (map.size() == 0) {
            if (null == sb) {
                sb = new StringBuilder();
                sb.append(path);
            }
        } else {
            for (String key : map.keySet()) {
                if (null == sb) {
                    sb = new StringBuilder();
                    sb.append(path);
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(map.get(key));
            }
        }


        //System.out.println("分类 : "+path + sb.toString());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Intercept())//使用拦截器
                .build();
        Request request = new Request.Builder()
                .url(sb.toString())
                .get()
                .build();
        //OKHttp 网络
        Call call = okHttpClient.newCall(request);
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                Log.e("SKN", "OK请求失败");
                okHttpCallBack.onFailed(e.getMessage());
            }


            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String str = response.body().string();
                //请求成功
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("WWWW", "请求成功" + str);
                        okHttpCallBack.onSuccess(str);
                    }
                });
            }
        });
    }
}