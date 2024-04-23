package com.example.API_Productos.models;


import com.example.API_Productos.database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

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

   /* @ManyToMany(mappedBy = "colores")
    private ArrayList<Producto> productos;*/

    //Constructor
    public Color (String color){

        this.nombre=color;
    }

}
