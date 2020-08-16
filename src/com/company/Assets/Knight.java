package com.company.Assets;

import static com.company.Levels.Introduction.player1;

public class Knight extends EnemyAttributes {

    // The peasant enemy type, fairly low damage low reward type
    public Knight(int health, int noOfEnemies) {
        super(health, noOfEnemies);
        this.enemyType = "Knight";
        this.normalAttack = "deeply cuts your ";
        this.missAttack = "missing, his greatsword pierces the air, nearly slicing your ";
        for (int i = 0; i < this.noOfEnemies; i++) {
            this.nameChoices.add(i, this.enemyType + " " + name[rand.nextInt(6)]);
        }
        this.attackDamage = (int) (20 * player1.defence);
        this.XPgain = 200;
        this.selfHarmHit = this.attackDamage / 2;
        String plural = noOfEnemies > 1 ? "misses you grazing all of their " + this.hitArea + "s":
                "misses you grazing his " + this.hitArea;
        this.attackType = new String[]{
                "bashes you with his shield and stabs you in the gut! You bleed profusely",
                normalAttack + this.hitArea,
                missAttack + this.hitArea,
                plural
        };
    }
}
