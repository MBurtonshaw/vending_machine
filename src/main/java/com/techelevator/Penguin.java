package com.techelevator;

public class Penguin extends Item {
    public Penguin(int id, String name, int price, int quantity) {
        super("Pe-" + id, name, price, quantity, "Squawk, Squawk, Whee!");
    }
    public Penguin(String id, String name, int price, int quantity) {
        super(id, name, price, quantity, "Squawk, Squawk, Whee!");
    }
}
