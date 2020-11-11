package com.prateek.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

/*
 * class to keep track of number of files and the number of records they hold
 */
@Component
public class MapData {
	private ConcurrentMap<String, Long> map;
	
	public MapData() {
		map = new ConcurrentHashMap<>();
	}

	public ConcurrentMap<String, Long> getMap() {
		return map;
	}
}
