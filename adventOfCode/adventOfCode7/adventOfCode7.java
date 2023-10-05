import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
public class AdventOfCode7{
    public static void main(String[] args) throws FileNotFoundException {
        File dataFile = new File("AOCinput7.txt");
        Scanner input = new Scanner(dataFile);
        Directory startingDir = new Directory();
        ArrayList<Directory> dirPath = new ArrayList<Directory>();
        ArrayList<Directory> dirsInList = new ArrayList<Directory>();
        String data;
        ArrayList<String> dirNames = new ArrayList<String>();
        input.nextLine();
        dirPath.add(startingDir);
        int count = 1;
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
                latestInPath.addChildDirectory(new Directory(latestInPath, getDirName(data)));

            }
            else if (classifyCommand(data) == 4) {// filename
                int fileSize = getFileSize(data);
                dirPath.get(dirPath.size() - 1).addFile(fileSize);
            }
        }
        ArrayList<Integer> allDirSizes = search(startingDir, new ArrayList<Integer>());
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
    public static ArrayList<Integer> search(Directory dir, ArrayList<Integer> allDirs) { 
        System.out.print("searching ");
        if (dir.getChildDirs().size() > 0) {
            for (int i = 0; i < dir.getChildDirs().size(); i++) {
                allDirs = combine(allDirs, search(dir.getChildDirs().get(i), allDirs));
            }
            allDirs.add(dir.getDirSize());
            return allDirs;
        }
        return new ArrayList<Integer>();
        
    }
    public static ArrayList<Integer> combine(ArrayList<Integer> a, ArrayList<Integer> b) {
        for (int i = 0; i < b.size(); i++) {
            a.add(b.get(i));
        }
        return a;
    }
    public static void printArray(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
    }
}