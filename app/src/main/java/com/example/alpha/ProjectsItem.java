package com.example.alpha;

public class ProjectsItem {

    String title, description;

    public ProjectsItem() {}

    public ProjectsItem(String title, String description) {

        this.title = title;
        this.description = description;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getDescription()
    {
        return this.description;
    }


}
