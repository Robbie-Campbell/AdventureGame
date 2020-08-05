package com.company.Assets;
import com.company.GameLoop.Introduction;
import com.company.Assets.Player;

import java.util.Random;
import java.util.Scanner;

public class PeasantEnemy {
    public String constantChoices = "C - Check current status\nU - Use item";
    Player player = Introduction.player1;
    public Random rand = new Random();
    public int health = 20;
    public int attackDamage = 5;
    public String[] name = {"Barry", "Gary", "Larry"};
    public String nameChoice = name[rand.nextInt(3)];
    public boolean hit = false;

    public void attack(){
        int successfulHit = rand.nextInt(100);
        System.out.println(successfulHit);
        if (successfulHit > 0 && successfulHit < 50)
        {
            player.health -= this.attackDamage;
            System.out.printf("Peasant %s attacked successfully! Your new Health is %d/%d\n",this.nameChoice, player.health, player.maxHealth);
        }
        else if (successfulHit < 90)
        {
            System.out.printf("Peasant %s Missed his attack!\n", this.nameChoice);
        }
        else{
            System.out.printf("Peasant %s Missed so badly he killed himself!\n", this.nameChoice);
            this.health = 0;
        }
    }

    public void takeDamage(){
        this.health -= player.attackDamage;
    }
    public void fight()
    {
        PeasantEnemy peasant = new PeasantEnemy();
        player.enemy = true;
        System.out.printf("Oh No, Peasant %s wants to fight you! What do you do?\n", peasant.nameChoice);
        while (this.health > 0) {
            System.out.println(constantChoices);
            System.out.println("R - run away.");
            Scanner choice = new Scanner(System.in);
            String action = choice.nextLine();
            switch (action) {
                case "C" -> {
                    player.checkStatus();
                }
                case "U" -> {
                    player.useItem();
                    this.takeDamage();
                    if (this.health > 0) {
                        this.attack();
                    }
                }
                case "R" ->{
                    System.out.println("You ran away");
                    return;
                }
            }
        }
        player.level++;
        player.attackDamage += 2;
        player.maxHealth += 5;
        System.out.printf("Congratulations! You won the fight! Your new level is %d, your new max health is %d and " +
                "your new AD is %d!\n", player.level, player.maxHealth, player.attackDamage);
    }
}
