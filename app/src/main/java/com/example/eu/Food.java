package com.example.eu;

/**
 * This is the part of food details code
 * Fetching name and price from firebase to match this with the namne or price that user giving
 */
public class Food
{
    private String name;
    private String price;

    public Food()
    {

    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }



    public void setName(String name) {
        this.name = name;
    }



    public void setPrice(String price) {
        this.price = price;
    }



    public Food(String name, String price)
    {
        if (name.trim().equals(""))
        {
            name = "Food NoName";
        }
        this.name = name;
        this.price = price;
    }



}