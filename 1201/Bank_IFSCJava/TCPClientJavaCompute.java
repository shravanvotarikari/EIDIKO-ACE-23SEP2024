import java.io.*;
import java.net.Socket;
import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.*;

public class TCPClientJavaCompute extends MbJavaComputeNode {
    public void evaluate(MbMessageAssembly inAssembly) throws MbException {
        MbMessage inMessage = inAssembly.getMessage();
        MbMessage outMessage = new MbMessage(inMessage);
        MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly, outMessage);

       
        String serverIP = "localhost";  
        int serverPort = 8989;              

        try {
            
            MbElement rootElement = inMessage.getRootElement();
            String ifscCode = rootElement.getFirstElementByPath("XMLNSC/Root/ifsc").getValueAsString();
            String accountNo = rootElement.getFirstElementByPath("XMLNSC/Root/acc").getValueAsString();

            
            String requestData = "ifsc=" + ifscCode + ",acc=" + accountNo;

            try (Socket socket = new Socket(serverIP, serverPort);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

              
            	out.println(requestData); 
                
            	String response = in.readLine();
                
                MbElement outRoot = outMessage.getRootElement();
                MbElement xmlnscRoot = outRoot.createElementAsLastChild(MbElement.TYPE_NAME, "XMLNSC", null);
                MbElement dataElement = xmlnscRoot.createElementAsLastChild(MbElement.TYPE_NAME, "Root", null);
                dataElement.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Response", response);
                

            }

        } catch (IOException e) {
            throw new MbUserException(this, "evaluate()", "", "", "TCP connection error: " + e.getMessage(), null);
        }

        
        getOutputTerminal("out").propagate(outAssembly);
    }
}