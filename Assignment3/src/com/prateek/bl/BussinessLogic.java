package com.prateek.bl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prateek.dao.IFlightDetailsDao;
import com.prateek.dao.IMapTableDAO;
import com.prateek.dao.IUserDAO;
import com.prateek.entity.FlightDetails;
import com.prateek.entity.MapTable;
import com.prateek.entity.User;
import com.prateek.map.MapData;
/*
 * class that contains the business logic
 */
@Component
public class BussinessLogic {
	@Autowired
	private IUserDAO userDao;
	@Autowired
	private IMapTableDAO mapDao;
	@Autowired
	private TaskExecution task;
	@Autowired
	private MapData data;
	@Autowired
	private IFlightDetailsDao flightDao;
	
	private static final String TAG = "BussinessLogic: ";
	
	// method to authenticate user
	public boolean authenticateUser(User user) {
		boolean result = true;
		List<User> list = userDao.getUser(user);
		
		if(list == null || list.isEmpty())
			result = false;
		
		return result;
	}
	
	public List<FlightDetails> getFlightDetails(FlightDetails input) {
		
		List<FlightDetails> list = flightDao.getFlightDetails(input);
		if(input.getFlightClass().equals("B") && list != null)
			adjustPremiumFare(list);
		
		return list;
	}
	
	@PostConstruct
	private void init() {
		List<MapTable> rows = mapDao.getTableRows();
		
		if(rows != null && !rows.isEmpty())
			rows.stream().forEach(row ->data.getMap().put(row.getFileName(),
				row.getRecordCount()));
		
		task.beiginTask();
	}
	
	@PreDestroy
	private void destroy() {
		task.endTask();
	}
	
	private void adjustPremiumFare(List<FlightDetails> list) {
		list.stream().forEach(flight -> {
			Double fare = flight.getFare();
			fare += fare*.4;
			flight.setFare(fare);
		});
	}
	
}
