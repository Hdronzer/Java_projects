package com.prateek.model;

public class InputDetails {
	
	private String departure;
	private String arrival;
	private String flightDate;
	private String flightClass;
	private int outputPrefrence;
	
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	public int getOutputPrefrence() {
		return outputPrefrence;
	}
	public void setOutputPrefrence(int outputPrefrence) {
		this.outputPrefrence = outputPrefrence;
	}
}
