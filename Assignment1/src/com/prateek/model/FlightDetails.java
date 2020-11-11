package com.prateek.model;

/*
 * class to represent excel file data
 */
public class FlightDetails {

	private String flightNo;
	private String departure;
	private String arrival;
	private String flightDate;
	private String flightTime;
	private Double flightDuration;
	private Double fare;
	private String seatAvailablity;
	private String flightClass;
	
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
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
	public String getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}
	public Double getFlightDuration() {
		return flightDuration;
	}
	public void setFlightDuration(Double flightDuration) {
		this.flightDuration = flightDuration;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public String getSeatAvailablity() {
		return seatAvailablity;
	}
	public void setSeatAvailablity(String seatAvailablity) {
		this.seatAvailablity = seatAvailablity;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	
}
