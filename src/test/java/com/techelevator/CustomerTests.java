package com.techelevator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class CustomerTests {
    @Test
    public void testCustomerFeedMoneyNegativeAmount() {
        Customer customer = new Customer();
        Bank bank = new Bank();
        Assert.assertFalse(customer.feedMoney(-20, bank));
    }

    @Test
    public void testCustomerFeedMoneyTwentyDollars() {
        Customer customer = new Customer();
        Bank bank = new Bank();
        customer.feedMoney(20, bank);
        Assert.assertTrue(bank.getBalance() == 2000);
    }

    @Test
    public void testCustomerFeedMoneyTenDollars() {
        Customer customer = new Customer();
        Bank bank = new Bank();
        customer.feedMoney(10, bank);
        Assert.assertTrue(bank.getBalance() == 1000);
    }

    @Test
    public void testCustomerFeedMoneyFiveDollars() {
        Customer customer = new Customer();
        Bank bank = new Bank();
        customer.feedMoney(5, bank);
        Assert.assertTrue(bank.getBalance() == 500);
    }

    @Test
    public void testCustomerFeedMoneyZeroDollars() {
        Customer customer = new Customer();
        Bank bank = new Bank();
        customer.feedMoney(0, bank);
        Assert.assertTrue(bank.getBalance() == 0);
    }
}
