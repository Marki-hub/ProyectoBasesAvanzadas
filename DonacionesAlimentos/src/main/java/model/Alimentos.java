/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author MoriTejo
 */
public class Alimentos {
    
    private int idAlimentos;
    private String nombre;
    private String categoria;
    private Date fechaCaducidad;
    private int cantidad;
    private int idDonador; // FR key

    public Alimentos() {
    }

    public Alimentos(int idAlimentos, String nombre, String categoria, Date fechaCaducidad, int cantidad, int idDonador) {
        this.idAlimentos = idAlimentos;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaCaducidad = fechaCaducidad;
        this.cantidad = cantidad;
        this.idDonador = idDonador;
    }

    public int getIdAlimentos() {
        return idAlimentos;
    }

    public void setIdAlimentos(int idAlimentos) {
        this.idAlimentos = idAlimentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdDonador() {
        return idDonador;
    }

    public void setIdDonador(int idDonador) {
        this.idDonador = idDonador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idAlimentos;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.categoria);
        hash = 79 * hash + Objects.hashCode(this.fechaCaducidad);
        hash = 79 * hash + this.cantidad;
        hash = 79 * hash + this.idDonador;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alimentos other = (Alimentos) obj;
        if (this.idAlimentos != other.idAlimentos) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (this.idDonador != other.idDonador) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return Objects.equals(this.fechaCaducidad, other.fechaCaducidad);
    }

    @Override
    public String toString() {
        return "Alimentos{" + "idAlimentos=" + idAlimentos + ", nombre=" + nombre + ", categoria=" + categoria + ", fechaCaducidad=" + fechaCaducidad + ", cantidad=" + cantidad + ", idDonador=" + idDonador + '}';
    }
    
    
    
    
}
