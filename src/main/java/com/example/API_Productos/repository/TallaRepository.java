package com.example.API_Productos.repository;

import com.example.API_Productos.models.Talla;
import org.springframework.data.repository.CrudRepository;

public interface TallaRepository extends CrudRepository<Talla, Long> {

    boolean existsByNombre(String nombre);

    Talla findByNombre(String nombre);
}
