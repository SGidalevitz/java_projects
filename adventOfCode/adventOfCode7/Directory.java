import java.util.ArrayList;
public class Directory {
    private Directory parentDirectory;
    private int totalDirSize;
    private boolean hasParentDirectory;
    private String dirName;
    private ArrayList<Directory> childDirectories = new ArrayList<Directory>();
    public Directory(Directory parentDirectory, String dirName) {
        this.parentDirectory = parentDirectory;
        this.hasParentDirectory = true;
        this.totalDirSize = 0;
        this.dirName = dirName;
    }
    public Directory() {
        this.hasParentDirectory = false;
        this.dirName = "/";
        this.totalDirSize = 0;
    }
    public void addChildDirectory(Directory dir) {
        childDirectories.add(dir);
    }
    public void addFile(int fileSize) { 
        totalDirSize += fileSize;
        parentDirectory.addFile(fileSize);
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