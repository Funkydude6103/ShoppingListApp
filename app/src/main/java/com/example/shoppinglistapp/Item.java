package com.example.shoppinglistapp;

public class Item
{
    String name;
    String quantity;
    String price;
    public final static String NAME="name";
    public final static String QUANTITY="quantity";
    public final static String PRICE="price";

    public Item(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Item()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
