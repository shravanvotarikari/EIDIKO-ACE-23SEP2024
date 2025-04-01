
import java.io.*;
import java.net.*;

public class SocketHandler {

    
    public static String sendMessageToServer(String serverHost, int port, String message) {
        String response = "";
        try (Socket socket = new Socket(serverHost, port)) {
            // Send data to server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println(message);

            // Receive response from server
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            response = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            response = "Error: " + e.getMessage();
        }
        return response;
    }
}
