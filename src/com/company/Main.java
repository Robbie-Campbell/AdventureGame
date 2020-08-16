package com.company;
import com.company.Levels.Introduction;
import com.company.Levels.LevelTwo;

// Call and run all classes
public class Main
{

    public static void main(String[] args)
    {
        Introduction welcome = new Introduction();
        welcome.introduction();
        /*LevelOne play = new LevelOne();
        play.startGame();*/
        LevelTwo secondLevel = new LevelTwo();
        secondLevel.startGame();
    }
}
