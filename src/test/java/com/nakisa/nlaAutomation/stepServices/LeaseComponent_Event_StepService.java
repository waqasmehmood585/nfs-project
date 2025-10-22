package com.nakisa.nlaAutomation.stepServices;

public interface LeaseComponent_Event_StepService {

	void createLeaseComponentEvent(String nameOfEvent) throws InterruptedException;

	void moveBetweenEntities(String level,String name) throws InterruptedException;
	void checkingEditableTerm();
}
