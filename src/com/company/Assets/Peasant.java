package com.company.Assets;

import static com.company.GameLoop.Introduction.player1;

public class Peasant extends EnemyAttributes{

    // The peasant enemy type, fairly low damage low reward type
    public Peasant(int health, int noOfEnemies) {
        super(health, noOfEnemies);
        this.enemyType = "Peasant";
        for (int i = 0; i < this.noOfEnemies; i++) {
            this.nameChoices.add(i, this.enemyType + " " + name[rand.nextInt(6)]);
        }
        this.attackDamage = (int) (5 * player1.defence);
        this.XPgain = 50;
        this.selfHarmHit = this.health;
        String plural = noOfEnemies > 1 ? "all of his friends!" : "himself!";
        this.attackType = new String[]{
                "punches you square in the face, that's gonna leave a bruise",
                "punches you in the belly, oof",
                "punches thin air! That's a bad miss",
                "missed so badly he killed " + plural
        };
    }
}
