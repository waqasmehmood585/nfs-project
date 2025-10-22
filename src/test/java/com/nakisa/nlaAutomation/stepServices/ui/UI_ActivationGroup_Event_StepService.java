package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.ActivationGroup_Event_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import org.springframework.stereotype.Component;

@Component
public class UI_ActivationGroup_Event_StepService extends DriverFactory implements ActivationGroup_Event_StepService {

	@Override
	public void createAGEvent(String agEventName, String modDate, String reason, String terminate, String penaltyAmount) {
		c_AG_EventPage.get().create_AG_Event(agEventName, modDate, reason, terminate, penaltyAmount);
	}
	@Override
	public void  deleteDraft(){
		c_AG_EventPage.get().deleteDraft();
	}


}
