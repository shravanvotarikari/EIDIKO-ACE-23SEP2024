import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ibm.broker.config.appdev.Connection;
import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;


public class Mf_itext_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessageAssembly outAssembly = null;
		try {
			// create new message as a copy of the input
			MbMessage outMessage = new MbMessage(inMessage);
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);
			// ----------------------------------------------------------
			// Add user code below
			MbElement xmlnsc = inMessage.getRootElement().getLastChild();
			MbElement ACCNAME = xmlnsc.getFirstChild().getFirstChild();
			
			java.sql.Connection conn =  getJDBCType4Connection("{JDBC_Policy}:JDBC", JDBC_TransactionType.MB_TRANSACTION_AUTO);
			
			String query = "SELECT * FROM BANKDETAIL AS B where B.NAME = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ACCNAME.getValueAsString());
			ResultSet rs = ps.executeQuery();
			
			
			
//			Transaction obj = new Transaction("2025-01-15", "Credit", 1000.50);
//			Transaction obj2 = new Transaction("2025-01-16", "Debit", 200.75);
//
//			
//			List<Transaction> transactions = new ArrayList<Transaction>();
//			transactions.add(obj);
//			transactions.add(obj2);
//			
			
		//	generatePDF("123456",transactions);
			
			
			Document document = new Document();
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/temp/BankStatement.pdf"));
            document.open();

            document.add(new Paragraph("Bank Details"));
            document.add(new Paragraph("-------------------------------------------------------------"));

            while (rs.next()) {
            	
            	//String accountId = rs.getString("ACCOUNT_ID");
            	
            	
                String ACCOUNT_NAME = rs.getString("NAME");
                int ACCOUNT_NUMBER = rs.getInt("ACC_NUM");
                int IFSC_CODE = rs.getInt("IFSC_CODE");
                int PHONE_NO = rs.getInt("PHONE_NO");
                String ACC_TYPE = rs.getString("ACC_TYPE");
                String ADDRESS = rs.getString("ADDRESS");
                String Date_of_Birth = rs.getString("DOB");

                // Add row details to the PDF
                document.add(new Paragraph( "NAME: " + ACCOUNT_NAME + ",ACC_NUM: "+ ACCOUNT_NUMBER + ",IFSC_CODE: " + IFSC_CODE + ",PHONE_NO" + PHONE_NO + ",ACC_TYPE: " + ACC_TYPE + ",ADDRESS: " + ADDRESS + ",DOB: " +Date_of_Birth));
            }
            
            
            document.close();
            writer.close();

            System.out.println("Bank Details generated Successfully");
			
			


			// End of user code
			// ----------------------------------------------------------
		} catch (MbException e) {
			// Re-throw to allow Broker handling of MbException
			throw e;
		} catch (RuntimeException e) {
			// Re-throw to allow Broker handling of RuntimeException
			throw e;
		} catch (Exception e) {
			// Consider replacing Exception with type(s) thrown by user code
			// Example handling ensures all exceptions are re-thrown to be handled in the flow
			throw new MbUserException(this, "evaluate()", "", "", e.toString(), null);
		}
		// The following should only be changed
		// if not propagating message to the 'out' terminal
		out.propagate(outAssembly);

	}

	
	

//	private void generatePDF(String accId, List<Transaction> transactions) {
//		
//		try {
//			Document document = new Document();
//			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("C:/temp/BankStatement.pdf"));
//			document.open();
//			document.add(new Paragraph("Bank Statement"));
//			document.add(new Paragraph("Account ID: " + accId));
//			for (Transaction txn : transactions) {
//			    document.add(new Paragraph(txn.getDate() + " | " + txn.getType() + " | " + txn.getAmount()));
//			}
//			document.close();
//			writer.close();
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	
//	}
//
////	
	
	/**
	 * onPreSetupValidation() is called during the construction of the node
	 * to allow the node configuration to be validated.  Updating the node
	 * configuration or connecting to external resources should be avoided.
	 *
	 * @throws MbException
	 */
	@Override
	public void onPreSetupValidation() throws MbException {
	}

	/**
	 * onSetup() is called during the start of the message flow allowing
	 * configuration to be read/cached, and endpoints to be registered.
	 *
	 * Calling getPolicy() within this method to retrieve a policy links this
	 * node to the policy. If the policy is subsequently redeployed the message
	 * flow will be torn down and reinitialized to it's state prior to the policy
	 * redeploy.
	 *
	 * @throws MbException
	 */
	@Override
	public void onSetup() throws MbException {
	}

	/**
	 * onStart() is called as the message flow is started. The thread pool for
	 * the message flow is running when this method is invoked.
	 *
	 * @throws MbException
	 */
	@Override
	public void onStart() throws MbException {
	}

	/**
	 * onStop() is called as the message flow is stopped. 
	 *
	 * The onStop method is called twice as a message flow is stopped. Initially
	 * with a 'wait' value of false and subsequently with a 'wait' value of true.
	 * Blocking operations should be avoided during the initial call. All thread
	 * pools and external connections should be stopped by the completion of the
	 * second call.
	 *
	 * @throws MbException
	 */
	@Override
	public void onStop(boolean wait) throws MbException {
	}

	/**
	 * onTearDown() is called to allow any cached data to be released and any
	 * endpoints to be deregistered.
	 *
	 * @throws MbException
	 */
	@Override
	public void onTearDown() throws MbException {
	}

}
