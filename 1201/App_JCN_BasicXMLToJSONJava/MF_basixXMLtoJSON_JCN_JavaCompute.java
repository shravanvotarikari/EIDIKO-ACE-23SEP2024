import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbJSON;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;

public class MF_basixXMLtoJSON_JCN_JavaCompute extends MbJavaComputeNode {

	public void evaluate(MbMessageAssembly inAssembly) throws MbException {
		MbOutputTerminal out = getOutputTerminal("out");
		MbOutputTerminal alt = getOutputTerminal("alternate");

		MbMessage inMessage = inAssembly.getMessage();
		
		MbElement inroot = inMessage.getRootElement().getLastChild(); //xmlnsc chaa msg ahe
		
		MbElement emp = inroot.getLastChild();
		
		MbElement fname = emp.getFirstChild(); //first child
		
		MbElement lname = fname.getNextSibling(); // next vala
		
		String emp_name = fname.getValueAsString()+" "+lname.getValueAsString(); //adding two name
		
		MbElement id = lname.getNextSibling();
		
		String emp_id = id.getValueAsString();
		
		MbElement dept = id.getNextSibling();
		
		String emp_dept = dept.getValueAsString();
		
		
		
		

		// create new empty message
		MbMessage outMessage = new MbMessage();
		MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly, outMessage);

		try {
			// optionally copy message headers
			copyMessageHeaders(inMessage, outMessage);
			// ----------------------------------------------------------
			// Add user code below
			
			
			MbElement outroot = outMessage.getRootElement();
			MbElement json = outroot.createElementAsLastChild(MbJSON.PARSER_NAME);
			MbElement data = json.createElementAsLastChild(MbJSON.OBJECT,"Data",null);
			
			
//			MbElement empdata = data.createElementAsLastChild(MbElement.TYPE_NAME, "Employee", null);
			data.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Emp_id", emp_id);
			data.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Emp_Name", emp_name);
			data.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "Emp_Dept", emp_dept);

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
