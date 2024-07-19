package com.techelevator;

public abstract class Item {
    private String id;
    private String name;
    private final int MAX_AMOUNT = 5;


    private int price;
    private final int initialQuantity;

    public int getInitialQuantity() {
        return initialQuantity;
    }

    private int quantity;
    private String sound;

    public Item(String id, String name, int price, int quantity, String sound) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sound = sound;
        if(quantity>MAX_AMOUNT){
            quantity = 5;
            System.out.println("The input quantity of " +name+ " should not more than " + MAX_AMOUNT);
            System.out.println("Auto set quantity to " + MAX_AMOUNT);
        }
        if(quantity<0){
            quantity = 0;
            System.out.println("The input quantity of " +name+ " should not negative value");
            System.out.println("Auto set quantity to " + MAX_AMOUNT);
        }
        this.quantity = quantity;
        this.initialQuantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getIsSoldOut() {
        if (getQuantity() == 0) {
            return true;
        }
        return false;
    }

    public String getSound() {
        return sound;
    }

    public boolean restock(int amountToRestock) {
            if (amountToRestock > 0 && amountToRestock <= (MAX_AMOUNT - quantity)) {
                quantity += amountToRestock;
                return true;
        }
        return false;
    }

}
