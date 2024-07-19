package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineTests {
    @Test
    public void testPurchasePass() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 10);
        Item item4 = new Cat(4, "name4", 40, 10);
        Customer customer = new Customer();
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        bank.depositTwentyDollar(1000);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        vendingMachine.feedMoney(100);
        String testString = "Dispensing... name3: $0.30, your remaining balance is $$99.70\n" +
                "Squawk, Squawk, Whee!";
		Assert.assertEquals(vendingMachine.purchase("3"), testString);
        System.out.println("All good");
    }

    @Test
    public void testPurchaseUnavailableItem() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 10);
        Item item4 = new Cat(4, "name4", 40, 10);
        Customer customer = new Customer();
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        bank.depositTwentyDollar(1000);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        vendingMachine.feedMoney(100);
        String testString = "!!! ---Please enter a valid number--- !!!";
		Assert.assertEquals(vendingMachine.purchase("5"), testString);
    }

    @Test
    public void testPurchaseSoldOut() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 0);
        Item item4 = new Cat(4, "name4", 40, 10);
        Customer customer = new Customer();
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        bank.depositTwentyDollar(1000);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        vendingMachine.feedMoney(100);
        String testString = "Sorry, name3 is sold out";
		Assert.assertEquals(vendingMachine.purchase("3"), testString);
    }

    @Test
    public void testPurchaseInsufficientFunds() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 10);
        Item item4 = new Cat(4, "name4", 40, 10);
        Customer customer = new Customer();
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        String testString = "Sorry, you don't have enough money, your current balance is $0.00\n" +
                "Please top-up more.";
        Assert.assertEquals(vendingMachine.purchase("3"), testString);
    }

    @Test
    public void testShowProducts() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 10);
        Item item4 = new Cat(4, "name4", 40, 10);
        Customer customer = new Customer();
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        String testString = "1) name1, $0.10\n" +
                "2) name2, $0.20\n" +
                "3) name3, $0.30\n" +
                "4) name4, $0.40\n";
        Assert.assertEquals(vendingMachine.showProducts(), testString);
    }

    @Test
    public void finishTransactionTestNoMoney() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 10);
        Item item4 = new Cat(4, "name4", 40, 10);
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        Customer customer = new Customer();
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        String testString = "Thank you for using CuteCo.\n" +
                "Your current amount is {$0 and 0 cents}.\n" +
                "Dispensing:\n";
        Assert.assertEquals(vendingMachine.finishTransaction(), testString);
    }

    @Test
    public void finishTransactionTestWithOneDollar() {
        Map<String, Item> newMap = new HashMap<>();
        Bank bank = new Bank();
        Customer customer = new Customer();
        bank.depositOneDollar(1);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        vendingMachine.feedMoney(1);
        String testString = "Thank you for using CuteCo.\n" +
                "Your current amount is {$1 and 0 cents}.\n" +
                "Dispensing:\n" + "$1	: 1\n";
        Assert.assertEquals(vendingMachine.finishTransaction(), testString);
    }

    @Test
    public void showCustomerBalanceTest() {
        Map<String, Item> newMap = new HashMap<>();
        Bank bank = new Bank();
        Customer customer = new Customer();
        bank.depositTwentyDollar(1);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        Assert.assertEquals(vendingMachine.showCustomerBalance(), "$0.00");
    }

    @Test
    public void testShowItemPrice() {
        Map<String, Item> newMap = new HashMap<>();
        Item item1 = new Duck(1, "name1", 10, 10);
        Item item2 = new Pony(2, "name2", 20, 10);
        Item item3 = new Penguin(3, "name3", 30, 10);
        Item item4 = new Cat(4, "name4", 40, 10);
        newMap.put("1", item1);
        newMap.put("2", item2);
        newMap.put("3", item3);
        newMap.put("4", item4);
        Bank bank = new Bank();
        Customer customer = new Customer();
        bank.depositTwentyDollar(1);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        Assert.assertEquals(vendingMachine.showItemPrice(item2), "$0.20");
    }

    @Test
    public void testWriteBill() {
        Map<String, Item> newMap = new HashMap<>();
        Bank bank = new Bank();
        Customer customer = new Customer();
        bank.depositTwentyDollar(1);
        VendingMachine vendingMachine = new VendingMachine(bank, newMap);
        Map<String, Integer> map = new HashMap<>();
        map.put("$20", 1);
        Assert.assertEquals(vendingMachine.writeBill(map), "Dispensing:\n" + "$20\t: 1\n");
    }

}

