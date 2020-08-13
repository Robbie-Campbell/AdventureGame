package com.company.Assets;

import com.company.GameLoop.Introduction;
import com.company.GameLoop.LevelOne;
import com.company.KeyFunctions.SleepFunction;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// A Peasant enemy object
public class EnemyAttributes {

    EnemyAttributes(int health, int noOfEnemies)
    {
        this.health = health;
        this.maxHealth = health;
        this.noOfEnemies = noOfEnemies;
    }

    private String[] bodyPart = {"arm", "leg", "neck", "belly"};
    String hitArea;
    String[] name = {"Barry", "Gary", "Larry", "Geoff", "Steve", "Carl"};
    // The random option used for all gameplay options
    Random rand = new Random();
    int XPgain = 0;

    // Determine the player for reference
    private Player player = Introduction.player1;
    private Player garth = LevelOne.garth;
    public String enemyType;
    public ArrayList<String> nameChoices = new ArrayList<>();

    // Define the attack output in the Inherited enemy types
    String[] attackType;
    int selfHarmHit = 0;

    // Set the enemy instance
    int health;
    private int maxHealth;
    int attackDamage;
    int noOfEnemies;

    private String getAllNames()
    {
        StringBuilder names = new StringBuilder();
        for (String singleName : this.nameChoices)
        {
            if (this.nameChoices.size() == 1)
            {
                return singleName;
            }
            else
            {
                names.append(singleName).append(", ");
            }
        }
        return names.toString();
    }

    private void garthHit(double damageTaken, String hitPower)
    {
        garth.health -= this.attackDamage * damageTaken;
        System.out.printf("%s also takes a %s! His new health is: %d/%d\n", garth.character, hitPower,
                garth.health, garth.maxHealth);
        SleepFunction.sleep();
    }

    // The enemy attack loop, uses random to determine hit severity
    private void attack()
    {
        for (int i = 0; i < this.noOfEnemies; i++)
        {
            int successfulHit = rand.nextInt(100);
            System.out.println(successfulHit);
            if (this.health > 0 && !player.isBlocked)
            {
                /*if (successfulHit < 10) {
                    player.health -= this.attackDamage * 2;
                    System.out.printf("%s %s!! Your new Health is %d/%d\n", this.nameChoices.get(i), this.attackType[0], player.health, player.maxHealth);
                    if (garth.exists) {
                        garthHit(1, "powerful attack");
                    }
                } else if (successfulHit < 60) {
                    player.health -= this.attackDamage;
                    System.out.printf("%s %s! Your new Health is %d/%d\n", this.nameChoices.get(i), this.attackType[1], player.health, player.maxHealth);
                    if (garth.exists) {
                        garthHit(0.5, "weak attack");
                    }
                } else if (successfulHit < 90) {
                    System.out.printf("%s %s!\n", this.nameChoices.get(i), this.attackType[2]);
                } else {*/
                    System.out.printf("%s %s!\n", this.nameChoices.get(i), this.attackType[3]);
                    this.health -= selfHarmHit;
                SleepFunction.sleep();
            }
        }
        player.isBlocked = false;
    }

    // The fight loop, this plays as long as the enemies health is above 0
    public void fight()
    {
        if (!player.isSober)
        {
            System.out.println("Oh shit, you feel too drunk to be in a fight right now!\n");
            SleepFunction.sleep();
        }
        player.enemy = true;
        while (this.health > 0)
        {
            player.setGameOver();
            System.out.printf("%s current health: %d/%d\n\n", this.getAllNames(), this.health, this.maxHealth);
            SleepFunction.sleep();
            if (enemyType.equals("Brute"))
            {
                this.hitArea = this.bodyPart[rand.nextInt(4)];

                // Redefines variable for child classes that use this selection
                this.attackType[1] = "grazes your " + this.hitArea;
                this.attackType[2] = "narrowly misses your " + this.hitArea;

            }
            System.out.println("What do you do?:");
            System.out.println("C - Check current status\nU - Use item/ attack\nR - run away.");
            Scanner choice = new Scanner(System.in);
            String action = choice.nextLine();
            switch (action) {
                case "C":
                    player.checkStatus();
                    break;
                case "U":
                    player.useItem();
                    if (this.health > 0 && player.item.equals("SW")) {
                        if (garth.exists) {
                            garth.setGameOver();
                            System.out.printf("%s tries to attack!\n", garth.character);
                            SleepFunction.sleep();
                            int successfulHit = rand.nextInt(2) + 1;
                            if (successfulHit == 1) {
                                System.out.printf("%s attacks successfully! Dealing %d damage!\n", garth.character, garth.damage);
                                this.health -= garth.damage;
                                SleepFunction.sleep();
                            } else {
                                System.out.println("Garth missed the enemy!\n");
                                SleepFunction.sleep();
                            }
                        }
                        this.health -= player.damage;
                        this.attack();
                    } else if (player.item.equals("SH")) {
                        player.Shield(this);
                        this.attack();
                    }
                    break;
                // Just a bit of fun :)
                case "R":
                    player.currentXP -= 10;
                    System.out.println("You ran away, You're a total coward and everyone thinks you're a bellend!\n");
                    SleepFunction.sleep();
                    System.out.printf("As a punishment you have lost 10XP! You should be ashamed!!! (new XP: %d / %d)\n",
                            player.currentXP, player.nextLevel);
                    SleepFunction.sleep();
                    return;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }

        // Determines if the player gets a reward after fighting the enemy, either an item or a level up
        // With further versions a full item array will be added and selected randomly, this is a placeholder.
        if (noOfEnemies == 1) {
            System.out.printf("%s has died!\n\n", this.getAllNames());
        }else{
            System.out.printf("%s have died!\n\n", this.getAllNames());
        }
        SleepFunction.sleep();
        if (garth.exists)
        {
            garth.currentXP += rand.nextInt(20) + this.XPgain;
            garth.levelUp();
            SleepFunction.sleep();
        }

        if (rand.nextInt(10) < 4)
        {
            System.out.printf("%s dropped a health potion!\n", this.getAllNames());
            player.items.put("Healing Potion (HP)", player.items.get("Healing Potion (HP)") +1);
        }

        player.enemy = false;
        player.currentXP += (rand.nextInt(20) + this.XPgain) * noOfEnemies;
        player.levelUp();
        SleepFunction.sleep();
    }
}
