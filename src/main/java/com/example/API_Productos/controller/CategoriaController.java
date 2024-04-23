package com.example.API_Productos.controller;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.service.CategoriaService;
import com.example.API_Productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;


    //Método para añadir una categoría
    public Categoria addCategoria(Categoria categoria){

        return categoriaService.addCategoria(categoria);
    }

}
