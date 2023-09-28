import java.util.ArrayList;
public class Item{

    private String name;
    private int startDurability;
    private int currentDurability;
    private int damage;
    private int type;
    private int durabilityUsage;
    private int cost;
    private int itemIndex;
    public Item(String itemName, int itemDurability, int itemDamage, int itemType, int itemCost, int index) {
        startDurability = itemDurability;
        currentDurability = itemDurability;
        damage = itemDamage;
        name = itemName;
        type = itemType;
        cost = itemCost;
        durabilityUsage = getDurabilityUsage(type);
        itemIndex = index;
    }
    public void stateVars() {
        System.out.print(name);
        
        if (cost != 0) {
            System.out.print("\tPrice: " + cost + " coins");
            if (cost < 100) {
                System.out.print(" ");
            }
        }
        if (cost == 0) {
            System.out.print("\tStarting durability: " + startDurability);
        }
        else {
            System.out.print("\tDurability: " + startDurability);
        }
        if (cost == 0) {
            System.out.print("\tCurrent durability: " + currentDurability + " ");
            printDurability();
        }
        
        else {
            System.out.print("\t");
        }
        System.out.println("\tDamage: " + damage);
    }
    public int getDurabilityUsage(int itemType) {
        if (itemType == 0) {
            return 20;
        }
        else if (itemType == 1) {
            return 10;
        }
        else {
            return 15;
        }
    }
    public String getName() {
        return name;
    }
    public int useItem(Player player) {
        if (currentDurability == 0) {
            return 0;
        }
        currentDurability -= durabilityUsage;
        if (currentDurability <= 0) {
            currentDurability = 0;
            itemBroke(player);
        }
        return damage;
    }
    public int getDamage() {
        return damage;
    }
    public void itemBroke(Player player) {
        printSeparator();
        System.out.println(name + " broke!");
        printSeparator();
        player.rearrangeItemIndices(itemIndex);
    }
    public int getCost() {
        return cost;
    }
    public void resetCost() {
        cost = 0;
    }
    public void printDurability() {
        int durabilityOutOfTen = (int)Math.round((double)currentDurability * 10 / startDurability);
        for (int i = 0; i < durabilityOutOfTen; i++) {
            System.out.print("▮");
        }
        for (int i = 0; i < (10 - durabilityOutOfTen); i++) {
            System.out.print("▯");
        }
    }
    public void setIndex(int i) {
        itemIndex = i;
    }
    public void printSeparator() {
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }
}

//item types
//sword: 0
//bow: 1
//mace: 2