package com.techelevator.view;

import com.techelevator.Item;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test

    public void getItemNameAndLocationAndPriceProvidesAStringBasedOnTheItem() {
        String input = "A1|Potato Crisps|3.05|Chip";
        Item i = new Item(input);
        assertEquals("Potato Crisps A1 $3.05", i.getItemNameAndLocationAndPrice());
    }

}
