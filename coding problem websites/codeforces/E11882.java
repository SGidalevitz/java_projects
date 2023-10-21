import java.util.Arrays;
import java.util.Scanner;
public class E11882 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int[] p = new int[m];
        int[] q = new int[n];
        for (int i = 0; i < m; i++) {
            p[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            q[i] = input.nextInt();
        }
        int[] arr = permute(new int[]{1, 2, 3, 4, 5}, 1);
        Arrays.stream(arr).forEach(num -> System.out.println(num));
    }
    public static String solve(int[] p, int[] q) {
        return "";
    }
    public static int[] permute(int[] perm, int pivot) {
        int ll = pivot - 1;
        int pivotInt = perm[pivot - 1];
        int rl = perm.length - pivot + 1;
        int[] newPerm = new int[perm.length];
        for (int i = 0; i < rl; i++) {
            newPerm[i] = perm[i + pivot - 1];
        }
        newPerm[rl] = pivotInt;
            for (int i = 0; i < ll; i++) {
                newPerm[rl + i] = perm[i];
            }
        return newPerm;

    }
}
