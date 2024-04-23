package com.example.API_Productos.controller;

import com.example.API_Productos.service.TallaProductoService;
import com.example.API_Productos.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TallaProductoController {

    @Autowired
    TallaProductoService tallaProductoService;
}
