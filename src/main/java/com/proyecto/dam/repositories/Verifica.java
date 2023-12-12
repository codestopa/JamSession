package com.proyecto.dam.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



//VERIFICA SI EL USUARIO EXISTE YA EN LA BDD, EN CASO DE NO EXISTIR, LO REGISTRA
public class Verifica {
	public static String usuario(String nombre) {
		String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		try {
		    Connection conexion = DriverManager.getConnection(url, "root", "");
		    String selectSql = "SELECT id FROM usuario WHERE nombre = ?";
		    String insertSql = "INSERT INTO usuario (nombre) VALUES (?)";


		    try (PreparedStatement select = conexion.prepareStatement(selectSql)) {
		        select.setString(1, nombre);
		        ResultSet resultSet = select.executeQuery();

		        if (resultSet.next()) {

		            System.out.println("Acceso permitido, hola " + nombre);
		            return resultSet.getString("id");
		        } else {
		            try (PreparedStatement insertStatement = conexion.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)){
		                insertStatement.setString(1, nombre);
		                insertStatement.executeUpdate();

		                try (ResultSet rs = insertStatement.getGeneratedKeys()) {
		                    if (rs.next()) {
		                        System.out.println("Nuevo usuario añadido a la base de datos.");
		                        return rs.getString(1);
		                    }
		                }
		            }
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return "0";
	}
	
	//VERIFICA SI UNA CANCION EXISTE YA EN LA BDD
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