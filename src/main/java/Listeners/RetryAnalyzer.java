package Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retry_count = 3;

	public boolean retry(ITestResult result) {
		if (counter < retry_count) {
			counter++;
			return true;
		}
		return false;
	}

}
