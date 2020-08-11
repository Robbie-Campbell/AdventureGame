package com.company.GameLoop;

import com.company.Assets.Player;
import com.company.Assets.PeasantEnemy;
import com.company.KeyFunctions.InventoryDisplay;
import com.company.KeyFunctions.SleepFunction;

import java.util.Scanner;

// The first area in the game
public class LevelOne {

    // Reintroduce the player object
    private Player player1 = Introduction.player1;
    private boolean undecided = true;
    private boolean hasTalked = false;
    private String constantChoices = "C - Check current status\nU - Use item";

    // The option for speaking to the villager further into the story
    private void spokeToVillagerOption(){
        hasTalked = true;
        System.out.println("After considering the townspersons suggestion for a while, you hear a kerfuffle " +
                "in the town center.\n");
        System.out.println("What do you do?:\n" + constantChoices);
        System.out.println("G - Go to the inn\nI - Investigate");
        Scanner choice = new Scanner(System.in);
        String action = choice.nextLine();
        switch (action) {
            case "C":
                player1.checkStatus();
            case "U":
                player1.useItem();
            case "G":
                return;
            case "I":
                System.out.println("2 men are fighting in the town center, they turn to you...");
                SleepFunction.sleep();

                // Instigate a fight with the 2 peasants, with further versions the 2 peasants will fight simultaniously,
                // I'm not quite sure how to implement this yet.
                PeasantEnemy peasant1 = new PeasantEnemy(30);
                PeasantEnemy peasant2 = new PeasantEnemy(20);
                System.out.printf("%s: 'Oi! You're that bandit from Glarbog aren't you! There's a pretty price on your head! " +
                        "wouldn't mind claiming it for meself!'\n", peasant1.nameChoice);
                SleepFunction.sleep();
                System.out.printf("Oh no! %s and %s want to kick the shit out of you!\n", peasant1.nameChoice,
                        peasant2.nameChoice);
                peasant1.fight();
                SleepFunction.sleep();
                System.out.printf("%s was watching from the sidelines, but he's ready to attack now!", peasant2.nameChoice);
                peasant2.fight();
            }
        }

    // The main game loop
    public void startGame() {
        boolean hasSearched = false;
        boolean unanswered = true;
        String setting = String.format("%s, You are a bandit in the land of Glarbog, exiled from your home, you start your journey \n" +
                "You are in the town outskirts and you are looking for your brother, Garth\n", player1.character);
        while (unanswered) {
            System.out.println(setting);
            String searchOption = !hasSearched ? "\nS - Search for garth" : "";
            String talkOption = !hasTalked ? "\nT - Talk to townspeople": "";
            System.out.printf("What do you do first?:\n%s%s%s\n", constantChoices, searchOption, talkOption);
            Scanner Choice = new Scanner(System.in);
            String action = Choice.nextLine();
            switch (action) {
                case "C":
                    player1.checkStatus();
                    break;
                case "U":
                    player1.useItem();
                    break;
                case "T":

                    System.out.println("You talk to a townsperson, they suggest you look to the inn in order to find garth.");
                    SleepFunction.sleep();
                    unanswered = false;
                    spokeToVillagerOption();
                    break;
                case "S":
                    if (!hasSearched) {
                        System.out.println("You try to look for Garth but to no avail, but it seems a something is happening in" +
                                " the middle of town...");
                        // A couple of pauses for readability and suspense.
                        SleepFunction.sleep();
                        // Instigate a fight.
                        PeasantEnemy peasant1 = new PeasantEnemy(20);
                        System.out.printf("A drunken %s bumbles over to you...\n", peasant1.enemyType);
                        SleepFunction.sleep();
                        System.out.printf("%s: 'Are you looking at me, funny %s! I'll teach you a lesson!'\n\n",peasant1.nameChoice, player1.genderChildStatus);
                        peasant1.fight();
                        hasSearched = true;
                        System.out.println("You return to the town centre, proud of your victory!");
                        SleepFunction.sleep();
                        break;
                    }
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
        // Obviously, more content will be added from here! thanks for reading
        System.out.println("Your search takes you to the inn where you find your brother, Garth...\n");
        SleepFunction.sleep();
        System.out.printf("Garth: '%s! It's great to see you again, come! Take a seat, i'll get you a beer!'\n", player1.character);
        System.out.println("{{1 Beer has been added to your inventory}}\n");
        player1.inventory.put("beer (B)", 1);
        SleepFunction.sleep();
        while (undecided) {
            System.out.printf("Garth: 'Cheers to you %s!'\n(he clearly wants you to have a drink with him) \n\nwhat do you do?\n", player1.genderSiblingStatus);
            System.out.println(constantChoices + "\nR - refuse to drink with him");
            Scanner Choice = new Scanner(System.in);
            String action = Choice.nextLine();
            switch (action) {
                case "C":
                    player1.checkStatus();
                    break;
                case "U":
                    player1.useItem();
                    if (player1.item.equals("B")) {
                        System.out.printf("Garth: 'Cheers to you %s! Now, let's get down to business...'\n", player1.character);
                        SleepFunction.sleep();
                        System.out.println("Suddenly a wild peasant appears!!! Garth hides under the table!");
                        PeasantEnemy testForDrunkenness = new PeasantEnemy(20);
                        testForDrunkenness.fight();
                        undecided = false;
                        System.out.printf("Garth: 'Well fought %s! Though i expect no less... from a bandit!'\n", player1.character);
                        SleepFunction.sleep();
                        System.out.println("TBC");
                    }
                    break;
                case "R":
                    System.out.println("Garth overemphasises a ridiculous sigh, he looks as though he is ready to cry,\n" +
                            "how cruel of you.");
                    undecided = false;
                    break;
                default:
                    System.out.println("Please pick a valid option");
            }
        }
    }
}
