package com.jason.test;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.ArrayList;

@RestController
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController(){
        peliculas.add(new Pelicula (1, "The Shawshank Redemption", 1994, 
                                    "Frank Darabont", "Drama", "Pelicula sobre alguien en la carcel"));
        peliculas.add(new Pelicula (2, "The Godfather", 1972, 
                                    "Francis Ford Coppola", "Drama", "Pelicula sobre la mafia"));   
        peliculas.add(new Pelicula (3, "The Dark Knight", 2008, 
                                    "Christopher Nolan", "Accion", "Pelicula sobre Batman"));
        peliculas.add(new Pelicula (4, "The Lord of the Rings: The Return of the King", 2003,
                                    "Peter Jackson", "Aventura", "Pelicula sobre un anillo magico"));
        peliculas.add(new Pelicula (5, "Pulp Fiction", 1994,
                                    "Quentin Tarantino", "Crimen", "Pelicula sobre criminales"));
    }
    @GetMapping("/peliculas")
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    public Pelicula getPeliculaById(@PathVariable int id)
    {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                return pelicula;
            }
        }
        return null;
    }

}
