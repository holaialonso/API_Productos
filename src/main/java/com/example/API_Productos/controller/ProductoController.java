package com.example.API_Productos.controller;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Producto;
import com.example.API_Productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Autowired
    CategoriaController categoriaController;

    @Autowired
    ColorController colorController;

    //Método para añadir un producto nuevo
    @GetMapping("/product/add")
    public ResponseEntity<Producto> addProducto(@RequestParam (value="name") String nombre,
                                                @RequestParam (value="brand") String marca,
                                                @RequestParam (value="weight") int gramaje,
                                                @RequestParam (value="quantityBox") int cantCaja,
                                                @RequestParam (value="composition") String composicion,
                                                @RequestParam (value="category") String categoria){

        Categoria auxCategoria = categoriaController.addCategoria(new Categoria (categoria));
        Producto producto = new Producto(nombre, marca, gramaje, cantCaja, composicion, 1, auxCategoria);
        producto=productoService.addProducto(producto);

        return new ResponseEntity<>(producto, HttpStatus.OK);

    }


    //Método para añadir colores al producto
    @GetMapping("/product/addColors")
    public ResponseEntity<Producto> addProductoColores( @RequestParam (value="id") int id,
                                                        @RequestParam (value="colors") String[] colores){

        Optional<Producto> producto=productoService.getProducto(id);

        if(producto!=null){

            ArrayList<Color> aux = new ArrayList<Color>;

            for(int i=0; i<colores.length; i++){

                aux.add(colorController.addColor(new Color(colores[i])));

            }

            producto.setColores(aux);
            productoService.addProducto(producto);

            return new ResponseEntity<>(productoService.getProducto(id), HttpStatus.OK);
        }
        else{
            return ResponseEntity.notFound().build();
        }


    }




}
