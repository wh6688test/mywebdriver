package util.testng;

import java.util.logging.Logger;

import org.testng.ITestResult;

//import common.util.CommonHelper;



public class CustomerLog extends org.testng.TestListenerAdapter {
	
	//private int max_line=0;
	private final Logger log= Logger.getLogger(this.getClass().getPackage().getName());
	
	public void onTestSuccess(ITestResult tr) {
		  log.info(tr.getName() + " : Testcase Skipped ===\n");
	}
	
	public void onTestFailure(ITestResult tr) {
		   log.info(tr.getName() + " : Testcase Failed ===\n");
	}
	
	public void onTestSkipped(ITestResult tr) {
		   log.info(tr.getName() + " : Testcase Skipped ===\n");
	}

	
}
