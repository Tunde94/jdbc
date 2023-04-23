package org.example.model;

import java.sql.Date; // java.util.Date nu functioneaza la fel

public class Food {
    private Integer id;
    private String name;
    private String description;
    private Integer calories_per_100;
    private Date expiration_date;

    public Food() {

    }

    public Food(Integer id, String name, String description, Integer calories_per_100, Date expiration_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories_per_100 = calories_per_100;
        this.expiration_date = expiration_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCalories_per_100() {
        return calories_per_100;
    }

    public void setCalories_per_100(Integer calories_per_100) {
        this.calories_per_100 = calories_per_100;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", calories_per_100=" + calories_per_100 +
                ", expiration_date=" + expiration_date +
                '}';
    }
}
