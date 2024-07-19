package com.techelevator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item cat = new Cat(1,"cat",100,4);
    Item duck = new Duck(1,"duck",200,10);
    Item penguin = new Penguin(1,"penguin",300,0);
    Item pony = new Pony(1,"pony",400,0);

    @Test
    void getId() {
        Assert.assertEquals("Cat ID test","C-1",cat.getId());
        Assert.assertEquals("Duck ID test","D-1",duck.getId());
        Assert.assertEquals("Penguin ID test","Pe-1",penguin.getId());
        Assert.assertEquals("Pony ID test","Po-1",pony.getId());
    }

    @Test
    void getSound() {
        Assert.assertEquals("Cat Sound test","Purr, Purr, Meow!",cat.getSound());
        Assert.assertEquals("Duck Sound test","Quack, Quack, Splash!",duck.getSound());
        Assert.assertEquals("Penguin Sound test","Squawk, Squawk, Whee!",penguin.getSound());
        Assert.assertEquals("Pony Sound test","Neigh, Neigh, Yay!",pony.getSound());
    }

    @Test
    void getQuantity() {
        Assert.assertEquals("Cat quantity test",4,cat.getQuantity());
        Assert.assertEquals("Duck quantity test",5,duck.getQuantity());
        Assert.assertEquals("Penguin quantity test",0,penguin.getQuantity());
        Assert.assertEquals("Pony quantity test",0,pony.getQuantity());
    }
}