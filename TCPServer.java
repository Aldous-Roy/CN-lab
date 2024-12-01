import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) {
        String fromClient;
        String toClient;
        final int PORT = 5050; // Changed to a different port number

        try {
            // Create a server socket listening on a new port
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("TCPServer waiting for client on port " + PORT);

            // Continuously accept and handle client connections
            while (true) {
                Socket connected = server.accept();
                System.out.println("The client " + connected.getInetAddress() + ":" + connected.getPort() + " is connected");

                // Input and output streams
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // Server input
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connected.getInputStream())); // Client input
                PrintWriter outToClient = new PrintWriter(connected.getOutputStream(), true); // Server output

                while (true) {
                    // Server sends a message
                    System.out.println("SEND (Type Q or q to Quit):");
                    toClient = inFromUser.readLine(); // Read input from server terminal
                    if (toClient.equalsIgnoreCase("q")) { // Quit condition
                        outToClient.println(toClient); // Notify the client
                        connected.close(); // Close the connection
                        break;
                    } else {
                        outToClient.println(toClient); // Send the message to the client
                    }

                    // Server receives a message from the client
                    fromClient = inFromClient.readLine();
                    if (fromClient.equalsIgnoreCase("q")) { // Quit condition
                        connected.close(); // Close the connection
                        break;
                    } else {
                        System.out.println("RECEIVED: " + fromClient); // Display the received message
                    }
                }
            }
        } catch (BindException e) {
            System.err.println("Port " + PORT + " is already in use. Please use a different port.");
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}