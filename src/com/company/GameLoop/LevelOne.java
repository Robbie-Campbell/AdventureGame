package com.company.GameLoop;
import com.company.Assets.Player;
import com.company.Assets.PeasantEnemy;
import java.util.Scanner;

public class LevelOne {
    Player player1 = Introduction.player1;
    String constantChoices = "C - Check current status\nU - Use item";

    public void startGame(){
        String setting = String.format("%s, You are a bandit in the land of Glarbog, exiled from your home, you start your journey \n" +
                "You are in the town center and you are looking for your brother, Garth\n" +
                "What do you do first?\n" +
                constantChoices +
                "\nT - Talk to townspeople\n" +
                "S - Search for Garth", player1.character);
        boolean unanswered = true;
        while (unanswered) {
            System.out.println(setting);
            Scanner Choice = new Scanner(System.in);
            String decision = Choice.nextLine();
            switch (decision) {
                case "C" ->{
                    player1.checkStatus();
                }
                case "U" -> {
                    player1.useItem();
                }
                case "T" -> {
                    System.out.println("You talk to a townsperson, they suggest you look at the inn to find garth");
                    unanswered = false;
                }
                case "S" -> {
                    System.out.println("You try to look for Garth but to no avail, but it seems a fight is breaking out in" +
                            " the middle of town...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PeasantEnemy peasant = new PeasantEnemy();
                    peasant.fight();
                    unanswered = false;
                }
                default -> System.out.println("Please enter a valid option");
            }
            System.out.println("And that's the game so far! This is a super fun project!!!");
        }
    }
}
