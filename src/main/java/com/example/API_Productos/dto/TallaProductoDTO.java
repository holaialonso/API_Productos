package com.example.API_Productos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class TallaProductoDTO {

    private String size;
    private double height;
    private double width;
}
