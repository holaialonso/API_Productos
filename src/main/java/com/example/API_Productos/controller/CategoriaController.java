package com.example.API_Productos.controller;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;


    //Método para añadir una categoría
    public Categoria addCategoria(Categoria categoria){
        return categoriaService.addCategoria(categoria);
    }

    //CREATE
    //Método para crear una categoría nueva
    @GetMapping("/category/add")
    public ResponseEntity<Categoria> addCategoria(@RequestParam(value="name") String name){

        //Talla
        Categoria categoria = new Categoria(name.toLowerCase());
        categoria = categoriaService.addCategoria(categoria);

        return new ResponseEntity(categoria, HttpStatus.OK);

    }


    //READ
    //Método para leer una categoría pasando el id
    @GetMapping("/category/get")
    public ResponseEntity getCategory(@RequestParam (value="id") int id){

        //Compruebo si tengo el producto
        if(categoriaService.getCategoria(id).isPresent()){
            Categoria categoria = categoriaService.getCategoria(id).get();
            return new ResponseEntity(categoria, HttpStatus.OK);
        }
        else{

            return new ResponseEntity("La categoría no existe.", HttpStatus.OK);
        }

    }


    //Método para obtener todas las tallas que tenemos dadas de alta
    @GetMapping("/category/getAll")
    public ResponseEntity getAllCategory()  {

        ArrayList<Categoria> categorias = categoriaService.getAllCategorias();

        return new ResponseEntity(categorias, HttpStatus.OK);

    }


    //UPDATE
    //Método para actualizar el nombre de la categoría
    @GetMapping("/category/updateName")
    public ResponseEntity updateCategoryName(@RequestParam (value="id") int id,
                                             @RequestParam (value="name") String nombre){

        nombre = nombre.toLowerCase();

        //Compruebo si tengo el producto
        if(categoriaService.getCategoria(id).isPresent()){
            Categoria categoria = categoriaService.getCategoria(id).get();

            if(categoriaService.issetCategoriaName(nombre)){
                return new ResponseEntity("No se puede actualizar, existe otra categoría con el mismo nombre.", HttpStatus.OK);
            }
            else{
                categoria.setNombre(nombre);
                categoria = categoriaService.updateCategoria(categoria);
                return new ResponseEntity(categoria, HttpStatus.OK);
            }
        }
        else{

            return new ResponseEntity("La categoría no existe", HttpStatus.OK);
        }

    }


    //DELETE
    //Método para eliminar una categoría
    @GetMapping("/category/delete")
    public ResponseEntity<String> deleteCategoria(@RequestParam (value="id") int id){

        String aux="";

        //Compruebo si tengo el producto
        if(categoriaService.getCategoria(id).isPresent()){
            categoriaService.deleteCategoria(id);
            aux="La categoría se ha eliminado correctamente.";
        }
        else{
            aux="La categoría no existe.";
        }

        return new ResponseEntity(aux, HttpStatus.OK);

    }

}
