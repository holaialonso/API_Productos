package com.example.API_Productos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class ProductoDTO {

    private int id;
    private String name;
    private String category;
    private String marca;
    private int weight;
    private int quantityPerBox;
    private String composition;
    private Boolean discontinued;
    private List<ColorDTO> colors;
    private List<TallaProductoDTO> sizes;

}
