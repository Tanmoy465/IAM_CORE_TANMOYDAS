/**
 * 
 */
package fr.epita.iamcoreTDas.tests.services.logger;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author ZAC
 *
 */
public class TestLog {
	
	static final Logger logger = LogManager.getLogger(TestLog.class);

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, IOException {		
		logger.info("essai");
	}

}
