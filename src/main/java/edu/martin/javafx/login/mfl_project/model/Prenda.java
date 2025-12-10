package edu.martin.javafx.login.mfl_project.model;

public class Prenda {

    private int id;
    private String nombre;
    private String color;
    private String talla;
    private Categoria categoria;

    private int cantidadEtiquetas;
    private String etiquetasResumen;

    public Prenda(int id, String nombre, String color, String talla, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.categoria = categoria;
    }

    public Prenda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCantidadEtiquetas() {
        return cantidadEtiquetas;
    }

    public void setCantidadEtiquetas(int cantidadEtiquetas) {
        this.cantidadEtiquetas = cantidadEtiquetas;
    }

    public String getEtiquetasResumen() {
        return etiquetasResumen;
    }

    public void setEtiquetasResumen(String etiquetasResumen) {
        this.etiquetasResumen = etiquetasResumen;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
