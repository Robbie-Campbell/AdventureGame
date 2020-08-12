package com.company.Assets;

import static com.company.GameLoop.Introduction.player1;

public class Peasant extends EnemyAttributes{
    public Peasant(int health) {
        super(health);
        this.enemyType = "Peasant";
        this.nameChoice = this.enemyType + " " + this.nameChoice;
        this.attackDamage = (int) (5 * player1.defence);
        this.XPgain = 50;
        this.selfHarmHit = this.health;
        this.attackType = new String[]{
                "punches you square in the face, that's gonna leave a bruise",
                "punches you in the belly, oof",
                "punches thin air! That's a bad miss",
                "missed so badly he killed himself!"
        };
    }
}
