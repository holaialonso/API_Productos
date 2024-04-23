package com.example.API_Productos.models;

import com.example.API_Productos.database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name= SchemaDB.TAB_TALLASPRODUCTOS)
public class TallaProducto implements Serializable{

    @Id
    @Column(name=SchemaDB.COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = SchemaDB.COL_ALTO)
    private double ancho;

    @Column(name = SchemaDB.COL_ANCHO)
    private double alto;
}

