package com.company.Assets;

// The brute enemy is stronger than the peasant enemy with higher AD
public class Brute extends EnemyAttributes {

    public Brute(int health) {
        super(health);
        this.enemyType = "Brute";
        this.nameChoice = this.enemyType + " " + this.nameChoice;
        this.attackDamage = 20;
        this.XPgain = 100;
        this.selfHarmHit = 20;
        this.attackType = new String[]{
                " stabs you in the face! Critically wounding you",
                " grazes your " + this.hitArea,
                " narrowly misses your " + this.hitArea,
                " slipped over and stabbed himself, dealing " + this.selfHarmHit + " damage!"
        };
    }
}
