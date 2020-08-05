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
        player1 = new Player(String.valueOf(nameInput));
        String gameAnnouncement = String.format("%s, Welcome to the game! You have %d/%d health and you have:%s in your inventory, \n" +
                "Good luck!", player1.character, player1.health, player1.maxHealth, InventoryDisplay.inventoryToString(player1.inventory));
        System.out.println(gameAnnouncement);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
