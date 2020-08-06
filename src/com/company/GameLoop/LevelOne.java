package com.company.GameLoop;

import com.company.Assets.Player;
import com.company.Assets.PeasantEnemy;
import java.util.Scanner;

// The first area in the game
public class LevelOne {

    // Reintroduce the player object
    Player player1 = Introduction.player1;
    String constantChoices = "C - Check current status\nU - Use item";

    // The option for speaking to the villager further into the story
    public void spokeToVillagerOption(){
        System.out.println("After considering the townspersons suggestion for a while, you hear a kerfuffle " +
                "in the town center. What do you do?");
        System.out.println(constantChoices);
        System.out.println("G - Go to the inn\nI - Investigate");
        Scanner choice = new Scanner(System.in);
        String action = choice.nextLine();
        switch (action) {
            case "C" -> {
                player1.checkStatus();
            }
            case "U" -> {
                player1.useItem();
            }
            case "I" -> {
                System.out.println("2 men are fighting in the town center, they turn to you...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Instigate a fight with the 2 peasants, with further versions the 2 peasants will fight simultaniously,
                // I'm not quite sure how to implement this yet.
                PeasantEnemy peasant1 = new PeasantEnemy(30);
                PeasantEnemy peasant2 = new PeasantEnemy(20);
                System.out.printf("Peasant %s shouts over 'Oi! You're that bandit from Glarbog aren't you! There's a pretty price on your head! " +
                        "wouldn't mind claiming it for meself!'\n", peasant1.nameChoice);
                System.out.printf("Oh no! Peasant %s and Peasant %s want to kick the shit out of you!\n", peasant1.nameChoice,
                        peasant2.nameChoice);
                peasant1.fight();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Peasant %s was watching from the sidelines, but he's ready to attack now!", peasant2.nameChoice);
                peasant2.fight();
            }
        }

    }

    // The main game loop
    public void startGame(){
        boolean unanswered = true;
        String setting = String.format("%s, You are a bandit in the land of Glarbog, exiled from your home, you start your journey \n" +
                "You are in the town center and you are looking for your brother, Garth\n",player1.character);
        while (unanswered) {
            System.out.println(setting);
            System.out.println("What do you do first?\n" + constantChoices +"T - Talk to townspeople\nS - Search for Garth");
            Scanner Choice = new Scanner(System.in);
            String action = Choice.nextLine();
            switch (action) {
                case "C" ->{
                    player1.checkStatus();
                }
                case "U" -> {
                    player1.useItem();
                }
                case "T" -> {
                    System.out.println("You talk to a townsperson, they suggest you look at the inn to find garth");
                    unanswered = false;
                    spokeToVillagerOption();
                }
                case "S" -> {
                    System.out.println("You try to look for Garth but to no avail, but it seems a something is happening in" +
                            " the middle of town...");

                    // A couple of pauses for readability and suspense.
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A drunken peasant bumbles over to you...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Instigate a fight.
                    System.out.println("He shouts angrily 'Are you looking at me funny boy! I'll teach you a lesson!'");
                    PeasantEnemy peasant1 = new PeasantEnemy(20);
                    peasant1.fight();
                    unanswered = false;
                }
                default -> System.out.println("Please enter a valid option");
            }
        }
        // Obviously, more content will be added from here! thanks for reading
        System.out.println("And that's the game so far! This is a super fun project!!!");
    }
}
