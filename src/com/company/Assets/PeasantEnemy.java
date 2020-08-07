package com.company.Assets;

import com.company.GameLoop.Introduction;
import java.util.Random;
import java.util.Scanner;

// A Peasant enemy object
public class PeasantEnemy {

    // The random option used for all gameplay options
    private Random rand = new Random();

    // Determine the player for reference
    private Player player = Introduction.player1;

    // Set the peasant instance
    private int health;
    private int attackDamage = 5;
    private String[] name = {"Barry", "Gary", "Larry", "Geoff", "Steve", "Carl"};
    public String nameChoice = name[rand.nextInt(6)];
    public PeasantEnemy(int health){
        this.health = health;
    }

    // The peasant attack loop, uses random to determine hit severity
    private void attack(){
        int successfulHit = rand.nextInt(100);
        if (this.health > 0) {
            if (successfulHit > 0 && successfulHit < 10) {
                player.health -= this.attackDamage * 2;
                System.out.printf("Peasant %s attacked for a critical!! Your new Health is %d/%d\n", this.nameChoice, player.health, player.maxHealth);
            } else if (successfulHit < 60) {
                player.health -= this.attackDamage;
                System.out.printf("Peasant %s attacked successfully! Your new Health is %d/%d\n", this.nameChoice, player.health, player.maxHealth);
            } else if (successfulHit < 90) {
                System.out.printf("Peasant %s Missed his attack!\n", this.nameChoice);
            } else {
                System.out.printf("Peasant %s Missed so badly he killed himself!\n", this.nameChoice);
                this.health = 0;
            }
        }
    }

    // The fight loop, this plays as long as the peasants health is above 0
    public void fight()
    {
        if (!player.isSober){
            System.out.println("Oh shit, you feel too drunk to be in a fight right now!\n");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        player.enemy = true;
        while (this.health > 0) {
            System.out.println("What do you do?:");
            String constantChoices = "C - Check current status\nU - Use item/ attack";
            System.out.println(constantChoices);
            System.out.println("R - run away.");
            Scanner choice = new Scanner(System.in);
            String action = choice.nextLine();
            switch (action) {
                case "C":
                    player.checkStatus();
                    break;
                case "U":
                    player.useItem();
                    if (this.health > 0 && player.item.equals("SW")) {
                        this.health -= player.damage;
                        this.attack();
                    }
                    else if (player.item.equals("SH"))
                    {
                        if (rand.nextInt(100) < 10) {
                            System.out.printf("%s Still broke your guard and dealt %d damage!!!", this.nameChoice, this.attackDamage);
                        }
                        else {
                            System.out.println("Attack successfully blocked!");
                        }
                    }
                    break;

                // Just a bit of fun :)
                case "R":
                    player.currentXP -= 10;
                    System.out.println("You ran away, You're a total coward and everyone thinks you're a bellend!\n");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("As a punishment you have lost 10XP! You should be ashamed!!! (new XP: %d / %d)\n",
                            player.currentXP, player.nextLevel);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                default: System.out.println("Please enter a valid option");
            }
        }

        // Determines if the player gets a reward after fighting the peasant, either an item or a level up
        // With further versions a full item array will be added and selected randomly, this is a placeholder.
        System.out.printf("%s has died!\n", this.nameChoice);
        if (rand.nextInt(10) < 4)
        {
            System.out.printf("Peasant %s dropped a health potion!\n", this.nameChoice);
            player.inventory.put("healing potion (HP)", player.inventory.get("healing potion (HP)") +1);
        }
        player.enemy = false;
        player.currentXP += rand.nextInt(20) + 50;
        player.levelUp();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
