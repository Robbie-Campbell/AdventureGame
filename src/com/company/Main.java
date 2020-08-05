package com.company;
import com.company.GameLoop.Introduction;
import com.company.GameLoop.LevelOne;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Introduction welcome = new Introduction();
        welcome.introduction();
        LevelOne play = new LevelOne();
        play.startGame();
    }
}
