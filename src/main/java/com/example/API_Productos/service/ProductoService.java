package com.example.API_Productos.service;

import com.example.API_Productos.models.Producto;

import java.util.Optional;

public interface ProductoService {

    Producto addProducto (Producto producto);

    Producto updateProducto(Producto producto);

    Boolean issetProducto(long id);

    Optional<Producto> getProducto (long id);


}
