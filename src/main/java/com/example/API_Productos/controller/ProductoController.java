package com.example.API_Productos.controller;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Producto;
import com.example.API_Productos.models.Talla;
import com.example.API_Productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
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

    @Autowired
    TallaController tallaController;

    //Método para añadir un producto nuevo
    @GetMapping("/product/add")
    public ResponseEntity<Producto> addProducto(@RequestParam (value="name") String nombre,
                                                @RequestParam (value="brand") String marca,
                                                @RequestParam (value="weight") int gramaje,
                                                @RequestParam (value="quantityBox") int cantCaja,
                                                @RequestParam (value="composition") String composicion,
                                                @RequestParam (value="category") String categoria,
                                                @RequestParam (value="colors") String[] colores,
                                                @RequestParam (value="sizes") String[] tallas
                                                ){
        //Categoria
        Categoria auxCategoria = categoriaController.addCategoria(new Categoria (categoria));

        //Producto
        Producto producto = new Producto(nombre, marca, gramaje, cantCaja, composicion, 1, auxCategoria);
        producto=productoService.addProducto(producto);

        //Colores
        producto=makeColores(producto, colores);

        //Tallas
        producto=makeTallas(producto, tallas);

        return new ResponseEntity<>(producto, HttpStatus.OK);

    }

        //Método para montar el arrayList de colores y guardar los colores del producto
        private Producto makeColores(Producto producto, String[] colores){

            ArrayList<Color> aux = new ArrayList<>();

            for(int i=0; i<colores.length; i++){

                Color color = colorController.addColor(new Color(colores[i])); //guardo el color
                productoService.saveColorProducto(producto, color); //lo asigno al producto
                aux.add(new Color(color.getId(), color.getCodColor(), color.getNombre())); //lo añado al array de colores del producto
            }

           // producto.setColores(aux); //seteo el array de colores en el producto

            return producto;

        }


        //Método para montar el arrayList de las tallas y guardar las tallas del producto
        private Producto makeTallas(Producto producto, String[] tallas){

            ArrayList<Talla> aux = new ArrayList<>();

            for(int i=0; i<tallas.length; i++){

                Talla talla = tallaController.addTalla(new Talla(tallas[i]));
                productoService.saveTallaProducto(producto, talla); //asigno la talla a un producto

            }

            return producto;

        }





}
