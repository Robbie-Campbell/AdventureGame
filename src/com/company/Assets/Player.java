package com.company.Assets;


import com.company.KeyFunctions.InventoryDisplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Stores all player information
public class Player{
    public int maxHealth = 100;
    public int health = 100;
    public int level = 1;
    public String character;
    boolean undecided = true;
    public int attackDamage = 10;
    public ArrayList<String> inventory = new ArrayList<>();
    public boolean enemy = false;
    public Player(String name) {
        character = name;
        inventory.add("sword (SW)");
        inventory.add("healing potion (HP)");
        inventory.add("shield (SH)");
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
                case "E" -> undecided = false;
                default -> System.out.println("Please enter a valid value\n");
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            switch (choice) {
                case "SW":
                    if (enemy) {
                        System.out.printf("You attack the enemy, dealing %d damage!%n", this.attackDamage);
                        undecided = false;
                        break;
                    } else {
                        System.out.println("You swing your sword aimlessly at the air.");
                        undecided = false;
                        break;
                    }
                case "HP":
                    if(this.inventory.contains("healing potion")) {
                        this.inventory.remove("healing potion");
                        if (this.health + 10 < maxHealth) {
                            this.health += 10;
                        }
                        else{
                            this.health = 100;
                        }
                        System.out.printf("Your health has is now %d%d\n", this.health, this.maxHealth);
                        undecided = false;
                        break;
                    }
                    else{
                        System.out.println("No health potion in inventory");
                        undecided = false;
                        break;
                    }
                case "SH":
                    if (enemy) {
                        System.out.println("You block the attack!");
                        undecided = false;
                    } else {
                        System.out.println("You raise your shield to the sky in a confused manner.");
                        undecided = false;
                    }
                case "E":
                    System.out.println("You leave the item select menu");
                    undecided = false;
                default:
                    System.out.println("Please select a valid option");
            }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}