package com.bwie.yuemonth.presenter;

import com.bwie.yuemonth.bean.JsonPayBean;
import com.bwie.yuemonth.interfaces.IPayModel;
import com.bwie.yuemonth.interfaces.IPayPresenter;
import com.bwie.yuemonth.model.PayModel;

import java.util.List;



public class PayPresenter implements IPayModel {

    private PayModel payModel;
    private IPayPresenter iPayPresenter;

    public PayPresenter(IPayPresenter iPayPresenter) {
        this.iPayPresenter = iPayPresenter;
        payModel = new PayModel();
    }

    public void receive(String uid,String urlTitle) {
        payModel.receive(uid,urlTitle, this);
    }

    @Override
    public void onSuccess(List<JsonPayBean.DataBean> data) {
        iPayPresenter.onSuccess(data);
    }

    @Override
    public void onFailed() {
        iPayPresenter.onFailed();
    }
}
