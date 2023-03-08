package com.example.tiendadejuancho.controladores;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.tiendadejuancho.entidades.Producto;
import com.example.tiendadejuancho.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tienda/api/")
public class ControladorProducto {

    @Autowired
    ServicioProducto servicioProducto;

    @PostMapping
    public ResponseEntity<?> registrarProducto(@RequestBody Producto producto){
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(servicioProducto.agregar(producto));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body("Erroe al registrar el producto");
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosProductos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicioProducto.buscarTodos());
        }catch(Exception error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error buscando los productos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoId(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicioProducto.buscarPorId(id));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error consultando el producto");
        }
    }
}
