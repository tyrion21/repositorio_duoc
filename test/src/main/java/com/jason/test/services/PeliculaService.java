package com.jason.test.services;

import java.util.List;
import java.util.Optional;

import com.jason.test.model.Pelicula;


/**
 * PeliculaService
 */
public interface PeliculaService {

    List<Pelicula> getAllPeliculas();    
    Optional<Pelicula> getPeliculaById(Long id);
    
}