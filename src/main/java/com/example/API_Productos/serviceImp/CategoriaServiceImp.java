package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.repository.CategoriaRepository;
import com.example.API_Productos.repository.ProductoRepository;
import com.example.API_Productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImp implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Método para añadir la categoría -> compruebo si la tengo o si la creo
    @Override
    public Categoria addCategoria(Categoria categoria) {
        if(!categoriaRepository.existsByNombre(categoria.getNombre())){
            return categoriaRepository.save(categoria);
        }
        else{
            return categoriaRepository.findByNombre(categoria.getNombre());
        }


    }


}
