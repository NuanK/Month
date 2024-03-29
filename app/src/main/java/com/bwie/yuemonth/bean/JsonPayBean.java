package com.bwie.yuemonth.bean;

import java.util.List;


public class JsonPayBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-12-20T20:48:09","orderid":4345,"price":2392,"status":0,"title":"订单测试标题3384","uid":3384},{"createtime":"2017-12-20T20:48:10","orderid":4347,"price":2392,"status":0,"title":"订单测试标题3384","uid":3384},{"createtime":"2017-12-20T20:48:34","orderid":4348,"price":118,"status":0,"title":"订单测试标题3384","uid":3384},{"createtime":"2017-12-20T20:48:58","orderid":4350,"price":2392,"status":0,"title":"订单测试标题3384","uid":3384},{"createtime":"2017-12-20T20:54:48","orderid":4367,"price":2392,"status":0,"title":"订单测试标题3384","uid":3384}]
     * page : 2
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-12-20T20:48:09
         * orderid : 4345
         * price : 2392.0
         * status : 0
         * title : 订单测试标题3384
         * uid : 3384
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
