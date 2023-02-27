package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.Item;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryTest {
    @Test
    public void inventoryConstructorTakesAList() {
        new Inventory(new ArrayList<>());
    }

    @Test
    public void ifInventoryHasItemThenHasItemReturnsTrue() {
        Inventory inventory = setupInventoryWithCrisps();

        assertTrue(inventory.hasItem("A1"));
        assertFalse(inventory.hasItem("A2"));
    }

    @Test
    public void generatesInventoryListFromExistingStock() {
        Inventory inventory = setupInventoryWithCrisps();

        assertEquals("\nA1 | Potato Crisps | $3.05 | Chip | Available Amount 5\n", inventory.generateInventoryList().toString());
    }

    @Test
    public void generatesInventoryListFromOutOfStock() {
        Inventory inventory = setupInventoryWithCrisps();
        Item e = inventory.getItemByLocation("A1");
        assertEquals("Crunch Crunch, Yum!", inventory.dispense(e));
        inventory.dispense(e);
        inventory.dispense(e);
        inventory.dispense(e);
        inventory.dispense(e);
        assertEquals("\nA1 | Potato Crisps | $3.05 | Chip | SOLD OUT\n", inventory.generateInventoryList().toString());
    }

    private Inventory setupInventoryWithCrisps() {
        Item findable = new Item("A1|Potato Crisps|3.05|Chip");
        List<Item> list = List.of(findable);
        Inventory inventory = new Inventory(list);
        return inventory;
    }

    @Test
    public void ifYouCanAffordItYouCanBuyAnItem() {
        Inventory inventory = setupInventoryWithCrisps();
        Item canPurchase = inventory.getItemByLocation("A1");
        Item cannotPurchase = new Item("A3|Premium Grain Waves|10.01|Chip");
        assertTrue(inventory.canPurchase(canPurchase, BigDecimal.TEN));
        assertFalse(inventory.canPurchase(canPurchase, BigDecimal.valueOf(3.04f)));
        assertFalse(inventory.canPurchase(canPurchase, BigDecimal.ZERO));
        assertFalse(inventory.canPurchase(cannotPurchase, BigDecimal.TEN));

    }

    @Test
    public void throwsExceptionIfItemDoesNotExist() {
        Inventory i = setupInventoryWithCrisps();
        try {
            i.getItemByLocation("A2");
            fail();
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals("Item Does Not Exist!", e.getMessage());
        }
    }

}
