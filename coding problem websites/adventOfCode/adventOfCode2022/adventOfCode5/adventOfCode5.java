import java.util.Scanner;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;

public class adventOfCode5 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("/Users/simon/Documents/AllCode/java/adventOfCode/AOCInput5.txt");
            Scanner input = new Scanner(inputFile);
            char[][] rawStacks = new char[100][9];
            int count = 0;
            String[] instructionsData = new String[1000];
            while (input.hasNextLine()) {

                String data = input.nextLine();
                if (data.equals("")) {
                    break;
                } else if (data.charAt(1) == '1') {
                    continue;
                } else {
                    rawStacks[count] = analyzeStacks(data);
                    count += 1;
                }

            }
            count = 0;
            while (input.hasNextLine()) {
                String data = input.nextLine();
                instructionsData[count] = data;
                count += 1;
            }
            int[][] instructions = parseInstructions(instructionsData, count);

            // after parse

            String[] allStacks = turnIntoReadableData(rawStacks);
            for (int i = 0; i < allStacks.length; i++) {
                allStacks[i] = (reverseString(allStacks[i]));
                allStacks[i] = allStacks[i].trim();
            }
            for (int i = 0; i < allStacks.length; i++) {
                System.out.println(allStacks[i]);
            }
            
            
            doAllInstructions(allStacks, instructions);
            String output = "";
            for (int i = 0; i < allStacks.length; i++) {
                output += allStacks[i].charAt(allStacks[i].length() - 1);
            }
            System.out.println(output);
            
        } catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }
    }

    public static char[] analyzeStacks(String raw) {
        int lengthOfRaw = raw.length();
        // int lengthOfRow = ((lengthOfRaw - 3) / 4) + 1;
        char[] itemsInRow = new char[9];
        for (int i = 0; i < 9; i++) {
            if ((4 * i) + 1 >= lengthOfRaw) {
                itemsInRow[i] = ' ';
            } else {
                itemsInRow[i] = raw.charAt((4 * i) + 1);
            }
        }
        return itemsInRow;

    }

    public static String turnIntoStr(char[] c) {
        String s = "";
        for (int i = 0; i < c.length; i++) {
            s += c[i];
        }
        return s;
    }

    public static String[] turnIntoReadableData(char[][] rawStacks) {
        char[][] allStacks = new char[9][100];
        for (int i = 0; i < rawStacks.length; i++) {
            for (int j = 0; j < rawStacks[0].length; j++) {
                //if (allStacks[i][j] == '\0') {
                //    break;
                //} 
                allStacks[j][i] = rawStacks[i][j];
                //System.out.printf("allstack[%d][%d] is: %d\n", j, i,  (int)allStacks[i][j]);

            }
        }
        String[] allStrings = new String[9];
        for (int i = 0; i < allStacks.length; i++) {
            String stackWOSpaces = "";
            for (int j = 0; j < allStacks[0].length; j++) {
                if (allStacks[i][j] == '\0') {
                    break;
                } 
                else {
                    
                    stackWOSpaces += allStacks[i][j];
                    //System.out.println(allStacks[i][j]);
                }
            }
            //System.out.println(stackWOSpaces);
            allStrings[i] = stackWOSpaces;
        }
        return allStrings;
    }

    public static int[][] parseInstructions(String[] instructionsData, int count) {
        int instructions[][] = new int[count][3];
        for (int i = 0; i < count; i++) {
            String[] byWord = instructionsData[i].split(" ");
            instructions[i][0] = Integer.parseInt("" + Integer.parseInt(byWord[1]));
            instructions[i][1] = Integer.parseInt("" + Integer.parseInt(byWord[3]));
            instructions[i][2] = Integer.parseInt("" + Integer.parseInt(byWord[5]));
            //System.out.println("" + instructions[i][0] + instructions[i][1] + instructions[i][2]);
        }
        return instructions;
    }
    public static String reverseString(String s) { 
        String str= s, nstr="";
        char ch;
        for (int i=0; i<str.length(); i++) {
            ch= str.charAt(i); //extracts each character
            nstr= ch+nstr; //adds each character in front of the existing string
        }
        return nstr;
    }
    public static String[] doInstruction(String[] stacks, int[] instructions) {
        int quantity = instructions[0];
        int origin = instructions[1] - 1;
        int destination = instructions[2] - 1;
        String lastChars = stacks[origin].substring(stacks[origin].length() - quantity);
        stacks[origin] = stacks[origin].substring(0, stacks[origin].length() - quantity);
        stacks[destination] += lastChars;
        
        
        return stacks;
    }
    public static String[] doAllInstructions(String[] stacks, int[][] instructions) {
        for (int i = 0; i < instructions.length; i++) {
            stacks = doInstruction(stacks, instructions[i]);
            printStacks(stacks);
        }
        return stacks;
    }
    public static void printStacks(String[] stacks) {
        for (int i = 0; i < stacks.length; i++) {
            System.out.println(stacks[i].trim());
        }
    }
}