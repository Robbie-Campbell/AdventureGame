package com.company.Assets;


import com.company.KeyFunctions.InventoryDisplay;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Stores all player information
public class Player{
    public Random rand = new Random();
    public int currentXP = 0;
    public int nextLevel = 50;
    public int maxHealth = 100;
    public int damage;
    public int health = 100;
    public int level = 1;
    public String item = "";
    public String character;
    boolean undecided = true;
    public int attackDamage = 10;
    public HashMap<String, Integer> inventory = new HashMap<>();
    public boolean enemy = false;
    public Player(String name) {
        this.character = name;
        this.inventory.put("sword (SW)", 1);
        this.inventory.put("healing potion (HP)", 1);
        this.inventory.put("shield (SH)", 1 );
    }

    public void levelUp(){
        if (currentXP >= nextLevel) {
            this.level++;
            this.attackDamage += 2;
            this.maxHealth += 5;
            this.health = maxHealth;
            System.out.printf("Congratulations! You leveled up! Your new level is %d, your new max health is %d and " +
                    "your new AD is %d!\n", this.level, this.maxHealth, this.attackDamage);
            this.currentXP  -= this.nextLevel;
            this.nextLevel *= 1.4;
            System.out.println("Current xp : " + this.currentXP);
            System.out.println("next level : " + this.nextLevel);
        }
    }

    // Allows to player to check their current stats
    public void checkStatus(){
        undecided = true;
        System.out.println("Check status of: H - health, L - level, AD - attack damage, I - inventory, (E - exit)");
        while (undecided) {
            Scanner check = new Scanner(System.in);
            String checktype = check.nextLine();
            switch (checktype) {
                case "H" -> {System.out.printf("Current health: %d/%d\n", this.health, this.maxHealth); undecided = false;}
                case "L" -> {System.out.printf("Current level: %d\n", this.level); undecided = false;}
                case "AD" -> {System.out.printf("Current attack damage: %d\n", this.attackDamage);  undecided = false;}
                case "I" -> {System.out.printf("Current inventory: %s\n", InventoryDisplay.inventoryToString(this.inventory));
                undecided = false;}
                case "E" -> { System.out.println("You leave the check status menu.");undecided = false; }
                default -> System.out.println("Please enter a valid value\n");
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int SwordAttack() {
        int hitChance = rand.nextInt(100);
        if (enemy) {
            int crit = 0;
            if (hitChance <= 10) {
                crit = this.attackDamage * 2;
                System.out.printf("You critically hit the enemy!! dealing %d damage!\n", this.attackDamage * 2);
            } else if (hitChance < 80) {
                crit = this.attackDamage;
                System.out.printf("You hit the enemy for %d damage!\n", this.attackDamage);
            } else {
                System.out.println("You missed the enemy!\n");
            }
            return crit;
        } else {
            System.out.println("You swing your sword aimlessly at the air.");
            return 0;
        }
    }

    // The responses for the players using their items
    public void useItem()
    {
        undecided = true;
        System.out.println("You can use: " + InventoryDisplay.inventoryToString(this.inventory) + " (or exit 'E')");
        Scanner useAnItem = new Scanner(System.in);
        String choice = useAnItem.nextLine();
        while (undecided){
            this.item = "";
            switch (choice) {
                case "SW":
                    this.item = "SW";
                    this.damage = this.SwordAttack();
                    undecided = false;
                    break;
                case "HP":
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
                        this.inventory.put("healing potion (HP)", this.inventory.get("healing potion (HP)") -1);
                        if (this.inventory.get("healing potion (HP)") == 0)
                        {
                            this.inventory.remove("healing potion (HP)");
                        }
                        break;
                    }
                    else{
                        System.out.println("No health potion in inventory");
                        undecided = false;
                        break;
                    }
                case "SH":
                    if (enemy) {
                        System.out.println("You use a shield.");
                        this.item = "SH";
                        undecided = false;
                        break;
                    } else {
                        System.out.println("You raise your shield to the sky in a confused manner.");
                        undecided = false;
                        break;
                    }
                case "E":
                    System.out.println("You leave the item select menu");
                    undecided = false;
                    break;
                default:
                    System.out.println("Please select a valid option");
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}