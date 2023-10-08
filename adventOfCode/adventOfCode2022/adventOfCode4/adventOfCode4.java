import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
public class adventOfCode4{
    public static void main(String[] args) {
        
        try {
            File inputFile = new File("AOCInput4.txt");
            Scanner input = new Scanner(inputFile);
            String[] data = new String[1000];
            int count = 0;
            while (input.hasNextLine()) {
                data[count] = input.nextLine();
                count += 1;
            }
            String[][] commaSplit = new String[count][2];
            for (int i = 0; i < count; i++) {
                commaSplit[i] = data[i].split(",");
            }
            int[][][] hyphenSplit = new int[count][2][2];
            for (int i = 0; i < hyphenSplit.length; i++) {
                for (int j = 0; j < hyphenSplit[0].length; j++) {
                    for (int k = 0; k < hyphenSplit[0][0].length; k++) {
                        hyphenSplit[i][j][k] = Integer.parseInt(commaSplit[i][j].split("-")[k]);
                    }
                }
            }
            int sum = 0;
            for (int i = 0; i < hyphenSplit.length; i++) {
                sum += includesAll(hyphenSplit[i]);
            }
            System.out.println(sum);
        }
        catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }  
    }
    public static int includesAll(int[][] ranges) {
        int a1 = ranges[0][0];
        int b1 = ranges[0][1];
        int a2 = ranges[1][0];
        int b2 = ranges[1][1];
        for (int i = a1; i <= b1; i++) {
            if ((i >= a2) && (i <= b2)) {
                return 1;
            }
        }
        return 0;
    }
}