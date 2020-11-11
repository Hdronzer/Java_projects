package com.prateek.fatory;

import com.prateek.code.SearchFlight;
import com.prateek.code.TaskExecution;
import com.prateek.model.FlightDetails;
import com.prateek.model.InputDetails;

public class SimpleFactory {
	
	private SimpleFactory() {}

	public static InputDetails getInputDetails() {
		return new InputDetails();
	}
	
	public static FlightDetails getFlightDetails() {
		return new FlightDetails();
	}
	
	public static TaskExecution getTaskExecution() {
		return new TaskExecution();
	}
	
	public static SearchFlight getSearchInstance() {
		return new SearchFlight();
	}
}
