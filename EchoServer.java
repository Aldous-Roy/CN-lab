import java.io.*;
import java.net.*;

public class EchoServer {
    private ServerSocket server;

    // Constructor to initialize the server on a given port
    public EchoServer(int portnum) {
        try {
            server = new ServerSocket(portnum);
            System.out.println("EchoServer started on port " + portnum);
        } catch (IOException err) {
            System.out.println("Error initializing server: " + err.getMessage());
        }
    }

    // Method to handle incoming client connections
    public void serve() {
        try {
            while (true) {
                System.out.println("Waiting for a client...");
                Socket client = server.accept(); // Accepts a client connection
                System.out.println("Client connected: " + client.getInetAddress());

                // Streams to read from and write to the client
                BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter w = new PrintWriter(client.getOutputStream(), true);

                // Send welcome message to client
                w.println("Welcome to the Java EchoServer. Type 'bye' to close the connection.");

                String line;
                // Loop to echo client messages until "bye" is received
                while ((line = r.readLine()) != null) {
                    System.out.println("Received from client: " + line); // Logs message
                    w.println("Got: " + line); // Echo message back to client
                    if (line.trim().equalsIgnoreCase("bye")) {
                        break; // Close connection on "bye"
                    }
                }

                System.out.println("Client disconnected.");
                client.close(); // Close the client socket
            }
        } catch (IOException err) {
            System.err.println("Error during communication: " + err.getMessage());
        }
    }

    // Main method to start the server
    public static void main(String[] args) {
        int port = 9999; // Specify the port number
        EchoServer server = new EchoServer(port); // Create the server instance
        server.serve(); // Start serving clients
    }
}