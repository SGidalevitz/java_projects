import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
public class adventOfCode3{
    public static void main(String[] args) {
        
        try {
            File inputFile = new File("AOCInput3.txt");
            Scanner input = new Scanner(inputFile);
            String[] rucksacks = new String[300];
            int prioritySum = 0;
            for (int i = 0; i < (rucksacks.length / 3); i++)
            {
                String data1 = input.nextLine();
                String data2 = input.nextLine();
                String data3 = input.nextLine();
                rucksacks[(3 * i)] = data1;
                rucksacks[(3 * i) + 1] = data2;
                rucksacks[(3 * i) + 2] = data3;
                //System.out.println(data);
                prioritySum += getPriority(strCompare(rucksacks[3 * i], rucksacks[(3 * i) + 1], rucksacks[(3 * i) + 2]));
            }
            System.out.println(prioritySum);
            input.close();
        }
        catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }  
    }
    //public static char[] turnIntoChar(String str) {
    //    char[] chars = new char[str.length()];
    //    for (int i = 0; i < str.length(); i++) {
    //        chars[i] = str.charAt(i);
    //    }
    //    return chars;
	//}
    public static char strCompare(String s1, String s2, String s3) {
        char[] strings = new char[100];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = '\u0000';
        }
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    strings[count] = s1.charAt(i);
                    count += 1;
                }
            }
        }
        char[] commons = new char[count]; 
        for (int i = 0; i < count; i++) {
            commons[i] = strings[i];
        }
        for (int i = 0; i < s3.length(); i++) {
            for (int j = 0; j < commons.length; j++) {
                if (s3.charAt(i) == commons[j]) {
                    return commons[j];
                }
            }
        }
        // if something's up
        return 'a';
    }
    public static int getPriority (char a) {
        //char[] PRIORITY = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String PRIORITY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return (PRIORITY.indexOf(a)) + 1;
    }
}