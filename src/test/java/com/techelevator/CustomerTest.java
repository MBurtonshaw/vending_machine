package com.techelevator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
    private Customer customer = new Customer();
    private Bank bank = new Bank();

    @BeforeEach
    void resetCustomer(){
        customer = new Customer();
    }


    @Test
    void getSetInfo() {
        //Arrange
        String defaultOutput;
        String validCustomerInput = "1234567890";
        String invalidCustomerInput = "123";
        //Act
        defaultOutput = customer.getInfo();
        customer.setInfo(validCustomerInput);
        String valid = customer.getInfo();
        customer.setInfo(invalidCustomerInput);
        String invalid = customer.getInfo();

        //Assert
        Assert.assertEquals("default test: ", "No valid information." , defaultOutput);
        Assert.assertEquals("valid test: ", "Call back to customer at: (123)-456-7890" , valid);
        Assert.assertEquals("invalid test: ", "Invalid ph number: " + invalidCustomerInput , invalid);
    }

    @Test
    void feedMoney() {
        boolean result1 = customer.feedMoney(36,bank);
        Assert.assertEquals("Feed 36$ test bank: ", 3600, bank.getBalance());
        Assert.assertTrue("Feed 36$ test return value: ", result1);

        boolean result2 = customer.feedMoney(0,bank);
        Assert.assertEquals("Feed 0$ test bank: ", 3600, bank.getBalance());
        Assert.assertTrue("Feed 0$ test return value: ", result2);

        boolean result3 = customer.feedMoney(-1,bank);
        Assert.assertEquals("Feed -1$ test bank: ", 3600, bank.getBalance());
        Assert.assertFalse("Feed -1$ test return value: ", result3);
    }

    @Test
    void getBalance() {
        customer.feedMoney(100,bank);
        Assert.assertEquals("Get 100$ balance: ", 10000, customer.getBalance());
    }

    @Test
    void deductMoney() {
        boolean result1 = customer.deductMoney(100);
        customer.feedMoney(10,bank);
        boolean result2 = customer.deductMoney(1000);
        customer.feedMoney(1,bank);
        boolean result3 = customer.deductMoney(1000000000);


        Assert.assertFalse("No money: ", result1);
        Assert.assertTrue("OK enough money: ", result2);
        Assert.assertFalse("You are not a millionaire : ", result3);
    }
}