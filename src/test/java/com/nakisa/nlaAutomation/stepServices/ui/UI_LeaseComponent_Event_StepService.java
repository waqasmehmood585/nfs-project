package com.nakisa.nlaAutomation.stepServices.ui;

import com.nakisa.nlaAutomation.stepServices.LeaseComponent_Event_StepService;
import com.nakisa.nlaAutomation.utils.DriverFactory;

import org.springframework.stereotype.Component;

@Component
public class UI_LeaseComponent_Event_StepService extends DriverFactory implements LeaseComponent_Event_StepService {

	@Override
	public void createLeaseComponentEvent(String nameOfEvent) throws InterruptedException {
		c_leaseComponent_eventPage.get().create_LeaseComponent_Event(nameOfEvent);
	}

	@Override
	public void moveBetweenEntities(String level, String name) throws InterruptedException {
		c_leaseComponent_eventPage.get().moveBetweenEntities(level,name);
	}
	@Override
	public void checkingEditableTerm(){

		c_leaseComponent_eventPage.get().checkingEditableTerm();
	}

}
