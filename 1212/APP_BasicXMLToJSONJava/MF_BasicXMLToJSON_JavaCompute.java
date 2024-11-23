import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbOutputTerminal;
import com.ibm.broker.plugin.MbUserException;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbJSON;

public class MF_BasicXMLToJSON_JavaCompute extends MbJavaComputeNode {

    public void evaluate(MbMessageAssembly inAssembly) throws MbException {
        MbOutputTerminal out = getOutputTerminal("out");
        
        MbMessage inMessage = inAssembly.getMessage();
        MbMessage outMessage = new MbMessage();
        MbMessageAssembly outAssembly = new MbMessageAssembly(inAssembly, outMessage);
        
        try {
            MbElement inputRoot = inMessage.getRootElement();
            MbElement xmlData = inputRoot.getLastChild(); 

            MbElement outputRoot = outMessage.getRootElement();
            MbElement jsonData = outputRoot.createElementAsLastChild(MbJSON.PARSER_NAME);
            MbElement jsonRoot = jsonData.createElementAsLastChild(MbElement.TYPE_NAME, MbJSON.DATA_ELEMENT_NAME, null);
            
            convertXmlToJson(xmlData, jsonRoot);

        } catch (MbException e) {
            throw e; 
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            
            throw new MbUserException(this, "evaluate()", "", "", e.toString(), null);
        }
        
        out.propagate(outAssembly);
    }

    private void convertXmlToJson(MbElement xmlElement, MbElement jsonParent) throws MbException {
      
        MbElement child = xmlElement.getFirstChild();
        
        while (child != null) {
            String name = child.getName();
            String value = (child.getValue() != null) ? child.getValue().toString() : null;
            
            if (value != null) {
                jsonParent.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, name, value);
            } else {
                MbElement newJsonObject = jsonParent.createElementAsLastChild(MbElement.TYPE_NAME, name, null);
                convertXmlToJson(child, newJsonObject);
            }
            child = child.getNextSibling();
        }
    }
}
