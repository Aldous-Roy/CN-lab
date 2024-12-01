import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to the server at localhost on port 9999
            Socket s = new Socket("127.0.0.1", 9999);
            System.out.println("Connected to the server.");

            // Streams to read from and write to the server
            BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter w = new PrintWriter(s.getOutputStream(), true);

            // Stream to read user input from the console
            BufferedReader con = new BufferedReader(new InputStreamReader(System.in));

            String line;
            // Read and print the server's welcome message
            while ((line = r.readLine()) != null) {
                System.out.println(line);
                break; // Exit after printing the welcome message
            }

            // Read user input, send it to the server, and print the server's response
            do {
                System.out.print("Enter message (Type 'bye' to quit): ");
                line = con.readLine(); // Read input from the console
                w.println(line); // Send user input to the server

                if (line.trim().equalsIgnoreCase("bye")) {
                    break; // Exit loop on "bye"
                }

                line = r.readLine(); // Read the server's response
                if (line != null) {
                    System.out.println("Server response: " + line);
                }
            } while (true);

            System.out.println("Disconnected from the server.");
            s.close(); // Close the socket
        } catch (IOException err) {
            System.err.println("Error: " + err.getMessage());
        }
    }
}