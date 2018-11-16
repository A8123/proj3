package com.cg.hbms.presentation;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.Status;
import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.service.HotelServiceImpl;
import com.cg.hbms.service.IHotelService;


public class EmployeeConsole {
	Scanner scanner = new Scanner(System.in);
	IHotelService service = new HotelServiceImpl();
	
	public void start(String userName) throws HMSExceptions {
		int choice = 0;
		int choiceOption = 0;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n"
				+ "~~~~~~~~~ Welcome " + userName + " ~~~~~~~~~\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Select your option");
		System.out.println("[1].Search \n [2].Book ");
		
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Enter digits only(1-4)");
		}
		switch (choice) {
		case 1:
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~Hotel List~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			List<Hotel> hotels = service.getAllHotels();
			for (Hotel hotel : hotels) {
				System.out.println("Hotel ID: "+hotel.getHotelId());
				System.out.println("Hotel Name: "+hotel.getHotelName());
				System.out.println("Hotel City: "+hotel.getHotelCity());
				System.out.println("Hotel Address: "+hotel.getAddress());
				System.out.println("Hotel Description: "+hotel.getHotelDescription());
				System.out.println("Hotel Rating: "+hotel.getRating());
				System.out.println("Hotel Rate: "+hotel.getRate());
				System.out.println("Hotel Phone Number: "+hotel.getPhoneNumber1());
				System.out.println("Hotel Email: "+hotel.geteMail());
				System.out.println();
				System.out.println();
			}
			break;
			
		case 2: 
			reserveHotel();
			break;
			
		default:
			break;
		}
	}
	public void reserveHotel() {
		try {
			List<Hotel> hotels = service.getAllHotels();
			if (hotels != null) {
				System.out.println("\tHotel Id");
				System.out.println("-----------------------------------------");
				for (Hotel hotel : hotels) {
					System.out
							.println(String.format("\t%s", hotel.getHotelId()));
				}
				
				System.out.print("hotelId> ");
				String hotelId=scanner.next();
				
				Hotel hotel = service.findHotel(hotelId);
				
				if(hotel==null){
					System.err.println("That Hotel Does Not Exist");
				}else if(hotel.getStatus()!=Status.AVAILABLE){
					System.err.println("That Hotel Already Reserved Or Issued");
				}else{
					Room entry = new Room();
					entry.setHotelId(hotelId);
					//RegisterEntry entry = new RegisterEntry();
					//entry.setAvailability(availability);
					//entry.setPerNightRate(perNightRate);
					//entry.setRoomId(roomId);
					//entry.setStudId(currentUser);
					//entry.setReservedDate(LocalDate.now());
					
					if(regEntryService.bookHotel(entry)){
						System.out.println("Your Book Is Reserved");
					}else{
						System.err.println("Operation Failed!");
					}
				}
			} else {
				System.out.println("No Records Found!");
			}
		} catch (HMSExceptions e) {
			System.err.println(e.getMessage());
		}
	}
	
}
