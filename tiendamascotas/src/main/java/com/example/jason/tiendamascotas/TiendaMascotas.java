package com.example.jason.tiendamascotas;

import java.util.*;

public class TiendaMascotas {

    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Producto> productos;
    private Map<String, List<Integer>> ventasxproductos;
    private String fechaVentas;

    public TiendaMascotas(int id, String nombre, String direccion, String telefono, List<Producto> productos,
            Map<String, List<Integer>> ventasxproductos, String fechaVentas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.productos = productos;
        this.ventasxproductos = ventasxproductos;
        this.fechaVentas = fechaVentas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Map<String, List<Integer>> getVentasxproductos() {
        return ventasxproductos;
    }

    public String getFechaVentas() {
        return fechaVentas;
    }

    public Integer getPrecioProducto(String nombreProducto) {
        return productos.stream().filter(producto -> producto.getNombre().equals(nombreProducto)).findFirst().get()
                .getPrecio();
    }

}
