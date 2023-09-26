import java.util.Scanner;
public class testingstuff{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int seed = 24;
        System.out.println(getSeed(num, seed));
    }
    public static int getSeed(int data, int mod) {
        int depth = doFunc(data, mod) % (data / 10);
        System.out.println(depth);
        while (depth != 0) {
            data = doFunc(data, depth);
            depth -= 1;
        }
        return data;
    }
    public static int doFunc(int data, int altData) {
        int initData = data;
        for (int i = 0; i < altData; i++) {
            if (data % 2 == 0) {
                data /= 2;
            }
            else {
                data = data*3 + 1;
            }
        }
        if (data == 2 || data == 4 || data == 1) {
            data = initData * altData;
        }
        
        return data;
    }
}
