package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Categoria;
import com.example.API_Productos.models.Talla;
import com.example.API_Productos.repository.CategoriaRepository;
import com.example.API_Productos.repository.ProductoRepository;
import com.example.API_Productos.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

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

    @Override
    public Categoria updateCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> getCategoria(long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public ArrayList<Categoria> getAllCategorias() {
        Iterable<Categoria> aux = categoriaRepository.findAll();
        Iterator<Categoria> iterator = aux.iterator();

        ArrayList<Categoria> categorias = new ArrayList<>();

        while (iterator.hasNext()) {
            categorias.add(iterator.next());
        }

        return categorias;
    }

    @Override
    public void deleteCategoria(long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Boolean issetCategoriaName(String name) {
        return categoriaRepository.existsByNombre(name);
    }


}
