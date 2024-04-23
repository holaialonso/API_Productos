package com.example.API_Productos.models;

import com.example.API_Productos.database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name= SchemaDB.TAB_CATEGORIAS)
public class Categoria implements Serializable {

    @Id
    @Column(name=SchemaDB.COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name=SchemaDB.COL_NOMBRE)
    private String nombre;

    /*@OneToMany(mappedBy = "categoria", cascade=CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Producto> productos;*/

    //Constructores
    public Categoria(String nombre){
        this.nombre=nombre;
    }
}
