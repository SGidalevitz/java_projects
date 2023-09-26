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
        System.out.println("You are out in the forest, and you need wood to build shelter for the night and to kindle a fire to cook dinner. It is getting dark soon, so you need to act quickly. ");
        logMinigame();
        doFight(player, new Enemy(0, 1, difficulty));
        doInputCycle(player);

    }

    public static void displayStore(Player player) {
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("You have " + player.getCoins() + " coins.");
        player.stateAllVars(player.getStoreItems());
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
        System.out.println("------------------------------------------------------------------------------------------------------------");
        enemy.stateVars();
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(player.getUsername() + " (" + player.getHealth() + " HP)" + " is fighting " + enemy.getNameAndLevel() + " (" + enemy.getHealth() + " HP)" + "!");
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
            System.out.println("------------------------------------------------------------------------------------------------------------");
        }
        int[] returnvals = {0, 0};
        return returnvals;
    }
    public static int getItemForUse(Player player) {
        Scanner input = new Scanner(System.in);
        System.out.println("These are your items:");
        player.stateAllVars(player.getItems());
        System.out.println("Which item would you like to use? (Choose a number from 1 to " + player.getItems().size() + ")");
        System.out.println("------------------------------------------------------------------------------------------------------------");
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
        int userDamage = player.getItems().get(itemOfChoice).useItem(player);
        System.out.println(player.getUsername() + " uses " + player.getItems().get(itemOfChoice).getName() + " for " + userDamage + " damage!");
        enemy.changeHealth(-1 * userDamage);
        
    }
    public static void enemyAttack(Player player, Enemy enemy) {
        System.out.println(enemy.getNameAndLevel() + " uses " + enemy.getEnemyAttack() + " for " + enemy.getEnemyAttackDamage() + " damage!");
        player.changeHealth(-1 * enemy.getEnemyAttackDamage());
    }
    public static void doGameOverSequence() {
        System.out.println("It was a great run, but your battle is over! Thanks for playing!");
        System.exit(0);
    }
    public static void doInputCycle(Player player) {
        Scanner input = new Scanner(System.in);
        String data = "";
        data = input.nextLine();
        while (!data.equals("c")) {
            while (!data.equals("c") && !data.equals("s") && !data.equals("i")) {
                System.out.println("Invalid choice. Remember: you may enter \"s\" to open the shop, \"c\" to continue, or \"i\" to view your items.");
                data = input.nextLine();
            }
            if (data.equals("s")) {
                displayStore(player);
                data = input.nextLine();
            }
            else if (data.equals("i")) {
                player.stateAllVars(player.getItems());
                data = input.nextLine();
            }
            else if (data.equals("c")) {
                continue;
            }
        }
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
    public static void logMinigame() {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        int total = 0;
        boolean isEmpty = false;
        int[][] logs = new int[5][5];
        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[0].length; j++) {
                logs[i][j] = Math.abs(rand.nextInt() % 2);
                if (logs[i][j] == 1) {
                    total++;
                }
            }
        }
        for (int i = 0; i < logs.length; i++) {
            for (int j = 0; j < logs[0].length; j++) {
                System.out.print(logs[i][j] + " ");
            }
            System.out.println();
        }
        while (!isEmpty) {
            System.out.println("Enter a coordinate x,y to collect the wood");
            String data = input.nextLine();
            while (data.length() != 3 || Integer.parseInt(data.substring(0, 1)) < 1 || Integer.parseInt(data.substring(0, 1)) > 5 || Integer.parseInt(data.substring(2)) < 1 || Integer.parseInt(data.substring(2)) > 5) {
                System.out.println("Invalid coordinate. Try again x,y");
                data = input.nextLine();
            }
            int row = 5 - Integer.parseInt(data.substring(2));
            int col = Integer.parseInt(data.substring(0, 1)) - 1;
            if (logs[row][col] == 1) {
                logs[row][col] = 0;
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
        System.out.println("You did it!");
        
        
    
        }
    }