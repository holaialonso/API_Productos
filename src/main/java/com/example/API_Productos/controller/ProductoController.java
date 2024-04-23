package com.example.API_Productos.controller;

import com.example.API_Productos.json_structure.ProductoColores;
import com.example.API_Productos.json_structure.ProductoTallas;
import com.example.API_Productos.models.*;
import com.example.API_Productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/product/addColors")
    public ResponseEntity<String> addProductColors(@RequestBody ProductoColores params

    ){
        int id = params.getId();
        Map<String, String> colores = params.getColors();
        String aux="";

        //En caso de que el producto exista -> introduzco los colores
        if(productoService.getProducto(id).isPresent()){

            Producto producto = productoService.getProducto(id).get();
            List<Color> auxColores = makeColores(colores);
            producto.setColores(auxColores);
            productoService.updateProducto(producto);

            aux="Los colores se han añadido correctamente al producto";


        }
        else{

            aux="El producto no existe, no se pueden añadir colores";

        }

        return new ResponseEntity<String>(aux, HttpStatus.OK);

    }


        //Método para montar el arrayList de colores y guardar los colores del producto
        private List<Color> makeColores(Map<String, String> colores){

            List<Color> aux = new ArrayList<>();

            // Iterar sobre el mapa de colores
            for (Map.Entry<String, String> entry : colores.entrySet()) {

                String nombreColor = entry.getKey();
                String hexadecimal = entry.getValue();

                Color color = colorController.addColor(new Color(nombreColor, hexadecimal)); //guardo el color
                aux.add(color); //lo añado al array de colores del product
            }

            return aux;
        }


    //Método para añadir tallas del producto
    @PostMapping("/product/addSizes")
    public ResponseEntity <String> addProductSizes(@RequestBody ProductoTallas params

    ){
        int id = params.getId();
        Map<String, String> tallas = params.getSizes();
        String aux="";

        //En caso de que el producto exista -> introduzco los colores
        if(productoService.getProducto(id).isPresent()){

            Producto producto = productoService.getProducto(id).get();
            List<TallaProducto> auxTallas = makeTallas(producto, tallas);
            producto.setTallas(auxTallas);
            productoService.updateProducto(producto);

            aux="Las tallas se han añadido correctamente al producto.";


        }
        else{

            aux="El producto no existe, no se pueden añadir tallas.";

        }

        return new ResponseEntity<String>(aux, HttpStatus.OK);


    }

        //Método para montar el arrayList de las tallas y guardar las tallas del producto
        private List<TallaProducto> makeTallas(Producto producto, Map<String, String> tallas){

            List<TallaProducto> aux = new ArrayList<>();

            for (Map.Entry<String, String> entry : tallas.entrySet()) {

                double ancho = 0;
                double alto=0;

                String nombreTalla = entry.getKey();

                //Calcular la medida
                String medida = entry.getValue().toLowerCase();
                String[] medidas = medida.split("x");

                if(medidas.length==2){
                    ancho = Double.parseDouble(medidas[0]);
                    alto = Double.parseDouble(medidas[1]);
                }


                Talla talla = tallaController.addTalla(new Talla(nombreTalla));
                aux.add(new TallaProducto(producto, talla, ancho, alto));
            }

            return aux;

        }





}
