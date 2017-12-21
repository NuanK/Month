package com.bwie.yuemonth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.yuemonth.adapter.MyAdapter;
import com.bwie.yuemonth.adapter.PriceAndCountEvent;
import com.bwie.yuemonth.bean.JsonShopCarBean;
import com.bwie.yuemonth.interfaces.IShopCarPresenter;
import com.bwie.yuemonth.presenter.SearchShopCarPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SearchShopCarActivity extends AppCompatActivity implements IShopCarPresenter {

    private ExpandableListView ex;
    private CheckBox selectAll;
    private TextView allPrice;
    private TextView count;
    private int uid = 3384;
    private SearchShopCarPresenter searchShopCarPresenter;
    private List<JsonShopCarBean.DataBean> groupList = new ArrayList<>();//商家店铺
    private List<List<JsonShopCarBean.DataBean.ListBean>> childList = new ArrayList<>();//商品
    private MyAdapter myAdapter;
    private RelativeLayout btnMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        /**
         * 用于传控件,
         * 需要导入依赖才可以进行传送
         */
        EventBus.getDefault().register(this);        //全选/全不选
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置全选
                myAdapter.changeAllListCbState(selectAll.isChecked());
            }
        });
        searchShopCarPresenter = new SearchShopCarPresenter(this);
        searchShopCarPresenter.receive(uid + "");
        /**
         * 点击结算跳转页面
         */
        btnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchShopCarActivity.this, ConfirmActivity.class);
                intent.putExtra("price",allPrice.getText()+"");
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnMoney = (RelativeLayout) findViewById(R.id.btnMoney);
        ex = (ExpandableListView) findViewById(R.id.ex);
        selectAll = (CheckBox) findViewById(R.id.selectAll);
        allPrice = (TextView) findViewById(R.id.allPrice);
        count = (TextView) findViewById(R.id.count);
    }

    @Override
    public void onSuccess(List<JsonShopCarBean.DataBean> data) {
        //清空一下商家店铺集合的数据
        groupList.clear();
        //清空一下商品集合的数据
        childList.clear();
        //将商家店铺大集合的数据存到另一个集合里
        groupList.addAll(data);
        //将商品小集合的数据存到另一个集合里
        for (int i = 0; i < data.size(); i++) {
            List<JsonShopCarBean.DataBean.ListBean> list = data.get(i).getList();
            childList.add(list);
        }
        /**
         * 配置适配器
         */
        myAdapter = new MyAdapter(SearchShopCarActivity.this, groupList, childList);
        ex.setAdapter(myAdapter);
        //默认展示二级列表数据
        for (int i = 0; i < data.size(); i++) {
            ex.expandGroup(i);
        }

    }

    @Override
    public void onFailed() {

    }

    //计算总价和总数量
    @Subscribe
    public void onMessageEven(PriceAndCountEvent event) {
        allPrice.setText(event.getPrice() + ".00");
        count.setText("结算(" + event.getCount() + ")");
    }
}
