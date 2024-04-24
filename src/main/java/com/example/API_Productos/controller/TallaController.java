package com.example.API_Productos.controller;


import com.example.API_Productos.models.Talla;
import com.example.API_Productos.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TallaController {

    @Autowired
    TallaService tallaService;

    //Método para guardar la talla
    public Talla addTalla(Talla talla){
        return tallaService.addTalla(talla);
    }


    //CREATE
    //Método para crear una talla nueva
    @GetMapping("/size/add")
    public ResponseEntity<Talla> addTalla(@RequestParam(value="name") String name){

        //Talla
        Talla talla = new Talla(name);
        talla = tallaService.addTalla(talla);

        return new ResponseEntity(talla, HttpStatus.OK);

    }


    //READ
    //Método para leer una talla pasando el id
    @GetMapping("/size/get")
    public ResponseEntity<Talla> getTalla(@RequestParam (value="id") int id){

        //Compruebo si tengo el producto
        if(tallaService.getTalla(id).isPresent()){
            Talla talla = tallaService.getTalla(id).get();
            return new ResponseEntity(talla, HttpStatus.OK);
        }
        else{

            return new ResponseEntity("La talla no existe.", HttpStatus.OK);
        }

    }

    //Método para obtener todas las tallas que tenemos dadas de alta
    @GetMapping("/size/getAll")
    public ResponseEntity getAllTallas()  {

        ArrayList<Talla> tallas = tallaService.getAllTallas();

        return new ResponseEntity(tallas, HttpStatus.OK);

    }


    //UPDATE
    //Método para actualizar el nombre de la talla
    @GetMapping("/size/updateName")
    public ResponseEntity updateTallaName(@RequestParam (value="id") int id,
                                          @RequestParam (value="name") String nombre){

        //Compruebo si tengo el producto
        if(tallaService.getTalla(id).isPresent()){
            Talla talla = tallaService.getTalla(id).get();

            if(tallaService.issetTallaName(nombre)){
                return new ResponseEntity("No se puede actualizar, existe otra talla con el mismo nombre.", HttpStatus.OK);
            }
            else{
                talla.setNombre(nombre);
                talla = tallaService.updateTalla(talla);
                return new ResponseEntity(talla, HttpStatus.OK);
            }
        }
        else{

            return new ResponseEntity("La talla no existe", HttpStatus.OK);
        }

    }

    //DELETE
    //Método para eliminar una talla
    @GetMapping("/size/delete")
    public ResponseEntity<String> deleteTalla(@RequestParam (value="id") int id){

        String aux="";

        //Compruebo si tengo el producto
        if(tallaService.getTalla(id).isPresent()){
            tallaService.deleteTalla(id);
            aux="La talla se ha eliminado correctamente.";
        }
        else{
            aux="La talla no existe.";
        }

        return new ResponseEntity(aux, HttpStatus.OK);

    }

}
