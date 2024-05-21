package com.isteMySQL.Util;
import java.sql.*;



public class VeritabaniUtil {
	static Connection conn=null;
	
	public static Connection Baglan() {
		try {
			//"jdbc:mysql//serverIPAdresi//vt_ismi", "kullanici adi", "şifre"
			conn=DriverManager.getConnection("jdbc:mysql://localhost/apartOtomasyonu", "nazife", "12345");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}
		
	}

}
