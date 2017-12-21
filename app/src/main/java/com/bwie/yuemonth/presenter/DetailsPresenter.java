package com.bwie.yuemonth.presenter;

import com.bwie.yuemonth.bean.JsonDetailsBean;
import com.bwie.yuemonth.interfaces.IDetailsModel;
import com.bwie.yuemonth.interfaces.IDetailsPresenter;
import com.bwie.yuemonth.model.DetailsModel;


public class DetailsPresenter implements IDetailsModel {

    private DetailsModel detailsModel;
    private IDetailsPresenter iDetailsPresenter;

    public DetailsPresenter(IDetailsPresenter iDetailsPresenter) {
        this.iDetailsPresenter = iDetailsPresenter;
        detailsModel = new DetailsModel();
    }

    public void receive(String pid){
        detailsModel.receive(pid,this);
    }

    @Override
    public void onSuccess(JsonDetailsBean.DataBean data) {
        iDetailsPresenter.onSuccess(data);
    }

    @Override
    public void onFailed() {
        iDetailsPresenter.onFailed();
    }
}
