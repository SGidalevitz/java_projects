import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
public class U1_1_1_2020 {
    public static void main(String[] args) {
        try {
            File in = new File("word.in");
            Scanner input = new Scanner(in);
            int n = input.nextInt();
            int k = input.nextInt();
            String[] words = input.nextLine().split(" ");
            int wc = 0;
            int cc = 0;
            while (counter < n) {
                String wordToPrint = "";
                while (cc < k && wordToPrint != words[wc]) {
                    wordToPrint += words[wc].charAt(cc);
                    cc++;
                }
                if (cc == k) {
                    System.out.println();
                    
                }
                else {
                    System.out.print(wordToPrint);
                    wc++;
                }
                
                wc++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}