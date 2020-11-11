package com.prateek.bl;

import static com.prateek.utils.Constants.PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prateek.dao.IFlightDetailsDao;
import com.prateek.dao.IMapTableDAO;
import com.prateek.map.MapData;

/*
 * class to execute data retrieval task from .csv files
 */
@Component
public class TaskExecution {
	
	private Path dirLocation;
	private ScheduledExecutorService executor;
	private ExecutorService service;
	@Autowired
	private MapData data;
	@Autowired
	private IFlightDetailsDao flightDao;
	@Autowired
	private IMapTableDAO mapDao;
	
	private static final String TAG = "TaskExecution: ";
	
	public TaskExecution() {
		dirLocation = Paths.get(PATH);
		executor = Executors.newScheduledThreadPool(1);
		service = Executors.newWorkStealingPool();
	}
	
	public void beiginTask() {
		executor.scheduleAtFixedRate(dataFetchTask, 0, 10, TimeUnit.SECONDS);
	}
	
	public void endTask() {
		executor.shutdown();
	}
	
	private List<Path> getFilesPath() {
		
		List<Path> filePath = new ArrayList<>();
		try(Stream<Path> stream = Files.list(dirLocation)) {
			stream
			.filter(file -> file.getFileName().toString().endsWith("csv"))
			.forEach(filePath::add);		
		}catch (IOException x) {
			System.out.println(TAG+"Operation could not complete");
			System.err.println(TAG + x);
		}
		return filePath;
	}
	
	private Runnable dataFetchTask = () -> {
		Stream<Path> stream = getFilesPath().stream();
		stream.forEach(file -> {
			String fileName = file.getFileName().toString();
			if(data.getMap().containsKey(fileName)) {
				long length = data.getMap().get(fileName);
				long lineCount = 0;
				try {
					lineCount = Files.lines(file).count();
				} catch (IOException e) {
					System.err.println(TAG + e);
					e.printStackTrace();
				}
				if((length+1) < lineCount)
					service.execute(new Task(file,length+1,flightDao,mapDao,data));
			}else {
				service.execute(new Task(file,1,flightDao,mapDao,data));
			}
		});
	};
	
}
