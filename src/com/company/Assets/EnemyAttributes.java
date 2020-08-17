package com.company.Assets;

import com.company.Levels.Introduction;
import com.company.Levels.LevelOne;
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

    // Name and specific body part hit conditions
    private String[] bodyPart = {"arm", "leg", "neck", "belly"};
    String normalAttack;
    String missAttack;
    String hitArea;
    String[] name = {"Barry", "Gary", "Larry", "Geoff", "Steve", "Carl"};

    // The random option used for all gameplay options
    Random rand = new Random();
    int XPgain = 0;

    // Determine the player for reference
    private Player player = Introduction.player1;
    private Ally garth = LevelOne.garth;
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

    // If there are more than one enemy, the names are appended into an array.
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

    // If garth is hit by the enemy his health is lowered
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

        // Condition for how many attacks should be executed, a similar method will be added for allies in a later version
        for (int i = 0; i < this.noOfEnemies; i++) {
            int successfulHit = rand.nextInt(100);

            // Makes sure the enemy is alive and that they are not presently blocking with their shield
            if (this.health > 0 && !player.isBlocked)
            {
                if (successfulHit < 10)
                {
                    player.health -= this.attackDamage * 2;
                    System.out.printf("%s %s!! Your new Health is %d/%d\n", this.nameChoices.get(i), this.attackType[0], player.health, player.maxHealth);
                    if (garth.exists)
                    {
                        garthHit(1, "powerful attack");
                    }
                }
                else if (successfulHit < 60)
                {
                    player.health -= this.attackDamage;
                    System.out.printf("%s %s! Your new Health is %d/%d\n", this.nameChoices.get(i), this.attackType[1], player.health, player.maxHealth);
                    if (garth.exists)
                    {
                        garthHit(0.5, "weak attack");
                    }
                }
                else if (successfulHit < 90)
                {
                    System.out.printf("%s %s!\n", this.nameChoices.get(i), this.attackType[2]);
                }
                else
                {

                    // If the enemy misses very poorly
                    System.out.printf("%s %s!\n", this.nameChoices.get(i), this.attackType[3]);
                    this.health -= selfHarmHit;
                    SleepFunction.sleep();
                }
            }
        }
            player.isBlocked = false;
    }

        // The fight loop, this plays as long as the enemies health is above 0
    public void fight()
    {
        // If the is drunk status effect is active
        if (!player.isSober) {
            System.out.println("Oh shit, you feel too drunk to be in a fight right now!\n");
            SleepFunction.sleep();
        }
        player.enemy = true;

        // The fight loop as long as the enemy/ enemies alive.
        while (this.health > 0) {

            // Check that the player is still alive
            player.setGameOver();
            if (garth.exists) {garth.gameOverState();}
            System.out.printf("%s current health: %d/%d\n\n", this.getAllNames(), this.health, this.maxHealth);
            SleepFunction.sleep();

            // This is very messy and I DON'T LIKE IT, but it's a band aid
            if (enemyType.equals("Brute") || (enemyType.equals("Knight"))) {
                this.hitArea = this.bodyPart[rand.nextInt(4)];

                // Redefines variable for child classes that use this selection
                this.attackType[1] = this.normalAttack + this.hitArea;
                this.attackType[2] = this.missAttack + this.hitArea;

            }
            System.out.println("What do you do?:");
            System.out.println("C - Check current status\nU - Use item/ attack\nR - run away.");
            Scanner choice = new Scanner(System.in);
            String action = choice.nextLine();

            // Player response to the enemy
            switch (action) {
                case "C":
                    player.checkStatus();
                    break;
                case "U":

                    // Checks to make sure that garth is alive and is able to attack the enemy
                    player.useItem();
                    if (this.health > 0 && player.item.equals("SW"))
                    {
                        if (garth.exists) {
                            System.out.printf("%s tries to attack!\n", garth.character);
                            SleepFunction.sleep();
                            int successfulHit = rand.nextInt(2) + 1;
                            if (successfulHit == 1)
                            {
                                System.out.printf("%s attacks successfully! Dealing %d damage!\n", garth.character, garth.damage);
                                this.health -= garth.damage;
                                SleepFunction.sleep();
                            }
                            else
                            {
                                System.out.println("Garth missed the enemy!\n");
                                SleepFunction.sleep();
                            }
                        }

                        // Result of successful attack
                        this.health -= player.damage;
                        this.attack();
                    }

                    // Shield attack response
                    else if (player.item.equals("SH"))
                    {
                        player.Shield(this);
                        this.attack();
                    }
                    break;
                // Just a bit of fun, if you run away you are punished with a loss to XP (and some verbal abuse.)
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

        // Determines if the player gets a reward after fighting the enemy/ enemies, either an item or a level up
        // With further versions a full item array will be added and selected randomly, this is a placeholder.

        // Checks if there is more than one enemy
        String pluralOrNot = noOfEnemies == 1 ? String.format("%s has died!\n\n", this.getAllNames()) :
                String.format("%s have died!\n\n", this.getAllNames());
        System.out.println(pluralOrNot);
        SleepFunction.sleep();

        // Garth reward
        if (garth.exists)
        {
            garth.currentXP += rand.nextInt(20) + this.XPgain;
            garth.levelUp();
            SleepFunction.sleep();
        }

        // Potential player reward
        if (rand.nextInt(10) < 4) {
            System.out.printf("%s dropped a health potion!\n\n", this.getAllNames());
            player.items.put("Healing Potion (HP)", player.items.get("Healing Potion (HP)") + 1);
            SleepFunction.sleep();
        }

        player.enemy = false;
        player.currentXP += (rand.nextInt(20) + this.XPgain) * noOfEnemies;
        player.levelUp();
        SleepFunction.sleep();

    }
}
