package com.company.Assets;

import static com.company.GameLoop.Introduction.player1;

// The brute enemy is stronger than the peasant enemy with higher AD
public class Brute extends EnemyAttributes {

    public Brute(int health) {
        super(health);
        this.enemyType = "Brute";
        this.nameChoice = this.enemyType + " " + this.nameChoice;
        this.attackDamage = (int) (20 * player1.defence);
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
