package com.anmolvedant.javaproj.domain;

public class item {
    public String category;
    public String desc;
    public String name;
    public int price;
//    public String id;

//    public item() {}
//    public item(String category, String desc, String name, int price)
//    {
//        this.category = category;
//        this.desc = desc;
//        this.name = name;
//        this.price = price;
//    }
    public void setCategory(String category)
    {
        this.category = category;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
//    public void setId(String id)
//    {
//        this.id = id;
//    }
    public String getName()
    {
        return this.name;
    }
    public String getDesc()
    {
        return this.desc;
    }
    public String getCategory()
    {
        return this.category;
    }
    public int getPrice()
    {
        return this.price;
    }
//    public String getId()
//    {
//        return this.id;
//    }
}