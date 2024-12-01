import java.io.*;

class PingServer {
    public static void main(String[] args) {
        try {
            // Prompt the user to enter an IP address
            System.out.print("Enter the IP Address to be pinged: ");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String ip = userInput.readLine();

            // Create a process to execute the ping command
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("ping " + ip);

            // Get the input stream of the process
            InputStream inputStream = process.getInputStream();
            BufferedReader processOutput = new BufferedReader(new InputStreamReader(inputStream));

            // Read and display the ping results
            String line;
            System.out.println("Pinging " + ip + "...");
            while ((line = processOutput.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}