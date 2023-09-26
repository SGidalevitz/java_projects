import java.util.ArrayList;
public class Enemy{
    private int currentHealth;
    private int startHealth;
    private int enemyType;
    private int enemyLevel;
    private String enemyTypeVerbal;
    private String enemyAttack;
    private int enemyAttackDamage;
    public Enemy(int type, int level, int difficulty) {
            enemyType = type;
            enemyLevel = level;
            int[] attributes = getAttributes(type, level, difficulty);
            startHealth = attributes[0];
            currentHealth = attributes[0];
            enemyAttackDamage = attributes[1];
            String[] verbals = getEnemyAndAttackVerbal(type);
            enemyTypeVerbal = verbals[0];
            enemyAttack = verbals[1];
            System.out.print();
        }
    public void stateVars() {
        System.out.print("Level " + enemyLevel + " " + enemyTypeVerbal);
        System.out.print("\tCurrent health: " + currentHealth);
        System.out.println("\tEnemy damage: " + enemyAttackDamage);
    }
    public String getNameAndLevel() {
        return "Level " + enemyLevel + " " + enemyTypeVerbal;
    }
    public int getStartHealth() {
        return startHealth;
    }
    public int getHealth() {
        return currentHealth;
    }
    public void changeHealth(int value) {
        currentHealth += value;
    }
    public String getEnemyAttack() {
        return enemyAttack;
    }
    public int getEnemyAttackDamage() {
        return enemyAttackDamage;
    }
    public int[] getAttributes(int type, int level, int difficulty) {
        if (type == 0) {
            int[] attributes = {(int)Math.round(200 * difficulty * Math.pow(level, 1.5)), (int)Math.round(20 * level * Math.sqrt(difficulty))};
            return attributes;
        }
        else if (type == 1) {
            int[] attributes = {(int)Math.round(300 * difficulty * Math.pow(level, 1.5)), (int)Math.round(30 * level * Math.sqrt(difficulty))};
            return attributes;
        }
        else {
            int[] attributes = {};
            return attributes;
        }
    }
    public String[] getEnemyAndAttackVerbal(int type) {
        if (type == 0) {
            String[] verbals = {"Goblin", "Spiked Wodden Mace"};
            return verbals;
        }
        else if (type == 1) {
            String[] verbals = {"Spider", "Venom"};
            return verbals;
        }
        else {
            String[] verbals = {"empty", "empty"};
            return verbals;
        }
    }
}


//enemy types
//goblin: 0
//spider: 1