package com.prateek.dao.impl;

import static com.prateek.utils.Constants.FLIGHT_QUERY_FARE;
import static com.prateek.utils.Constants.FLIGHT_QUERY_DURATION;
import static com.prateek.utils.Constants.ERROR_FLIGHT_FETCH;
import static com.prateek.utils.Constants.ERROR_BATCH_SAVE;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.dao.IFlightDetailsDao;
import com.prateek.entity.FlightDetails;

/*
 * DAO class to perform operation on flight_details table
 */
@Repository
public class FlightDetailsDAOImpl implements IFlightDetailsDao {
	@Autowired
	private SessionFactory factory;

	private final int BATCH_SIZE = 20;
	private static final String TAG = "FlightDetailsDAOImpl: ";
	
	@Override
	public List<FlightDetails> getFlightDetails(FlightDetails input) {
		List<FlightDetails> res = null;
		Transaction trx = null;
		try(Session session = factory.getCurrentSession()) {
			trx = session.beginTransaction();
			Query<FlightDetails> query;
			if(input.getSortPreference() == 1)
				query = session.createQuery(FLIGHT_QUERY_FARE,FlightDetails.class);
			else
				query = session.createQuery(FLIGHT_QUERY_DURATION,FlightDetails.class);
			
			query.setParameter("departure", input.getDeparture());
			query.setParameter("arrival", input.getArrival());
			query.setParameter("flightDate", input.getFlightDate());
			query.setParameter("flightClass", input.getFlightClass());
             
            res = query.getResultList();
            trx.commit();
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_FLIGHT_FETCH);
			 System.err.println(TAG + ex.getMessage());
		}
		return res;
	}

	@Override
	public boolean saveFlightDetails(List<FlightDetails> list) {
		boolean result = false;
		Transaction trx = null;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
			System.out.println("save called with"+list.size());
			for(int i = 0;i<list.size();i++) {
				if (i > 0 && i % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
		        }
				session.save(list.get(i));
			}
			System.out.println("persist done");
			trx.commit();
			result = true;
		}catch(Exception ex) {	
			System.err.println(TAG + ERROR_BATCH_SAVE);
			System.err.println(TAG + ex.getMessage());
			
			if(trx != null) trx.rollback();
		}
		return result;
	}

}
