package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {

    int quantityRemaining;
    private String location;
    private String name;
    private BigDecimal price;
    private Category category;

    public Item(String item) {
        this.quantityRemaining = 5;
        String[] stuff = item.split("\\|");
        this.location = item.substring(0, 2);
        this.name = stuff[1];
        this.price = BigDecimal.valueOf(Float.valueOf(stuff[2]));
        this.category = new Category(stuff[3]);
    }

    public boolean inStock() {
        return quantityRemaining > 0;
    }

    public void reduceQuantity() {
        quantityRemaining--;
    }

    public String printable() {
        return getInfo() + " | Available Amount " + quantityRemaining;
    }

    private String getInfo() {
        return location + " | " + name + " | $" + price.setScale(2, RoundingMode.HALF_EVEN) + " | " + category.getName();
    }

    public String outOfStock() {
        return getInfo() + " | SOLD OUT";
    }


    public String getLocation() {
        return location;

    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getMessage() {
        return category.getMessage();
    }

    public String getItemNameAndLocationAndPrice() {
        return name + " " + location + " " + CurrencyHelper.makeItMoney(price);
    }

    public String getName() {
        return name;
    }

    public int getSold() {
        return 5 - quantityRemaining;
    }
}
