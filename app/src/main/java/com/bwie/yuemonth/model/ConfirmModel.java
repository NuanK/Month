package com.bwie.yuemonth.model;

import com.bwie.yuemonth.bean.JsonConfirmBean;
import com.bwie.yuemonth.interfaces.IConfirmModel;
import com.bwie.yuemonth.utils.IOkHttpUtils;
import com.bwie.yuemonth.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;


public class ConfirmModel {

    public void receive(String uid, String price, final IConfirmModel iConfirmModel) {
        OKHttpUtils instance = OKHttpUtils.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("price", price);
        instance.doGet("http://120.27.23.105/product/createOrder", hashMap, new IOkHttpUtils() {
            @Override
            public void onSuccess(String str) {
                if (str != null) {
                    Gson gson = new Gson();
                    JsonConfirmBean jsonConfirmBean = gson.fromJson(str, JsonConfirmBean.class);
                    if (jsonConfirmBean != null) {
                        String msg = jsonConfirmBean.getMsg();
                        if (msg != null) {
                            iConfirmModel.onSuccess(msg);
                        }
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                iConfirmModel.onFailed();
            }
        });
    }

}
