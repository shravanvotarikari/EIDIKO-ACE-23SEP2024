import java.io.*;
import java.net.*;

public class DummyTCPServer {
	
	public static void startServer(){
		
		try{
		
		int port = 8989;  // Must match the JavaCompute node's port

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Dummy TCP Server started on port " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) { // Automatically closes socket
                System.out.println("Connection received from " + clientSocket.getInetAddress());

                // Read data from the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                StringBuilder receivedData = new StringBuilder();
                String line;
                
                while ((line = in.readLine()) != null) {  // Read entire message
                    receivedData.append(line).append("\n");
                }

                System.out.println("Received: " + receivedData.toString().trim());

                // Send response
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Hello from Dummy Backend!");

            }
			catch (IOException e) {
                System.err.println("Error handling client connection: " + e.getMessage());
            }
			catch (Exception e) {
                System.err.println("Error handling client connection: " + e.getMessage());
            }
        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
    public static void main(String[] args){
        DummyTCPServer.startServer();
    }
}