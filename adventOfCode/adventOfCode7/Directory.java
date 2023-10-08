import java.util.ArrayList;
public class Directory {
    private Directory parentDirectory;
    private int totalDirSize;
    private boolean hasParentDirectory;
    private String dirName;
    private int dirIndex;
    public static ArrayList<Integer> allDirSizes = new ArrayList<Integer>();
    private ArrayList<Directory> childDirectories = new ArrayList<Directory>();
    public Directory(Directory parentDirectory, String dirName, int dirIndex) {
        this.parentDirectory = parentDirectory;
        this.hasParentDirectory = true;
        this.totalDirSize = 0;
        this.dirName = dirName;
        this.dirIndex = dirIndex;
        allDirSizes.add(0);
        
    }
    public Directory() {
        this.hasParentDirectory = false;
        this.dirName = "/";
        this.totalDirSize = 0;
        this.dirIndex = 0;
        allDirSizes.add(0);
    }
    public void addChildDirectory(Directory dir) {
        childDirectories.add(dir);
    }
    public void addFile(int fileSize) { 
        totalDirSize += fileSize;
        if (hasParentDirectory) {
            parentDirectory.addFile(fileSize);
        }
        allDirSizes.set(dirIndex, totalDirSize);
    }
    public ArrayList<Directory> getChildDirs() {
        return childDirectories;
    }
    public int getDirSize() {
        return totalDirSize;
    }
    public String getDirName() {
        return dirName;
    }
}