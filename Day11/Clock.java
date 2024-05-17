import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock {
    public static void main(String[] args) {
        // Define a formatter to format the time output
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        while (true) {
            // Get the current time
            LocalTime currentTime = LocalTime.now();
            
            // Format and print the current time
            System.out.println(currentTime.format(formatter));
            
            // Pause execution for one second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Handle the interrupted exception
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }
}
