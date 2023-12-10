package com.proyecto.dam.repositories;

import java.util.Scanner;

public class Menu {
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		
		int idusuario;
		int idcancion;
		String nombreusuario;
		String nombrecancion;
		System.out.println("Bienvenido a la JamSession App");
		System.out.println("-INICIO DE SESION-");
		
		do {
			System.out.print("Introduce tu nombre: ");
			nombreusuario = sc.nextLine();
			idusuario = Integer.parseInt(Verifica.usuario(nombreusuario));
			
		}while(idusuario == 0);
		
		System.out.println("-APUNTARSE A UNA CANCION-");
		
		do {
			System.out.print("Introduce el nombre de la cancion: ");
			nombrecancion = sc.nextLine();
			idcancion = Integer.parseInt(Verifica.cancion(nombrecancion));
		}while(idcancion==0);
		System.out.print("Introduce el instrumento: ");
		String instrumento = sc.nextLine();
		CreateDB.addUser(idusuario, idcancion, instrumento);
		
		System.out.println("Perfecto "+nombreusuario+", has sido añadido a: "+nombrecancion+" tocando: "+instrumento);
	}
}
