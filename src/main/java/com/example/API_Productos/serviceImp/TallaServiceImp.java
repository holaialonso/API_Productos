package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Talla;
import com.example.API_Productos.repository.TallaRepository;
import com.example.API_Productos.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TallaServiceImp implements TallaService {

    @Autowired
    TallaRepository tallaRepository;

    @Override
    public Talla addTalla(Talla talla) {

        if(!tallaRepository.existsByNombre(talla.getNombre())){
            return tallaRepository.save(talla);
        }
        else{
            return tallaRepository.findByNombre(talla.getNombre());
        }
    }
}
