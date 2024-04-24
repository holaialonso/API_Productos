package com.example.API_Productos.controller;

import com.example.API_Productos.dto.ColorDTO;
import com.example.API_Productos.dto.ProductoDTO;
import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Producto;
import com.example.API_Productos.service.CategoriaService;
import com.example.API_Productos.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ColorController {

    @Autowired
    ColorService colorService;

    //Método para guardar un color
    public Color addColor(Color color){
        return colorService.addColor(color);
    }


    //CREATE
    //Método para crear un color nuevo
    @GetMapping("/color/add")
    public ResponseEntity<ColorDTO> addColor(@RequestParam (value="name") String name,
                                             @RequestParam (value="hexadecimal") String hexadecimal){

        //Color
        Color color = new Color(name, hexadecimal);
              color = colorService.addColor(color);

        return new ResponseEntity(color.toDataTransferObject(), HttpStatus.OK);

    }


    //READ
    //Método para obtener el color pasando un id
    @GetMapping("/color/get")
    public ResponseEntity<ColorDTO> getColor(@RequestParam (value="id") int id){

        //Compruebo si tengo el producto
        if(colorService.getColor(id).isPresent()){
            Color color = colorService.getColor(id).get();
            return new ResponseEntity(color.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El color no existe.", HttpStatus.OK);
        }

    }


    //Método para obtener todos los colores que tenemos dados de alta
    @GetMapping("/color/getAll")
    public ResponseEntity readAllColors()  {

        ArrayList<Color> colores = colorService.getAllColores();
        ArrayList<ColorDTO> response = new ArrayList<>();

        for(int i=0; i<colores.size(); i++){

            response.add(colores.get(i).toDataTransferObject());
        }

        return new ResponseEntity(response, HttpStatus.OK);

    }

    //UPDATE
    //Método para actualizar el nombre del color (al mismo tiempo actualiza el código del color)
    @GetMapping("/color/updateName")
    public ResponseEntity updateColorName(@RequestParam (value="id") int id,
                                          @RequestParam (value="name") String nombre){

        //Compruebo si tengo el producto
        if(colorService.getColor(id).isPresent()){
            Color color = colorService.getColor(id).get();
            color.setNombre(nombre);
            color.setCodColor();
            color = colorService.updateColor(color);
            return new ResponseEntity(color.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El color no existe.", HttpStatus.OK);
        }

    }


    //Método para actualizar el nombre del color (al mismo tiempo actualiza el código del color)
    @GetMapping("/color/updateHexadecimal")
    public ResponseEntity updateColorHexadecimal(@RequestParam (value="id") int id,
                                                 @RequestParam (value="hex") String hexadecimal){

        //Compruebo si tengo el producto
        if(colorService.getColor(id).isPresent()){
            Color color = colorService.getColor(id).get();
            color.setHexadecimal(hexadecimal);
            color = colorService.updateColor(color);
            return new ResponseEntity(color.toDataTransferObject(), HttpStatus.OK);
        }
        else{

            return new ResponseEntity("El color no existe.", HttpStatus.OK);
        }

    }


    //DELETE
    //Método para eliminar un color
    @GetMapping("/color/delete")
    public ResponseEntity<String> deleteColor(@RequestParam (value="id") int id){

        String aux="";

        //Compruebo si tengo el producto
        if(colorService.getColor(id).isPresent()){
            colorService.deleteColor(id);
            aux="El color se ha eliminado correctamente.";
        }
        else{
            aux="El color no existe.";
        }

        return new ResponseEntity(aux, HttpStatus.OK);

    }





}
