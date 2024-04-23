package com.example.API_Productos.models;

import com.example.API_Productos.database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.validation.Schema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name= SchemaDB.TAB_TALLAS)
public class Talla implements Serializable {

    @Id
    @Column(name=SchemaDB.COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= SchemaDB.COL_NOMBRE)
    private String nombre;


    //Constructor
    public Talla(String nombre){
        this.nombre=nombre;
    }
}
