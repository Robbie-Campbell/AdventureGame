package com.company.Assets;

import com.company.GameLoop.Introduction;
import java.util.Random;
import java.util.Scanner;

// A Peasant enemy object
public class PeasantEnemy {

    // The random option used for all gameplay options
    public Random rand = new Random();
    public String constantChoices = "C - Check current status\nU - Use item";

    // Determine the player for reference
    Player player = Introduction.player1;

    // Set the peasant instance
    public int health;
    public int attackDamage = 5;
    String[] name = {"Barry", "Gary", "Larry", "Geoff", "Steve", "Carl"};
    public String nameChoice = name[rand.nextInt(6)];
    public boolean hit = false;
    public PeasantEnemy(int health){
        this.health = health;
    }

    // The peasant attack loop, uses random to determine hit severity
    public void attack(){
        int successfulHit = rand.nextInt(100);
        if (successfulHit > 0 && successfulHit < 10)
        {
            player.health -= this.attackDamage * 2;
            System.out.printf("Peasant %s attacked for a critical!! Your new Health is %d/%d\n",this.nameChoice, player.health, player.maxHealth);
        }
        else if(successfulHit < 60){
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

    // The fight loop, this plays as long as the peasants health is above 0
    public void fight()
    {
        player.enemy = true;
        while (this.health > 0) {
            System.out.println("What do you do?:");
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
                    if (this.health > 0 && player.item.equals("SW")) {
                        this.health -= player.damage;
                        this.attack();
                    }
                    else if (player.item.equals("SH"))
                    {
                        if (rand.nextInt(100) < 10) {
                            System.out.printf("%s Still broke your guard and dealt %d damage!!!", this.nameChoice, this.attackDamage);
                        }
                        else{
                            System.out.println("Attack successfully blocked!");
                        }
                    }
                }

                // Just a bit of fun :)
                case "R" ->{
                    System.out.println("You ran away, You're a total coward and everyone thinks you're a bellend!\n");
                    return;
                }
            }
        }

        // Determines if the player gets a reward after fighting the peasant, either an item or a level up
        // With further versions a full item array will be added and selected randomly, this is a placeholder.
        if (rand.nextInt(10) < 4)
        {
            System.out.printf("Peasant %s dropped a health potion!\n", this.nameChoice);
            player.inventory.put("healing potion (HP)", player.inventory.get("healing potion (HP)") +1);
        }
        player.enemy = false;
        player.currentXP += rand.nextInt(20) + 50;
        player.levelUp();
    }
}
