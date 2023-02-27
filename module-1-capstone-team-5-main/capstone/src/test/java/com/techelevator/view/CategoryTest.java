package com.techelevator.view;

import com.techelevator.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    @Test
    public void categoryReturnsMessageBasedOnNamePassedIn() {
        assertEquals("Crunch Crunch, Yum!", new Category("Chip").getMessage());
        assertEquals("Munch Munch, Yum!", new Category("Candy").getMessage());
        assertEquals("Glug Glug, Yum!", new Category("Drink").getMessage());
        assertEquals("Chew Chew, Yum!", new Category("Gum").getMessage());
    }

    @Test
    public void categoryReturnsNameUsedInConstructor() {
        assertEquals("Chip", new Category("Chip").getName());
        assertEquals("Rainbow Sparkle Kitten", new Category("Rainbow Sparkle Kitten").getName());
    }

    @Test
    public void categoryThrowsErrorWhenNonSupportedNameIsUsed() {
        try {
            new Category("Rainbow Sparkle Kittens").getMessage();
            fail();
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }
}
