import java.util.Scanner;
public class guessANumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = input.nextInt();
        int guess = 0;
        while (guess!= number) {
            System.out.print("Enter a guess: ");
            guess = input.nextInt();
            if (guess < number) {
                System.out.println("Too low!");
            } else if (guess > number) {
                System.out.println("Too high!");
            }
        }
        System.out.println("You got it!");
    
    
    }
}
