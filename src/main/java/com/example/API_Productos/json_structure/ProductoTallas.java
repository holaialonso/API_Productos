package com.example.API_Productos.json_structure;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class ProductoTallas {

    private int id;
    private Map<String, String> sizes;
}