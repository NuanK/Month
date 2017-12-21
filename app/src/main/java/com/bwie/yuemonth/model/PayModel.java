package com.bwie.yuemonth.model;

import com.bwie.yuemonth.bean.JsonPayBean;
import com.bwie.yuemonth.interfaces.IPayModel;
import com.bwie.yuemonth.utils.IOkHttpUtils;
import com.bwie.yuemonth.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;



public class PayModel {

    public void receive(String uid, String urlTitle, final IPayModel iPayModel) {
        OKHttpUtils instance = OKHttpUtils.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        if(urlTitle.trim().equals("9")){

        }else{
            hashMap.put("status",urlTitle);
        }
        instance.doGet("http://120.27.23.105/product/getOrders", hashMap, new IOkHttpUtils() {
            @Override
            public void onSuccess(String str) {
                if (str != null) {
                    Gson gson = new Gson();
                    JsonPayBean jsonPayBean = gson.fromJson(str, JsonPayBean.class);
                    if (jsonPayBean != null) {
                        List<JsonPayBean.DataBean> data = jsonPayBean.getData();
                        if (data != null) {
                            iPayModel.onSuccess(data);
                        }
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                iPayModel.onFailed();
            }
        });
    }

}
