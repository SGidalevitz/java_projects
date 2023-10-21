import java.util.Scanner;
public class A1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long n = input.nextInt();
        long m = input.nextInt();
        long a = input.nextInt();
        System.out.println((long)Math.ceil((double)m / a)  * (long)Math.ceil((double)n / a));
    }
}