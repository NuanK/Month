package com.bwie.yuemonth.model;

import com.bwie.yuemonth.bean.JsonAppShopCarBean;
import com.bwie.yuemonth.interfaces.IAddShopCarModel;
import com.bwie.yuemonth.utils.IOkHttpUtils;
import com.bwie.yuemonth.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;



public class AddShopCarModel {

    public void receive(String uid, String pid, final IAddShopCarModel iAddShopCarModel) {
        OKHttpUtils instance = OKHttpUtils.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("pid", pid);
        instance.doGet("http://120.27.23.105/product/addCart", hashMap, new IOkHttpUtils() {
            @Override
            public void onSuccess(String str) {
                if (str != null) {
                    Gson gson = new Gson();
                    JsonAppShopCarBean jsonAppShopCarBean = gson.fromJson(str, JsonAppShopCarBean.class);
                    if (jsonAppShopCarBean != null) {
                        String msg = jsonAppShopCarBean.getMsg();
                        iAddShopCarModel.onASuccess(msg);
                    }
                }
            }

            @Override
            public void onFailed(String message) {
                iAddShopCarModel.onAFailed();
            }
        });
    }

}
