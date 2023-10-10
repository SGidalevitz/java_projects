import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class AdventOfCode9 {
    public static void main(String[] args) throws FileNotFoundException {
        String[] data;
        boolean sample = false;
        if (sample) {
            data = readInput(new File("AOCSampleInput9.txt"));
        }
        else {
            data = readInput(new File("AOCInput9.txt"));
        }
        System.out.println("Part 1: " + part1(data));

    }
    public static String[] readInput(File inputFile) throws FileNotFoundException {
        Scanner lineCalculator = new Scanner(inputFile);
        int lineCount = 0;
        while (lineCalculator.hasNextLine()) {
            lineCalculator.nextLine();
            lineCount++;
        }
        lineCalculator.close();
        Scanner input = new Scanner(inputFile);
        String[] data = new String[lineCount];
        for (int i = 0; i < lineCount; i++) {
            data[i] = input.nextLine();
        }
        return data;
    }
    public static int[] parseLine(String line) {// right: 0, left: 1, up: 2, down: 3, except: -1
        HashMap<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('R', 0);
            put('L', 1);
            put('U', 2);
            put('D', 3);
        }};  
        return new int[]{map.get(line.charAt(0)), Integer.parseInt(line.split(" ")[1])};
    }
    public static boolean isClose(int hx, int hy, int tx, int ty) {
        return Math.abs(hx - tx) <= 1 && Math.abs(hy - ty) <= 1;
    
    }
    public static boolean containsValue(ArrayList<Integer[]> arr, Integer[] value) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i)[0].equals(value[0]) && arr.get(i)[1].equals(value[1])) {
                return true;
            }
        }
        return false;
    }
    public static int part1(String[] data) {
        int hx = 0, hy = 0, tx = 0, ty = 0, px = 0, py = 0;
        ArrayList<Integer[]> visitedCoordinates = new ArrayList<Integer[]>();
        for (int i = 0; i < data.length; i++) {
            int d = parseLine(data[i])[0];
            int q = parseLine(data[i])[1];
            for (int j = 0; j < q; j++) {
                if (d == 0) {
                    hx += 1;
                } 
                else if (d == 1) {
                    hx -= 1;
                } 
                else if (d == 2) {
                    hy += 1;
                } 
                else if (d == 3) {
                    hy -= 1;
                }
                if (!isClose(hx, hy, tx, ty)) {
                    tx = px;
                    ty = py;
                }
                if (!containsValue(visitedCoordinates, new Integer[]{tx, ty})) {
                    visitedCoordinates.add(new Integer[]{tx, ty});
                }
                px = hx;
                py = hy;
            }
            
            
            
        }
        return visitedCoordinates.size();
    }
}