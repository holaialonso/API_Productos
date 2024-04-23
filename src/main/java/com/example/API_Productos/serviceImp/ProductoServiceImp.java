package com.example.API_Productos.serviceImp;

import com.example.API_Productos.models.Producto;
import com.example.API_Productos.repository.ProductoRepository;
import com.example.API_Productos.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

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


}
