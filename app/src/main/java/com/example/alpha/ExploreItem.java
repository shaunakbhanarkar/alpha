package com.example.alpha;

public class ExploreItem {

    String name, full_form;

    public ExploreItem(String name, String full_form)
    {
        this.name = name;
        this.full_form = full_form;
    }

    public String getName()
    {
        return this.name;
    }

    public String getFull_form()
    {
        return this.full_form;
    }
}
