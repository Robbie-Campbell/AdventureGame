package com.company.Assets;


import com.company.KeyFunctions.InventoryDisplay;
import com.company.KeyFunctions.SleepFunction;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Stores all player information
public class Player
{

    // Ally helper
    public boolean exists = false;
    public double defence = 1;

    // Decided if enemy can attack (regarding shield)
    boolean isBlocked = false;

    // The random option used for all gameplay options
    private Random rand = new Random();

    // The level up variables
    int currentXP = 0;
    int damage;
    int nextLevel = 50;
    private int level = 1;

    // Character traits
    public int maxHealth = 50;
    public boolean isSober = true;
    public boolean isCold = false;
    public String genderChildStatus;
    public String genderSiblingStatus;
    public int health = 50;
    public String character;
    private int attackDamage = 5;

    // All different inventory types.
    public HashMap<String, Integer> weapons = new HashMap<>();
    public HashMap<String, Integer> armour = new HashMap<>();
    public HashMap<String, Integer> items = new HashMap<>();

    // Sets which item is in use
    public String item = "";

    // Conditional statements
    boolean enemy = false;
    private boolean undecided = true;

    // Set the players inventory load and gender
    public Player(String name, String gender)
    {
        this.character = name;
        this.weapons.put("Broken Sword (SW)", 5);
        this.weapons.put("Leather Shield {{Parry}} (SH)", 0);
        this.items.put("Healing Potion (HP)", 1);

        for (int f : this.weapons.values()) {
            this.attackDamage += f;
        }

        // Set gender status
        switch(gender)
        {
            case "M":
                this.genderChildStatus = "boy";
                this.genderSiblingStatus = "brother";
                break;
            case "F":
                this.genderChildStatus = "girl";
                this.genderSiblingStatus = "sister";
                break;
            default:
                this.genderChildStatus = "person";
                this.genderSiblingStatus = "sibling";
        }
    }

    // The function which levels up the player character, resets the player XP back to 0 plus the leftover amount from
    // last level up, if the player gains multiple levels this is accounted for.
    void levelUp()
    {
        if (currentXP >= nextLevel)
        {
            boolean moreThanOneLevel = true;
            while (moreThanOneLevel)
            {
                this.level++;
                this.attackDamage += 2;
                this.maxHealth += 5;
                this.health = maxHealth;
                this.currentXP -= this.nextLevel;
                this.nextLevel *= 1.4;
                if (currentXP <= nextLevel)
                {
                    System.out.printf("%s leveled up! %s's new level is %d, new max health is %d and " +
                            "new AD is %d!\n", this.character, this.character, this.level, this.maxHealth, this.attackDamage);
                    System.out.printf("Current XP : %dXP/%dXP\n", this.currentXP, this.nextLevel);
                    moreThanOneLevel = false;
                }
            }
        }
    }

    // If the player is cold they have a negative status effect
    public void coldStatus()
    {
        if (isCold)
        {
            this.attackDamage *= 0.8;
            this.defence *= 1.25;
            System.out.printf("Cold status added:\nnew AD:%d\nnew defence:%d%%\n", this.attackDamage, (int) (100 - this.defence * 100));
            SleepFunction.sleep();
        }
        else
        {
            this.attackDamage *= 1.25;
            this.defence *= 0.8;
            System.out.printf("Cold status removed:\nnew AD:%d\nnew defence:%d%%", this.attackDamage, (int) (100 - this.defence * 100));
            SleepFunction.sleep();
        }
    }

    // Exit the program at 0 health (or death of Garth function defined in Ally class)
    public void setGameOver()
     {
        if (health <= 0)
        {
            System.out.printf("%s died! You lose!", this.character);
            System.exit(0);
        }
     }

    // Allows to player to check their current stats, inventory etc.
    public void checkStatus()
    {
        undecided = true;
        System.out.println("Check status of: H - Health\nL - Level\nAD - Attack damage\nI - Items\nA - Armour\nW - Weapons\n(E - exit)");
        while (undecided)
        {
            Scanner check = new Scanner(System.in);
            String checkType = check.nextLine();
            String searchOption;
            switch (checkType)
            {
                case "H":System.out.printf("Current health: %d/%d\n", this.health, this.maxHealth); undecided = false; break;
                case "L":System.out.printf("Current level: %d\n", this.level); undecided = false; break;
                case "AD":System.out.printf("Current attack damage: %d\n", this.attackDamage);  undecided = false; break;

                // For each of the inventory checks an inline condition is satisfied to make sure that the HashMap isn't
                // Empty.
                case "I":searchOption = this.items.isEmpty() ? "No items in inventory" : String.format("Items: %s",
                        InventoryDisplay.inventoryToString(this.items, "and", "", false));
                    System.out.println(searchOption);
                case "A":
                    searchOption = this.armour.isEmpty() ? "No armour equipped" : String.format("Armour: %s",
                            InventoryDisplay.inventoryToString(this.armour, "and", "% less damage received", false));
                    System.out.println(searchOption);
                    undecided = false;
                    break;
                case "W":
                    searchOption = this.weapons.isEmpty() ? "No weapons available" : String.format("Weapons: %s",
                            InventoryDisplay.inventoryToString(this.weapons, "and", " damage", false));
                    System.out.println(searchOption);
                    undecided = false;
                    break;
                case "E": System.out.println("You leave the check status menu.");undecided = false; break;
                default:System.out.println("Please enter a valid value\n");
            }
            SleepFunction.sleep();
        }
    }

