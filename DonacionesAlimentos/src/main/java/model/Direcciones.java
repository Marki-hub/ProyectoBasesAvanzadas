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
public class Direcciones {
    
    private int idDirecciones;
    private String calle;
    private String numero;
    private String colonia;


    public Direcciones() {
    }

    public Direcciones(int idDirecciones, String calle, String numero, String colonia) {
        this.idDirecciones = idDirecciones;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
    }

    public int getIdDirecciones() {
        return idDirecciones;
    }

    public void setIdDirecciones(int idDirecciones) {
        this.idDirecciones = idDirecciones;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idDirecciones;
        hash = 53 * hash + Objects.hashCode(this.calle);
        hash = 53 * hash + Objects.hashCode(this.numero);
        hash = 53 * hash + Objects.hashCode(this.colonia);
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
        final Direcciones other = (Direcciones) obj;
        if (this.idDirecciones != other.idDirecciones) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.colonia, other.colonia);
    }

    @Override
    public String toString() {
        return "Direcciones{" + "idDirecciones=" + idDirecciones + ", calle=" + calle + ", numero=" + numero + ", colonia=" + colonia + '}';
    }
    
    
}
