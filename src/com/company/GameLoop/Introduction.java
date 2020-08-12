package com.company.GameLoop;

import com.company.Assets.Player;
import com.company.KeyFunctions.InventoryDisplay;
import com.company.KeyFunctions.SleepFunction;

import java.util.Scanner;
public class Introduction {
    // Establishes the player name and gender and sets the player on their way
    public static Player player1;
    public void introduction(){
        System.out.println("Please enter your characters name: ");
        Scanner enterName = new Scanner(System.in);
        String nameInput = enterName.nextLine();
        System.out.println("Please enter your characters gender: (M/F/O)");
        Scanner enterGender = new Scanner(System.in);
        String genderInput = enterGender.nextLine();
        player1 = new Player(nameInput, genderInput);
        String gameAnnouncement = String.format("%s, Welcome to the game! You have %d/%d health, you have: %s in your" +
                " inventory and no armour equipped \nGood luck!\n", player1.character, player1.health, player1.maxHealth,
                InventoryDisplay.inventoryToString(player1.inventory, "and", true));
        System.out.println(gameAnnouncement);
        SleepFunction.sleep();
    }

}
