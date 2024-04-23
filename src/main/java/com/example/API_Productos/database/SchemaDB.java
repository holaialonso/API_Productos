package com.example.API_Productos.database;

public interface SchemaDB {

    String DB_NAME="API_Productos";
    String SERVER="127.0.0.1:3306";
    String USER="root";
    String PASSWORD="";

    //Tablas
    String TAB_PRODUCTOS="productos";
    String TAB_CATEGORIAS="categorias";
    String TAB_COLORES="colores";
    String TAB_TALLAS="tallas";
    String TAB_COLORESPRODUCTOS="colores_productos";
    String TAB_TALLASPRODUCTOS="tallas_productos";

    //Columnas
    String COL_ID="id";
    String COL_NOMBRE="nombre";
    String COL_GRAMAJE="gramaje";
    String COL_CANTCAJA="cant_caja";
    String COL_COMPOSICION="composicion";
    String COL_DESCATALOGADO="activo";
    String COL_MARCA="marca";
    String COL_REFIDCATEGORIA="ref_id_categoria";
    String COL_CODCOLOR="cod_color";
    String COL_REFIDCOLOR="ref_id_color";
    String COL_REFIDPRODUCTO="ref_id_producto";
    String COL_REFIDTALLA="ref_id_talla";
    String COL_ANCHO="ancho";
    String COL_ALTO="alto";


}