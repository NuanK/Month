package com.bwie.yuemonth.presenter;

import com.bwie.yuemonth.interfaces.IAddShopCarModel;
import com.bwie.yuemonth.interfaces.IAddShopCarPresenter;
import com.bwie.yuemonth.model.AddShopCarModel;


public class AddShopCarPresenter implements IAddShopCarModel {

    private AddShopCarModel addShopCarModel;
    private IAddShopCarPresenter iAddShopCarPresenter;

    public AddShopCarPresenter(IAddShopCarPresenter iAddShopCarPresenter) {
        this.iAddShopCarPresenter = iAddShopCarPresenter;
        addShopCarModel = new AddShopCarModel();
    }

    public void receive(String uid, String pid){
        addShopCarModel.receive(uid,pid,this);
    }

    @Override
    public void onASuccess(String msg) {
        iAddShopCarPresenter.onASuccess(msg);
    }

    @Override
    public void onAFailed() {
        iAddShopCarPresenter.onAFailed();
    }
}
