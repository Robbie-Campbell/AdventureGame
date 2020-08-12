package com.company.Assets;

public class Ally extends Player
{
    public Ally(String name, String gender)
    {
        super(name, gender);
        this.damage = 5;
        this.maxHealth = 40;
        this.health = 40;
        this.nextLevel = 100;
    }
}
