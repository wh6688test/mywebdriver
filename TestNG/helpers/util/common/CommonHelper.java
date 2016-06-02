package util.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.LogManager;

//http://stackoverflow.com/questions/5950557/good-examples-using-java-util-logging
public class CommonHelper {
	
    static void testLog(String logMsg, String fileName) throws IOException {
    	List<String>logMsgs=Arrays.asList(logMsg);
    	Path logFile=Paths.get(fileName);
    	Files.write(logFile, logMsgs, Charset.forName("UTF_8"), StandardOpenOption.APPEND);
    }
    public static void testLog(String logMsg) {
	       System.out.print(logMsg);
	}
 
    public Properties readTestProperties() {

		Properties prop = new Properties();
		InputStream input = null;
		try {
		  input = getClass().getClassLoader().getResourceAsStream("test.properties");
		  prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;

	}
    
    public Set<Object> getAllKeys(Properties prop) {
    	return prop.keySet();
    	
    }
    
    Map<String, String> getTestPropertyMap(Properties testProp) {   
    	Set<Object>keys=getAllKeys(testProp);
    	Map<String, String> map = new HashMap<String, String>();
    	for (Object keyObject : keys ) {
    		String key=(String)keyObject;
    		map.put(key, testProp.getProperty(key)); 		
    	}
    	return map;
    	
    }
    
	public static void loadLogger(String logPropertyFile) throws SecurityException, IOException {
        FileInputStream fis =  new FileInputStream(logPropertyFile);
        LogManager.getLogManager().readConfiguration(fis);
	}
	
    
}
