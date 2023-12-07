package com.proyecto.dam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.dam.entities.usuario;
import com.proyecto.dam.repositories.ConexionBD;
import com.proyecto.dam.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class BBDDController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping(produces = "application/json")
    public List<usuario> obtenerUsuarios() {
        List<usuario> usuarios = usuarioRepository.findAll();
        System.out.println("Usuarios: " + usuarios);
        return usuarios;
    }






}
