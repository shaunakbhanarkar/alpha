package com.example.alpha;

public class WorkExperienceItem {

    String company, role, experience_duration;

    public WorkExperienceItem(String company, String role, String experience_duration)
    {
        this.company = company;
        this.role = role;
        this.experience_duration = experience_duration;
    }

    public WorkExperienceItem(){}

    public String getCompany()
    {
        return this.company;
    }

    public String getRole()
    {
        return this.role;
    }


    public String getExperience_duration()
    {
        return this.experience_duration;
    }
}
