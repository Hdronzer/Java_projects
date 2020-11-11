package com.prateek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * class to store hashMap key value as columns
 */
@Entity
@Table(name="map_table")
public class MapTable {

	@Id
	@Column(name="file_name",nullable = false)
	private String fileName;
	
	@Column(name="record_count")
	private long recordCount;
	
	public MapTable() {}
	
	public MapTable(String fileName, long recordCount) {
		this.fileName = fileName;
		this.recordCount = recordCount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
}
