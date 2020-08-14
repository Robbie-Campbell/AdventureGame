package com.company.Assets;

import com.company.GameLoop.Introduction;

import java.util.Scanner;

public class Ally extends Player
{

    // Redefine Player to ally values.
    public Ally(String name, String gender)
    {
        super(name, gender);
        this.damage = 5;
        this.maxHealth = 40;
        this.health = 40;
        this.nextLevel = 100;
    }

    // Give the ability to revive an ally after their death by using a health potion.
    void gameOverState(){
        if (this.health <= 0)
        {
            System.out.printf("Oh no %s is critically wounded! Press F to use a health potion to save them!\n", this.character);
            Scanner saveAlly = new Scanner(System.in);
            String revive = saveAlly.nextLine();
            if (Introduction.player1.items.containsKey("Healing Potion (HP)"))
            {
                if (revive.equals("F"))
                {
                    Introduction.player1.items = removeItemFromHashMap("Healing Potion (HP)");
                    this.health = (int) (this.maxHealth * 0.25);
                    System.out.printf("%s has been revived back to %d/%d health\n", this.character, this.health, this.maxHealth);
                }
            }
            else
            {
                this.setGameOver();
            }
        }
    }
}
