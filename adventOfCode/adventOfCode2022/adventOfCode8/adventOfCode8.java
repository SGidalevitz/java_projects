import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
public class adventOfCode8{
    public static void main(String[] args) throws FileNotFoundException {
        boolean sample = false;
        int data[][];
        if (sample) {
            data = readInput(new File("AOCSampleInput8.txt"));
        }
        else {
            data = readInput(new File("AOCInput8.txt"));
        }
        int[][] visibilityArray = createVisibilityArray(data);
        int row = data.length;
        int col = data[0].length;
        /* 
        for (int i = 0; i < col; i++) {
            visibilityArray = pushIntoArray(data, visibilityArray, 0, i, true);
        }
        for (int i = 0; i < col; i++) {
            visibilityArray = pushIntoArray(data, visibilityArray, 0, i, false);
        }
        for (int i = 0; i < row; i++) {
            visibilityArray = pushIntoArray(data, visibilityArray, 1, i, true);
        }
        for (int i = 0; i < row; i++) {
            visibilityArray = pushIntoArray(data, visibilityArray, 1, i, false);
        }
        print2DArray(visibilityArray);
        System.out.println(countArray(visibilityArray));
        */
        int[] maxScore = new int[4];
        int rowCoord = 0;
        int colCoord = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int[] scenicScore = getScenicScores(data, visibilityArray, r, c);
                if (calcProduct(scenicScore) > calcProduct(maxScore)) {
                    maxScore = scenicScore;
                    rowCoord = r;
                    colCoord = c;
                }
    
            }
        }
        
        System.out.println("Max Scenic Score: " + calcProduct(maxScore));
        System.out.println("Indices: " + rowCoord + ", " + colCoord);
        print1DArray(maxScore);
        
        //System.out.println(getVisibility(new int[]{3, 3, 4, 4, 2, 1, 9, 10, 14, 17, 13}, 5));
        
        
    }
    public static int[][] readInput(File inputFile) throws FileNotFoundException {
        Scanner lineCalculator = new Scanner(inputFile);
        int row = 1;
        int col = lineCalculator.nextLine().length();
        while (lineCalculator.hasNextLine()) {
            lineCalculator.nextLine();
            row++;
        }
        lineCalculator.close();
        Scanner input = new Scanner(inputFile);
        int[][] data = new int[row][col];
        for (int r = 0; r < row; r++) {
            String line = input.nextLine();
            for (int c = 0; c < col; c++) {
                data[r][c] = Integer.parseInt(line.charAt(c) + "");
            }
        }
        input.close();
        return data;
    }
    public static int[][] createVisibilityArray(int[][] data) {
        int[][] visibilityArray = new int[data.length][data[0].length];
        for (int i = 0; i < visibilityArray.length; i++) {
            for (int j = 0; j < visibilityArray[i].length; j++) {
                if (i == 0 || i == visibilityArray.length - 1|| j == 0 || j == visibilityArray[i].length - 1) {
                    visibilityArray[i][j] = 1;
                }
                else {
                    visibilityArray[i][j] = 0;
                }
            }
        }
        return visibilityArray;
    }
    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void print1DArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static int getVisibility(int[] arr, int startHeight) {
        boolean tooHigh = false;
        int max = 0;
        int sum = 0;

       // System.out.println(startHeight);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= startHeight) {
                sum += 1;
                return sum;
            }
            sum += 1;
        
        }
        return sum;
    }
    /*
    public static int[][] pushIntoArray(int[][] data, int[][] visibilityArray, int dim, int val, boolean front) { // for dim, row is 0, col is 1
        if (dim == 0) { // row
            int[] vals = new int[data[0].length];
            if (front) {
                for (int i = 0; i < data[0].length; i++) {
                    vals[i] = data[val][i];
                }
                int[] visibility = getVisibility(vals);
                for (int i = 0; i < visibility.length; i++) {
                    if (visibility[i] == 1) {
                           visibilityArray[val][i] = 1;
                    }
                }
            }
            else {
                for (int i = 0; i < data[0].length; i++) {
                    vals[i] = data[val][vals.length - i - 1];
                }
                int[] visibility = getVisibility(vals);
                for (int i = 0; i < visibility.length; i++) {
                    if (visibility[i] == 1) {
                        visibilityArray[val][visibility.length - i - 1] = 1;
                    }
                }
            }
        }
        else {
            int[] vals = new int[data[0].length];
            if (front) {
                for (int i = 0; i < data[0].length; i++) {
                    vals[i] = data[i][val];
                }
                int[] visibility = getVisibility(vals);
                for (int i = 0; i < visibility.length; i++) {
                    if (visibility[i] == 1) {
                        visibilityArray[i][val] = 1;
                    }
                }
            }
            else {
                for (int i = 0; i < data[0].length; i++) {
                    vals[i] = data[vals.length - i - 1][val];
                }
                int[] visibility = getVisibility(vals);
                for (int i = 0; i < visibility.length; i++) {
                    if (visibility[i] == 1) {
                        visibilityArray[visibility.length - i - 1][val] = 1;
                    }
                }
            }
        }
        return visibilityArray;
    }
    */
    public static int countArray(int[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;

    }
    public static int[] getScenicScores(int[][] data, int[][] visibilityArray, int row, int col) {
        //System.out.println("row: " + row + " col: " + col);
        int dataRow = data.length;
        int dataCol = data[0].length;
        int downDistance = dataRow - row - 1;
        int upDistance = row;
        int leftDistance = col;
        int rightDistance = dataCol - col - 1;
        int startingHeight = data[row][col];
        //System.out.println(leftDistance + " " + rightDistance + " " + downDistance + " " + upDistance);
        //down
        int[] valsDown = new int[downDistance];
        for (int i = 0; i < downDistance; i++) {
            valsDown[i] = data[row + 1 + i][col];
        }
        int downScenicScore = getVisibility(valsDown, startingHeight);
        //up
        int[] valsUp = new int[upDistance];
        for (int i = 0; i < upDistance; i++) {
            valsUp[i] = data[row - i - 1][col];
        }
        int upScenicScore = getVisibility(valsUp, startingHeight);
        //left
        int[] valsLeft = new int[leftDistance];
        for (int i = 0; i < leftDistance; i++) {
            valsLeft[i] = data[row][col - i - 1];
        }
        int leftScenicScore = getVisibility(valsLeft, startingHeight);
        //right
        int[] valsRight = new int[rightDistance];
        for (int i = 0; i < rightDistance; i++) {
            valsRight[i] = data[row][col + i + 1];
        }
        int rightScenicScore = getVisibility(valsRight, startingHeight);

        return new int[]{downScenicScore, upScenicScore, leftScenicScore, rightScenicScore};
    }
    public static int calcProduct(int[] arr) {
        int product = 1;
        for (int i = 0; i < arr.length; i++) {
            product *= arr[i];
        }
        return product;
    }
        



}
