package com.nakisa.nlaAutomation.stepServices;

public interface ActivationGroup_Event_StepService {

	void createAGEvent(String agEventName, String modDate, String reason, String terminate,
					   String penaltyAmount) throws InterruptedException;


    void deleteDraft();
}
