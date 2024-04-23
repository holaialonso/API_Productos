package com.example.API_Productos.controller;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import com.example.API_Productos.service.CategoriaService;
import com.example.API_Productos.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColorController {

    @Autowired
    ColorService colorService;

    //Método para guardar un color
    public Color addColor(Color color){

        return colorService.addColor(color);
    }

}
