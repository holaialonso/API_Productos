package com.example.API_Productos.serviceImp;

import com.example.API_Productos.repository.TallaProductoRepository;
import com.example.API_Productos.repository.TallaRepository;
import com.example.API_Productos.service.TallaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TallaProductoServiceImp implements TallaProductoService {

    @Autowired
    TallaProductoRepository tallaProductoRepository;
}
