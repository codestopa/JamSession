package com.proyecto.dam.repositories;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
//CLASE PARA EL MENU DEL USUARIO
public class Menu {
	//MUESTRA UN MENU EN CONSOLA JUNTO CON UN INICIO DE SESION EN EL CUAL SI EL USUARIO NO FIGURA EN LA BBDD, SE LE AÑADE
	public static void menu() {
		int n;
		ArrayList <String> canciones = new ArrayList<String>();
		ArrayList <String> usuarios = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		File menuinfo = new File("src\\main\\java\\com\\proyecto\\dam\\repositories\\menuinfo.txt");
		int idusuario;
		String nombreusuario;
		System.out.println("Bienvenido a la JamSession App");

		System.out.println("-INICIO DE SESION-");
		
		do {
			System.out.print("Introduce tu nombre: ");
			nombreusuario = sc.nextLine();
			idusuario = Integer.parseInt(Verifica.usuario(nombreusuario)); //COMPRUEBA CON EL ID SI EL USUARIO FIGURA EN LA BBDD
			
		}while(idusuario == 0);
		leer(menuinfo);
		while(true) {
			do {
				System.out.print(">>> ");
				n = scan.nextInt();
				switch(n) {
				case 1:
					apuntarse(idusuario, nombreusuario);
					break;
				case 2:
					cuadrante();
					break;
					
				default:
					System.out.println("opcion no valida, elija otra");
			}
			}while(n!=1 && n!=2);
		}
		
		
		
	}
	

	//METODO PARA DEVOLVER TODAS LAS CANCIONES DE LA TABLA CANCIONES
	public static ArrayList<String> canciones(){
		ArrayList<String> canciones = new ArrayList<String>();

	    String db = "jamsession";
	    String url = "jdbc:mysql://localhost:3306/" + db;
	    String sql = "SELECT nombre FROM canciones";

	    try {
	        Connection conexion = DriverManager.getConnection(url, "root", "");
	        Statement st = conexion.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            String nombre = rs.getString("nombre");
	            canciones.add(nombre);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return canciones;
	}
	
	//METODO PARA DEVOLVER TODOS LOS ARTISTAS DE TODAS LAS CANCIONES
	public static ArrayList<String> artistas(){
		ArrayList<String> artistas = new ArrayList<String>();

	    String db = "jamsession";
	    String url = "jdbc:mysql://localhost:3306/" + db;
	    String sql = "SELECT artista FROM canciones";

	    try {
	        Connection conexion = DriverManager.getConnection(url, "root", "");
	        Statement st = conexion.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            String artista = rs.getString("artista");
	            artistas.add(artista);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return artistas;
	}	
	
	
	//METODO PARA DEVOLVER LOS USUARIOS JUNTO A SU INSTRUMENTO EN CADA CANCION, LA CUAL SE ESPECIFICA COMO PARAMETRO POR SU ID
	public static ArrayList<String> usuarios(int idCancion) {
	    ArrayList<String> usuariosConInstrumento = new ArrayList<>();

	    String db = "jamsession";
	    String url = "jdbc:mysql://localhost:3306/" + db;
	    String sql = "SELECT usuario.nombre, participa.instrumento FROM usuario "
	            + "INNER JOIN participa ON usuario.id = participa.usuario_id WHERE participa.cancion_id = " + idCancion;

	    try {
	        Connection conexion = DriverManager.getConnection(url, "root", "");
	        Statement st = conexion.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            String nombreUsuario = rs.getString("nombre");
	            String instrumento = rs.getString("instrumento");
	            String usuarioConInstrumento = nombreUsuario + " - " + instrumento;
	            usuariosConInstrumento.add(usuarioConInstrumento);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usuariosConInstrumento;
	}
	
	
	//METODO PARA INTRODUCIR A UN USUARIO A UNA CANCION ESPECIFICANDO SU NOMBRE E ID COMO PARAMETROS Y SU INSTRUMENTO 
	public static void apuntarse(int idusuario, String nombreusuario) {
		Scanner sc = new Scanner(System.in);
		int idcancion;
		String nombrecancion;
		do {
			System.out.print("Introduce el nombre de la cancion: ");
			nombrecancion = sc.nextLine();
			idcancion = Integer.parseInt(Verifica.cancion(nombrecancion));
		}while(idcancion==0);
		System.out.print("Introduce el instrumento: ");
		String instrumento = sc.nextLine();
		instrumento = instrumento.toLowerCase();
		CreateDB.addUser(idusuario, idcancion, instrumento);
		
	}
	
	//METODO PARA MOSTRAR EL CUADRANTE DE CANCIONES CON ARTISTAS Y PARTICIPANTES CON INSTRUMENTOS
	 public static void cuadrante() {        
	        ArrayList<String> canciones = canciones();
	        ArrayList<String> artistas = artistas();
	        int i = 0;

	        for (String cancion : canciones) {
	            int idCancion = obtenerIdCancion(cancion);
	            ArrayList<String> usuarios = usuarios(idCancion);
	            System.out.print(cancion+" - ");
	            System.out.println(artistas.get(i));
	            
	            if (!usuarios.isEmpty()) {
	                for (String usuario : usuarios) {
	                    System.out.println("\t" + usuario);
	                }
	            } else {
	                System.out.println("\tNo hay usuarios para esta canción.");
	            }
	            i++;
	        }
	    }
	 
	 //METODO PARA TOMAR EL ID DE UNA CANCION ESPECIFICANDO SU NOMBRE COMO PARAMETRO
	 public static int obtenerIdCancion(String nombreCancion) {
		    int idCancion = 0;

		    String db = "jamsession";
		    String url = "jdbc:mysql://localhost:3306/" + db;
		    String sql = "SELECT id FROM canciones WHERE nombre = '" + nombreCancion + "'";

		    try {
		        Connection conexion = DriverManager.getConnection(url, "root", "");
		        Statement st = conexion.createStatement();
		        ResultSet rs = st.executeQuery(sql);

		        if (rs.next()) {
		            idCancion = rs.getInt("id");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return idCancion;
		}
	 
	//METODO PARA LEER UN ARCHIVO DE TEXTO CON BUFFEREDREADER
	public static void leer(File file) {
		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((linea = br.readLine())!=null) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}