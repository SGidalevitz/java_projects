import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class macro {
    private static boolean isWatching = true;
    private static boolean overrideKeyPressed = false;

    public static void main(String[] args) {
        try {
            // Specify the Discord URI
            URI discordUri = new URI("discord://");

            // Check if Desktop is supported
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Check if the URI is supported (can be opened)
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(discordUri);
                } else {
                    System.out.println("Desktop browse action not supported.");
                }
            } else {
                System.out.println("Desktop is not supported.");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            final AtomicInteger count = new AtomicInteger(0);
            Robot robot = new Robot();
            int cd = 3000;
            int timesBetweenSells = 10;
            Timer timer = new Timer(150, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int currentCount = count.getAndIncrement();
                    
                    if (!isWatching) {
                        return; // If not watching, skip typing '/fish'
                    }

                    // Type '/fish' and press Enter
                    int[] keyCodes = {
                        KeyEvent.VK_SLASH, KeyEvent.VK_F, KeyEvent.VK_I, KeyEvent.VK_S, KeyEvent.VK_H, KeyEvent.VK_ENTER, KeyEvent.VK_ENTER
                    };
                    int[] keyCodesAlt = {
                        KeyEvent.VK_SLASH, KeyEvent.VK_S, KeyEvent.VK_E, KeyEvent.VK_L, KeyEvent.VK_L, KeyEvent.VK_SPACE, KeyEvent.VK_A, KeyEvent.VK_ENTER, KeyEvent.VK_ENTER
                    };
                    int[] numKeyCodes = {
                        KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9
                    };
                    
                    if (currentCount % timesBetweenSells == 0) {
                        for (int keyCode : keyCodesAlt) {
                            robot.keyPress(keyCode);
                            robot.keyRelease(keyCode);
                        }
                    } else {
                        for (int keyCode : keyCodes) {
                            robot.keyPress(keyCode);
                            robot.keyRelease(keyCode);
                            
                        }
                        robot.keyPress(numKeyCodes[timesBetweenSells - currentCount % timesBetweenSells]);
                        robot.keyRelease(numKeyCodes[timesBetweenSells - currentCount % timesBetweenSells]);
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                        try {
                            Thread.sleep(cd); // Simulate some work
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            // Create a JFrame to listen for key events
            JFrame frame = new JFrame("Key Listener");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // Handle keyTyped event if needed
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // Check if the 'O' key is pressed
                    if (e.getKeyChar() == '\\') {
                        // Override key is pressed
                        overrideKeyPressed = true;
                        System.out.println("Override key pressed!");
                        // Add your custom action here
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // Handle keyReleased event if needed
                }
            });

            // Start the timer
            timer.start();

            // Simulate your main program here
            for (int i = 0; i < 1000; i++) {
                System.out.println("Main program is running... (" + i + "/1000)");
                try {
                    Thread.sleep(1000); // Simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Stop the background thread and timer
            isWatching = false;
            timer.stop();

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
