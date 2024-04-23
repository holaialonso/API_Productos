package com.example.API_Productos.repository;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    boolean existsByNombre(String nombre);

    Producto findByNombre(String nombre);


}

