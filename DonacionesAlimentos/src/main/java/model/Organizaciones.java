package model;

import java.util.Objects;

public class Organizaciones {
    private int idOrganizaciones;
    private String nombreOrganizacion;
    private String nombreResponsable;
    private String apellidoPaternoResponsable;
    private String apellidoMaternoResponsable;
    private int idDireccion;
    private String telefono;
    private String correo;

    public Organizaciones() {}

    public Organizaciones(int idOrganizaciones, String nombreOrganizacion, String nombreResponsable,
                          String apellidoPaternoResponsable, String apellidoMaternoResponsable,
                          int idDireccion, String telefono, String correo) {
        this.idOrganizaciones = idOrganizaciones;
        this.nombreOrganizacion = nombreOrganizacion;
        this.nombreResponsable = nombreResponsable;
        this.apellidoPaternoResponsable = apellidoPaternoResponsable;
        this.apellidoMaternoResponsable = apellidoMaternoResponsable;
        this.idDireccion = idDireccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdOrganizaciones() { return idOrganizaciones; }
    public void setIdOrganizaciones(int idOrganizaciones) { this.idOrganizaciones = idOrganizaciones; }

    public String getNombreOrganizacion() { return nombreOrganizacion; }
    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public String getNombreResponsable() { return nombreResponsable; }
    public void setNombreResponsable(String nombreResponsable) { this.nombreResponsable = nombreResponsable; }

    public String getApellidoPaternoResponsable() { return apellidoPaternoResponsable; }
    public void setApellidoPaternoResponsable(String ap) { this.apellidoPaternoResponsable = ap; }

    public String getApellidoMaternoResponsable() { return apellidoMaternoResponsable; }
    public void setApellidoMaternoResponsable(String am) { this.apellidoMaternoResponsable = am; }

    public int getIdDireccion() { return idDireccion; }
    public void setIdDireccion(int idDireccion) { this.idDireccion = idDireccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public int hashCode() {
        return Objects.hash(idOrganizaciones, nombreOrganizacion, idDireccion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Organizaciones other = (Organizaciones) obj;
        return idOrganizaciones == other.idOrganizaciones && idDireccion == other.idDireccion
                && Objects.equals(nombreOrganizacion, other.nombreOrganizacion);
    }

    @Override
    public String toString() {
        return "Organizaciones{idOrganizaciones=" + idOrganizaciones + ", nombreOrganizacion=" + nombreOrganizacion + '}';
    }
}
