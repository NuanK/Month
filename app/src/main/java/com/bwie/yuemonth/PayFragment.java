package com.bwie.yuemonth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.yuemonth.adapter.MyRecyclerViewAdapter;
import com.bwie.yuemonth.bean.JsonPayBean;
import com.bwie.yuemonth.interfaces.IPayPresenter;
import com.bwie.yuemonth.presenter.PayPresenter;

import java.util.List;



public class PayFragment extends Fragment implements IPayPresenter {

    private View view;
    private String uid = "3384";
    private PayPresenter payPresenter;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myAdapter;
    private String urlTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.activity_pay, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();//初始化数据
        Bundle bundle = getArguments();
        urlTitle = bundle.getString("url").toString();
//        Log.i("qqqq",urlTitle);
        payPresenter = new PayPresenter(this);//实例化
        payPresenter.receive(uid, urlTitle);
    }

    //初始化数据
    private void initView() {
        recyclerView = view.findViewById(R.id.recyclerView);
    }


    @Override
    public void onSuccess(List<JsonPayBean.DataBean> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        myAdapter = new MyRecyclerViewAdapter(getActivity(), data);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onFailed() {

    }
}
