package com.bwie.yuemonth.presenter;

import com.bwie.yuemonth.interfaces.IConfirmModel;
import com.bwie.yuemonth.interfaces.IConfirmPresenter;
import com.bwie.yuemonth.model.ConfirmModel;


public class ConfirmPresenter implements IConfirmModel {

    private ConfirmModel confirmModel;
    private IConfirmPresenter iConfirmPresenter;

    public ConfirmPresenter(IConfirmPresenter iConfirmPresenter) {
        this.iConfirmPresenter = iConfirmPresenter;
        confirmModel = new ConfirmModel();
    }

    public void receive(String uid, String price) {
        confirmModel.receive(uid, price, this);
    }

    @Override
    public void onSuccess(String msg) {
        iConfirmPresenter.onSuccess(msg);
    }

    @Override
    public void onFailed() {
        iConfirmPresenter.onFailed();
    }
}
