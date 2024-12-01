import java.io.*;
import java.net.*;

class UDPServer {
    public static DatagramSocket serverSocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static byte[] buf = new byte[1024];
    public static int cport = 789, sport = 790;

    public static void main(String[] args) throws IOException {
        // Initialize the DatagramSocket on the server port
        serverSocket = new DatagramSocket(sport);
        dp = new DatagramPacket(buf, buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();

        System.out.println("Server is Running...");

        while (true) {
            // Receive a packet from the client
            serverSocket.receive(dp);
            String str = new String(dp.getData(), 0, dp.getLength());

            // Terminate if the client sends "STOP"
            if (str.equalsIgnoreCase("STOP")) {
                System.out.println("Terminated...");
                break;
            }

            // Display the received message from the client
            System.out.println("Client: " + str);

            // Get server response from user input
            System.out.print("Enter response: ");
            String response = dis.readLine();

            // Convert response to bytes and send it back to the client
            buf = response.getBytes();
            serverSocket.send(new DatagramPacket(buf, response.length(), ia, cport));
        }

        // Close the server socket
        serverSocket.close();
    }
}