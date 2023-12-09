package com.proyecto.dam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.dam.entities.usuario;

public interface UsuarioRepository extends JpaRepository<usuario, Long> {
	usuario findUsuarioById(Long id);
	//prueba
}


