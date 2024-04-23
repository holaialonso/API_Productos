package com.example.API_Productos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

@Setter
@Getter
public class ColorDTO {

    private String codColor;
    private String name;
    private String hex;
}
