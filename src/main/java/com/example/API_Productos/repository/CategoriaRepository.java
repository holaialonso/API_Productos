package com.example.API_Productos.repository;

import com.example.API_Productos.models.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);

    Categoria findByNombre(String nombre);

}
