package com.company.Levels;

import com.company.Assets.Brute;
import com.company.Assets.Ally;
import com.company.Assets.Knight;
import com.company.Assets.Player;
import com.company.KeyFunctions.SleepFunction;

import java.util.Scanner;


// This is a more complex level with winding choices that can lead the player down a variety of paths
public class LevelTwo
{

    // Global variables
    private final Player player1 = Introduction.player1;
    private final Ally garth = LevelOne.garth;
    private final String constantChoices = LevelOne.constantChoices;
    private boolean unsure = true;

    public void startGame()
    {
        // Garth is now an ally to the player who helps them in fights, this is displayed in this fight with a relatively
        // easy enemy
        garth.exists = true;
        System.out.printf("%s: 'But luckily for you i want to help you!', %s winks at you\n", garth.character, garth.character);
        SleepFunction.sleep();
        Brute barBrute = new Brute(40, 1);
        System.out.printf("%s: 'Oi you two! I heard your conversation, i'm not going to sit here and listen" +
                " to a couple of scheming vagabonds! Bear your weapons and face death!'\n", barBrute.nameChoices.get(0));
        SleepFunction.pressEnterToContinue();
        System.out.printf("%s: 'I'll help you %s!'\n", garth.character, player1.character);
        SleepFunction.sleep();
        barBrute.fight();

        // This is some exposition giving the player an actual mission, the story will be better fleshed out
        // later, this is a project focusing more on game mechanics than effective writing
        System.out.printf("%s: 'Well fought, %s! Look, i know you want to assassinate king Hammond the wise,\n" +
                "and it has been our duty ever since the death of our father, Cameroono Baggis\n" +
                "but you know that you will never defeat him on your own!'\n", garth.character, player1.genderSiblingStatus);
        player1.isSober = true;
        SleepFunction.levelEnd();
        System.out.printf("%s: 'Look, lets get out of Bagbottom for now, we're still going to\n" +
                "need some help, we should seek out a friend of mine, called Grimbo Reeves\n" +
                "a powerful wizard, who will certainly help us on our quest!'\n\n", garth.character);
        SleepFunction.pressEnterToContinue();
        System.out.printf("You and %s flee glarbog, on the road together you reach a fork in the road...\n", garth.character);

        // A cheeky ruse! the player is lead in the opposite direction of where they wanted to go, yes it's cliche!
        // But if i'm fleshing out death valley options in code then i'm going to force people to go that way whether
        // they like it or not!
        while (unsure) {
            SleepFunction.sleep();
            System.out.printf("A signpost offers 2 directions, going west is a sign denoting 'wizards \n" +
                    "cove' and to the east is 'death valley'\n" +
                    "What do you do?\n%s\nW - Head to wizards cove\nD - Head to death valley\n", constantChoices);
            Scanner choice1 = new Scanner(System.in);
            String decision1 = choice1.nextLine();
            String direction = decision1.equals("D") ? "wizards cove!\n" : "death valley!\n";

            // Oh no! What a shocker!
            String tricked = "Unbeknown to you and Garth, an evil Phorlag has flipped the signpost leading you in the opposite\n" +
                    "direction to where was intended! You're now going to ";
            switch (decision1) {
                case "U":
                    player1.useItem();
                    break;
                case "C":
                    player1.checkStatus();
                    break;
                case "D":
                    unsure = false;
                    System.out.println(tricked + direction);
                    SleepFunction.pressEnterToContinue();
                    wizardsCove();
                    break;
                case "W":
                    unsure = false;
                    System.out.println(tricked + direction);
                    SleepFunction.pressEnterToContinue();
                    deathValley();
                    break;
                default:
                    System.out.println("Enter a valid option.");
                    return;
            }
        }
    }

    ///////////////////////////////////////// WIZARD'S COVE CHOICE OPTIONS ////////////////////////////////////////////
    private void wizardsCove(){
        System.out.println("The path to wizards cove is littered with glistening white trees flowing gently in the breeze");
        SleepFunction.sleep();
        System.out.printf("%s: 'That's Grimbo's castle!, as whimsical as you would expect of Grimbo!'\n" +
                "at the end of the road you see a large castle, set on a huge snow-topped mountain\n",garth.character);
        SleepFunction.pressEnterToContinue();

    }

    ///////////////////////////////////////// THE DEATH VALLEY CHOICE OPTIONS /////////////////////////////////////////
    private void deathValley(){
        SleepFunction.sleep();
        System.out.println("The road that you are on is very gloomy and all around an ominous presence makes itself known...");
        SleepFunction.pressEnterToContinue();
        System.out.printf("%s: 'I'm not sure that this is the way to the wizards cove you know %s, I feel as though we\n" +
                "may be in trouble if we don't get off the road soon.'\n\n", garth.character, player1.genderSiblingStatus);
        SleepFunction.sleep();
        System.out.printf("What do you do?\n%s\nS - Stay on the road\nW - Go into the woods\n", constantChoices);
        Scanner choice2 = new Scanner(System.in);
        String decision2 = choice2.nextLine();
        unsure = true;

        // Determines whether or not the player initiates a tough fight
        while (unsure) {
            switch (decision2) {
                case "U":
                    player1.useItem();
                    return;
                case "C":
                    player1.checkStatus();
                    return;
                case "S":
                    unsure = false;
                    stayOnRoad();
                    break;
                case "W":
                    unsure = false;
                    intoWoods();
                    break;
                default:
                    System.out.println("Enter a valid option.");
            }
        }
    }

    // The player decides to stay on the road, this initiates a difficult fight.
    private void stayOnRoad(){
        System.out.printf("%s and %s stay on the road when suddenly you are confronted by a dark knight!!\n", player1.character, garth.character);
        Knight darkKnight = new Knight(60, 1);
        SleepFunction.sleep();
        System.out.printf("%s: 'I have finally found you %s, and your brother too, prepare to die, for the kingdom.'\n",
                darkKnight.nameChoices.get(0), player1.character);
        SleepFunction.pressEnterToContinue();
        System.out.println("You know that this is going to be a hard fight, good luck!\n");
        darkKnight.fight();

        // After the fight the player is awarded with a better weapon which deals more damage than their initial broken sword
        // which is removed from inventory
        player1.weapons.put("a Greatsword (SW)", 10);
        player1.removeItemFromHashMap("Broken Sword (SW)", player1.weapons);
        System.out.printf("%s dropped his Greatsword! it deals 10 damage!\n", darkKnight.nameChoices.get(0));
        SleepFunction.pressEnterToContinue();
        System.out.printf("%s: 'nice sword %s, but we best keep going onwards! Although, i feel as though this path will lead us" +
                "to even more danger'", garth.character, player1.character);

    }

    // The player goes off of the road, progressing the story without the knight fight (so less XP and no weapon buff)
    private void intoWoods(){
        return;
    }
}
