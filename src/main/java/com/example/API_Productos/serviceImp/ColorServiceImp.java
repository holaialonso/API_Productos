package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Producto;
import com.example.API_Productos.repository.CategoriaRepository;
import com.example.API_Productos.repository.ColorRepository;
import com.example.API_Productos.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ColorServiceImp implements ColorService {

    @Autowired
    private ColorRepository colorRepository;


    @Override
    public Color addColor(Color color) {

        if(!colorRepository.existsByNombre(color.getNombre())){
            return colorRepository.save(color);
        }
        else{
            return colorRepository.findByNombre(color.getNombre());
        }
    }

    @Override
    public Color updateColor(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Optional<Color> getColor(long id) {
        return colorRepository.findById(id);
    }

    @Override
    public ArrayList<Color> getAllColores() {

        Iterable<Color> aux = colorRepository.findAll();
        Iterator<Color> iterator = aux.iterator();

        ArrayList<Color> colores = new ArrayList<>();

        while (iterator.hasNext()) {
            colores.add(iterator.next());
        }

        return colores;
    }

    @Override
    public void deleteColor(long id) {
        colorRepository.deleteById(id);
    }
}
