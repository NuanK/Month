package com.bwie.yuemonth.adapter;



public class PriceAndCountEvent {
    private int price;
    private int count;
    private int to;

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getTo(){
        return  to;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
