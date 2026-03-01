/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author marki
 */
public class Donadores {
    private int idDonador;
    private String nombre;
    private String tipo;
    private int idDireccion; //FK a Direcciones
    private String telefono;
    private String correo;

    public Donadores() {
    }

    public Donadores(int idDonador, String nombre, String tipo, int idDireccion, String telefono, String correo) {
        this.idDonador = idDonador;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idDireccion = idDireccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdDonador() {
        return idDonador;
    }

    public void setIdDonador(int idDonador) {
        this.idDonador = idDonador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.idDonador;
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.tipo);
        hash = 83 * hash + this.idDireccion;
        hash = 83 * hash + Objects.hashCode(this.telefono);
        hash = 83 * hash + Objects.hashCode(this.correo);
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
        final Donadores other = (Donadores) obj;
        if (this.idDonador != other.idDonador) {
            return false;
        }
        if (this.idDireccion != other.idDireccion) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        return Objects.equals(this.correo, other.correo);
    }

    @Override
    public String toString() {
        return "Donadores{" + "idDonador=" + idDonador + ", nombre=" + nombre + ", tipo=" + tipo + ", idDireccion=" + idDireccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
    
    
}
