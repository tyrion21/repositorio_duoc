package com.example.jason.publicaciones;


public class Comentario {

    private int id;
    private String descripcion;


        public Comentario(int id, String descripcion) {
            this.id = id;
            this.descripcion = descripcion;
        }

        public int getId() {
            return id;
        }

        public String getDescripcion () {
            return descripcion;
        }


    } 



