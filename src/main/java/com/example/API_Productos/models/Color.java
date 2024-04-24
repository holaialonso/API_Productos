package com.example.API_Productos.models;


import com.example.API_Productos.database.SchemaDB;
import com.example.API_Productos.dto.ColorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name= SchemaDB.TAB_COLORES)
public class Color {

    @Id
    @Column(name=SchemaDB.COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name=SchemaDB.COL_CODCOLOR)
    private String codColor;

    @Column(name=SchemaDB.COL_NOMBRE)
    private String nombre;

    @Column(name=SchemaDB.COL_HEXADECIMAL)
    private String hexadecimal;



    //Constructor
    public Color (String color, String hexadecimal){

        color = color.toLowerCase();
        this.codColor=makeCodColor(color);
        this.nombre=color;
        this.hexadecimal=hexadecimal;
    }


    //Setter
    public void setCodColor(){

        this.codColor=makeCodColor(this.nombre);
    }


    //Método para construir el código del color
    private String makeCodColor(String nombre){

        String codColor = "C00"+nombre.toUpperCase().charAt(0);

        return codColor;
    }


    //Método para mostrar el color con unos valores determinados
    public ColorDTO toDataTransferObject(){

        ColorDTO color = new ColorDTO(
                                        this.getCodColor(),
                                        this.getNombre(),
                                        this.getHexadecimal()
                                        );

        return color;
    }
}
