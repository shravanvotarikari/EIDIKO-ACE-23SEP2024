package asymmetricED;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.ibm.broker.plugin.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FileNodeMsgFlopw_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
        // Get the message and its root element
        MbMessage inMessage = inAssembly.getMessage();
        MbElement root = inMessage.getRootElement();
        
        // Retrieve the binary content of the PDF from the message flow (ensure it's BLOB)
        MbElement blobElement = root.getFirstElementByPath("BLOB/BLOB");
        
        // Get the byte array from the BLOB element
        byte[] pdfData = (byte[]) blobElement.getValue();

        // Create an input stream from the byte array
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfData);

        MbMessageAssembly outAssembly = null;
try {
        	

        	PDDocument document = PDDocument.load(inputStream); // Attempt to load the PDF document

            // Load the PDF document using PDFBox
            
            // Use PDFTextStripper to extract text from the PDF
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String extractedText = pdfStripper.getText(document);

            // Close the PDF document after processing
            document.close();

            MbMessage outMessage =new MbMessage();
            MbElement outputRoot = outMessage.getRootElement();
            //outputRoot.createElementAsLastChild(MbJSON.PARSER_NAME);
            MbElement BLOB = outputRoot.createElementAsFirstChild(MbBLOB.PARSER_NAME);
            BLOB.createElementAsLastChild(MbElement.TYPE_NAME,"BLOB",extractedText.getBytes());

            // Create a new message as a copy of the input
            //MbMessage outMessage = new MbMessage(inMessage);
            outAssembly = new MbMessageAssembly(inAssembly, outMessage);

        } catch (IOException e) {
            throw new MbUserException(this, "evaluate()", "", "", "IOException: " + e.getMessage(), null);
        } catch (Exception e) {
            throw new MbUserException(this, "evaluate()", "", "", "Exception: " + e.toString(), null);
        }

        // Propagate the output message to the 'out' terminal
        getOutputTerminal("out").propagate(outAssembly);
    }

	
	

	

}
