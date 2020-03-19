package com.example.alpha;

public class TestScoresItem {

    String test_name, test_score;

    public TestScoresItem(){}

    public TestScoresItem(String test_name, String test_score)
    {
        this.test_name = test_name;
        this.test_score = test_score;
    }

    public String getTest_name()
    {
        return this.test_name;
    }

    public String getTest_score()
    {
        return this.test_score;
    }
}
