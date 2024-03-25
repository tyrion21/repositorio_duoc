package com.example.jason.publicaciones;

import java.util.List;

public class Publicacion {

    private int id;
    private String autor;
    private String contenido;
    private List<Comentario> comentarios;
    private List<Double> calificaciones;

    public Publicacion(int id, String autor, String contenido, List<Comentario> comentarios, 
            List<Double> calificaciones) {
        this.id = id;
        this.autor = autor;
        this.contenido = contenido;
        this.comentarios = comentarios;
        this.calificaciones = calificaciones;
    }

  
    public int getId() {
        return id;
    }

    public String getAutor() { 
        return autor;
    }

    public String getContenido() {
        return contenido;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public List<Double> getCalificaciones() {
        return calificaciones;
    }

}



