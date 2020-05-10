package com.anmolvedant.javaproj.domain;

import java.util.Date;

public class orderdb extends order {
    private double bill;
    public Date time;
    private int orderid;
//    public orderdb(){super();}
//    public orderdb(order o, double bill, Timestamp time, int orderid){
//        super(o.items,o.name,o.phone);
//        this.bill = bill;
//        this.time = time;
//        this.orderid = orderid;
//    }
    public void setBill(Double bill)
    {
        this.bill = bill;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }
    public void setOrderid(int b)
    {
        this.orderid = b;
    }
    public Date getTime()
    {
        return this.time;
    }
    public double getBill()
    {
        return this.bill;
    }
    public int getOrderid()
    {
        return this.orderid;
    }
}
