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
public class Entregas {
    
    private int idEntrega;
    private Date fechaEntrega;
    private String estado;
    private int idOrganizaciones; // fr key
    private int idAlimentos;      // fr key

    public Entregas() {
    }

    public Entregas(int idEntrega, Date fechaEntrega, String estado, int idOrganizaciones, int idAlimentos) {
        this.idEntrega = idEntrega;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.idOrganizaciones = idOrganizaciones;
        this.idAlimentos = idAlimentos;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdOrganizaciones() {
        return idOrganizaciones;
    }

    public void setIdOrganizaciones(int idOrganizaciones) {
        this.idOrganizaciones = idOrganizaciones;
    }

    public int getIdAlimentos() {
        return idAlimentos;
    }

    public void setIdAlimentos(int idAlimentos) {
        this.idAlimentos = idAlimentos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.idEntrega;
        hash = 83 * hash + Objects.hashCode(this.fechaEntrega);
        hash = 83 * hash + Objects.hashCode(this.estado);
        hash = 83 * hash + this.idOrganizaciones;
        hash = 83 * hash + this.idAlimentos;
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
        final Entregas other = (Entregas) obj;
        if (this.idEntrega != other.idEntrega) {
            return false;
        }
        if (this.idOrganizaciones != other.idOrganizaciones) {
            return false;
        }
        if (this.idAlimentos != other.idAlimentos) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        return Objects.equals(this.fechaEntrega, other.fechaEntrega);
    }

    @Override
    public String toString() {
        return "Entregas{" + "idEntrega=" + idEntrega + ", fechaEntrega=" + fechaEntrega + ", estado=" + estado + ", idOrganizaciones=" + idOrganizaciones + ", idAlimentos=" + idAlimentos + '}';
    }
    
    
    
    
    
    
    
}
