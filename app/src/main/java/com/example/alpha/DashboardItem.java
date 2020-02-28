package com.example.alpha;

public class DashboardItem {

    int step;
    String name;

    public DashboardItem(int step, String name){
        this.step = step;
        this.name = name;
    }

    public int getStep(){
        return this.step;
    }

    public String getName(){
        return this.name;
    }
}
