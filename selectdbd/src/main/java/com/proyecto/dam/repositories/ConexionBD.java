package com.proyecto.dam.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	public static void conexion() {
		String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		String user = "root";
		String pass = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conexion = DriverManager.getConnection(url, user, pass);
			System.out.println("conexion establecida con: "+db);
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
	

