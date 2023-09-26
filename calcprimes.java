public class calcprimes{
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 2; i <= 1000; i++) {
            sum += isPrime(i) ? 1 : 0;
        }
        System.out.println(sum);
    }
    public static boolean isPrime(int num) {
        boolean returnval = true;
        int toggle = 0;
        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                toggle = 1;
            }
        }
        if (toggle == 0) {
            return true;
        } else {
            return false;
        }

    }
}