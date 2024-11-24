import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbXMLNSC;

public class JSON_TO_CSV_JavaCompute extends MbJavaComputeNode {

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
			MbElement jsonRoot = inMessage.getRootElement().getLastChild().getFirstChild();
            MbElement employeesArray = jsonRoot.getFirstChild();

         // Create CSV body in output message
            MbElement csvParser = outMessage.getRootElement().createElementAsLastChild("BLOB");
            MbElement csvData = csvParser.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, "BLOB", null);


            // Add CSV header
            StringBuilder csvContent = new StringBuilder();
            csvContent.append("EmployeeID,FirstName,LastName,Department,Salary\n");

            // Traverse through each employee in JSON array and build CSV rows
            MbElement employeeElement = employeesArray.getFirstChild();
            while (employeeElement != null) {
                // Retrieve each employee's details
                String employeeId = employeeElement.getFirstElementByPath("employee_id").getValueAsString();
                String firstName = employeeElement.getFirstElementByPath("first_name").getValueAsString();
                String lastName = employeeElement.getFirstElementByPath("last_name").getValueAsString();
                String department = employeeElement.getFirstElementByPath("department").getValueAsString();
                String salary = employeeElement.getFirstElementByPath("salary").getValueAsString();

                // Append each employee's data as a CSV row
                csvContent.append(employeeId).append(",");
                csvContent.append(firstName).append(",");
                csvContent.append(lastName).append(",");
                csvContent.append(department).append(",");
                csvContent.append(salary).append("\n");

                // Move to the next employee in the JSON array
                employeeElement = employeeElement.getNextSibling();
            }

            // Set CSV data to output message
            csvData.setValue(csvContent.toString().getBytes());

            // Propagate the output message
            out.propagate(outAssembly);
            

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
