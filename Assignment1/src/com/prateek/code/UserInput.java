package com.prateek.code;

import java.util.Scanner;

import com.prateek.fatory.SimpleFactory;
import com.prateek.model.InputDetails;

/*
 * Class to get input from user
 */
public class UserInput {

	public static void main(String[] args) {
		
		char response = 'Y';
		InputDetails details = SimpleFactory.getInputDetails();
		TaskExecution task = SimpleFactory.getTaskExecution();
		SearchFlight search = SimpleFactory.getSearchInstance();
		
		task.beiginTask();
		
		try (Scanner input = new Scanner(System.in)){
			
			while(response == 'y' || response == 'Y') {
				displayConsole(input, details);
				
				search.serchRecords(details);
				
				System.out.println("Do you want to search more y/n ?");
				response = input.next().charAt(0);
			}
		}catch (Exception e) {
			System.out.println("Input entered is not in correct format");
			System.out.println(e.getMessage());
		}finally {
			task.endTask();
		}
		
	}
	
	private static void displayConsole(Scanner input, InputDetails details) {
		// fetching the inputs
		System.out.println("Welcome to Flight search prorgram!!");
		System.out.println("Please Enter the below details to begin search :-\n");
		
		System.out.println("Departure Location (ex - DEL)");
		details.setDeparture(input.next().toUpperCase());
		
		System.out.println("Arrival Location (ex - DEL)");
		details.setArrival(input.next().toUpperCase());
		
		System.out.println("Flight date (Format - dd-mm-yyyy)");
		details.setFlightDate(input.next());
		
		System.out.println("Flight class (E or B)");
		details.setFlightClass(input.next().toUpperCase());
		
		System.out.println("Preferecne for sort (1 or 2) \n1(fare)\n2(fare and Flight duration)");
		details.setOutputPrefrence(input.nextInt());
	}

}
