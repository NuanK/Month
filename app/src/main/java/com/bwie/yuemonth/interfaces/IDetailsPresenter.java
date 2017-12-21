package com.bwie.yuemonth.interfaces;

import com.bwie.yuemonth.bean.JsonDetailsBean;



public interface IDetailsPresenter {

    void onSuccess(JsonDetailsBean.DataBean data);

    void onFailed();

}
