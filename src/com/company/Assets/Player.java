package com.company.Assets;


import com.company.KeyFunctions.InventoryDisplay;
import com.company.KeyFunctions.SleepFunction;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Stores all player information
public class Player{

    // Decided if enemy can attack (regarding shield)
    boolean isBlocked = false;

    // The random option used for all gameplay options
    private Random rand = new Random();

    // The level up variables
    int currentXP = 0;
    int nextLevel = 50;
    private int level = 1;

    // Character traits
    public int maxHealth = 100;
    boolean isSober = true;
    int damage;
    public String genderChildStatus;
    public String genderSiblingStatus;
    public int health = 100;
    public String character;
    private int attackDamage = 10;

    public HashMap<String, Integer> inventory = new HashMap<>();

    // Sets which item is in use
    public String item = "";

    // Conditional statements
    boolean enemy = false;
    private boolean undecided = true;
    public Player(String name, String gender) {
        this.character = name;
        this.inventory.put("sword (SW)", 1);
        this.inventory.put("healing potion (HP)", 1);
        this.inventory.put("shield (SH)", 1 );

        // Set gender status
        switch(gender){
            case "M":
                this.genderChildStatus = "boy";
                this.genderSiblingStatus = "brother";
                break;
            case "F":
                this.genderChildStatus = "girl";
                this.genderSiblingStatus = "sister";
                break;
            default:
                this.genderChildStatus = "person";
                this.genderSiblingStatus = "sibling";
        }
    }

    // The function which levels up the player character
    void levelUp(){
        if (currentXP >= nextLevel) {
            this.level++;
            this.attackDamage += 2;
            this.maxHealth += 5;
            this.health = maxHealth;
            System.out.printf("Congratulations! You leveled up! Your new level is %d, your new max health is %d and " +
                    "your new AD is %d!\n", this.level, this.maxHealth, this.attackDamage);
            this.currentXP  -= this.nextLevel;
            this.nextLevel *= 1.4;
            System.out.printf("Current XP : %dXP\n", this.currentXP);
            System.out.printf("Next Level XP needed : %dXP\n", this.nextLevel - this.currentXP);
        }
    }

    // Allows to player to check their current stats
    public void checkStatus(){
        undecided = true;
        System.out.println("Check status of: H - health, L - level, AD - attack damage, I - inventory, (E - exit)");
        while (undecided) {
            Scanner check = new Scanner(System.in);
            String checkType = check.nextLine();
            switch (checkType) {
                case "H":System.out.printf("Current health: %d/%d\n", this.health, this.maxHealth); undecided = false; break;
                case "L":System.out.printf("Current level: %d\n", this.level); undecided = false; break;
                case "AD":System.out.printf("Current attack damage: %d\n", this.attackDamage);  undecided = false; break;
                case "I":System.out.printf("Current inventory: %s\n", InventoryDisplay.inventoryToString(this.inventory, "and"));
                undecided = false; break;
                case "E": System.out.println("You leave the check status menu.");undecided = false; break;
                default:System.out.println("Please enter a valid value\n");
            }
        }
    }

    // Attack any enemies, probabilities determine the likelihood of successful and critical attacks
    private int SwordAttack() {
        int hitChance;
        if (isSober) {
            hitChance = rand.nextInt(100);
        }
        else{
            hitChance = rand.nextInt(80) + 20;
        }
        if (enemy) {
            int crit = 0;
            if (hitChance <= 10) {
                crit = this.attackDamage * 2;
                System.out.printf("You critically hit the enemy!! dealing %d damage!\n", this.attackDamage * 2);
            } else if (hitChance < 80) {
                crit = this.attackDamage;
                System.out.printf("You hit the enemy for %d damage!\n", this.attackDamage);
            }else if (hitChance > 90 && !isSober){
                this.health -= 30;
                System.out.println("Ouch! You're so drunk that you stabbed yourself in the face! -30 health!");
            }
            else {
                System.out.println("You missed the enemy!\n");
            }
            return crit;
        } else {
            System.out.println("You swing your sword aimlessly at the air.");
            return 0;
        }
    }

