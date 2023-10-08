import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class adventOfCode2{
    public static void main(String[] args) {
        try {
            File inputFile = new File("AOCInput2.txt");
            Scanner input = new Scanner(inputFile);
            String[] guide = new String[2500];
            char[][] guideInd = new char[2500][2];
            int count = 0;
            int totalScore = 0;
            while (input.hasNextLine()) {
                guide[count] = input.nextLine();
                count += 1;
            }
            for (int i = 0; i < guide.length; i++) {
                guideInd[i][0] = guide[i].charAt(0);
                //System.out.println(guideInd[i][0]);
                guideInd[i][1] = guide[i].charAt(2);
                //System.out.println(guideInd[i][1]);
            }
            
            System.out.println(determineTotalScore(guideInd));
        }
        catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }
    }
    public static int determineScore(char[] guide) {
        int totalScore = 0;
        if (guide[0] == 'A') { // opponent plays rock
            
            if (guide[1] == 'X') { // rock vs rock
                totalScore += 3; // draw
            }
            else if (guide[1] == 'Y') { // rock vs paper
                totalScore += 4; // win
            }
            else { // rock vs scissors
                totalScore += 8; // loss
            }

        }
        else if (guide[0] == 'B') { // opponent plays paper
            if (guide[1] == 'X') { // paper vs rock
                totalScore += 1; // loss
            }
            else if (guide[1] == 'Y') { // paper vs paper
                totalScore += 5; // draw
            }
            else { // paper vs scissors
                totalScore += 9; // win
            }

        }
        else { // opponent plays scissors
            if (guide[1] == 'X') { // scissors vs rock
                totalScore += 2; // win
            }
            else if (guide[1] == 'Y') { // scissors vs paper
                totalScore += 6; // loss
            }
            else { // scissors vs scissors
                totalScore += 7; // draw
            }

        }
        return totalScore;
    }
    public static int determineTotalScore(char[][] guide) {
        int totalScore = 0;
        for (int i = 0; i < guide.length; i++) {
            totalScore += determineScore(guide[i]);  
        }
        return totalScore;
    }
}