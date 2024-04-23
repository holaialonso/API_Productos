package com.example.API_Productos.controller;

import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Talla;
import com.example.API_Productos.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TallaController {

    @Autowired
    TallaService tallaService;

    //Método para guardar la talla
    public Talla addTalla(Talla talla){

        return tallaService.addTalla(talla);
    }

}
