package edu.martin.javafx.login.mfl_project.model;

public class Producto {
    private int id;
    private String nombre;
    private String color;
    private String talla;
    private double precio;

    public Producto() {}

    public Producto(int id, String nombre, String color, String talla, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.precio = precio;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
