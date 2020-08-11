package com.company.Assets;

public class PeasantEnemy extends EnemyAttributes{
    public PeasantEnemy(int health) {
        super(health);
        this.enemyType = "Peasant";
        this.nameChoice = this.enemyType + " " + this.nameChoice;
        this.attackDamage = 5;
        this.XPgain = 50;
        this.selfHarmHit = this.health;
        this.attackType = new String[]{
                " punches you square in the face, that's gonna leave a bruise",
                " punches you in the belly, oof",
                " punches thin air! That's a bad miss",
                " missed so badly he killed himself!"
        };
    }
}
