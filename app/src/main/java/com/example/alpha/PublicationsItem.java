package com.example.alpha;

public class PublicationsItem {

    String title, publication;

    public PublicationsItem() {}

    public PublicationsItem(String title, String publication )
    {
        this.title = title;
        this.publication = publication;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getPublication()
    {
        return this.publication;
    }

}
