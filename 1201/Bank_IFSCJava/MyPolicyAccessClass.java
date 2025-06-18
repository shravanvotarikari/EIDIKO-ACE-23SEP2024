import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbPolicy;

public class MyPolicyAccessClass {
    public static String getPolicyProperty(String policyName, String propertyName) {
        try {
            return MbPolicy.getPolicy("UserDefined", policyName).getPropertyValueAsString(propertyName);
        } catch (MbException | NullPointerException e) {
            System.out.println("Error fetching policy '" + policyName + "': " + e);
            return null;
        }
    }
}