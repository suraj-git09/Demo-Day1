import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Stopwatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER to start the stopwatch.");
        scanner.nextLine(); // Wait for the user to press ENTER to start

        Instant startTime = Instant.now();
        System.out.println("Stopwatch started. Press ENTER to stop the stopwatch.");

        // Create a separate thread to handle the timing
        Thread stopwatchThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Instant currentTime = Instant.now();
                Duration elapsed = Duration.between(startTime, currentTime);
                
                long hours = elapsed.toHours();
                long minutes = elapsed.toMinutes() % 60;
                long seconds = elapsed.getSeconds() % 60;
                
                System.out.printf("Elapsed time: %02d:%02d:%02d\r", hours, minutes, seconds);
                
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Re-interrupt the thread if interrupted
                }
            }
        });

        stopwatchThread.start();
        scanner.nextLine(); // Wait for the user to press ENTER to stop

        stopwatchThread.interrupt(); // Stop the thread
        try {
            stopwatchThread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the final elapsed time
        Instant endTime = Instant.now();
        Duration finalElapsed = Duration.between(startTime, endTime);

        long hours = finalElapsed.toHours();
        long minutes = finalElapsed.toMinutes() % 60;
        long seconds = finalElapsed.getSeconds() % 60;

        System.out.printf("Final elapsed time: %02d:%02d:%02d\n", hours, minutes, seconds);
        scanner.close();
    }
}

