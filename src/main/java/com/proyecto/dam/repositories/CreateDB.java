package com.proyecto.dam.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CreateDB {
	

	
	//METODO PARA EJECUTAR QUERYS DE SQL, EL PARAMETRO sqlContent SALE DEL METODO readSQLFile
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
	
	
	//LEE UN ARCHIVO SQL CON BUFFEREDREADER Y LO DEVUELVE COMO STRING
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
    
    
    //CONEXION A LA BASE DE DATOS A PARTIR DE UN ARCHIVO SQL, CREA LAS TABLAS Y CANCIONES DE EJEMPLO
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
    
    
    //AGREGA A UN USUARIO EXISTENTE A UNA CANCION, INTRODUCIÉNDOLO EN LA TABLA participa
    public static void addUser(int usuarioid, int cancionid, String instrumento) {
    	String db = "jamsession";
		String url = "jdbc:mysql://localhost:3306/" + db;
		if (!participacionExistente(usuarioid, cancionid, url)) {
            String sql = "INSERT INTO participa (usuario_id, cancion_id, instrumento) VALUES (" + usuarioid + "," + cancionid + ",'" + instrumento + "')";
            try {
                Connection conexion = DriverManager.getConnection(url, "root", "");
                Statement st = conexion.createStatement();
                st.executeUpdate(sql);
                conexion.close();
                System.out.println("Usuario añadido correctamente a la canción.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El usuario ya está en la canción.");
        }
    }
    
    //COMPRUEBA SI UN USUARIO SE ENCUENTRA YA PARTICIPANDO EN UNA CANCIÓN
    private static boolean participacionExistente(int usuarioid, int cancionid, String url) {
        String query = "SELECT * FROM participa WHERE usuario_id = " + usuarioid + " AND cancion_id = " + cancionid;
        try {
            Connection conexion = DriverManager.getConnection(url, "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean existe = rs.next();
            conexion.close();
            return existe;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    
    
    	
    
    

    
    
    }
    
}