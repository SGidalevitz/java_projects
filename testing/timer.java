import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class timer {
    public static void main(String[] args) {
        System.out.println("How long do you want the timer to be?");
        Scanner input = new Scanner(System.in);
        int seconds = input.nextInt();
        setTimer(seconds);
        try {
            Desktop.getDesktop().browse(new URI("https://villagemerc.com/cdn/shop/products/owletblue_2_2048x2048.jpg?v=1631293406"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        

    
    }
    public static void setTimer(int seconds) {
        try {
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
