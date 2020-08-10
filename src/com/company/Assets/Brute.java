package com.company.Assets;

public class Brute extends EnemyAttributes {
    public Brute(int health) {
        super(health);
        this.enemyType = "Brute ";
        this.nameChoice = this.enemyType + this.nameChoice;
        this.attackDamage = 20;
    }
}
