package com.bwie.yuemonth.interfaces;

import com.bwie.yuemonth.bean.JsonDetailsBean;



public interface IDetailsModel {

    void onSuccess(JsonDetailsBean.DataBean data);

    void onFailed();

}
