package com.techelevator;

public class Pony extends Item {
    public Pony(int id, String name, int price, int quantity) {
        super("Po-" + id, name, price, quantity, "Neigh, Neigh, Yay!");
    }
    public Pony(String id, String name, int price, int quantity) {
        super(id, name, price, quantity, "Neigh, Neigh, Yay!");
    }
}
