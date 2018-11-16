package com.cg.hbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.utility.JdbcUtility;

public class HotelDaoImpl implements IHotelDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	@Override
	public String getRole(String userName, String password)
			throws HMSExceptions {
		String role = null;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query1);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString(1);
			} else {
				role = "Invalid Credintials or Username Does not exists";
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return role;
	}

	@Override
	public String addHotelDetails(Hotel hotel) throws HMSExceptions {
		String hotelId = null;
		int insert = 0;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query2);
			preparedStatement.setString(1, hotel.getHotelCity());
			preparedStatement.setString(2, hotel.getHotelName());
			preparedStatement.setString(3, hotel.getAddress());
			preparedStatement.setString(4, hotel.getHotelDescription());
			preparedStatement.setDouble(5, hotel.getRate());
			preparedStatement.setString(6, hotel.getPhoneNumber1());
			preparedStatement.setString(7, hotel.getPhoneNumber2());
			preparedStatement.setString(8, hotel.getRating());
			preparedStatement.setString(9, hotel.geteMail());
			preparedStatement.setString(10, hotel.getFax());
			insert = preparedStatement.executeUpdate();
			if (insert >= 0) {
				PreparedStatement preparedStatement1 = connection
						.prepareStatement(IQueryConstants.Query3);
				preparedStatement1.setString(1, hotel.getHotelName());
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					hotelId = resultSet.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return hotelId;
	}

	@Override
	public Hotel getHotelDetails(String hotelId) throws HMSExceptions {
		connection = JdbcUtility.getConnection();
		Hotel hotel = null;
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query4);
			preparedStatement.setString(1, hotelId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String hotelCity = resultSet.getString(2);
				String hotelName = resultSet.getString(3);
				String address = resultSet.getString(4);
				String hotelDescription = resultSet.getString(5);
				Double rate = resultSet.getDouble(6);
				String phoneNumber1 = resultSet.getString(7);
				String phoneNumber2 = resultSet.getString(8);
				String rating = resultSet.getString(9);
				String eMail = resultSet.getString(10);
				String fax = resultSet.getString(11);
				hotel = new Hotel(hotelCity, hotelName, address,
						hotelDescription, rate, phoneNumber1, phoneNumber2,
						rating, eMail, fax);
			} else {
				System.err
						.println("Entered Hotel ID is Wrong!!! There is no Hotel with such Hotel Id");
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		} catch (NullPointerException e) {
			throw new HMSExceptions(HMSExceptionList.Error7);
		}
		return hotel;
	}

	@Override
	public int deleteById(String hotelId) throws HMSExceptions {
		connection = JdbcUtility.getConnection();
		int flag = 0;
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query5);
			preparedStatement.setString(1, hotelId);
			flag = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return flag;
	}

	@Override
	public int modifyById(String hotelId1, String hotelDescription)
			throws HMSExceptions {
		connection = JdbcUtility.getConnection();
		int result = 0;
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query6);
			preparedStatement.setString(1, hotelDescription);
			preparedStatement.setString(2, hotelId1);
			try {
				result = preparedStatement.executeUpdate();
			} catch (NullPointerException e) {
				throw new HMSExceptions(HMSExceptionList.Error7);
			}

		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}

		return result;
	}

	@Override
	public String addRoomDetails(Room room) throws HMSExceptions {

		String roomId = "";
		int insert = 0;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query7);
			preparedStatement.setString(1, room.getHotelId());
			preparedStatement.setString(2, room.getRoomId());
			preparedStatement.setString(3, room.getRoomNumber());
			preparedStatement.setString(4, room.getRoomType());
			preparedStatement.setDouble(5, room.getPerNightRate());
			preparedStatement.setString(6, room.getAvailability());
			insert = preparedStatement.executeUpdate();
			if (insert >= 0) {
				PreparedStatement preparedStatement1 = connection
						.prepareStatement(IQueryConstants.Query8);
				preparedStatement1.setString(1, room.getHotelId());
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					roomId = resultSet.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return roomId;
	}

	@Override
	public Room getRoomDetials(String hotelId, String roomId)
			throws HMSExceptions {
		connection = JdbcUtility.getConnection();
		Room room = null;
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query9);
			preparedStatement.setString(1, hotelId);
			preparedStatement.setString(2, roomId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String hotelId1 = resultSet.getString(1);
				String roomId1 = resultSet.getString(2);
				String roomNumber = resultSet.getString(3);
				String roomType = resultSet.getString(4);
				double perDayRate = resultSet.getDouble(5);
				String availability = resultSet.getString(6);
				room = new Room(hotelId1, roomId1, roomNumber, roomType,
						perDayRate, availability);
			} else {
				System.err.println("Room Does not exist!!");
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return room;
	}

	@Override
	public int deleteRoomById(String roomId) throws HMSExceptions {
		connection = JdbcUtility.getConnection();
		int delete;
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query10);
			preparedStatement.setString(1, roomId);
			delete = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return delete;
	}

	@Override
	public List<Hotel> getAllHotels() throws HMSExceptions {
		connection = JdbcUtility.getConnection();
		List<Hotel> list = new ArrayList<Hotel>();
		try {
			preparedStatement = connection
					.prepareStatement(IQueryConstants.Query11);
			ResultSet resultSet =preparedStatement.executeQuery();
			while(resultSet.next()){
				String hotelID = resultSet.getString(1);
				String hotelCity = resultSet.getString(2);
				String hotelName = resultSet.getString(3);
				String address = resultSet.getString(4);
				String hotelDescription = resultSet.getString(5);
				Double rate = resultSet.getDouble(6);
				String phoneNumber1 = resultSet.getString(7);
				String phoneNumber2 = resultSet.getString(8);
				String rating = resultSet.getString(9);
				String eMail = resultSet.getString(10);
				String fax = resultSet.getString(11);
				Hotel hotel = new Hotel(hotelID,hotelCity, hotelName, address,
						hotelDescription, rate, phoneNumber1, phoneNumber2,
						rating, eMail, fax);
				list.add(hotel);
			}
			
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return list;
	}

	@Override
	public Hotel findHotel() throws HMSExceptions {
		// TODO Auto-generated method stub
		return null;
	}

}
