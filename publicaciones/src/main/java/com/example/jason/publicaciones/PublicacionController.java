package com.example.jason.publicaciones;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController

public class PublicacionController {

    private List<Publicacion> publicaciones = new ArrayList<>();

    public PublicacionController() {

        // Crear listas de comentarios y calificaciones
        List<Comentario> comentarios1 = Arrays.asList(new Comentario(1, "Buen post"), new Comentario(2, "Interesante"));
        List<Double> calificaciones1 = Arrays.asList(4.5, 3.7);
        List<Comentario> comentarios2 = Arrays.asList(new Comentario(3, "Me encantó"), new Comentario(4, "Muy útil"));
        List<Double> calificaciones2 = Arrays.asList(5.0, 4.8);
        List<Comentario> comentarios3 = Arrays.asList(new Comentario(5, "No me gustó"), new Comentario(6, "Podría mejorar"));
        List<Double> calificaciones3 = Arrays.asList(2.1, 2.5);

        publicaciones.add(new Publicacion(1, "Jason", "La guerra rusa-ucraniana", comentarios1, calificaciones1));

        publicaciones.add(new Publicacion(2, "Pepe", "Dolar al alza", comentarios2, calificaciones2));

        publicaciones.add(new Publicacion(3, "Maria", "Animales en extincion", comentarios3, calificaciones3));

        revisarPublicaciones();
    }

    @GetMapping("/publicaciones")
    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    @GetMapping("/publicaciones/{idPublicacion}/califica_promedio")
        
    public ResponseEntity<Map<String, Object>> getPromedioCalificaciones(@PathVariable("idPublicacion") int id) {
        Publicacion publicacion = publicaciones.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (publicacion == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Publicación no encontrada");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        double promedio = publicacion.getCalificaciones().stream().mapToDouble(Double::doubleValue).average()
                .orElse(0.0);

        Map<String, Object> response = new HashMap<>();
        response.put("Promedio de Calificaciones", promedio);
        response.put("Publicacion", id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void revisarPublicaciones() {

        for (Publicacion publicacion : publicaciones) {
            System.out.println("ID: " + publicacion.getId());
            System.out.println("Autor: " + publicacion.getAutor());
            System.out.println("Contenido: " + publicacion.getContenido());

            System.out.println("Comentarios:");
            for (Comentario comentario : publicacion.getComentarios()) {
                System.out.println("ID: " + comentario.getId() + ", Texto: " + comentario.getDescripcion());
            }

            System.out.println("Calificaciones:");
            for (Double calificacion : publicacion.getCalificaciones()) {
                System.out.println(calificacion);
            }

            // Calcular el promedio de las calificaciones
            double suma = 0;
            for (Double calificacion : publicacion.getCalificaciones()) {
                suma += calificacion;
            }
            double promedio = suma / publicacion.getCalificaciones().size();
            System.out.println("Promedio de calificaciones: " + promedio);
        }
    }
}
