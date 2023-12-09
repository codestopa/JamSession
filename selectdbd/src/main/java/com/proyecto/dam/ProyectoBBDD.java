package com.proyecto.dam;

import java.sql.DriverManager;
import java.sql.SQLException;

// TuAppApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.proyecto.dam.repositories.ConexionBD;
import com.proyecto.dam.repositories.CreateDB;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.proyecto.dam.repositories")
public class ProyectoBBDD {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoBBDD.class, args);
        
        ConexionBD.conexion();
    	CreateDB.createDB();
    }
}

