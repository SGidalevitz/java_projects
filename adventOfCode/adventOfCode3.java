import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
public class adventOfCode1{
    public static void main(String[] args) {
        try {
            File inputFile = new File("AOCInput3.txt");
            Scanner input = new Scanner(inputFile);
            char[][] rucksacks = new char[6][100];
            String[] rucksacksSplit = new String[12];
            for (int i = 0; i < rucksacks.length; i++)
            {
                rucksacks[i] = turnIntoChar(input.nextLine());
            }
            for (int i = 0; i < rucksacks.length; i++)
            {
                String part1 = "";
                String part2 = "";
                for (int j = 0; j < rucksacks[i].length; j++)
                {
                    int splitAt = (rucksacks[i].length / 2) - 1;
                    
                    if (j <= splitAt) {
                        part1 += j;
                    }
                    else {
                        part2 += j;
                    }
                }
                rucksacksSplit[2 * i] = part1;
                rucksacksSplit[(2 * i) + 1] = part2;
                
            }
        }
        catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }
    }
    public static char[] turnIntoChar(String str) {
        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        return chars;
	}
}