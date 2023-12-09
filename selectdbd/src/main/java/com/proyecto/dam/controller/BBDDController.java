package com.proyecto.dam.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dam.entities.Canciones;
import com.proyecto.dam.entities.usuario;
import com.proyecto.dam.repositories.CancionesRepository;

import com.proyecto.dam.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class BBDDController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CancionesRepository cancionRepository;

    
    
    
    @GetMapping("/usuario")
    public List<usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @GetMapping("/usuario/{id}")
    public Optional<usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity<usuario> crearUsuario(@RequestBody usuario usuario) {
        usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
    	usuarioRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
    
    @DeleteMapping("/usuario")
    public ResponseEntity<String> eliminarTodosLosUsuarios() {
    	usuarioRepository.deleteAll();
        return ResponseEntity.ok("Todos los usuarios eliminados con éxito");
    }

    @GetMapping("/canciones")
    public List<Canciones> obtenerCanciones() {
        return cancionRepository.findAll();
    }
    
    @GetMapping("/canciones/{id}")
    public Optional<usuario> obtenerCanciones(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }
    
    @PostMapping("/canciones")
    public ResponseEntity<Canciones> crearCanciones(@RequestBody Canciones cancion) {
        Canciones nuevaCancion = cancionRepository.save(cancion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancion);
    }
}




