package com.example.API_Productos.models;


import com.example.API_Productos.database.SchemaDB;
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
    public Color (String color){

        this.codColor=makeCodColor(color);
        this.nombre=color;
        this.hexadecimal="#ffffff";
    }

    public Color(int id, String codColor, String color){

        this.id=id;
        this.codColor=codColor;
        this.nombre=color;
    }


    //Método para construir el código del color
    private String makeCodColor(String nombre){

        String codColor = "C00"+nombre.toUpperCase().charAt(0);

        return codColor;
    }
}
