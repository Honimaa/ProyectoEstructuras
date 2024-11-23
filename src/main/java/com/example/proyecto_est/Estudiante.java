package com.example.proyecto_est;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String usuario;
    private String clave;
    private List<Tarea> tareas;

    public Estudiante(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
        this.tareas = new ArrayList<>();
    }

    public Estudiante() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        tareas = tareas;
    }


}
