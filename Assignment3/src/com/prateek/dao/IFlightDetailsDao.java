package com.prateek.dao;

import java.util.List;

import com.prateek.entity.FlightDetails;

public interface IFlightDetailsDao {

	public List<FlightDetails> getFlightDetails(FlightDetails details);
	
	public boolean saveFlightDetails(List<FlightDetails> list);
}
