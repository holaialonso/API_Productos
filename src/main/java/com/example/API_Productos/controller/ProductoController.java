package com.example.API_Productos.controller;

import com.example.API_Productos.models.*;
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
import java.util.List;
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
                                                @RequestParam (value="category") String categoria
                                                ){
        //Categoria
        Categoria auxCategoria = categoriaController.addCategoria(new Categoria (categoria));

        //Producto
        Producto producto = new Producto(nombre, marca, gramaje, cantCaja, composicion, 1, auxCategoria);
        producto=productoService.addProducto(producto);

        return new ResponseEntity<>(producto, HttpStatus.OK);

    }


    //Método para añadir colores al producto
    @GetMapping("/product/addColors")
    public ResponseEntity<String> addProductColors(@RequestParam (value="id") int id,
                                                   @RequestParam (value="colors") String[] colores
    ){
        String aux="";

        //En caso de que el producto exista -> introduzco los colores
        if(productoService.getProducto(id).isPresent()){

            Producto producto = productoService.getProducto(id).get();
            producto.setColores(makeColores(colores));
            productoService.addProducto(producto);

            aux="Los colores se han añadido correctamente al producto";

        }
        else{

            aux="El producto no existe, no se pueden añadir colores";
        }

        return new ResponseEntity<>(aux, HttpStatus.OK);

    }


        //Método para montar el arrayList de colores y guardar los colores del producto
        private List<Color> makeColores(String[] colores){

            List<Color> aux = new ArrayList<>();

            for(int i=0; i<colores.length; i++){

                Color color = colorController.addColor(new Color(colores[i])); //guardo el color
                aux.add(new Color(color.getId(), color.getCodColor(), color.getNombre())); //lo añado al array de colores del producto
            }

            return aux;
        }


        //Método para montar el arrayList de las tallas y guardar las tallas del producto
       /* private List<TallaProducto> makeTallas(String[] tallas){

            List<TallaProducto> aux = new ArrayList<>();

            for(int i=0; i<tallas.length; i++){

                Talla talla = tallaController.addTalla(new Talla(tallas[i]));
                aux.add(new TallaProducto(talla, 0, 0));
            }

            return aux;

        }*/





}
