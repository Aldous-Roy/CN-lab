import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String fromServer;
        String toServer;

        // Connect to the server at localhost on port 5050
        Socket clientSocket = new Socket("localhost", 5050);

        // Input and output streams for communication
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // User input
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true); // Output to server
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Input from server

        System.out.println("Connected to the server on port 5050");

        while (true) {
            // Receive a message from the server
            fromServer = inFromServer.readLine();
            if (fromServer == null || fromServer.equalsIgnoreCase("q")) {
                System.out.println("Server has closed the connection.");
                clientSocket.close();
                break;
            } else {
                System.out.println("RECEIVED: " + fromServer);
            }

            // Send a message to the server
            System.out.println("SEND (Type Q or q to Quit):");
            toServer = inFromUser.readLine();

            if (toServer.equalsIgnoreCase("q")) {
                outToServer.println(toServer);
                clientSocket.close();
                break;
            } else {
                outToServer.println(toServer);
            }
        }
    }
}