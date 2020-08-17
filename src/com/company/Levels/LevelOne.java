package com.company.Levels;

import com.company.Assets.Ally;
import com.company.Assets.Player;
import com.company.Assets.Peasant;
import com.company.KeyFunctions.SleepFunction;

import java.util.Scanner;

// The first area in the game
public class LevelOne
{

    // Reintroduce the player object
    private final Player player1 = Introduction.player1;
    public static Ally garth = new Ally("Garth", "M");
    private boolean undecided = true;
    private boolean hasTalked = false;
    public static String constantChoices = "C - Check current status\nU - Use item";
    // The option for speaking to the villager further into the story
    private void spokeToVillagerOption()
    {
        hasTalked = true;
        System.out.println("After considering the townspersons suggestion for a while, you hear a kerfuffle " +
                "in the town center.\n");
        SleepFunction.sleep();
        System.out.println("What do you do?:\n" + constantChoices);
        System.out.println("G - Go to the inn\nI - Investigate");
        Scanner choice = new Scanner(System.in);
        String action = choice.nextLine();
        switch (action)
        {
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
                Peasant peasants = new Peasant(30, 2);
                System.out.printf("%s: 'Oi! You're that bandit from Glarbog aren't you! There's a pretty price on your head! " +
                        "wouldn't mind claiming it for meself!'\n", peasants.nameChoices.get(0));
                SleepFunction.sleep();
                System.out.printf("Oh no! %s and %s want to kick the shit out of you!\n", peasants.nameChoices.get(0),
                        peasants.nameChoices.get(1));
                SleepFunction.pressEnterToContinue();
                peasants.fight();
            }
        }

    // Level one of the game
    public void startGame()
    {
        SleepFunction.pressEnterToContinue();
        boolean hasSearched = false;
        boolean unanswered = true;
        String setting = String.format("%s, You are a bandit in the land of Glarbog, exiled from your home, you start your journey \n" +
                "You are in the outskirts of Bogbottom city and you are looking for your brother, %s\n", player1.character, garth.character);
        while (unanswered)
        {
            System.out.println(setting);
            SleepFunction.sleep();
            String searchOption = !hasSearched ? "\nS - Search for Garth" : "";
            String talkOption = !hasTalked ? "\nT - Talk to townspeople": "";
            System.out.printf("What do you do first?:\n%s%s%s\n", constantChoices, searchOption, talkOption);
            Scanner Choice = new Scanner(System.in);
            String action = Choice.nextLine();
            switch (action)
            {
                case "C":
                    player1.checkStatus();
                    break;
                case "U":
                    player1.useItem();
                    break;
                case "T":

                    System.out.printf("You talk to a townsperson, they suggest you look to the inn in order to find %s.\n", garth.character);
                    SleepFunction.sleep();
                    unanswered = false;
                    spokeToVillagerOption();
                    break;
                case "S":
                    if (!hasSearched)
                    {
                        System.out.printf("You try to look for %s but to no avail, but it seems a something is happening in" +
                                " the middle of town...\n", garth.character);

                        // A couple of pauses for readability and suspense.
                        SleepFunction.sleep();

                        // Instigate a fight.
                        Peasant peasant1 = new Peasant(20, 1);
                        System.out.printf("A drunken %s bumbles over to you...\n", peasant1.enemyType);
                        SleepFunction.pressEnterToContinue();
                        System.out.printf("%s: 'Are you looking at me, funny %s! I'll teach you a lesson!'\n\n",peasant1.nameChoices.get(0), player1.genderChildStatus);
                        SleepFunction.sleep();
                        peasant1.fight();
                        hasSearched = true;
                        System.out.println("You return to the town centre, proud of your victory!\n");
                        SleepFunction.pressEnterToContinue();
                        break;
                    }
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
        // First meeting with your brother garth
        System.out.printf("Your search takes you to the inn where you find your brother, %s...\n", garth.character);
        SleepFunction.sleep();
        System.out.printf("%s: '%s! It's great to see you again, come! Take a seat, i'll get you a beer!'\n", garth.character, player1.character);
        garth.exists = true;
        SleepFunction.sleep();
        System.out.println("{{1 Beer has been added to your inventory}}\n");
        player1.items.put("beer (B)", 1);
        SleepFunction.pressEnterToContinue();
        while (undecided)
        {
            System.out.printf("%s: 'Cheers to you %s!'\n(he clearly wants you to have a drink with him) \n\n", garth.character, player1.genderSiblingStatus);
            SleepFunction.sleep();
            System.out.println("What do you do?\n" + constantChoices + "\nR - Refuse to drink with him");
            Scanner Choice = new Scanner(System.in);
            String action = Choice.nextLine();
            switch (action) {
                case "C":
                    player1.checkStatus();
                    break;
                case "U":
                    player1.useItem();
                    if (player1.item.equals("B")) {
                        System.out.printf("%s: 'Cheers to you %s! Now, let's get down to business...'\n", garth.character, player1.character);
                        SleepFunction.sleep();
                        Peasant barPeasant = new Peasant(20, 1);
                        System.out.printf("%s: 'Wait, you're %s! Thankyou for the beer earlier!'\n", barPeasant.nameChoices.get(0), garth.character);
                        SleepFunction.sleep();
                        System.out.printf("%s: 'You're welcome, friend!'\n", garth.character);
                        SleepFunction.sleep();
                        System.out.printf("%s: 'Maybe you and your friend could put this helmet to better use...\n", barPeasant.nameChoices.get(0));
                        player1.defence -= 0.1;
                        SleepFunction.sleep();
                        player1.armour.put("Leather helmet", 10);
                        System.out.println("The Leather helmet reduces damage from all types by 10 percent.\n");
                        SleepFunction.pressEnterToContinue();
                        undecided = false;
                    }
                    else
                    {
                        continue;
                    }
                    break;
                case "R":
                    System.out.printf("%s overemphasises a ridiculous sigh, he looks as though he is ready to cry,\n" +
                            "how cruel of you.", garth.character);
                    SleepFunction.sleep();
                    System.out.println("Anyways, let's get down to business...\n\n");
                    undecided = false;
                    break;
                default:
                    System.out.println("Please pick a valid option");
            }
            SleepFunction.sleep();
            System.out.printf("%s: '%s, I know the truth about your past... I know that you are a bandit.'\n", garth.character, player1.character);
            SleepFunction.sleep();
            System.out.println("End of part one");
            SleepFunction.levelEnd();
        }
    }
}
