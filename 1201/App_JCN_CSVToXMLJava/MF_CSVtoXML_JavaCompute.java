import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXMLNSC;

public class MF_CSVtoXML_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		MbMessageAssembly outAssembly = null;
		
		
		
		
		try {
			// optionally copy message headers
			//copyMessageHeaders(inMessage, outMessage);
			// ----------------------------------------------------------
			// Add user code below
			
			MbMessage outMessage = new MbMessage();
			outAssembly = new MbMessageAssembly(inAssembly, outMessage);
			// ----------------------------------------------------------
			// Add user code below
			
			/*
			 	 structure
			 	DFDL
			 		ValidateCSV
			 			header
			 			record
			 			...
			 			
			 */
			
			MbElement csv = inMessage.getRootElement().getLastChild().getLastChild();
			
			MbElement record = csv.getFirstChild().getNextSibling();
			
			MbElement xmlnsc = outMessage.getRootElement().createElementAsLastChild(MbXMLNSC.PARSER_NAME);
			MbElement employees = xmlnsc.createElementAsFirstChild(MbElement.TYPE_NAME,"Employees",null);
			
			while(record != null) {
				
				MbElement field1 = record.getFirstChild();
				MbElement field2 = field1.getNextSibling();
				MbElement field3 = field2.getNextSibling();
				MbElement field4 = field3.getNextSibling();
				MbElement field5 = field4.getNextSibling();
				
				MbElement employee = employees.createElementAsLastChild(MbElement.TYPE_NAME,"Employee",null);
				
				employee.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"employee_id",field1.getValue());
				employee.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"first_name",field2.getValue());
				employee.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"last_name",field3.getValue());
				employee.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"department",field4.getValue());
				employee.createElementAsLastChild(MbElement.TYPE_NAME_VALUE,"salary",field5.getValue());
				
				record = record.getNextSibling();
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

	public void copyMessageHeaders(MbMessage inMessage, MbMessage outMessage) throws MbException {
		MbElement outRoot = outMessage.getRootElement();

		// iterate though the headers starting with the first child of the root
		// element, stopping before the last child (body)
		MbElement header = inMessage.getRootElement().getFirstChild();
		while (header != null && header.getNextSibling() != null) {
			// copy the header and add it to the out message
			MbElement newHeader = outRoot.createElementAsLastChild(header.getParserClassName());
			newHeader.setName(header.getName());
			newHeader.copyElementTree(header);
			// move along to next header
			header = header.getNextSibling();
		}
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
