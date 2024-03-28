package com.jason.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jason.test.model.Pelicula;


public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{

    
} 