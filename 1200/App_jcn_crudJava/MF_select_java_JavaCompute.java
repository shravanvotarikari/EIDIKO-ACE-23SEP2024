import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXMLNSC;
import com.ibm.broker.plugin.MbNode.JDBC_TransactionType;

public class MF_select_java_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessageAssembly outAssembly = null;
		try {
			// create new message as a copy of the input
			MbMessage outMessage = new MbMessage();
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);
			// ----------------------------------------------------------
			// Add user code below

			
			
			MbElement inroot = inMessage.getRootElement();
			MbElement Eid = inroot.getFirstElementByPath("XMLNSC/INFORMATION/EID");
			//MbElement Ename = inroot.getFirstElementByPath("XMLNSC/INFORMATION/ENAME");
			//MbElement Address = inroot.getFirstElementByPath("XMLNSC/INFORMATION/ADDRESS");
			
			//String EID = Eid.getValueAsString();
//			String ENAME = Ename.getValueAsString();
//			String ADDRESS = Address.getValueAsString();
			
			Connection conn = getJDBCType4Connection("xe", JDBC_TransactionType.MB_TRANSACTION_AUTO);
			
			//String query = "SELECT ID,ENAME,ADDRESS FROM INFORMATION WHERE ID = ?";
	
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION");
			
			MbElement xmlnsc = outMessage.getRootElement().createElementAsLastChild(MbXMLNSC.PARSER_NAME);
	         MbElement Root = xmlnsc.createElementAsLastChild(MbElement.TYPE_NAME,"Root",null);
			
	         
	         while (rs.next()) {
					String id = rs.getString("ID");
					String name = rs.getString("ENAME");
					String address = rs.getString("ADDRESS");
					
					MbElement emp = Root.createElementAsLastChild(MbElement.TYPE_NAME,"Root",null);
					
					emp.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"ID",id);
					emp.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"ENAME",name);
					emp.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"ADDRESS",address);
				}
	         
	         
	         
	         
	         
	         
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
