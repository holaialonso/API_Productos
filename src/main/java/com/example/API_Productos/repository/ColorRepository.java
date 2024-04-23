package com.example.API_Productos.repository;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import org.springframework.data.repository.CrudRepository;

public interface ColorRepository extends CrudRepository<Color, Long> {

    Boolean existsByNombre(String nombre);

    Color findByNombre(String nombre);
}
