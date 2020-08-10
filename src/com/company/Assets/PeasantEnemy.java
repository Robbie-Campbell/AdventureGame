package com.company.Assets;

public class PeasantEnemy extends EnemyAttributes{
    public PeasantEnemy(int health) {
        super(health);
        this.enemyType = "Peasant ";
        this.nameChoice = this.enemyType + this.nameChoice;
        this.attackDamage = 5;
    }
}
