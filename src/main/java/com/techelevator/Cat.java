package com.techelevator;

public class Cat extends Item {
    public Cat(int id, String name, int price, int quantity) {
        super("C-" + id, name, price, quantity, "Purr, Purr, Meow!");
    }
    public Cat(String id, String name, int price, int quantity) {
        super(id, name, price, quantity, "Purr, Purr, Meow!");
    }
}
