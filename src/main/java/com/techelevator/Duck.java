package com.techelevator;

public class Duck extends Item {
    public Duck(int id, String name, int price, int quantity) {
        super("D-" + id, name, price, quantity, "Quack, Quack, Splash!");
    }

    public Duck(String id, String name, int price, int quantity) {
        super(id, name, price, quantity, "Quack, Quack, Splash!");
    }
}

