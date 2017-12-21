package com.bwie.yuemonth.model;

import com.bwie.yuemonth.bean.JsonShopCarBean;
import com.bwie.yuemonth.interfaces.IShopCarModel;
import com.bwie.yuemonth.utils.IOkHttpUtils;
import com.bwie.yuemonth.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;


public class SearchShopCarModel {

    public void receive(String uid, final IShopCarModel iShopCarModel) {
        OKHttpUtils instance = OKHttpUtils.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        instance.doGet("http://120.27.23.105/product/getCarts", hashMap, new IOkHttpUtils() {
            @Override
            public void onSuccess(String str) {
                if (str != null) {
                    Gson gson = new Gson();
                    JsonShopCarBean jsonShopCarBean = gson.fromJson(str, JsonShopCarBean.class);
                    if (jsonShopCarBean != null) {
                        List<JsonShopCarBean.DataBean> data = jsonShopCarBean.getData();
                        iShopCarModel.onSuccess(data);
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                iShopCarModel.onFailed();
            }
        });
    }

}
