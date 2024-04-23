package com.example.API_Productos.service;

import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Producto;
import com.example.API_Productos.models.Talla;

import java.util.Optional;

public interface ProductoService {

    Producto addProducto (Producto producto);

    Boolean issetProducto(long id);

    Optional<Producto> getProducto (long id);

    void saveColorProducto(Producto producto, Color color);

    void saveTallaProducto(Producto producto, Talla talla);

}
