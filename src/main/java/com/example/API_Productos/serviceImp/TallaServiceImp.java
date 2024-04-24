package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Color;
import com.example.API_Productos.models.Talla;
import com.example.API_Productos.repository.TallaRepository;
import com.example.API_Productos.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

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

    @Override
    public Talla updateTalla(Talla talla) {
        return tallaRepository.save(talla);
    }

    @Override
    public Optional<Talla> getTalla(long id) {
        return tallaRepository.findById(id);
    }

    @Override
    public ArrayList<Talla> getAllTallas() {
        Iterable<Talla> aux = tallaRepository.findAll();
        Iterator<Talla> iterator = aux.iterator();

        ArrayList<Talla> tallas = new ArrayList<>();

        while (iterator.hasNext()) {
            tallas.add(iterator.next());
        }

        return tallas;
    }

    @Override
    public void deleteTalla(long id) {
        tallaRepository.deleteById(id);
    }

    @Override
    public Boolean issetTallaName(String name) {
        return tallaRepository.existsByNombre(name);
    }
}
