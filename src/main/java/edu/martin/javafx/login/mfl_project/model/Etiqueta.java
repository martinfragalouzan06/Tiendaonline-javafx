package edu.martin.javafx.login.mfl_project.model;

public class Etiqueta {

    private int id;
    private String nombre;

    public Etiqueta() {}
    public Etiqueta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
