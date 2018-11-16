package com.cg.hbms.dao;

import java.util.List;

import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.exceptions.HMSExceptions;

public interface IHotelDao {

	String getRole(String userName, String password) throws HMSExceptions;

	String addHotelDetails(Hotel hotel)throws HMSExceptions;

	Hotel getHotelDetails(String hotelId)throws HMSExceptions;

	int deleteById(String hotelId)throws HMSExceptions;

	int modifyById(String hotelId1, String hotelDescription)throws HMSExceptions;

	String addRoomDetails(Room room) throws HMSExceptions;

	Room getRoomDetials(String hotelId, String roomId)throws HMSExceptions;

	int deleteRoomById(String roomId)throws HMSExceptions;

	List<Hotel> getAllHotels()throws HMSExceptions;

	Hotel findHotel()throws HMSExceptions;

}
