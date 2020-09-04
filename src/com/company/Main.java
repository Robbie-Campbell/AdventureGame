/*
Author: Robbie Campbell
Organisation: Bournemouth and Poole College
Date: 19/08/2020
Description:
This is a fantasy game which i am using as a vessel further my Java knowledge, i don't have an intention
of implementing a GUI in this project, only to focus on some core gaming concepts. This isn't a very serious attempt
at high quality storytelling, although i do intend to sprinkle a bit better exposition throughout when the project has
matured a bit more.
Regardless, i hope you enjoy wandering through Glarbog with Garth!

Version: Pre-Alpha
 */

package com.company;
import com.company.Levels.Introduction;
import com.company.Levels.LevelOne;
import com.company.Levels.LevelTwo;

// Call and run all classes
public class Main
{

    public static void main(String[] args)
    {
        Introduction welcome = new Introduction();
        welcome.introduction();
        //LevelOne play = new LevelOne();
        //play.startGame();
        LevelTwo secondLevel = new LevelTwo();
        secondLevel.startGame();
    }
}
