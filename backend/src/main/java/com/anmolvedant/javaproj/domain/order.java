package com.anmolvedant.javaproj.domain;

public class order {
    public String[] items;
    public String name;
    public long phone;
//    public order(){}
//    public order(String[] items, String name, int phone)
//    {
//        this.items = items;
//        this.name = name;
//        this.phone = phone;
//    }
    public void setItems(String s)
    {
        this.items = s.split(" ");
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPhone(long phone)
    {
        this.phone = phone;
    }
    public long getPhone()
    {
        return phone;
    }
    public String getItems()
    {
        return String.join(" ", items);
    }
    public String getName()
    {
        return name;
    }
}
