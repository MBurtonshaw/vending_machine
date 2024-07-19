package com.techelevator;

public class Bank {
    private int numberOfOneCent;
    private int numberOfTenCent;
    private int numberOfFiveCent;
    private int numberOfQuarterCent;
    private int numberOfOneDollar;
    private int numberOfFiveDollar;
    private int numberOfTenDollar;
    private int numberOfTwentyDollar;

    public int getBalance(){
        return numberOfOneCent * Money.ONE_CENT +
                numberOfFiveCent * Money.FIVE_CENT +
                numberOfTenCent * Money.TEN_CENT +
                numberOfQuarterCent * Money.QUARTER_CENT +
                numberOfOneDollar * Money.ONE_DOLLAR +
                numberOfFiveDollar * Money.FIVE_DOLLAR +
                numberOfTenDollar * Money.TEN_DOLLAR +
                numberOfTwentyDollar * Money.TWENTY_DOLLAR;

    }
    

    public Bank() {
    }

    public boolean deposit(int numberOfOneCent, int numberOfTenCent, int numberOfFiveCent, int numberOfQuarterCent, int numberOfOneDollar, int numberOfFiveDollar, int numberOfTenDollar, int numberOfTwentyDollar) {
        if(numberOfOneCent >0 && numberOfTenCent >0 && numberOfFiveCent >0 && numberOfQuarterCent >0 && numberOfOneDollar >0 && numberOfFiveDollar >0 && numberOfTenDollar >0 && numberOfTwentyDollar>0){
            //method to handle money being deposited
            this.numberOfOneCent += numberOfOneCent;
            this.numberOfTenCent += numberOfTenCent;
            this.numberOfFiveCent += numberOfFiveCent;
            this.numberOfQuarterCent += numberOfQuarterCent;
            this.numberOfOneDollar += numberOfOneDollar;
            this.numberOfFiveDollar += numberOfFiveDollar;
            this.numberOfTenDollar += numberOfTenDollar;
            this.numberOfTwentyDollar += numberOfTwentyDollar;
            return true;
        }
        return false;
    }

    public boolean dispense(int numberOfOneCent, int numberOfTenCent, int numberOfFiveCent, int numberOfQuarterCent, int numberOfOneDollar, int numberOfFiveDollar, int numberOfTenDollar, int numberOfTwentyDollar) {
        if(numberOfOneCent >0 && numberOfTenCent >0 && numberOfFiveCent >0 && numberOfQuarterCent >0 && numberOfOneDollar >0 && numberOfFiveDollar >0 && numberOfTenDollar >0 && numberOfTwentyDollar>0){
            //method to handle money being deducted
            this.numberOfOneCent -= numberOfOneCent;
            this.numberOfTenCent -= numberOfTenCent;
            this.numberOfFiveCent -= numberOfFiveCent;
            this.numberOfQuarterCent -= numberOfQuarterCent;
            this.numberOfOneDollar -= numberOfOneDollar;
            this.numberOfFiveDollar -= numberOfFiveDollar;
            this.numberOfTenDollar -= numberOfTenDollar;
            this.numberOfTwentyDollar -= numberOfTwentyDollar;
            return true;
        }
        return false;
    }

    /*  The following are methods to deposit various specific amounts of currency */
    public  boolean  depositOneCent(int numberOfOneCent) {
        if(numberOfOneCent <= 0){
            return false;
        }
        this.numberOfOneCent += numberOfOneCent;
        return true;
    }

    public  boolean  depositTenCent(int numberOfTenCent) {
        if(numberOfTenCent <= 0){
            return false;
        }
        this.numberOfTenCent += numberOfTenCent;
        return true;
    }

    public  boolean  depositFiveCent(int numberOfFiveCent) {
        if(numberOfFiveCent <= 0){
            return false;
        }
        this.numberOfFiveCent += numberOfFiveCent;
        return true;
    }

    public  boolean  depositQuarterCent(int numberOfQuarterCent) {
        if(numberOfQuarterCent <= 0){
            return false;
        }
        this.numberOfQuarterCent += numberOfQuarterCent;
        return true;
    }

    public  boolean  depositOneDollar(int numberOfOneDollar) {
        if(numberOfOneDollar <= 0){
            return false;
        }
        this.numberOfOneDollar += numberOfOneDollar;
        return true;
    }

    public  boolean  depositFiveDollar(int numberOfFiveDollar) {
        if(numberOfFiveDollar <= 0){
            return false;
        }
        this.numberOfFiveDollar += numberOfFiveDollar;
        return true;
    }

    public  boolean  depositTenDollar(int numberOfTenDollar) {
        if(numberOfTenDollar <= 0){
            return false;
        }
        this.numberOfTenDollar += numberOfTenDollar;
        return true;
    }

    public  boolean  depositTwentyDollar(int numberOfTwentyDollar) {
        if(numberOfTwentyDollar <= 0){
            return false;
        }
        this.numberOfTwentyDollar += numberOfTwentyDollar;
        return true;
    }

    /*  The following are methods to dispense various specific amounts of currency back to the user */

    public  boolean  dispenseOneCent(int numberOfOneCent) {
        if(this.numberOfOneCent > numberOfOneCent && numberOfOneCent>0){
            this.numberOfOneCent -= numberOfOneCent;
            return true;
        }
        return false;
    }

    public boolean  dispenseTenCent(int numberOfTenCent) {
        if(this.numberOfTenCent > numberOfTenCent && numberOfTenCent>0){
            this.numberOfTenCent -= numberOfTenCent;
            return true;
        }
        return false;
    }

    public  boolean  dispenseFiveCent(int numberOfFiveCent) {
        if(this.numberOfFiveCent > numberOfFiveCent && numberOfFiveCent>0){
            this.numberOfFiveCent -= numberOfFiveCent;
            return true;
        }
        return false;
    }

    public  boolean  dispenseQuarterCent(int numberOfQuarterCent) {
        if(this.numberOfQuarterCent > numberOfQuarterCent && numberOfQuarterCent>0){
            this.numberOfQuarterCent -= numberOfQuarterCent;
            return true;
        }
        return false;
    }

    public  boolean  dispenseOneDollar(int numberOfOneDollar) {
        if(this.numberOfOneDollar >numberOfOneDollar && numberOfOneDollar>0){
            this.numberOfOneDollar -= numberOfOneDollar;
            return true;
        }
        return false;
    }

    public  boolean  dispenseFiveDollar(int numberOfFiveDollar) {
        if(this.numberOfFiveDollar > numberOfFiveDollar && numberOfFiveDollar>0){
            this.numberOfFiveDollar -= numberOfFiveDollar;
            return true;
        }
        return false;
    }

    public  boolean  dispenseTenDollar(int numberOfTenDollar) {
        if(this.numberOfTenDollar> numberOfTenDollar && numberOfTenDollar>0){
            this.numberOfTenDollar -= numberOfTenDollar;
            return true;
        }
        return false;
    }

    public  boolean  dispenseTwentyDollar(int numberOfTwentyDollar) {
        if(this.numberOfTwentyDollar > numberOfTwentyDollar && numberOfTwentyDollar>0){
            this.numberOfTwentyDollar -= numberOfTwentyDollar;
            return true;
        }
        return false;
    }

}
