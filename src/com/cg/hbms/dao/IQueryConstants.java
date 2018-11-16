package com.cg.hbms.dao;

public interface IQueryConstants {

	String Query1 = "SELECT role FROM HMS_Login_Table WHERE username=? and password=?";
	
	String Query2 = "INSERT INTO hotel values(hotel_id_sequence.nextval,?,?,?,?,?,?,?,?,?,?)";
	
	String Query3 = "SELECT HOTEL_ID FROM hotel WHERE HOTEL_NAME=?";
	String Query4 = "SELECT * FROM hotel WHERE HOTEL_ID=?";
	String Query5 = "DELETE FROM hotel WHERE HOTEL_ID=?";

	String Query6 = "UPDATE hotel SET DESCRIPTION=? WHERE HOTEL_ID=?";

	String Query7 = "INSERT INTO room_table values(?,?,?,?,?,?)";

	String Query8 = "SELECT ROOM_ID FROM room_table WHERE HOTEL_ID=?";

	String Query9 = "SELECT * FROM room_table WHERE HOTEL_ID=? and ROOM_ID=?";

	String Query10 = "DELETE FROM room_table WHERE ROOM_ID=?";

	String Query11 = "SELECT * FROM hotel";

}
