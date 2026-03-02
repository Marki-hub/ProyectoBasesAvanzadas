package model;

import java.util.Objects;

public class Donadores {
    private int idDonador;
    private String nombre;
    private String tipo;
    private int idDireccion;
    private String telefono;
    private String correo;

    public Donadores() {}

    public Donadores(int idDonador, String nombre, String tipo, int idDireccion, String telefono, String correo) {
        this.idDonador = idDonador;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idDireccion = idDireccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdDonador() { return idDonador; }
    public void setIdDonador(int idDonador) { this.idDonador = idDonador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getIdDireccion() { return idDireccion; }
    public void setIdDireccion(int idDireccion) { this.idDireccion = idDireccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public int hashCode() {
        return Objects.hash(idDonador, nombre, tipo, idDireccion, telefono, correo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Donadores other = (Donadores) obj;
        return idDonador == other.idDonador && idDireccion == other.idDireccion
                && Objects.equals(nombre, other.nombre) && Objects.equals(tipo, other.tipo)
                && Objects.equals(telefono, other.telefono) && Objects.equals(correo, other.correo);
    }

    @Override
    public String toString() {
        return "Donadores{idDonador=" + idDonador + ", nombre=" + nombre + ", tipo=" + tipo
                + ", idDireccion=" + idDireccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }
}
