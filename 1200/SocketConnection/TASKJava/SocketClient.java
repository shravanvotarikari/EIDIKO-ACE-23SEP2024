import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

    public static String sendMessageToSocket(String host, int port, String message) {
        String response = "";
        try {
            // Create socket connection
            Socket socket = new Socket(host, port);
            
            // Send data to socket
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(message);

            // Receive response from socket
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            response = reader.readLine();
            
            // Close connection
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            response = "Error: " + e.getMessage();
        }
        return response;
    }
}
