package com.bwie.yuemonth.interfaces;

import com.bwie.yuemonth.bean.JsonPayBean;

import java.util.List;



public interface IPayModel {

    void onSuccess(List<JsonPayBean.DataBean> data);

    void onFailed();

}
