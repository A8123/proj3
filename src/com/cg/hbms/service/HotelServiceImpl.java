package com.cg.hbms.service;

import java.util.List;

import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.dao.HotelDaoImpl;
import com.cg.hbms.dao.IHotelDao;
import com.cg.hbms.exceptions.HMSExceptions;

public class HotelServiceImpl implements IHotelService {

	IHotelDao dao = new HotelDaoImpl();
	@Override
	public String getRole(String userName, String password) throws HMSExceptions {
		return dao.getRole(userName, password);
	}
	@Override
	public String addHotelDetails(Hotel hotel) throws HMSExceptions {
		return dao.addHotelDetails(hotel);
	}
	@Override
	public Hotel getHotelDetails(String hotelId) throws HMSExceptions {
		return dao.getHotelDetails(hotelId);
	}
	@Override
	public int deleteById(String hotelId) throws HMSExceptions {
		return dao.deleteById(hotelId);
	}
	@Override
	public int modifyById(String hotelId1, String hotelDescription) throws HMSExceptions {
		return dao.modifyById(hotelId1,hotelDescription);
	}
	@Override
	public String addRoomDetails(Room room) throws HMSExceptions {
		return dao.addRoomDetails(room);
	}
	@Override
	public Room getRoomDetails(String hotelId, String roomId)
			throws HMSExceptions {
		return dao.getRoomDetials(hotelId,roomId);
	}
	@Override
	public int deleteRoomById(String roomId) throws HMSExceptions{
		return dao.deleteRoomById(roomId);
	}
	@Override
	public List<Hotel> getAllHotels() throws HMSExceptions {
		return dao.getAllHotels();
	}
	@Override
	public Hotel findHotel(String hotelId) throws HMSExceptions {
		
		return dao.findHotel();
	}

}
