package com.proyecto.dam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.dam.entities.Canciones;

public interface CancionesRepository extends JpaRepository<Canciones, Long> {
	Canciones findCancionById(Long id);
}
