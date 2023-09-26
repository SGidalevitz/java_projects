import java.util.ArrayList;
public class Player{
    private int playerHealth;
    private int playerCoins;
    private ArrayList<Item> playerItems;
    private ArrayList<Object> playerObjects;
    private String username;
    private ArrayList<Item> storeItems;
    public Player(int difficulty, String user) {
        playerHealth = 1000;
        playerItems = openingSequence(difficulty);
        username = user;
        storeItems = initStoreItems();
        ArrayList<Object> playerObjects = new ArrayList<Object>();
        System.out.print();

    }
    public int getHealth() {
        return playerHealth;
    }
    public void changeHealth(int value) {
        playerHealth += value;
    }
    public ArrayList<Item> getItems() {
        return playerItems;
    }
    public int getCoins() {
        return playerCoins;
    }
    public void changeCoins(int value) {
        playerCoins += value;
    }
    public ArrayList<Item> initStoreItems() {
        ArrayList<Item> storeItems = new ArrayList<Item>();
        storeItems.add(new Item("Flaming Mace", 450, 250, 2, 500, -1));
        storeItems.add(new Item("Rusty Dagger", 100, 50, 0, 50, -1));
        storeItems.add(new Item("Steel Longsword", 400, 180, 0, 300, -1));
        storeItems.add(new Item("Elven Bow", 250, 120, 1, 200, -1));
        storeItems.add(new Item("Warhammer", 350, 220, 0, 350, -1));
        storeItems.add(new Item("Crossbow", 300, 160, 1, 250, -1));
        return storeItems;
    }
    public ArrayList<Item> getStoreItems() {
        return storeItems;
    }
    public ArrayList<Item> getStarterItems(int difficulty) {
        if (difficulty == 1) {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item("Basic Sword", 300, 120, 0, 0, 0));
            items.add(new Item("Basic Bow", 200, 150, 1, 0, 1));
            return items;
        }
        else if (difficulty == 2) {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item("Basic Sword", 250, 0, 0, 0, 0));
            items.add(new Item("Basic Bow", 175, 125, 1, 0, 1));
            return items;
        }
        else {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item("Basic Sword", 300, 120, 0, 0, 0));
            return items;
        }
    }
    public ArrayList<Item> openingSequence(int difficulty) {
        String difficultyVerbal;
        int startingCoins;
        if (difficulty == 1) {
            difficultyVerbal = "Easy";
            playerCoins = 500;
        }
        else if (difficulty == 2) {
            difficultyVerbal = "Medium";
            playerCoins = 400;
        }
        else {
            difficultyVerbal = "Hard";
            playerCoins = 300;
        }
        System.out.println("Last thing: I have given you " + playerCoins + " coins to start the game. At any point in which you are not in a fight, you may enter \"s\" to open the shop, \"c\" to continue, or \"i\" to view your items.");
        System.out.println("All set! You are playing on " + difficultyVerbal + " difficulty. Have fun!");
        return getStarterItems(difficulty);
    }
    public String getUsername() {
        return username;
    }
    public void stateAllVars(ArrayList<Item> items) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < items.size(); i++) {
            System.out.print("Item " + (i + 1) + ": ");
            items.get(i).stateVars();
        }
    }
    public void rearrangeItemIndices(int index) {
        playerItems.remove(index);
        for (int i = 0; i < playerItems.size(); i++) {
            playerItems.get(i).setIndex(i);
        }
    }
    public void rearrangeObjectIndices(int index) {
        playerObjects.remove(index);
        for (int i = 0; i < playerObjects.size(); i++) {
            playerObjects.get(i).setIndex(i);
        }
    }
}