package com.company.Assets;

import static com.company.GameLoop.Introduction.player1;

// The brute enemy is stronger than the peasant enemy with higher AD and a higher XP reward
public class Brute extends EnemyAttributes {

    public Brute(int health, int noOfEnemies) {
        super(health, noOfEnemies);
        this.enemyType = "Brute";
        for (int i = 0; i < noOfEnemies; i++) {
            this.nameChoices.add(this.enemyType + " " + name[rand.nextInt(6)]);
        }
        this.attackDamage = (int) (10 * player1.defence);
        this.XPgain = 100;
        this.selfHarmHit = 20;
        this.attackType = new String[]{
                "stabs you in the face! Critically wounding you",
                "grazes your " + this.hitArea,
                "narrowly misses your " + this.hitArea,
                "slipped over and stabbed himself, dealing " + this.selfHarmHit + " damage!"
        };
    }
}
