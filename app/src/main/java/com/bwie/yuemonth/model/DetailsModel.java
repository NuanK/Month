package com.bwie.yuemonth.model;

import com.bwie.yuemonth.bean.JsonDetailsBean;
import com.bwie.yuemonth.interfaces.IDetailsModel;
import com.bwie.yuemonth.utils.IOkHttpUtils;
import com.bwie.yuemonth.utils.OKHttpUtils;
import com.google.gson.Gson;

import java.util.HashMap;



public class DetailsModel {

    public void receive(String pid, final IDetailsModel iDetailsModel){
        OKHttpUtils instance = OKHttpUtils.getInstance();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pid",pid);
        instance.doGet("http://120.27.23.105/product/getProductDetail", hashMap, new IOkHttpUtils() {
            @Override
            public void onSuccess(String str) {
                if(str!=null){
                    Gson gson = new Gson();
                    JsonDetailsBean jsonDetailsBean = gson.fromJson(str, JsonDetailsBean.class);
                    if(jsonDetailsBean!=null){
                        JsonDetailsBean.DataBean data = jsonDetailsBean.getData();
                        iDetailsModel.onSuccess(data);
                    }
                }
            }

            @Override
            public void onFailed(String message) {

            }
        });
    }

}
