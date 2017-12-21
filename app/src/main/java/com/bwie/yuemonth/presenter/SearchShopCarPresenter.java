package com.bwie.yuemonth.presenter;

import com.bwie.yuemonth.bean.JsonShopCarBean;
import com.bwie.yuemonth.interfaces.IShopCarModel;
import com.bwie.yuemonth.interfaces.IShopCarPresenter;
import com.bwie.yuemonth.model.SearchShopCarModel;

import java.util.List;



public class SearchShopCarPresenter implements IShopCarModel {

    private SearchShopCarModel searchShopCarModel;
    private IShopCarPresenter iShopCarPresenter;

    public SearchShopCarPresenter(IShopCarPresenter iShopCarPresenter) {
        this.iShopCarPresenter = iShopCarPresenter;
        searchShopCarModel = new SearchShopCarModel();
    }

    public void receive(String uid) {
        searchShopCarModel.receive(uid, this);
    }

    @Override
    public void onSuccess(List<JsonShopCarBean.DataBean> data) {
        iShopCarPresenter.onSuccess(data);
    }

    @Override
    public void onFailed() {
        iShopCarPresenter.onFailed();
    }
}
