package edu.martin.javafx.login.mfl_project.model;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
    private String nombre;
    private String apellido;
    private String nickname;
    private String password;
    private String email;
    private LocalDate fecha_nacimiento;

    private List<Producto> productosFavoritos;
    private boolean admin = false;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String nickname, String password, String email, LocalDate fecha_nacimiento, boolean admin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.admin = admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
