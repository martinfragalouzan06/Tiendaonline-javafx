package edu.martin.javafx.login.mfl_project.model;

public class Etiqueta {

    private int id;
    private String nombre;

    public Etiqueta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Etiqueta() {
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

    @Override
    public String toString() {
        return nombre;
    }
}
