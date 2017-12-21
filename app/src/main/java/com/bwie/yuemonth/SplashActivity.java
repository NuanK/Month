package com.bwie.yuemonth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView img;
    private AnimationSet animationSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        initAnimates();//初始化动画
        /**
         * 动画的监听事件
         */
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /**
                 * 结束监听的事件
                 */
                Intent intent = new Intent(SplashActivity.this, DetailsActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //初始化动画
    private void initAnimates() {
        //创建属性动画对象
        animationSet = new AnimationSet(true);
        /** 参数1：从哪个旋转角度开始
         * 参数2：转到什么角度
         * 后4个参数用于设置围绕着旋转的圆的圆心在哪里
         * 参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
         * 参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
         * 参数5：确定y轴坐标的类型
         * 参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
//        rotateAnimation.setFillAfter(true);
        animationSet.addAnimation(rotateAnimation);
        /**
         * 移动
         * 参数1～2：x轴的开始位置
         * 参数3～4：y轴的开始位置
         * 参数5～6：x轴的结束位置
         * 参数7～8：x轴的结束位置
         */
        TranslateAnimation translateAnimation =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 3f);
        translateAnimation.setDuration(3000);
//        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);

        //创建一个AnimationSet对象，参数为Boolean型，
        //true表示使用Animation的interpolator，false则是使用自己的
        //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        //设置动画执行的时间
        alphaAnimation.setDuration(3000);
//        alphaAnimation.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);

        //参数1：x轴的初始值
        //参数2：x轴收缩后的值
        //参数3：y轴的初始值
        //参数4：y轴收缩后的值
        //参数5：确定x轴坐标的类型
        //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        //参数7：确定y轴坐标的类型
        //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                2, 1f, 2, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
//        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        //使动画静止
        animationSet.setFillAfter(true);
        img.startAnimation(animationSet);
    }
}
