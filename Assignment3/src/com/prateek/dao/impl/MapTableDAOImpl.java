package com.prateek.dao.impl;

import static com.prateek.utils.Constants.MAP_QUERY;
import static com.prateek.utils.Constants.ERROR_MAP_ROW_FETCH;
import static com.prateek.utils.Constants.ERROR_MAP_ROW_SAVE;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.dao.IMapTableDAO;
import com.prateek.entity.MapTable;

/*
 * DAO class to get rows from map_table
 */
@Repository
public class MapTableDAOImpl implements IMapTableDAO {
	@Autowired
	private SessionFactory factory;
	
	private static final String TAG = "MapTableDAOImpl: ";
	
	@Override
	@Transactional
	public List<MapTable> getTableRows() {
		List<MapTable> res = null;
		try(Session session = factory.openSession()) {
            Query<MapTable> query = session.createQuery(MAP_QUERY,MapTable.class);
            res = query.getResultList();
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_MAP_ROW_FETCH);
			 System.err.println(TAG + ex.getMessage());
		}
		return res;
	}

	@Override
	public void saveOrUpdateTableRows(MapTable table) {
		Transaction trx = null;
		
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            session.saveOrUpdate(table);
            trx.commit();
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_MAP_ROW_SAVE);
			 System.err.println(TAG + ex.getMessage());
			 
			 if(trx != null) trx.rollback();
		}
	}

}
