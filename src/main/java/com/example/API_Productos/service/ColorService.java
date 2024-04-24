package com.example.API_Productos.service;

import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Producto;

import java.util.ArrayList;
import java.util.Optional;

public interface ColorService {

    Color addColor(Color color);

    Color updateColor(Color color);

    Optional<Color> getColor (long id);

    ArrayList<Color> getAllColores();

    void deleteColor(long id);
}
