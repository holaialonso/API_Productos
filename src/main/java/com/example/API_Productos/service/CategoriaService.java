package com.example.API_Productos.service;

import com.example.API_Productos.models.Categoria;

import java.util.ArrayList;
import java.util.Optional;

public interface CategoriaService {

    Categoria addCategoria(Categoria categoria);

    Categoria updateCategoria(Categoria categoria);

    Optional<Categoria> getCategoria (long id);

    ArrayList<Categoria> getAllCategorias();

    void deleteCategoria(long id);

    Boolean issetCategoriaName(String name);


}
