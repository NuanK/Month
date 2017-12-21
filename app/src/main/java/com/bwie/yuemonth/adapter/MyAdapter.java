package com.bwie.yuemonth.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.bwie.yuemonth.R;
import com.bwie.yuemonth.bean.JsonShopCarBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;



public class MyAdapter extends BaseExpandableListAdapter {

    private Context context;//上下文
    private List<JsonShopCarBean.DataBean> groupList;//商家店铺
    private List<List<JsonShopCarBean.DataBean.ListBean>> childList;//商品

    public MyAdapter(Context context, List<JsonShopCarBean.DataBean> groupList, List<List<JsonShopCarBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    class ViewHolder {
        CheckBox checkGroup;
        TextView storeName;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        //优化
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.layout_group, null);
            holder.checkGroup = view.findViewById(R.id.checkGroup);
            holder.storeName = view.findViewById(R.id.storeName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //赋值
        holder.storeName.setText(groupList.get(i).getSellerName());
        //设置以及列表的点击状态
        holder.checkGroup.setChecked(groupList.get(i).isChecked());
        //一级列表checkBox的点击事件
        holder.checkGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断一级列表复选框的状态,设置为true或false
                groupList.get(i).setChecked(holder.checkGroup.isChecked());
                //改变二级checkBox的状态
                changeChildState(i, holder.checkGroup.isChecked());
                //算钱
                EventBus.getDefault().post(computer());
                //改变全选状态   isAllGroupCbSelect判断一级是否全部选中
                changeAllState(isAllGroupSelect());
                //必刷新
                notifyDataSetChanged();
            }
        });
        //返回视图
        return view;
    }

    class ViewHolder1 {
        CheckBox checkChild;
        ImageView img;
        TextView title;
        TextView price;
        TextView lessen;
        TextView count;
        TextView add;
        Button btnDelete;
    }

    /**
     * 子列表
     */
    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final ViewHolder1 holder1;
        final JsonShopCarBean.DataBean.ListBean listBean = childList.get(i).get(i1);
        if (view == null) {
            holder1 = new ViewHolder1();
            view = View.inflate(context, R.layout.layout_child, null);
            holder1.checkChild = view.findViewById(R.id.checkChild);
            holder1.img = view.findViewById(R.id.img);
            holder1.title = view.findViewById(R.id.title);
            holder1.price = view.findViewById(R.id.price);
            holder1.lessen = view.findViewById(R.id.lessen);
            holder1.count = view.findViewById(R.id.count);
            holder1.add = view.findViewById(R.id.add);
            holder1.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(holder1);
        } else {
            holder1 = (ViewHolder1) view.getTag();
        }
        //赋值
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0], holder1.img);
        holder1.title.setText(listBean.getTitle());
        holder1.price.setText(listBean.getPrice() + "");
        holder1.count.setText(listBean.getNum()+"");
        //设置二级列表checkbox的属性
        holder1.checkChild.setChecked(childList.get(i).get(i1).isChecked());
        //二级列表的点击事件
        holder1.checkChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置该条目中的checkbox属性值
                listBean.setChecked(holder1.checkChild.isChecked());
                //计算价钱
                PriceAndCountEvent priceAndCountEvent = computer();
                EventBus.getDefault().post(priceAndCountEvent);
                //判断当前checkbox是选中的状态
                if (holder1.checkChild.isChecked()) {
                    //如果全部选中(isAllChildCbSelected)
                    if (isAllChildCbSelected(i)) {
                        //改变一级列表的状态
                        changeGroupState(i, true);
                        //改变全选的状态
                        changeAllState(isAllGroupSelect());
                    }
                } else {
                    //如果没有全部选中,一级列表的checkbox为false不为选中
                    changeGroupState(i, false);
                    changeAllState(isAllGroupSelect());
                }
                notifyDataSetChanged();
            }
        });
        //加号
        holder1.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = listBean.getNum();
                //num为int类型所以要加空字符串
                holder1.count.setText(++num + "");
                listBean.setNum(num);
                //如果二级列表的checkbox为选中,计算价钱
                if (holder1.checkChild.isChecked()) {
                    PriceAndCountEvent priceAndCountEvent = computer();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });
        //减号
        holder1.lessen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = listBean.getNum();
                if (num == 1) {
                    return;
                }
                holder1.count.setText(--num + "");
                listBean.setNum(num);
                if (holder1.checkChild.isChecked()) {
                    PriceAndCountEvent priceAndCountEvent = computer();
                    EventBus.getDefault().post(priceAndCountEvent);
                }
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    //改变二级列表的checkBox的状态,如果一级选中,控制二级也选中
    private void changeChildState(int i, boolean flag) {
        List<JsonShopCarBean.DataBean.ListBean> listBeen = childList.get(i);
        for (int j = 0; j < listBeen.size(); j++) {
            JsonShopCarBean.DataBean.ListBean listBean = listBeen.get(j);
            listBean.setChecked(flag);
        }
    }

    //判断一级列表是否全部选中
    public boolean isAllGroupSelect() {
        for (int i = 0; i < childList.size(); i++) {
            JsonShopCarBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    //改变全选的状态
    private void changeAllState(boolean flag) {
        //导入的类
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCheckd(flag);
        EventBus.getDefault().post(messageEvent);
    }

    //改变列表的checkBox的状态
    private void changeGroupState(int i,boolean flag){
        JsonShopCarBean.DataBean dataBean = groupList.get(i);
        dataBean.setChecked(flag);
    }


    //判断二级列表是否全部选中
    private boolean isAllChildCbSelected(int i) {
        List<JsonShopCarBean.DataBean.ListBean> listBeen = childList.get(i);
        for (int j = 0; j < listBeen.size(); j++) {
            JsonShopCarBean.DataBean.ListBean listBean = listBeen.get(j);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    //设置全选,反选
    public void changeAllListCbState(boolean flag) {
        for (int i = 0; i < childList.size(); i++) {
            changeGroupState(i, flag);
            changeChildState(i, flag);
        }
        //算钱
        EventBus.getDefault().post(computer());
        notifyDataSetChanged();
    }

    //计算列表的价钱
    private PriceAndCountEvent computer() {
        int count = 0;
        int price = 0;
        int to = 0;
        for (int i = 0; i < childList.size(); i++) {
            List<JsonShopCarBean.DataBean.ListBean> listBeen = childList.get(i);
            for (int j = 0; j < listBeen.size(); j++) {
                JsonShopCarBean.DataBean.ListBean listBean = listBeen.get(j);
                if (listBean.isChecked()) {
                    price += listBean.getNum() * listBean.getPrice();
                    count += listBean.getNum();
                    to += listBean.getNum();
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        priceAndCountEvent.setTo(to);
        return priceAndCountEvent;
    }
}
