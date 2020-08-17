package com.company.KeyFunctions;

import java.util.Scanner;

// A function used in many places to pause the flow of text.
public class SleepFunction {
    public static void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void levelEnd(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pressEnterToContinue(){
        sleep();
        Scanner delay = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        String pause = delay.nextLine();
    }
}
