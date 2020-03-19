package com.example.alpha;

public class LettersOfRecommendationItem {

    String recommender, designation;

    public LettersOfRecommendationItem(){}

    public LettersOfRecommendationItem(String recommender, String designation){
        this.recommender = recommender;
        this.designation = designation;
    }

    public String getRecommender(){
        return this.recommender;
    }

    public String getDesignation(){
        return this.designation;
    }

}
