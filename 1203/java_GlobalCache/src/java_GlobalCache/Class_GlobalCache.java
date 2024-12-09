package java_GlobalCache;

import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbGlobalMap;

public class Class_GlobalCache {
	public static void insertData(String key,byte[] data) {
		try {
			MbGlobalMap container = MbGlobalMap.getGlobalMap("containerVar");
			if(container.containsKey(key)) {
				container.update(key, data);
			}
			else {
				container.put(key, data);
			}
		} catch (MbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static byte[] getData  (String key) {
		try {
			MbGlobalMap globalMap = MbGlobalMap.getGlobalMap("containerVar");
		if (globalMap.containsKey(key)) {
			return (byte[])globalMap.get(key);			
			
		}
		} catch (MbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
