package com.proyecto.dam.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateDB {
	

	

	private static void executeQueries(Connection connection, String sqlContent) {
        try {
            Statement statement = connection.createStatement();

            String[] queries = sqlContent.split(";");

            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.execute(query);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
    private static String readSQLFile(String archivo) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    
    
    public static void createDB() {
    	String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		try {
			Connection conexion = DriverManager.getConnection(url, "root", "");
			String rutasql = "src\\main\\java\\com\\proyecto\\dam\\repositories\\createdb.sql";
	    	String contenido = readSQLFile(rutasql);
	    	executeQueries(conexion, contenido);
			conexion.close();
			System.out.println("Base de datos creada correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    
    public static void addUser(int usuarioid, int cancionid, String instrumento) {
    	String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		String sql = "INSERT INTO participa (usuario_id, cancion_id, instrumento) VALUES ("+usuarioid+","+cancionid+",'"+instrumento+"')";
		try {
			Connection conexion = DriverManager.getConnection(url, "root", "");
			Statement st = conexion.createStatement();
	    	st.executeUpdate(sql);
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    
    	
    
    

    
    
    
    
}