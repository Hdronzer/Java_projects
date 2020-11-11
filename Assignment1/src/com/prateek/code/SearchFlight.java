package com.prateek.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.prateek.map.FileData;
import com.prateek.model.FlightDetails;
import com.prateek.model.InputDetails;

/*
 * class to perform search and display result
 */
public class SearchFlight {
	private FileData data;
	private boolean flag = false;
	
	public SearchFlight() {
		data = FileData.getInstance();
	}
	
	public void serchRecords(InputDetails input) {
		
		flag = false;
		int sortOrder = input.getOutputPrefrence();
		Predicate<FlightDetails> [] p = new Predicate[4];
		// predicate conditions to return matching records
		p[0] = details-> details.getArrival().equalsIgnoreCase(input.getArrival());
		p[1] = details-> details.getDeparture().equalsIgnoreCase(input.getDeparture());
		p[2] = details-> details.getFlightDate().equalsIgnoreCase(input.getFlightDate());
		p[3] = details-> details.getFlightClass().contains(input.getFlightClass());
		
		
		if(sortOrder == 1)
			sortUsingFare(p,input);
		else
			sortUsingFareAndDuration(p,input);
		
		if(!flag)
			System.out.println("No Flights available according to your preference");

	}
	
	private void sortUsingFareAndDuration(Predicate<FlightDetails> [] p, InputDetails input) {
		
		Stream<Map.Entry<String, ArrayList<FlightDetails>>> stream = data.map.entrySet().stream();
		stream
		.forEach(e -> {
			String key = e.getKey();
			e.getValue().stream()
			.filter(p[0].and(p[1]).and(p[2]).and(p[3]))
			.sorted(Comparator.comparing(FlightDetails::getFare)
					.thenComparing(FlightDetails::getFlightDuration))
			.forEach(flightDetails -> {
				if(flightDetails != null)
					flag = true;
				displayResult(key,flightDetails,input);
			});
		});
	}
	
	private void sortUsingFare(Predicate<FlightDetails> [] p, InputDetails input) {
		
		Stream<Map.Entry<String, ArrayList<FlightDetails>>> stream = data.map.entrySet().stream();
		stream
		.forEach(e -> {
			String key = e.getKey();
			e.getValue().stream()
			.filter(p[0].and(p[1]).and(p[2]).and(p[3]))
			.sorted(Comparator.comparing(FlightDetails::getFare))
			.forEach(flightDetails -> {
				if(flightDetails != null)
					flag = true;
				displayResult(key,flightDetails,input);
			});
		});
	}
	
	private void displayResult(String key, FlightDetails flightDetails, InputDetails input) {
		
		String separator = "| ";
		Double fare = flightDetails.getFare();
		// increasing fare if business class
		if(input.getFlightClass().equals("B"))
			fare += fare*.4;
		
		System.out.println(key.substring(0,key.length()-4) + ":-");
		
		System.out.println("FLIGHT_NO:" + flightDetails.getFlightNo() + separator
						+ "DEP_LOC:" + flightDetails.getDeparture() + separator
						+ "ARR_LOC:" + flightDetails.getArrival() + separator
						+ "FLIGHT_DATE:" + flightDetails.getFlightDate() + separator
						+ "FLIGHT_TIME:" + flightDetails.getFlightTime() + separator
						+ "DURATION:" + flightDetails.getFlightDuration() + separator
						+ "FARE:" + fare + separator
						+ "SEAT_AVAIL:" + flightDetails.getSeatAvailablity() + separator
						+ "CLASS:" + flightDetails.getFlightClass());
	}
	
	
}
