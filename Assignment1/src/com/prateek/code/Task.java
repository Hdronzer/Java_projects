package com.prateek.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.prateek.fatory.SimpleFactory;
import com.prateek.map.FileData;
import com.prateek.model.FlightDetails;

/*
 * class containing logic to read .csv file and store data
 */
public class Task implements Runnable{
	private Path filePath;
	private FileData data;
	private ArrayList<FlightDetails> list;
	private int linesToSkip = 1;
	
	private static final String TAG = "Task: ";
	
	public Task(Path filePath, int linesToSkip) {
		this.filePath = filePath;
		this.linesToSkip = linesToSkip;
		data = FileData.getInstance();
		if(linesToSkip == 1)
			list = new ArrayList<>();
		else {
			list = data.map.get(filePath.getFileName().toString());
		}
	}

	@Override
	public void run() {
		
		try(Stream<String> stream = Files.lines(filePath)) {
			stream
			.skip(linesToSkip)
			.forEach(line -> writeData(line));
			data.map.put(filePath.getFileName().toString(), list);
		}catch (IOException | NumberFormatException ex) {
			System.out.println(TAG + "Data read operation could not complete for file " + filePath.getFileName());
			System.err.println(TAG + ex);
		}
	}
	
	private void writeData(String line) throws NumberFormatException {
		
		FlightDetails details = null;
		String [] arr = line.split("\\|");
		
		details = SimpleFactory.getFlightDetails();
		
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
