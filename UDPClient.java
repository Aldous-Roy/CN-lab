import java.io.*;
import java.net.*;

class UDPClient {
    public static DatagramSocket clientSocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static byte[] buf = new byte[1024];
    public static int cport = 789, sport = 790;

    public static void main(String[] args) throws IOException {
        // Initialize the DatagramSocket on the client port
        clientSocket = new DatagramSocket(cport);
        dp = new DatagramPacket(buf, buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();

        System.out.println("Client is Running... Type 'STOP' to quit");

        while (true) {
            // Read user input from the console
            String str = new String(dis.readLine());

            // Convert the input string to bytes and send it to the server
            buf = str.getBytes();

            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                // Send the STOP message to the server and exit the loop
                clientSocket.send(new DatagramPacket(buf, str.length(), ia, sport));
                break;
            }

            // Send the message to the server
            clientSocket.send(new DatagramPacket(buf, str.length(), ia, sport));

            // Receive the server's response
            clientSocket.receive(dp);

            // Convert the response bytes to a string and print it
            String str2 = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Server: " + str2);
        }

        // Close the socket after communication ends
        clientSocket.close();
    }
}