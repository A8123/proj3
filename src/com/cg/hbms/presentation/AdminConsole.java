package com.cg.hbms.presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.service.HotelServiceImpl;
import com.cg.hbms.service.IHotelService;

public class AdminConsole {
	Scanner scanner = new Scanner(System.in);
	IHotelService service = new HotelServiceImpl();

	public void start(String userName) throws HMSExceptions {
		int choice = 0;
		int choiceOption = 0;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n"
				+ "~~~~~~~~~ Welcome " + userName + " ~~~~~~~~~\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Select your option");
		System.out.println("Choose Following Options" + "\n"
				+ "[1]. Perform Hotel Management");
		System.out.println("[2]. Perform Room Management" + "\n"
				+ "[3]. View List of Hotels");
		System.out.println("[4]. View Bookings of specific hotel" + "\n"
				+ "[5]. View guest list of specific hotel");
		System.out.println("[6]. View bookings for specified date" + "\n"
				+ "[7]. Exit");
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Enter digits only(1-4)");
		}
		switch (choice) {
		case 1:
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n"
					+ "~~ Welcome Hotel Management Menu ~~" + "\n"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Select your option");
			System.out.println("Choose Following Options" + "\n"
					+ "[1]. Add Hotel Details");
			System.out.println("" + "[2]. Delete Hotel Details" + "\n"
					+ "[3]. Modify Hotel Offers");
			// System.out.println("[4]. Exit from Hotel Management Menu");
			try {
				choiceOption = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only(1-3)");
			}
			switch (choiceOption) {
			case 1:
				Hotel hotel = addHotelDetails();
				try {
					String id = service.addHotelDetails(hotel);
					System.out.println("Hotel " + hotel.getHotelName()
							+ " is registered successfully with id: " + id);
				} catch (HMSExceptions e) {
					System.err.println(e.getMessage());
				}
				break;

			case 2:
				System.out.println("Enter the Id of hotel to delete");
				String hotelId = scanner.nextLine();
				try {
					Hotel hotelDetails = service.getHotelDetails(hotelId);
					System.out.println("Hotel Name: "
							+ hotelDetails.getHotelName() + "\n"
							+ "Hotel City: " + hotelDetails.getHotelCity());
					System.out.println("Confirm Delete? (y/n)?");
					String confirm = scanner.nextLine();
					if (confirm.equals("y")) {
						int delete = service.deleteById(hotelId);
						if (delete == 1) {
							System.out.println("Details of Hotel "
									+ hotelDetails.getHotelName()
									+ " deleted successfully");
						}
					} else {
						System.out
								.println("Delete Operation Cancelled by user");
					}
				} catch (HMSExceptions e) {
					System.err.println(e.getMessage());
				}

				break;
			case 3:
				System.out.println("Enter the Id of hotel to modify");
				String hotelId1 = scanner.nextLine();

				try {
					Hotel hotelDetails = service.getHotelDetails(hotelId1);
					if (!hotelDetails.getHotelName().isEmpty()) {
						System.out
								.println("Enter the new Hotel Description for hotel: "
										+ hotelDetails.getHotelName());
					} else {
						throw new HMSExceptions(HMSExceptionList.Error7);
					}
					String description = scanner.nextLine();
					int modify = service.modifyById(hotelId1, description);
					if (modify > 0) {
						System.out
								.println("Hotel Description Updated for Hotel with Id: "
										+ hotelId1);
					} else {
						System.err.println("Wrong Hotel Id");
					}
				} catch (HMSExceptions e) {
					System.err.println(e.getMessage());
				}

				break;
			default:
				break;
			}

			break;
		case 2:
			System.out.println("Enter Hotel Id to perform Room Management");
			String hotelId = scanner.next();
			Hotel hotelDetails = service.getHotelDetails(hotelId);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n"
					+ "~~ Welcome Room Management Menu ~~" + "\n"
					+ "~~~~~~~~HOTEL " + hotelDetails.getHotelName()
					+ " ~~~~~~~~~~~~~" + "\n"
					+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Select your option");
			System.out.println("Choose Following Options" + "\n"
					+ "[1]. Add Room Details");
			System.out.println("" + "[2]. Delete Room Details");
			/* + "\n"+ "[3]. Modify Hotel Offers"); */
			// System.out.println("[4]. Exit from Hotel Management Menu");
			try {
				choiceOption = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only(1-3)");
			}
			switch (choiceOption) {
			case 1:
				System.out.println("Enter the room details for hotel: "
						+ hotelDetails.getHotelName());
				if (!hotelDetails.getHotelName().isEmpty()) {
					Room room = addRoomDetails(hotelId);
					try {
						System.out.println(room.toString());
						String id = service.addRoomDetails(room);
						System.out.println("The Room with id: "
								+ room.getRoomId() + " for Hotel with  Id: "
								+ id + " added!!");
					} catch (HMSExceptions e) {
						System.err.println(e.getMessage());
					}
				}
				break;

			case 2:
				try {
					System.out.println("Enter the Room Id to delete? ");
					String roomId = scanner.next();
					Room roomDetails = service.getRoomDetails(hotelId, roomId);
					System.out.println("Hotel Name: "
							+ hotelDetails.getHotelName() + "\n" + "Room Id: "
							+ roomId + "\n" + "Room Number: "
							+ roomDetails.getRoomNumber());
					System.out.println("Confirm Delete? (y/n)?");
					String confirm = scanner.next();
					if (confirm.equals("y")) {
						int delete = service.deleteRoomById(roomId);
						if (delete == 1) {
							System.out.println("Details of Room "
									+ roomDetails.getRoomNumber()
									+ " deleted successfully");
						}
					} else {
						System.out
								.println("Delete Operation Cancelled by user");
					}
				} catch (HMSExceptions e) {
					System.err.println(e.getMessage());
				}

				break;

			}
		case 3:{
			/*
			 * 
			 * case 3 for main case View List of Hotels
			 * 
			 */
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
			
		}
		default:
			break;
		}
	}

	private Hotel addHotelDetails() {
		System.out.println("Enter the Hotel Details");
		System.out.println("Enter City");
		String hotelCity = scanner.nextLine();
		System.out.println("Enter Hotel Name");
		String hotelName = scanner.nextLine();
		System.out.println("Enter Address");
		String address = scanner.nextLine();
		System.out.println("Enter Description");
		String hotelDescription = scanner.nextLine();
		System.out.println("Enter Average Rate Per Night ");
		double rate = scanner.nextDouble();
		System.out.println("Enter Phone Number 1");
		String phoneNumber1 = scanner.next();
		System.out.println("Enter Phone Number 2");
		String phoneNumber2 = scanner.next();
		System.out.println("Enter Ratings");
		String rating = scanner.next();
		System.out.println("Enter Email");
		String eMail = scanner.next();
		System.out.println("Enter Fax");
		String fax = scanner.next();
		Hotel hotel = new Hotel(hotelCity, hotelName, address,
				hotelDescription, rate, phoneNumber1, phoneNumber2, rating,
				eMail, fax);
		return hotel;
	}

	private Room addRoomDetails(String hotelId2) {
		System.out.println("Enter the Room Details");
		System.out.println("Enter Room Id");
		String roomId = scanner.next();
		System.out.println("Enter Room Number");
		String roomNumber = scanner.next();
		System.out.println("Enter Room Type");
		String roomType = scanner.next();
		System.out.println("Enter Rate per Day");
		double perNightRate = scanner.nextDouble();
		System.out.println("Enter Availablity");
		String availability = scanner.next();

		Room room = new Room(hotelId2, roomId, roomNumber, roomType,
				perNightRate, availability);

		return room;
	}
}
