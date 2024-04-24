package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Producto;
import com.example.API_Productos.repository.ColorRepository;
import com.example.API_Productos.repository.ProductoRepository;
import com.example.API_Productos.repository.TallaRepository;
import com.example.API_Productos.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Override
    public Producto addProducto(Producto producto) {

        if(!productoRepository.existsByNombre(producto.getNombre())){
            return productoRepository.save(producto);
        }
        else{
            return productoRepository.findByNombre(producto.getNombre());
        }


    }

    @Override
    public Producto updateProducto(Producto producto) {

        return productoRepository.save(producto);
    }

    @Override
    public Boolean issetProducto(long id) {

        if(productoRepository.existsById(id)){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Optional<Producto> getProducto(long id) {

        return productoRepository.findById(id);
    }

    @Override
    public ArrayList<Producto> getAllProductos() {

        Iterable<Producto> aux = productoRepository.findAll();
        Iterator<Producto> iterator = aux.iterator();

        ArrayList<Producto> productos = new ArrayList<>();

        while (iterator.hasNext()) {
           productos.add(iterator.next());
        }

        return productos;
    }

    @Override
    public void deleteProducto(long id) {
        productoRepository.deleteById(id);
    }


}
