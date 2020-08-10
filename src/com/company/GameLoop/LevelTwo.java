package com.company.GameLoop;

import com.company.Assets.Brute;
import com.company.Assets.Player;

public class LevelTwo {
    private Player player1 = Introduction.player1;

    public void startGame(){
        Brute brute1 = new Brute(50);
        System.out.printf("%s approaches you and says angrily 'we dont take kindly to bandits in our town!'\n\n", brute1.nameChoice);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        brute1.fight();
    }
}
