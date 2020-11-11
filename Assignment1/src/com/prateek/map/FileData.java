package com.prateek.map;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import com.prateek.model.FlightDetails;

public class FileData {
	private static volatile FileData instance;
	public ConcurrentHashMap<String, ArrayList<FlightDetails>> map;
	
	private FileData() {
		map = new ConcurrentHashMap<>();
	}
	
	public static FileData getInstance() {
		if(instance == null) {
			synchronized (FileData.class) {
				if(instance == null) {
					instance = new FileData();
				}
			}
		}
		return instance;
	}

}