    // Attack any enemies, probabilities determine the likelihood of successful and critical attacks
    void Shield(EnemyAttributes enemyInstance) {
        int blockChance;
        isBlocked = true;
        if (isSober) {
            blockChance = rand.nextInt(100);
        }
        else{
            blockChance = rand.nextInt(90) + 10;
        }
        if (enemy) {
            if (blockChance <= 20) {
                enemyInstance.attackDamage -= (int) Math.round(enemyInstance.attackDamage * 0.2);
                System.out.printf("You parried the enemies attack!! their new AD is %d!\n", enemyInstance.attackDamage );
            } else if (blockChance < 70) {
                System.out.println("You blocked the enemies attack!\n");
            } else if (blockChance > 90 && !isSober)
            {
                this.health -= enemyInstance.attackDamage * 2;
                System.out.printf("%s smashes through your drunken defence for a critical!! Dealing %d damage!\n",
                        enemyInstance.nameChoice, enemyInstance.attackDamage * 2);
            }
            else {
                this.health -= enemyInstance.attackDamage;
                System.out.printf("%s broke through your defence!! Dealing %d damage!\n", enemyInstance.nameChoice,
                        enemyInstance.attackDamage);
            }
        } else {
            System.out.println("You swing your sword aimlessly at the air.");
        }
    }

    private HashMap<String, Integer> removeItemFromInventory(String itemForRemove)
    {
        this.inventory.put(itemForRemove, this.inventory.get(itemForRemove) -1);
        if (this.inventory.get(itemForRemove) == 0)
        {
            this.inventory.remove(itemForRemove);
        }
        return this.inventory;
    }

    // The responses for the players using their items
    public void useItem()
    {
        // Display inventory available items
        undecided = true;
        System.out.println("You can use: " + InventoryDisplay.inventoryToString(this.inventory, "or") + " (or exit 'E')");
        Scanner useAnItem = new Scanner(System.in);
        String choice = useAnItem.nextLine();

        // Decision loop
        while (undecided){
            this.item = "";
            switch (choice) {

                // Set the damage for reference to enemy attacks
                case "SW":
                    this.item = "SW";
                    this.damage = this.SwordAttack();
                    undecided = false;
                    break;
                case "HP":
                    // Health potion options
                    if(this.inventory.containsKey("healing potion (HP)")) {
                        if (this.health == this.maxHealth)
                        {
                            System.out.println("You are already at maximum health.");
                            undecided = false;
                            break;
                        }
                        if (this.health + 10 < this.maxHealth) {
                            this.health += 10;
                        }
                        else{
                            this.health = this.maxHealth;
                        }
                        System.out.printf("Your health has is now %d/%d\n", this.health, this.maxHealth);
                        undecided = false;

                        // Remove one health potion from the hashmap or remove from inventory entirely
                        this.inventory = removeItemFromInventory("healing potion (HP)");
                        break;
                    }
                    else{
                        System.out.println("No health potion in inventory");
                        undecided = false;
                        break;
                    }
                case "SH":
                    // Block an enemy attack... haven't really decided what to do here yet
                    if (enemy) {
                        this.item = "SH";
                        undecided = false;
                        break;
                    } else {
                        System.out.println("You raise your shield to the sky in a confused manner.");
                        undecided = false;
                        break;
                    }
                case "B":
                    this.item = "B";
                    if(this.inventory.containsKey("beer (B)")) {
                        System.out.println("You neck the whole beer in one gulp!\n");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("You're feeling drunk! You feel unable to attack as confidently!");
                        isSober = false;
                        this.inventory = removeItemFromInventory("beer (B)");
                    }
                    undecided = false;
                    break;
                case "E":
                    // Leave the menu
                    System.out.println("You leave the item select menu");
                    undecided = false;
                    break;
                default: System.out.println("Please select a valid option");
                undecided = false;
                break;
            }
        }
        SleepFunction.sleep();
    }
}