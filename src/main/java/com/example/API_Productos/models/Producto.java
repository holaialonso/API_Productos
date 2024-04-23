package com.example.API_Productos.models;

import com.example.API_Productos.database.SchemaDB;
import com.example.API_Productos.dto.ColorDTO;
import com.example.API_Productos.dto.ProductoDTO;
import com.example.API_Productos.dto.TallaProductoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.validation.Schema;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


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

    @OneToOne
    @JoinColumn(name=SchemaDB.COL_REFIDCATEGORIA)
    private Categoria categoria;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = SchemaDB.TAB_COLORESPRODUCTOS,
            joinColumns = @JoinColumn(name = SchemaDB.COL_REFIDPRODUCTO),
            inverseJoinColumns = @JoinColumn(name = SchemaDB.COL_REFIDCOLOR)
    )
    private List<Color> colores = new ArrayList<>();

    @ManyToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<TallaProducto> tallas = new ArrayList<>();


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


    //Método para mostrar el producto con unos valores determinados
    public ProductoDTO toDataTransferObject()  {

        ProductoDTO producto = new ProductoDTO(
                                                this.id,
                                                this.nombre,
                                                this.categoria.getNombre(),
                                                this.marca,
                                                this.gramaje,
                                                this.cantCaja,
                                                this.composicion,
                                                (this.descatalogado==1) ? false : true,
                                                toDataTransferObject_Colors(),
                                                toDataTransferObject_Sizes()
                                                );

        return producto;
    }

        //Método para formatear a json los colores del producto
        private List<ColorDTO> toDataTransferObject_Colors() {

            List<ColorDTO> aux = new ArrayList();

            for(int i=0; i<this.colores.size(); i++){

                Color color = this.colores.get(i);
                aux.add(new ColorDTO(color.getCodColor(), color.getNombre(), color.getHexadecimal()));

            }

            return aux;

        }


        //Método para formatear a json las tallas del producto
        private List<TallaProductoDTO> toDataTransferObject_Sizes() {

            List<TallaProductoDTO> aux = new ArrayList();

            for(int i=0; i<this.tallas.size(); i++){

                TallaProducto talla = this.tallas.get(i);
                aux.add(new TallaProductoDTO(talla.getTalla().getNombre(), talla.getAlto(), talla.getAncho()));
            }

            return aux;
        }




}

