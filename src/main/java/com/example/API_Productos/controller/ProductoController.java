package com.example.API_Productos.controller;

import com.example.API_Productos.dto.ProductoDTO;
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

    //CREATE
    //Método para añadir un producto nuevo
    @GetMapping("/product/add")
    public ResponseEntity<ProductoDTO> addProducto(@RequestParam (value="name") String nombre,
                                                   @RequestParam (value="brand") String marca,
                                                   @RequestParam (value="weight") int gramaje,
                                                   @RequestParam (value="quantityBox") int cantCaja,
                                                   @RequestParam (value="composition") String composicion,
                                                   @RequestParam (value="category") String categoria
                                                ){
        //Categoria
        Categoria auxCategoria = categoriaController.addCategoria(new Categoria (categoria.toLowerCase()));

        //Producto
        Producto producto = new Producto(nombre, marca, gramaje, cantCaja, composicion, 1, auxCategoria);
        producto=productoService.addProducto(producto);

        return new ResponseEntity(producto.toDataTransferObject(), HttpStatus.OK);

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


    //READ - SELECT
    @GetMapping("/product/get")
    public ResponseEntity readProducto(@RequestParam (value="id") int id) {

        //Compruebo si tengo el producto
        if(productoService.getProducto(id).isPresent()){
            Producto producto = productoService.getProducto(id).get();
            return new ResponseEntity(producto.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El producto no existe.", HttpStatus.OK);
        }

    }


    @GetMapping("/product/getAll")
    public ResponseEntity readAllProductos()  {

       //productoService.find
        ArrayList<Producto> productos = productoService.getAllProductos();
        ArrayList<ProductoDTO> response = new ArrayList<>();

        for(int i=0; i<productos.size(); i++){

            response.add(productos.get(i).toDataTransferObject());
        }

        return new ResponseEntity(response, HttpStatus.OK);

    }

    //UPDATE
    //Método para actualizar el nombre del producto
    @GetMapping("/product/updateName")
    public ResponseEntity updateProductName(@RequestParam (value="id") int id,
                                            @RequestParam (value="name") String nombre){

        //Compruebo si tengo el producto
        if(productoService.getProducto(id).isPresent()){
            Producto producto = productoService.getProducto(id).get();
                     producto.setNombre(nombre);
                     producto = productoService.updateProducto(producto);
            return new ResponseEntity(producto.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El producto no existe.", HttpStatus.OK);
        }

    }


    //Método para actualizar la categoría del producto
    @GetMapping("/product/updateCategory")
    public ResponseEntity updateProductCategory(@RequestParam (value="id") int id,
                                                @RequestParam (value="category") String categoria){

        //Compruebo si tengo el producto
        if(productoService.getProducto(id).isPresent()){

            Categoria auxCategoria = categoriaController.addCategoria(new Categoria (categoria.toLowerCase()));

            Producto producto = productoService.getProducto(id).get();
                     producto.setCategoria(auxCategoria);
                     producto = productoService.updateProducto(producto);
            return new ResponseEntity(producto.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El producto no existe.", HttpStatus.OK);
        }

    }


    //Método para descatalogar / activar un producto
    @GetMapping("/product/updateDiscontinued")
    public ResponseEntity updateProductDiscontinued(@RequestParam (value="id") int id,
                                                    @RequestParam (value="discontinued") Boolean discontinued){

        //Compruebo si tengo el producto
        if(productoService.getProducto(id).isPresent()){
            Producto producto = productoService.getProducto(id).get();
            producto.setDescatalogado((discontinued) ? 0 : 1);
            producto = productoService.updateProducto(producto);
            return new ResponseEntity(producto.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El producto no existe.", HttpStatus.OK);
        }

    }


    //DELETE
    //Método para eliminar un producto
    @GetMapping("/product/delete")
    public ResponseEntity<String> deleteProducto(@RequestParam (value="id") int id){

        String aux="";

        //Compruebo si tengo el producto
        if(productoService.getProducto(id).isPresent()){
            productoService.deleteProducto(id);
            aux="El producto se ha eliminado correctamente.";
        }
        else{
            aux="El producto no existe.";
        }

        return new ResponseEntity(aux, HttpStatus.OK);
    }

}
