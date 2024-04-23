package com.example.API_Productos.models;

import com.example.API_Productos.database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.validation.Schema;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name=SchemaDB.TAB_PRODUCTOS)

public class Producto implements Serializable {

    @Id
    @Column(name=SchemaDB.COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= SchemaDB.COL_NOMBRE)
    private String nombre;

    @Column(name=SchemaDB.COL_MARCA)
    private String marca;

    @Column(name=SchemaDB.COL_GRAMAJE)
    private int gramaje; //gramaje de la prenda

    @Column(name=SchemaDB.COL_CANTCAJA)
    private int cantCaja; //cantidad de prendas que cabe en una caja

    @Column(name=SchemaDB.COL_COMPOSICION)
    private String composicion;

    @Column(name=SchemaDB.COL_DESCATALOGADO)
    private int descatalogado;

    @ManyToOne
    @JoinColumn(name=SchemaDB.COL_REFIDCATEGORIA)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = SchemaDB.TAB_COLORESPRODUCTOS,
            joinColumns = @JoinColumn(name = SchemaDB.COL_REFIDPRODUCTO),
            inverseJoinColumns = @JoinColumn(name = SchemaDB.COL_REFIDCOLOR)
    )
    private ArrayList<Color> colores;

    @ManyToMany
    @JoinTable(
            name = SchemaDB.TAB_TALLASPRODUCTOS,
            joinColumns = @JoinColumn(name = SchemaDB.COL_REFIDPRODUCTO),
            inverseJoinColumns = @JoinColumn(name = SchemaDB.COL_REFIDTALLA)
    )
    private ArrayList<Talla> tallas;


    //Constructores
    public Producto(String nombre, String marca, int gramaje, int cantCaja, String composicion, int descatalogado, Categoria categoria){

        this.nombre=nombre;
        this.marca=marca;
        this.gramaje=gramaje;
        this.cantCaja=cantCaja;
        this.composicion=composicion;
        this.descatalogado=descatalogado;
        this.categoria=categoria;
    }


    public void setColores(Array
                           )

}

