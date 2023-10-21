import java.util.Scanner;
public class AppleDivision {
    static int n;
    static int[] apples;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        apples = new int[n];
        for (int i = 0; i < apples.length; i++) {
            apples[i] = input.nextInt();
        }
        System.out.println(recurseApples(0, 0, 0));
    }
    public static long recurseApples(int index, long sum1, long sum2) {
        if (index == n) {
            return Math.abs(sum1 - sum2);
        }
        return Math.min(recurseApples(index + 1, sum1 + apples[index], sum2), recurseApples(index + 1, sum1, sum2 + apples[index]));
    }
}