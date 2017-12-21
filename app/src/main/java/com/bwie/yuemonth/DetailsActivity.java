package com.bwie.yuemonth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.yuemonth.bean.JsonDetailsBean;
import com.bwie.yuemonth.interfaces.IAddShopCarPresenter;
import com.bwie.yuemonth.interfaces.IDetailsPresenter;
import com.bwie.yuemonth.presenter.AddShopCarPresenter;
import com.bwie.yuemonth.presenter.DetailsPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailsActivity extends AppCompatActivity implements IDetailsPresenter, IAddShopCarPresenter {

    private ImageView img;
    private TextView title;
    private TextView price;
    private Button btnShopCar;
    private Button btnJoin;
    private DetailsPresenter detailsPresenter;
    private String uid = "3384";
    private String pid = "2";
    private AddShopCarPresenter addShopCarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsPresenter = new DetailsPresenter(this);
        addShopCarPresenter = new AddShopCarPresenter(this);
        initView();
        initData();
        /**
         * 点击购物车跳转页面
         */
        btnShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, SearchShopCarActivity.class);
                startActivity(intent);
            }
        });
        //点击加入加入购物车的点击事件
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShopCarPresenter.receive("3384", "2");
            }
        });

    }

    private void initData() {
        detailsPresenter.receive(pid);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        title = (TextView) findViewById(R.id.title);
        price = (TextView) findViewById(R.id.price);
        btnShopCar = (Button) findViewById(R.id.btnShopCar);//购物车
        btnJoin = (Button) findViewById(R.id.btnJoin);//加入购物车
    }

    @Override
    public void onSuccess(JsonDetailsBean.DataBean data) {
        String[] split = data.getImages().split("\\|");
        ImageLoader.getInstance().displayImage(split[0], img);
        title.setText(data.getTitle());
        price.setText(data.getPrice() + "");

    }

    @Override
    public void onASuccess(String msg) {
        Toast.makeText(DetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAFailed() {

    }

    @Override
    public void onFailed() {

    }
}
