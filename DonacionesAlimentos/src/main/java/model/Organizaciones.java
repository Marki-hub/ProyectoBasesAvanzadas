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
public class Organizaciones {
    private int idOrganizaciones;
    private String nombreOrganizacion;
    private String nombreResponsable;
    private String apellidoPaternoResponsable;
    private String apellidoMaternoResponsable;
    private int idDireccion; //FK a Direcciones
    private String telefono;
    private String correo;

    public Organizaciones() {
    }

    public Organizaciones(int idOrganizaciones, String nombreOrganizacion, String nombreResponsable, String apellidoPaternoResponsable, String apellidoMaternoResponsable, int idDireccion, String telefono, String correo) {
        this.idOrganizaciones = idOrganizaciones;
        this.nombreOrganizacion = nombreOrganizacion;
        this.nombreResponsable = nombreResponsable;
        this.apellidoPaternoResponsable = apellidoPaternoResponsable;
        this.apellidoMaternoResponsable = apellidoMaternoResponsable;
        this.idDireccion = idDireccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdOrganizaciones() {
        return idOrganizaciones;
    }

    public void setIdOrganizaciones(int idOrganizaciones) {
        this.idOrganizaciones = idOrganizaciones;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getApellidoPaternoResponsable() {
        return apellidoPaternoResponsable;
    }

    public void setApellidoPaternoResponsable(String apellidoPaternoResponsable) {
        this.apellidoPaternoResponsable = apellidoPaternoResponsable;
    }

    public String getApellidoMaternoResponsable() {
        return apellidoMaternoResponsable;
    }

    public void setApellidoMaternoResponsable(String apellidoMaternoResponsable) {
        this.apellidoMaternoResponsable = apellidoMaternoResponsable;
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
        int hash = 3;
        hash = 29 * hash + this.idOrganizaciones;
        hash = 29 * hash + Objects.hashCode(this.nombreOrganizacion);
        hash = 29 * hash + Objects.hashCode(this.nombreResponsable);
        hash = 29 * hash + Objects.hashCode(this.apellidoPaternoResponsable);
        hash = 29 * hash + Objects.hashCode(this.apellidoMaternoResponsable);
        hash = 29 * hash + this.idDireccion;
        hash = 29 * hash + Objects.hashCode(this.telefono);
        hash = 29 * hash + Objects.hashCode(this.correo);
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
        final Organizaciones other = (Organizaciones) obj;
        if (this.idOrganizaciones != other.idOrganizaciones) {
            return false;
        }
        if (this.idDireccion != other.idDireccion) {
            return false;
        }
        if (!Objects.equals(this.nombreOrganizacion, other.nombreOrganizacion)) {
            return false;
        }
        if (!Objects.equals(this.nombreResponsable, other.nombreResponsable)) {
            return false;
        }
        if (!Objects.equals(this.apellidoPaternoResponsable, other.apellidoPaternoResponsable)) {
            return false;
        }
        if (!Objects.equals(this.apellidoMaternoResponsable, other.apellidoMaternoResponsable)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        return Objects.equals(this.correo, other.correo);
    }

    @Override
    public String toString() {
        return "Organizaciones{" + "idOrganizaciones=" + idOrganizaciones + ", nombreOrganizacion=" + nombreOrganizacion + ", nombreResponsable=" + nombreResponsable + ", apellidoPaternoResponsable=" + apellidoPaternoResponsable + ", apellidoMaternoResponsable=" + apellidoMaternoResponsable + ", idDireccion=" + idDireccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
    
    
}
