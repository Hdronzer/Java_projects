package com.prateek.entity;

import static com.prateek.utils.Constants.VALID_REQUIRD;
import static com.prateek.utils.Constants.VALID_CHARACTER;
import static com.prateek.utils.Constants.VALID_DATE;
import static com.prateek.utils.Constants.VALID_PREFERENCE;
import static com.prateek.utils.Constants.VALID_FLIGHT_CLASS;
import static com.prateek.utils.Constants.PATTERN_DATE;
import static com.prateek.utils.Constants.PATTERN_CHARACTER;
import static com.prateek.utils.Constants.PATTERN_CLASS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/*
 * class to represent excel file data
 */
@Entity
@Table(name="flight_details")
public class FlightDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private int id;
	
	@Column(name="flight_No")
	private String flightNo;
	
	@NotNull(message=VALID_REQUIRD)
	@Pattern(regexp=PATTERN_CHARACTER,message=VALID_CHARACTER)
	@Column(name="departure")
	private String departure;
	
	@NotNull(message=VALID_REQUIRD)
	@Pattern(regexp=PATTERN_CHARACTER,message=VALID_CHARACTER)
	@Column(name="arrival")
	private String arrival;
	
	@NotNull(message=VALID_REQUIRD)
	@Pattern(regexp=PATTERN_DATE,message=VALID_DATE)
	@Column(name="flight_date")
	private String flightDate;
	
	@Column(name="flight_time")
	private String flightTime;
	
	@Column(name="flight_duration")
	private Double flightDuration;
	
	@Column(name="fare")
	private Double fare;
	
	@Column(name="seat_availablity")
	private String seatAvailablity;
	
	@NotNull(message=VALID_REQUIRD)
	@Pattern(regexp=PATTERN_CLASS,message=VALID_FLIGHT_CLASS)
	@Column(name="flight_class")
	private String flightClass;
	
	@Transient
	@NotNull(message=VALID_REQUIRD)
	@Min(value=1,message=VALID_PREFERENCE)
	@Max(value=2,message=VALID_PREFERENCE)
	private int sortPreference;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	public int getSortPreference() {
		return sortPreference;
	}
	public void setSortPreference(int sortPreference) {
		this.sortPreference = sortPreference;
	}
	
}
