package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ItemTests {
    @Test
    public void testRestockOneItem() {
        Item item = new Duck(1, "itemTest", 5, 0);
        Assert.assertTrue(item.restock(5));
    }

    @Test
    public void testRestockTooManyItems() {
        Item item = new Duck(1, "itemTest", 5, 3);
        Assert.assertFalse(item.restock(4));
    }

    @Test
    public void testRestockWithNoItems() {
        Item item = new Duck(1, "itemTest", 5, 3);
        Assert.assertFalse(item.restock(0));
    }

    @Test
    public void testRestockWithNegativeNumber() {
        Item item = new Duck(1, "itemTest", 5, 3);
        Assert.assertFalse(item.restock(-2));
    }
}
