package com.example.proyecto_est;

import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;

public class Tarea {
    private String nombre;
    private String nombreMateria;
    private String descripcion;
    private int dificultad;
    private LocalDate entrega;
    private int horasDedicadas;
    private boolean terminado;
    private LocalDateTime fechaTerminacion;

    public Tarea(String nombre, String nombreMateria, String descripcion, int dificultad, LocalDate entrega, int horasDedicadas, boolean terminado,LocalDateTime fechaTerminacion) {
        this.nombre = nombre;
        this.nombreMateria = nombreMateria;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.entrega = entrega;
        this.horasDedicadas = horasDedicadas;
        this.terminado = terminado;
        this.fechaTerminacion = fechaTerminacion;
    }

    public Tarea() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

    public void setEntrega(LocalDate entrega) {
        this.entrega = entrega;
    }

    public int getHorasDedicadas() {
        return horasDedicadas;
    }

    public void setHorasDedicadas(int horasDedicadas) {
        this.horasDedicadas = horasDedicadas;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public LocalDateTime getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(LocalDateTime fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }
}




