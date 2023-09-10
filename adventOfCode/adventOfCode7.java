import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;

public class adventOfCode7{
	public static void main(String[] args) {
        try {
            File inputFile = new File("AOCInput7.txt");
            Scanner input = new Scanner(inputFile);
            ArrayList<Integer> parseList = new ArrayList<Integer>();
            ArrayList<Integer> allDirs = new ArrayList<Integer>();
            while (input.hasNextLine()) {
                String data = input.nextLine();
                
                int type = classifyCommand(data);
                if (type == 0 || type == 2) { //change dir or dir in dir
                    continue;
                }
                if (type == 1) { //list
                    parseList.add(0);
                }
                if (type == 3) { //file
                    int lastNum = parseList.get(parseList.size() - 1);
                    parseList.set(parseList.size() - 1, parseList.get(parseList.size() - 1) + Integer.parseInt(data.split(" ")[0]));
                }
                if (type == 4) { //cd ..
                    int lastNum = parseList.get(parseList.size() - 1);
                    parseList.remove(parseList.size() - 1);
                    for (int i = 0; i < parseList.size(); i++) {
                        int parseListAtIndex = parseList.get(i);
                        parseList.set(i, parseListAtIndex + lastNum);
                    }
                    allDirs.add(lastNum);
                }
            }
            while(parseList.size() != 0) {
                int lastNum = parseList.get(parseList.size() - 1);
                parseList.remove(parseList.size() - 1);
                if (parseList.size() == 0) {
                    break;
                }
                for (int i = 0; i < parseList.size(); i++) {
                    int parseListAtIndex = parseList.get(i);
                    parseList.set(i, parseListAtIndex + lastNum);
                }
                allDirs.add(lastNum);
            }
        
            int sum = 0;
            for (int i = 0; i < allDirs.size(); i++) {
                sum += allDirs.get(i);
            }
            //System.out.println("sum = " + sum);
            //System.out.println(allDirs.get(allDirs.size() - 1));
            
            //System.out.println(minSpace);
            ArrayList<Integer> toRemove = new ArrayList<Integer>();
            int biggestIndex = 0;
            System.out.println("allDirs size = " + allDirs.size());
            for (int i = 0; i < allDirs.size(); i++) {
                if (allDirs.get(i) >= 42000000) {
            //        toRemove.add(i);
                    System.out.print(i + " ");
                }
            }
            //System.out.println();
            //for (int i = 0; i < toRemove.size(); i++) {
            //    System.out.println(toRemove.get(toRemove.size() - i - 1));
            //    allDirs.remove(toRemove.get(toRemove.size() - i - 1));
            //    System.out.println("size after purge " + i + " = " + allDirs.size());
            //}
            allDirs.remove(153);
            allDirs.remove(152);
            allDirs.remove(145);
            allDirs.remove(110);
            allDirs.remove(55);
            //System.out.println("allDirs size after purge = " + allDirs.size());
            int minSpace = allDirs.get(0);
            for (int i = 0; i < allDirs.size(); i++) {
                if (allDirs.get(i) > minSpace) {
                    minSpace = allDirs.get(i);
                    biggestIndex = i;
                }
            }
            System.out.println("total size on disk = " + minSpace);
            minSpace -= 40000000;
            System.out.println("minimum necessary deletion = " + minSpace);
            
            int minSpaceFound = allDirs.get(biggestIndex);
            for (int i = 0; i < allDirs.size(); i++) {
                if (allDirs.get(i) < minSpaceFound && allDirs.get(i) >= minSpace) {
                    minSpaceFound = allDirs.get(i);
                }
            }
            System.out.println(minSpaceFound); 
            
        }
    
        catch (FileNotFoundException e) {
            // Handle the exception, e.g., print an error message
            e.printStackTrace();
        }
    }
    
	public static int classifyCommand (String data) {
		if (data.charAt(0) == '$') {
			if (data.charAt(2) == 'c') {
				if (data.equals("$ cd ..")) {
                    return 4;
                }
                else {
                    return 0;
                }
			}
			else if (data.charAt(2) == 'l') {
				return 1;
			}
			
		}
		else if (data.charAt(0) == 'd') {
			return 2;
		}
		else if (data.charAt(0) == '1' || data.charAt(0) == '2' || data.charAt(0) == '3' || data.charAt(0) == '4' || data.charAt(0) == '5' || data.charAt(0) == '6' || data.charAt(0) == '7' || data.charAt(0) == '8' || data.charAt(0) == '9') {
			return 3;
		}
            return -1;
		// 0 = change dir
		// 1 = list
		// 2 = dir in dir
		// 3 = file
        // 4 = cd ..
	}	
}