    // Attack any enemies, probabilities determine the likelihood of successful and critical attacks, with further
    // versions the complexity of attacks will be improved.
    private int SwordAttack()
    {
        int hitChance;

        // The isSober bool makes the hit probability much less likely.
        if (isSober)
        {
            hitChance = rand.nextInt(100);
        }
        else
        {
            hitChance = rand.nextInt(80) + 20;
        }
        if (enemy)
        {
            int crit = 0;
            if (hitChance <= 10)
            {
                crit = this.attackDamage * 2;
                System.out.printf("You critically hit the enemy!! dealing %d damage!\n", this.attackDamage * 2);
            }
            else if (hitChance < 80)
            {
                crit = this.attackDamage;
                System.out.printf("You hit the enemy for %d damage!\n", this.attackDamage);
            }

            // Option is only available with the drunk status effect.
            else if (hitChance > 90 && !isSober)
            {
                this.health -= 30;
                System.out.println("Ouch! You're so drunk that you stabbed yourself in the face! -30 health!");
            }
            else
            {
                System.out.println("You missed the enemy!\n");
            }
            return crit;
        }

        // Condition for if there is no enemy in sight, basically a joke response.
        else
        {
            System.out.println("You swing your sword aimlessly at the air.");
            return 0;
        }
    }

    // Use the shield against the enemies, successful parries will decrease the enemies attack damage for the rest of
    // the fight. Possible fiddling with the probabilities in future as the reward may not be worth the risk of use.
    void Shield(EnemyAttributes enemyInstance)
    {
        int blockChance;
        isBlocked = true;

        // The isSober bool makes the block probability slightly less likely.
        if (isSober)
        {
            blockChance = rand.nextInt(100);
        }
        else
        {
            blockChance = rand.nextInt(90) + 10;
        }
        if (enemy)
        {
            if (blockChance <= 20)
            {
                enemyInstance.attackDamage -= (int) Math.round(enemyInstance.attackDamage * 0.2);
                System.out.printf("You parried the enemies attack!! their new AD is %d!\n", enemyInstance.attackDamage );
            }
            else if (blockChance < 70)
            {
                System.out.println("You blocked the enemies attack!\n");
            }

            // Option is only available with the drunk status effect.
            else if (blockChance > 90 && !isSober)
            {
                this.health -= enemyInstance.attackDamage * 2;
                System.out.printf("%s smashes through your drunken defence for a critical!! Dealing %d damage!\n",
                        enemyInstance.nameChoices, enemyInstance.attackDamage * 2);
            }
            else
            {
                this.health -= enemyInstance.attackDamage;
                System.out.printf("%s broke through your defence!! Dealing %d damage!\n", enemyInstance.nameChoices,
                        enemyInstance.attackDamage);
            }
        }

        // Condition for if there is no enemy in sight, basically a joke response.
        else
        {
            System.out.println("You swing your sword aimlessly at the air.");
        }
    }

    // A function which removes a selected items from any of the HashMaps (and empties the HashMap when necessary).
    public HashMap<String, Integer> removeItemFromHashMap(String itemForRemove, HashMap<String, Integer> HMType)
    {
        HMType.put(itemForRemove, HMType.get(itemForRemove) -1);
        if (HMType.get(itemForRemove) == 0)
        {
            HMType.remove(itemForRemove);
        }
        return HMType;
    }

    // Adds the drunk status effect and removes a beer from inventory.
    private void getDrunk()
    {
        if(this.items.containsKey("beer (B)"))
        {
            System.out.println("You neck the whole beer in one gulp!\n");
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("You're feeling drunk! You feel unable to attack as confidently!");
            isSober = false;
            this.items = removeItemFromHashMap("beer (B)", this.items);
        }
    }

    // The responses for the players using their items
    public void useItem()
    {
        // Display items available items
        undecided = true;
        System.out.println("You can use: "
                + InventoryDisplay.inventoryToString(this.items, ",", "\n", true)
                + InventoryDisplay.inventoryToString(this.weapons, "or a", " damage)", false)
                + " (or exit 'E')");
        Scanner useAnItem = new Scanner(System.in);
        String choice = useAnItem.nextLine();

        // Decision loop
        while (undecided)
        {
            this.item = "";
            switch (choice)
            {

                // Set the damage for reference to enemy attacks
                case "SW":
                    this.item = "SW";
                    this.damage = this.SwordAttack();
                    undecided = false;
                    break;
                case "HP":
                    // Health potion options
                    if(this.items.containsKey("Healing Potion (HP)"))
                    {
                        if (this.health == this.maxHealth)
                        {
                            System.out.println("You are already at maximum health.");
                            undecided = false;
                            break;
                        }
                        if (this.health + 10 < this.maxHealth)
                        {
                            this.health += 20;
                        }
                        else
                        {
                            this.health = this.maxHealth;
                        }
                        System.out.printf("Your health is now %d/%d\n", this.health, this.maxHealth);
                        undecided = false;

                        // Remove one health potion from the HashMap or remove from items entirely
                        this.items = removeItemFromHashMap("Healing Potion (HP)", this.items);
                        break;
                    }

                    // Illegal arg check
                    else
                    {
                        System.out.println("No health potion in items");
                        undecided = false;
                        break;
                    }
                case "SH":
                    // Block an enemy attack... haven't really decided what to do here yet
                    if (enemy)
                    {
                        this.item = "SH";
                        undecided = false;
                        break;
                    }
                    else
                    {
                        // A joke response if no enemy is present
                        System.out.println("You raise your shield to the sky in a confused manner.");
                        undecided = false;
                        break;
                    }
                case "B":

                    // The use a beer option
                    this.item = "B";
                    this.getDrunk();
                    undecided = false;
                    break;
                case "E":
                    // Leave the menu
                    System.out.println("You leave the item select menu");
                    undecided = false;
                    break;
                default: System.out.println("Please select a valid option");
                undecided = false;
                break;
            }
        }
        SleepFunction.sleep();
    }
}