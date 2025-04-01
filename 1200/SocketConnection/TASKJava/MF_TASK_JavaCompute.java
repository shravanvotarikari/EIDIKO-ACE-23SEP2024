import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbPolicy;
import com.ibm.broker.plugin.MbUserException;

public class MF_TASK_JavaCompute extends MbJavaComputeNode {

	@Override
	public void evaluate(MbMessageAssembly arg0) throws MbException {
		// TODO Auto-generated method stub
		
	}

	 public static String getPolicyProperty1(String policyName, String propertyName ) {
		    String resultPropertyValue = null; 
		    try {
		      MbPolicy myPol = MbPolicy.getPolicy("UserDefined", policyName);
		      if (myPol != null) {
		        resultPropertyValue = myPol.getPropertyValueAsString(propertyName);
		        if (resultPropertyValue == null) {
		          System.out.println("Unable to find property '"+propertyName+"' in UserDefined policy with name '"+policyName+"'");
		        } else {
		          System.out.println("Found property '"+propertyName+"' with value '"+resultPropertyValue+"' in UserDefined policy with name '"+policyName+"'");
		        }
		      } else {
		        System.out.println("Unable to find UserDefined policy with name '"+policyName+"'");
		      }
		    } catch (MbException mbe) {
		      System.out.println("Exception caught trying to find UserDefined policy with name '"+policyName+"'. Exception details: '"+mbe.toString()+"'"); 
		    }
		    return resultPropertyValue;
		}

}
