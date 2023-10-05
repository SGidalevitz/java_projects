import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class timer {
    public static void main(String[] args) {
        //ask user for length of timer
        System.out.println("How long do you want the timer to be?");
        Scanner input = new Scanner(System.in);
        int seconds = input.nextInt();
        //call method to set timer
        setTimer(seconds);
        //open google chrom
        try {
            Desktop.getDesktop().browse(new URI("https://villagemerc.com/cdn/shop/products/owletblue_2_2048x2048.jpg?v=1631293406"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        

    
    }
    //method for setting a timer
    public static void setTimer(int seconds) {
        try {
            //print out seconds left after each second
            for (int i = 0; i < seconds; i++) {
                System.out.println(seconds - i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Timer is over!");
    }
}
