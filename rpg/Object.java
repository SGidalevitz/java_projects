public class Object{
    private String objectName;
    private int objectStartDurability;
    private int objectCurrentDurability;
    private int objectType;
    private int objectDurabilityUsage;
    private int objectIndex;
    public Object(String name, int durability, int type, int index) {
        objectName = name;
        objectStartDurability = durability;
        objectCurrentDurability = durability;
        objectType = type;
        objectIndex = index;
        getDurabilityUsage();
    }
    public void useItem(Player player) {
        objectCurrentDurability -= objectDurabilityUsage;
        if (objectType == 0) {
            
        }
        else {
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
        else {
            objectDurabilityUsage = 0;
        }
    }
    public void setIndex(int i) {
        objectIndex = i;
    }
    public void itemBroke(Player player) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(objectName + " broke!");
        System.out.println("------------------------------------------------------------------------------------------------------------");
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
}