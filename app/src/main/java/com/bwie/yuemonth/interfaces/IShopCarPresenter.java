package com.bwie.yuemonth.interfaces;

import com.bwie.yuemonth.bean.JsonShopCarBean;

import java.util.List;


public interface IShopCarPresenter {

    void onSuccess(List<JsonShopCarBean.DataBean> data);

    void onFailed();

}
