package com.company.GameLoop;

import com.company.Assets.Player;
import com.company.KeyFunctions.InventoryDisplay;
import java.util.Scanner;
public class Introduction {
    // Establishes the player name and sets the player on their way
    public static Player player1;
    public void introduction(){
        System.out.println("Please enter your characters name: ");
        Scanner enterName = new Scanner(System.in);
        String nameInput = enterName.nextLine();
        System.out.println("Please enter your characters gender: (M/F/O)");
        Scanner enterGender = new Scanner(System.in);
        String genderInput = enterGender.nextLine();
        player1 = new Player(nameInput, genderInput);
        String gameAnnouncement = String.format("%s, Welcome to the game! You have %d/%d health and you have: %s in your inventory, \n" +
                "Good luck!\n", player1.character, player1.health, player1.maxHealth, InventoryDisplay.inventoryToString(player1.inventory, "and"));
        System.out.println(gameAnnouncement);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
