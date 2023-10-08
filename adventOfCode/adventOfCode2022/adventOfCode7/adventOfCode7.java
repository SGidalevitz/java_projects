import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
public class AdventOfCode7{

    public static void main(String[] args) throws FileNotFoundException {
        File dataFile = new File("AOCInput7.txt");
        Scanner input = new Scanner(dataFile);
        Directory startingDir = new Directory();
        ArrayList<Directory> dirPath = new ArrayList<Directory>();
        String data;
        input.nextLine();
        dirPath.add(startingDir);
        while(input.hasNextLine()) {
            data = input.nextLine();
            if (classifyCommand(data) == 0) { // $ cd ..
                
                dirPath.remove(dirPath.size() - 1);
            }
            else if (classifyCommand(data) == 1) {// $ cd dirname
                String dirName = getDirNameFromCd(data);
                Directory latestInPath = dirPath.get(dirPath.size() - 1);
                ArrayList<Directory> childDirs = latestInPath.getChildDirs();
                ArrayList<String> childDirNames = new ArrayList<String>();
                for (int i = 0; i < childDirs.size(); i++) {
                    childDirNames.add(childDirs.get(i).getDirName());
                }
                dirPath.add(childDirs.get(getIndexOfDir(childDirNames, dirName)));
            }
            else if (classifyCommand(data) == 2) {// $ ls
                //do nothing
            }
            else if (classifyCommand(data) == 3) {// dir dirname
                Directory latestInPath = dirPath.get(dirPath.size() - 1);
                int ind = Directory.allDirSizes.size();
                latestInPath.addChildDirectory(new Directory(latestInPath, getDirName(data), ind));
            }
            else if (classifyCommand(data) == 4) {// filename
                int fileSize = getFileSize(data);
                dirPath.get(dirPath.size() - 1).addFile(fileSize);
            }
        }
        int sum = 0;
        int totalSpaceUsed = Directory.allDirSizes.get(0);
        int unusedSpaceNecessary = 40000000;
        int spaceNecessaryForDeletion = totalSpaceUsed - unusedSpaceNecessary;
        int min = totalSpaceUsed;
        for (int i = 1; i < Directory.allDirSizes.size(); i++) {
            if (Directory.allDirSizes.get(i) < min && Directory.allDirSizes.get(i) >= spaceNecessaryForDeletion) {
                min = Directory.allDirSizes.get(i);
            }
        }
        System.out.println(min);
        
    }
    public static int classifyCommand(String data) {
        if (data.charAt(0) == '$') {
            if (data.equals("$ cd ..")) {
                return 0;
            }
            else if (data.charAt(2) == 'c') {
                return 1;
            }
            else {
                return 2;
            }
        }
        else if (data.charAt(0) == 'd') {
            return 3;
        }
        else {
            return 4;
        }
    }
    public static int getFileSize(String data) {
        return Integer.parseInt(data.split(" ")[0]);
    }
    public static String getDirName(String data) {
        return data.split(" ")[1];
    }
    public static String getDirNameFromCd(String data) {
        return data.split(" ")[2];
    }
    public static int getIndexOfDir(ArrayList<String> dirNames, String dirName) {
        return dirNames.indexOf(dirName);
    }
}
