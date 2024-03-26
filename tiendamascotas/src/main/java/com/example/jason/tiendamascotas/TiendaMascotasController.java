package com.example.jason.tiendamascotas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TiendaMascotasController {

    private List<TiendaMascotas> tiendas = new ArrayList<>();

    public TiendaMascotasController() {

        tiendas.add(new TiendaMascotas(1, "SweetPet", "San Martin 555", "942002942",
                Arrays.asList(
                        new Producto(1, "Toallita para cachorros", "Suave y sedosa toallita para secar a su mascota",
                                5000),
                        new Producto(2, "Juguete para gato", "Juguete para gato con catnip", 10000)),
                llenarVentasProductos("Toallita para cachorros", 10, 20, 30), "02/03/2024"));

        tiendas.add(new TiendaMascotas(2, "Cachorros Felices", "Luis Cruz 783", "945525107",
                Arrays.asList(new Producto(3, "Comida para perros", "Comida balanceada para perros", 15000),
                        new Producto(4, "Collar para perro", "Collar de cuero para perro", 20000)),
                llenarVentasProductos("Comida para perros", 15, 25, 35), "22/05/2023"));

        tiendas.add(new TiendaMascotas(3, "Mascotas y Amigos", "Av. La Marina 123", "955633942",
                Arrays.asList(new Producto(5, "Comida para gato", "Comida balanceada para gatos", 12000),
                        new Producto(6, "Juguete para perro", "Pelota para perro", 8000)),
                llenarVentasProductos("Comida para gato", 20, 30, 40), "12/07/2022"));

    }

    private Map<String, List<Integer>> llenarVentasProductos(String productos, Integer venta1, Integer venta2,
            Integer venta3) {
        Map<String, List<Integer>> retornoVentas = new HashMap<String, List<Integer>>();

        List<Integer> ventas = Arrays.asList(venta1, venta2, venta3);

        retornoVentas.put(productos, ventas);

        return retornoVentas;
    }

    @GetMapping("/tiendas")
    public List<TiendaMascotas> getTiendas() {
        return tiendas;
    }

    @GetMapping("/tiendas/{idTienda}/total_ventas_anuales")
    public ResponseEntity<Map<String, Object>> getVentasTotalesAnuales(@PathVariable("idTienda") Integer id) {
        Map<String, Integer> ventasPorFecha = new HashMap<>();
        TiendaMascotas tienda = tiendas.get(id - 1);

        if (tienda == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Tienda no encontrada");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, List<Integer>> entry : tienda.getVentasxproductos().entrySet()) {
            String nombreProducto = entry.getKey();
            Integer precioProducto = tienda.getPrecioProducto(nombreProducto);

            for (Integer venta : entry.getValue()) {
                String[] fecha = tienda.getFechaVentas().split("/");
            
                String anno = fecha[2];

                if (ventasPorFecha.containsKey(anno)) {
                    ventasPorFecha.put(anno, ventasPorFecha.get(anno) + venta * precioProducto);
                } else {
                    ventasPorFecha.put(anno, venta * precioProducto);
                }
            }
        }

        Integer totalVentasAnuales = ventasPorFecha.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("El valor total de las ventas anuales de la tienda",
                tienda.getNombre() + " es " + totalVentasAnuales);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tiendas/{idTienda}/total_ventas_mensuales")
    public ResponseEntity<Map<String, Object>> getVentasTotalesMensuales(@PathVariable("idTienda") Integer id) {
        Map<String, Integer> ventasPorFecha = new HashMap<>();
        TiendaMascotas tienda = tiendas.get(id - 1);

        if (tienda == null)  {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Tienda no encontrada");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, List<Integer>> entry : tienda.getVentasxproductos().entrySet()) {
            String nombreProducto = entry.getKey();
            Integer precioProducto = tienda.getPrecioProducto(nombreProducto);

            for (Integer venta : entry.getValue()) {
                String[] fecha = tienda.getFechaVentas().split("/");
                
                String mes = fecha[1];

                if (ventasPorFecha.containsKey(mes)) {
                    ventasPorFecha.put(mes, ventasPorFecha.get(mes) + venta * precioProducto);
                } else {
                    ventasPorFecha.put(mes, venta * precioProducto);
                }
            }
        }
        
        Integer totalVentasMensuales = ventasPorFecha.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("El valor total de las ventas mensuales de la tienda", tienda.getNombre() + " es " + totalVentasMensuales);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/tiendas/{idTienda}/total_ventas_diarias")
    public ResponseEntity<Map<String, Object>> getVentasTotalesDiarias(@PathVariable("idTienda") Integer id) {
        Map<String, Integer> ventasPorFecha = new HashMap<>();
        TiendaMascotas tienda = tiendas.get(id - 1);

        if (tienda == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Tienda no encontrada");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        for (Map.Entry<String, List<Integer>> entry : tienda.getVentasxproductos().entrySet()) {
            String nombreProducto = entry.getKey();
            Integer precioProducto = tienda.getPrecioProducto(nombreProducto);

            for (Integer venta : entry.getValue()) {
                String[] fecha = tienda.getFechaVentas().split("/");
                String dia = fecha[0];

                if (ventasPorFecha.containsKey(dia)) {
                    ventasPorFecha.put(dia, ventasPorFecha.get(dia) + venta * precioProducto);
                } else {
                    ventasPorFecha.put(dia, venta * precioProducto);
                }
            }
        }

        Integer totalVentasDiarias = ventasPorFecha.values().stream().mapToInt(Integer::intValue).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("El valor total de las ventas diarias de la tienda",
                tienda.getNombre() + " es " + totalVentasDiarias);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    
}
