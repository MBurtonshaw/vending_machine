package com.techelevator;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    private String info;
    private int amount;
    public Customer() {
        amount = 0;
        info = "No valid information.";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        String str = info.replaceAll("\\D", "");
        if(str.length()<10){
            str = "Invalid ph number: " + str;
        }else{
            str = String.format("(%s)-%s-%s",str.substring(0,3), str.substring(3,6), str.substring(6));
            str = "Call back to customer at: " + str;
        }
        this.info = str;
    }

    public boolean feedMoney(int amountInDollar, Bank vmBank){
        //method to deposit money into the machine
        //checking for a valid amount above 0
        if(amountInDollar<0){
            return false;
        }
        //converting the amount entered by the customer from dollars to cents
        amount += amountInDollar * 100;


        while (amountInDollar > 0) {
            //checking for $20 bills
            if (amountInDollar > 20) {
                //if found, one $20 is deposited
                vmBank.depositTwentyDollar(1);
                //and $20 is subtracted from the amount to be deposited
                amountInDollar -= 20;
                /* The following does the same for $10 bills, $5 bills, and $1 bills */
            }else if(amountInDollar > 10){
                vmBank.depositTenDollar(1);
                amountInDollar -= 10;
            } else if (amountInDollar > 5) {
                vmBank.depositFiveDollar(1);
                amountInDollar -= 5;
            }else {
                vmBank.depositOneDollar(1);
                amountInDollar -=1;
            }
        }
        return true;
    }
    public int getBalance() {
        return amount;
    }


    public boolean deductMoney(int price){
        if(amount>=price){
            amount = amount-price;
            return true;
        }
        return false;
    }

}
