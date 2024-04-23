package com.example.API_Productos.repository;

import com.example.API_Productos.models.Talla;
import com.example.API_Productos.models.TallaProducto;
import org.springframework.data.repository.CrudRepository;

public interface TallaProductoRepository extends CrudRepository<TallaProducto, Long> {
}
