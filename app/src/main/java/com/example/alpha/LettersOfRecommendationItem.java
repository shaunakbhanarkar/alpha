package com.example.alpha;

public class LettersOfRecommendationItem {

    String name, recommender, designation;

    public LettersOfRecommendationItem(String name, String recommender, String designation){
        this.name = name;
        this.recommender = recommender;
        this.designation = designation;
    }

    public String getName(){
        return this.name;
    }

    public String getRecommender(){
        return this.recommender;
    }

    public String getDesignation(){
        return this.designation;
    }

}
