package com.company.GameLoop;

import com.company.Assets.Brute;
import com.company.Assets.Ally;
import com.company.Assets.Player;
import com.company.KeyFunctions.SleepFunction;


// At present this is just a placeholder
public class LevelTwo
{
    private Player player1 = Introduction.player1;
    private Ally garth = LevelOne.garth;

    public void startGame()
    {
        System.out.printf("%s: '%s, luckily for you i want to help you!', %s winks at you\n", garth.character, player1.character, garth.character);
        SleepFunction.sleep();
        Brute barBrute = new Brute(40, 1);
        System.out.printf("%s: 'I heard your conversation boys, get ready to get battered!'\n", barBrute.nameChoices);
        SleepFunction.sleep();
        System.out.printf("%s: 'I'll help you %s!\n", garth.character, player1.character);
        barBrute.fight();
        System.out.printf("%s: 'Well fought, %s! Let's get the fuck outta here\n", garth.character, player1.genderSiblingStatus);
        player1.isSober = true;
        SleepFunction.sleep();
    }
}
