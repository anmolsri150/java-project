package com.anmolvedant.javaproj.domain;

public class orderstatus1 extends orderstatus {
    public String statusid;
    public orderstatus1(){}
    public orderstatus1(String name, String status, String id, String statusid){
        super(name, status, id);
        this.statusid = statusid;
    }
}
