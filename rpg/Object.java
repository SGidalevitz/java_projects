public class Object{
    private String objectName;
    private int objectDurability;
    private int objectType;
    private int objectDurabilityUsage;
    private int objectIndex;
    public Object(String name, int durability, int type, int index) {
        objectName = name;
        objectDurability = durability;
        objectType = type;
        System.out.print();
        
    }
    public void useItem(Player player) {
        objectDurability -= objectDurabilityUsage;
        if (objectType == 0) {
            
        }
        else {

        }
        if (objectDurability <= 0) {
            player.rearrangeObjectIndices(objectIndex);
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
  
}