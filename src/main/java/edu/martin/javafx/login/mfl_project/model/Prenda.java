package edu.martin.javafx.login.mfl_project.model;

public class Prenda {


    private int id;
    private String nombre;
    private String color;
    private String talla;
    private int usuarioId;
    private Categoria categoria;

    public Prenda() {}

    public Prenda(int id, String nombre, String color, String talla, int usuarioId, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.usuarioId = usuarioId;
        this.categoria = categoria;
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
