import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class rpg{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int difficulty = getDifficulty();
        String user = getUsername();
        Player player = new Player(difficulty, user);
        doInputCycle(player);
        printDialogue(0);
        doInputCycle(player);
        player.addStartingAxe();
        logMinigame(player);
        printDialogue(1);
        doInputCycle(player);
        doFight(player, new Enemy(0, 1, difficulty));
        doInputCycle(player);
        printDialogue(2);
        doInputCycle(player);
        player.getObjects().add(new Object("Healing Potion", 100, 1, 0, 1));
        doInputCycle(player);
        
    }

    public static void displayStore(Player player) {
        printSeparator();
        System.out.println("You have " + player.getCoins() + " coins.");
        player.showItems(player.getStoreItems(), false);
        Scanner input = new Scanner(System.in);
        
        System.out.println("Would you like to purchase any of these items? (Choose a number from 1 to " + player.getStoreItems().size() + " or choose 0 to not buy anything)");
        int storeItemChoice = input.nextInt();
        while (storeItemChoice < 0 || storeItemChoice > player.getStoreItems().size()) {
            System.out.println("That's not a valid index. Would you like to purchase any of these items? (Choose a number from 1 to " + player.getStoreItems().size() + " or 0 to not buy anything)");
            storeItemChoice = input.nextInt();
        }
        if (storeItemChoice == 0) {
            System.out.println("Thanks for visiting!");
        }
        else {
            storeItemChoice -= 1;
            if (player.getCoins() >= player.getStoreItems().get(storeItemChoice).getCost()) {
                player.changeCoins(player.getStoreItems().get(storeItemChoice).getCost() * -1);
                player.getItems().add(player.getStoreItems().get(storeItemChoice));
                System.out.println(player.getStoreItems().get(storeItemChoice).getName() + " successfully purchased! You now have " + player.getCoins() + " coins.");
                player.getStoreItems().remove(storeItemChoice);
                player.getItems().get(player.getItems().size() - 1).resetCost();
            }
            else {
                System.out.println("Not enough coins! This item costs " + player.getStoreItems().get(storeItemChoice).getCost() + " coins, but you only have " + player.getCoins() +  " coins.");
            }
            System.out.println("Thanks for visiting! Would you like to shop again? (0/No, 1/Yes)");
            int choice = input.nextInt();
            while (choice != 0 && choice != 1) {
                System.out.println("That's not a valid choice. Would you like to shop again? (0/No, 1/Yes)");
                choice = input.nextInt();
            }
            if (choice == 1) {
                displayStore(player);
            }
        }
    }
    public static int getDifficulty() {
        Scanner input = new Scanner(System.in);
        int difficulty;
        System.out.println("Welcome to RPG!\n" + 
            "In this game, you are able to play an RPG within your own terminal.\n" + 
            "I hope you enjoy!\n" + 
            "Which difficulty would you like to play on? (1/Easy, 2/Medium, 3/Hard)"
        );
        difficulty = input.nextInt();
        while (difficulty < 1 || difficulty > 3) {
            System.out.println("That isn't a valid difficulty!\n" + 
            "Which difficulty would you like to play on? (1/Easy, 2/Medium, 3/Hard)");
            difficulty = input.nextInt();
        }
        return difficulty;
    }
    public static int[] doFight(Player player, Enemy enemy) { 
        printSeparator();
        System.out.println(player.getUsername() + " (" + player.getHealth() + " HP)" + " is fighting " + enemy.getNameAndLevel() + " (" + enemy.getHealth() + " HP)" + "!");
        printSeparator();
        enemy.stateVars();
        boolean fightOver = false;
        while (!fightOver) {
            int itemOfChoice = getItemForUse(player);
            userAttack(player, enemy, itemOfChoice);
            if (enemy.getHealth() <= 0) {
                System.out.println(player.getUsername() + " wins!");
                int[] returnvals = {0, player.getHealth()};
                return returnvals;
            }
            enemyAttack(player, enemy);
            if (player.getHealth() <= 0) {
                System.out.println(enemy.getNameAndLevel() + " wins!");
                doGameOverSequence();
                int[] returnvals = {1, player.getHealth()};
                return returnvals;
            }
            System.out.print("Your health is: " + player.getHealth() + "\t\t\t");
            printPlayerHealthScale(player);
            System.out.println();
            System.out.print("The " + enemy.getNameAndLevel() + "'s health is: " + enemy.getHealth() + "\t");
            printEnemyHealthScale(enemy);
            System.out.println();
            printSeparator();
        }
        int[] returnvals = {0, 0};
        return returnvals;
    }
    public static int getItemForUse(Player player) {
        Scanner input = new Scanner(System.in);
        System.out.println("These are your items:");
        player.showItems(player.getItems(),  true);
        System.out.println("Which item would you like to use? (Choose a number from 1 to " + player.getItems().size() + ")");
        printSeparator();
        int itemOfUse = input.nextInt();
        while (itemOfUse < 1 || itemOfUse > player.getItems().size()) {
            System.out.println("That is not a valid option. Which item would you like to use? (Choose a number from 1 to " + player.getItems().size() + ")");
            itemOfUse = input.nextInt();
        }
        return itemOfUse - 1;
    }
    public static String getUsername() {
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like to name yourself?");
        return input.nextLine();
    }
    public static void userAttack(Player player, Enemy enemy, int itemOfChoice) {
        String nameOfItem = player.getItems().get(itemOfChoice).getName();
        int userDamage = player.getItems().get(itemOfChoice).useItem(player);
        String username = player.getUsername();
        System.out.println(username + " uses " + nameOfItem + " for " + userDamage + " damage!");
        enemy.changeHealth(-1 * userDamage);
        
    }
    public static void enemyAttack(Player player, Enemy enemy) {
        String enemyNameAndLevel = enemy.getNameAndLevel();
        String enemyAttack = enemy.getEnemyAttack();
        int enemyAttackDamage = enemy.getEnemyAttackDamage();
        System.out.println(enemyNameAndLevel + " uses " + enemyAttack + " for " + enemyAttackDamage + " damage!");
        player.changeHealth(-1 * enemy.getEnemyAttackDamage());
    }
    public static void doGameOverSequence() {
        printSeparator();
        System.out.println("It was a great run, but your battle is over! Thanks for playing!");
        printSeparator();
        System.exit(0);
    }
    public static void doInputCycle(Player player) {
        printSeparator();
        System.out.print("You may enter \"s\" to open the shop, \"c\" to continue, ");
        if (player.getUsableObjects(player).size() != 0) {
            System.out.print("\"u\" to use your items");
        }
        System.out.println("or \"i\" to view your items.");
        Scanner input = new Scanner(System.in);
        String data = "";
        data = input.nextLine();
        
        while (!data.equals("c")) {
            while (!data.equals("c") && !data.equals("s") && !data.equals("i") && !data.equals("u")) {
                printSeparator();
                System.out.println("Invalid choice. You may enter \"s\" to open the shop, \"c\" to continue, ");
                if (player.getUsableObjects(player).size() != 0) {
                    System.out.print("\"u\" to use your items, ");
                }
                System.out.println("or \"i\" to view your items.");
                data = input.nextLine();
            }
            if (data.equals("s")) {
                displayStore(player);
                
            }
            else if (data.equals("i")) {
                player.showItemsAndObjects(player.getItems(), player.getObjects(), true);
            }
            else if (data.equals("u")) {
                if (player.getUsableObjects(player).size() != 0) {
                    itemUseProcess(player); 
                }
                else {
                System.out.println("No items to use.");
                }
            }
                         
            else if (data.equals("c")) {
                continue;
            }
            printSeparator();
            System.out.println("You may enter \"s\" to open the shop, \"c\" to continue, \"u\" to use your items, or \"i\" to view your items.");
            data = input.nextLine();
        }
    }
    
    public static void itemUseProcess(Player player) {
        Scanner input = new Scanner(System.in);
        player.showObjects(player.getUsableObjects(player), true);
        printSeparator();
        System.out.println("Pick a number from 1 to " + player.getUsableObjects(player).size() + " to choose which item you will use");
        int choice = input.nextInt();
        while (choice < 1 || choice > player.getUsableObjects(player).size()) {
            System.out.println("That's not a valid choice. Pick a number from 1 to " + player.getUsableObjects(player).size() + " to choose which item you will use");
            choice = input.nextInt();
        }
        player.getUsableObjects(player).get(choice - 1).useObject(player);
        
        
    }

    public static void printPlayerHealthScale(Player player) {
        int healthOutOfTen = (int)Math.round((double)player.getHealth() * 10 / 1000);
        for (int i = 0; i < healthOutOfTen; i++) {
            System.out.print("▮");
        }
        for (int i = 0; i < (10 - healthOutOfTen); i++) {
            System.out.print("▯");
        }
    }
    public static void printEnemyHealthScale(Enemy enemy) {
        int healthOutOfTen = (int)Math.round((double)enemy.getHealth() * 10 / enemy.getStartHealth());
        for (int i = 0; i < healthOutOfTen; i++) {
            System.out.print("▮");
        }
        for (int i = 0; i < (10 - healthOutOfTen); i++) {
            System.out.print("▯");
        }
    }
    public static void logMinigame(Player player) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        boolean isEmpty = false;
        String nameOfObject = player.getObjects().get(0).getName();
        String username = player.getUsername();
        int[][] logs = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
        
        ArrayList<Integer> logPlacements = new ArrayList<Integer>();
        while (logPlacements.size() != 5) {
            int randInt = rand.nextInt(0, 25);
            boolean inList = false;
            for (int i = 0; i < logPlacements.size(); i++) {
                if (randInt == logPlacements.get(i)) {
                    inList = true;
                    break;
                }
            }
            if (!inList) {
                logPlacements.add(randInt);
            }
        }
        for (int i = 0; i < logPlacements.size(); i++) {
            int logIndex = logPlacements.get(i);
            logs[logIndex % 5][logIndex / 5] = 1;
        }
        printSeparator();
        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[0].length; j++) {
                System.out.print(logs[i][j] + " ");
            }
            System.out.println();
        }
        while (!isEmpty) {
            System.out.println("Enter a coordinate x,y to collect the wood");
            String data = input.nextLine();
            if (data.equals("s")) {
                break;
            }
            while (data.length() != 3 || Integer.parseInt(data.substring(0, 1)) < 1 || Integer.parseInt(data.substring(0, 1)) > 5 || Integer.parseInt(data.substring(2)) < 1 || Integer.parseInt(data.substring(2)) > 5) {
                System.out.println("Invalid coordinate. Try again x,y");
                data = input.nextLine();
                if (data.equals("s")) {
                break;
                }
            }
            int row = 5 - Integer.parseInt(data.substring(2));
            int col = Integer.parseInt(data.substring(0, 1)) - 1;
            if (logs[row][col] == 1) {
                logs[row][col] = 0;
                player.getObjects().get(0).useObject(player);
                printSeparator();
                System.out.print(username + " uses " + nameOfObject + "!");
                System.out.print(" Durability: ");
                player.getObjects().get(0).printDurability();
                System.out.println(" (" + player.getObjects().get(0).getCurrentDurability() + "/" + player.getObjects().get(0).getStartDurability() + ")");
                printSeparator();
            }
            isEmpty = true;
            for (int i = 0; i < logs.length; i++) {
                for (int j = 0; j < logs[0].length; j++) {
                    System.out.print(logs[i][j] + " ");
                        
                    if (logs[i][j] == 1) {
                        isEmpty = false;
                    }
                }
                System.out.println();
            }
            
        }
    }
    public static void printSeparator() {
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }
    public static void printDialogue(int section) {
        printSeparator();
        if (section == 0) {
            System.out.println("You are out in the forest, and you need wood to build shelter for the night and to kindle a fire to cook dinner. It is getting dark soon, so you need to act quickly. ");
            System.out.println("To help, I have given you an axe that will help you chop down logs. Here are your current items: ");
        }
        else if (section == 1) {
            System.out.println("Great work collecting the wood. You bring it back to your base, and begin building a shelter, but a dark figure bats your eye. You try to look at it better, but it starts charging towards you.");
        }
        else if (section == 2) {
            System.out.println("Great job defeating that goblin! You recollect yourself and put together logs to build a fire. During your water-cleansing procses,");
            System.out.println("you notice that upon boiling water above this fire, it turns into healing serum.");
            System.out.println("You bottle it up quickly and put it in your bag. You have gotten a new item - Healing Potion.");
        }

        
    }
    
}