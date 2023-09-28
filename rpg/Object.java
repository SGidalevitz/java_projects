public class Object{
    private String objectName;
    private int objectStartDurability;
    private int objectCurrentDurability;
    private int objectType;
    private int objectDurabilityUsage;
    private int objectIndex;
    private int objectCost;
    private boolean isUsable;
    public Object(String name, int durability, int type, int cost, int index) {
        objectName = name;
        objectStartDurability = durability;
        objectCurrentDurability = durability;
        objectType = type;
        objectIndex = index;
        objectCost = cost;
        if (objectType == 1) {
            isUsable = true;
        }
        else {
            isUsable = false;
        }
        objectAcquiredProcess(objectType);
        getDurabilityUsage();
    }
    public void objectAcquiredProcess(int type) {
        printSeparator();
        if (type == 0) {
            System.out.println(objectName + " acquired! (" + objectStartDurability + " durability)");
        }
        if (type == 1) {
            System.out.println(objectName + " acquired! (" + objectStartDurability + " HP)");
        }

        
    }
    public int getCurrentDurability() {
        return objectCurrentDurability;
    }
    public int getStartDurability() {
        return objectStartDurability;
    }
    public boolean getUsability() {
        return isUsable;
    }
    public void useObject(Player player) {
        objectCurrentDurability -= objectDurabilityUsage;
        if (objectType == 0) {

        }
        else if (objectType == 1) { //health potion
            player.changeHealth(objectStartDurability);
            System.out.println("Health increased by " + objectStartDurability +" HP!");
        }
        if (objectCurrentDurability <= 0) {
            objectCurrentDurability = 0;
            itemBroke(player);
        }
    }
    public void getDurabilityUsage() {
        if (objectType == 0) {
            objectDurabilityUsage = 10;
        }
        else if (objectType == 1) {
            objectDurabilityUsage = objectStartDurability;
        }
    }
    public void setIndex(int i) {
        objectIndex = i;
    }
    public void itemBroke(Player player) {
        printSeparator();
        System.out.println(objectName + " broke!");
        printSeparator();
        player.rearrangeObjectIndices(objectIndex);
    }
    public void printDurability() {
        int durabilityOutOfTen = (int)Math.round((double)objectCurrentDurability * 10 / objectStartDurability);
        for (int i = 0; i < durabilityOutOfTen; i++) {
            System.out.print("▮");
        }
        for (int i = 0; i < (10 - durabilityOutOfTen); i++) {
            System.out.print("▯");
        }
    }
    public String getName() {
        return objectName;
    }
    public void stateVars() {
        System.out.print(objectName);
        
        if (objectCost != 0) {
            System.out.print("\tPrice: " + objectCost + " coins");
            if (objectCost < 100) {
                System.out.print(" ");
            }
        }
        if (objectCost == 0) {
            System.out.print("\tStarting durability: " + objectStartDurability);
        }
        else {
            System.out.print("\tDurability: " + objectStartDurability);
        }
        if (objectCost == 0) {
            System.out.print("\tCurrent durability: " + objectCurrentDurability + " ");
            printDurability();
        }
        
        else {
            System.out.print("\t");
        }
        System.out.println();
    }
    public void printSeparator() {
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }
}

//0 tool, not fighting, e.g. axe
//1 health potion