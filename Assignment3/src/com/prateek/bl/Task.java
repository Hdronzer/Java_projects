package com.prateek.bl;

import static com.prateek.utils.Constants.ERROR_FILE_READ;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.prateek.dao.IFlightDetailsDao;
import com.prateek.dao.IMapTableDAO;
import com.prateek.entity.FlightDetails;
import com.prateek.entity.MapTable;
import com.prateek.map.MapData;

/*
 * class containing logic to read .csv file and store data
 */
public class Task implements Runnable{
	private long linesToSkip = 1;
	private long linesRead = 0;
	private Path filePath;
	private MapData data;
	private ArrayList<FlightDetails> list;
	private IFlightDetailsDao flightDao;
	private IMapTableDAO mapDao;
	
	private static final String TAG = "Task: ";
	
	public Task(Path filePath, long linesToSkip, IFlightDetailsDao flightDao,
			IMapTableDAO mapDao,MapData data) {
		this.filePath = filePath;
		this.linesToSkip = linesToSkip;
		this.list = new ArrayList<>();	
		this.flightDao = flightDao;
		this.mapDao = mapDao;
		this.data = data;
	}

	@Override
	public void run() {
		linesRead = linesToSkip -1;// remove the header of .csv file
		
		try(Stream<String> stream = Files.lines(filePath)) {
			stream
			.skip(linesToSkip)
			.forEach(line -> {
				writeData(line);
				linesRead++;
			});
			
			if(flightDao.saveFlightDetails(list)) {
				String fileName = filePath.getFileName().toString();
				data.getMap().put(fileName, linesRead);
				mapDao.saveOrUpdateTableRows(new MapTable(fileName,linesRead));
			}
		}catch (IOException | NumberFormatException ex) {
			System.out.println(TAG + ERROR_FILE_READ + filePath.getFileName());
			System.err.println(TAG + ex);
		}
	}
	
	private void writeData(String line) {
		
		FlightDetails details = new FlightDetails();
		String [] arr = line.split("\\|");
		
		details.setFlightNo(arr[0]);
		details.setDeparture(arr[1]);
		details.setArrival(arr[2]);
		details.setFlightDate(arr[3]);
		details.setFlightTime(arr[4]);
		details.setFlightDuration(Double.parseDouble(arr[5]));
		details.setFare(Double.parseDouble(arr[6]));
		details.setSeatAvailablity(arr[7]);
		details.setFlightClass(arr[8]);
		
		list.add(details);
	}
}
