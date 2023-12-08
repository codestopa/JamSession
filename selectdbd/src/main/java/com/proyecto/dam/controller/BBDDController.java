package com.proyecto.dam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dam.entities.Canciones;
import com.proyecto.dam.entities.usuario;
import com.proyecto.dam.repositories.CancionesRepository;
import com.proyecto.dam.repositories.ConexionBD;
import com.proyecto.dam.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class BBDDController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CancionesRepository cancionRepository;

    @GetMapping("/usuario")
    public List<usuario> obtenerUsuario() {
        return usuarioRepository.findAll();
    }

    @PostMapping("/usuario")  // Agregamos la ruta específica para la creación de usuarios
    public ResponseEntity<usuario> crearUsuario(@RequestBody usuario usuario) {
        usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping("/canciones")
    public List<Canciones> obtenerCanciones() {
        return cancionRepository.findAll();
    }
}




