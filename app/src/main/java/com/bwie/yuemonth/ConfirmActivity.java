package com.bwie.yuemonth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.yuemonth.interfaces.IConfirmPresenter;
import com.bwie.yuemonth.presenter.ConfirmPresenter;

public class ConfirmActivity extends AppCompatActivity implements IConfirmPresenter {

    private String price;
    private String uid = "3384";
    private TextView myPrice;
    private RelativeLayout order;
    private ConfirmPresenter confirmPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        initView();//初始化视图
        Intent intent = getIntent();
        price = intent.getStringExtra("price");
        myPrice.setText("实付款:¥" + price);
        confirmPresenter = new ConfirmPresenter(this);
        /**
         * 立即下单的点击事件
         */
        order.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                confirmPresenter.receive(uid,price+"");
            }
        });
    }

    //初始化视图
    private void initView() {
        myPrice = (TextView) findViewById(R.id.myPrice);
        order = (RelativeLayout) findViewById(R.id.order);
    }

    @Override
    public void onSuccess(String msg) {
        if(msg.trim()=="订单创建成功"||msg.trim().equals("订单创建成功")){
            Toast.makeText(ConfirmActivity.this,msg,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ConfirmActivity.this, MyOrderActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(ConfirmActivity.this,msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailed() {

    }
}
