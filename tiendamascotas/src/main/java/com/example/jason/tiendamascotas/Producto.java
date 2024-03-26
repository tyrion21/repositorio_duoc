package com.example.jason.tiendamascotas;

public class Producto {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer precio;

    public Producto(Integer id, String nombre, String descripcion, Integer precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }
    

}
