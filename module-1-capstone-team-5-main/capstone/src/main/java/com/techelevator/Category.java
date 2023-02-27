package com.techelevator;

public class Category {
    String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        switch (name) {
            case "Chip":
                return "Crunch Crunch, Yum!";
            case "Candy":
                return "Munch Munch, Yum!";
            case "Drink":
                return "Glug Glug, Yum!";
            case "Gum":
                return "Chew Chew, Yum!";
            default:
                throw new RuntimeException("Unsupported Snack Type");
        }
    }
}
