package com.proyecto.dam.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


	@Entity
	public class usuario {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nombre;
	    private String password;
	   
	    
	    public usuario() {
	    }

	    // Constructor con argumentos
	    public usuario(String nombre) {
	        this.nombre = nombre;
	    }

	    // Getter y Setter para id
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    // Getter y Setter para nombre
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }


	    @Override
	    public String toString() {
	        return "usuario{" +
	                "id=" + id +
	                ", nombre='" + nombre + '\'' +
	                '}';
	    }

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}