package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import com.example.API_Productos.repository.CategoriaRepository;
import com.example.API_Productos.repository.ColorRepository;
import com.example.API_Productos.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;

public class ColorServiceImp implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    //Método para añadir el color -> compruebo si lo tengo o si la creo
    @Override
    public Color addColor(Color color) {
        if(!colorRepository.existsByNombre(color.getNombre())){
            return colorRepository.save(color);
        }
        else{
            return colorRepository.findByNombre(color.getNombre());
        }


    }
}
