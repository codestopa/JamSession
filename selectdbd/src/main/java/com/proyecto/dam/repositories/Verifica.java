package com.proyecto.dam.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Verifica {
	public static String usuario(String nombre) {
		String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		try {
			Connection conexion = DriverManager.getConnection(url, "root", "");
			String sql = "select id from usuario where nombre ='"+nombre+"'";
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {

				System.out.println("Acceso permitido, hola "+nombre);
                return resultSet.getString("id");
            } else {
                System.out.println("No se encontró ningún usuario con este nombre. ");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}
	
	public static String cancion(String nombre) {
		String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		try {
			Connection conexion = DriverManager.getConnection(url, "root", "");
			String sql = "select id from canciones where nombre ='"+nombre+"'";
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
                return resultSet.getString("id");
            } else {
                System.out.println("No se encontró ninguna cancion con este nombre. ");
            }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "0";
	}
}
