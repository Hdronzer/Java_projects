package com.prateek.dao;

import java.util.List;

import com.prateek.entity.MapTable;

public interface IMapTableDAO {

	public List<MapTable> getTableRows();

	public void saveOrUpdateTableRows(MapTable table);
}
