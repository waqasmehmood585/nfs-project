package com.nakisa.nlaAutomation.Listeners;

import com.nakisa.nlaAutomation.stepDefinitions.MasterHooks;
import org.apache.directory.api.util.Strings;
import org.apache.hadoop.hbase.protobuf.generated.ZooKeeperProtos;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {


    public static ThreadLocal<Integer> reTryCount = new ThreadLocal<>();

    @Override
    public boolean retry(ITestResult result) {
        if (reTryCount.get() < MasterHooks.configurationProperties.get().getReTryCount()) {
            return true;
        }
        reTryCount.remove();
        return false;
    }
}
	
	

