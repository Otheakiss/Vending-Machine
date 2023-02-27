package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

public class Inventory {

    List<Item> stock;

    public Inventory(List<Item> stock) {
        this.stock = stock;
    }

    public StringBuffer generateInventoryList() {
        StringBuffer inventoryList = new StringBuffer();
        inventoryList.append('\n');
        //TODO Even spacing for titles?
        for (Item i : stock) {
            if (i.inStock()) {
                inventoryList.append(i.printable() + "\n");
            } else {
                inventoryList.append(i.outOfStock() + "\n");
            }
        }
        return inventoryList;
    }

    public boolean hasItem(String itemLocationFromUser) {

        for (Item itemExist : stock ){
            if(itemExist.getLocation().equalsIgnoreCase(itemLocationFromUser)){
                return true;

            }

        }
        return false;

    }

    public boolean canPurchase(Item i, BigDecimal money) {

        if(i.getPrice().compareTo(money) <= 0) {
            return true;
        }
        return false;

    }

    public String dispense(Item item) {
        //Reduce quantity of inventory
        item.reduceQuantity();
        // dispense items - weird message - yum
        return item.getMessage();
    }

    public Item getItemByLocation(String itemLocationFromUser) {
        for (Item item : stock ){
            if(item.getLocation().equalsIgnoreCase(itemLocationFromUser)){
                return item;
            }
        }
        throw new RuntimeException("Item Does Not Exist!");
    }

    public List<Item> getStock() {
        return stock;
    }
}