package com.example.API_Productos.service;


import com.example.API_Productos.models.Talla;

import java.util.ArrayList;
import java.util.Optional;

public interface TallaService {

    Talla addTalla(Talla talla);

    Talla updateTalla(Talla talla);

    Optional<Talla> getTalla (long id);

    ArrayList<Talla> getAllTallas();

    void deleteTalla(long id);

    Boolean issetTallaName(String name);
}
