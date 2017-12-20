package ocelot.app.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ocelot.app.R;

/**
 * Created by Jose on 23/10/2017.
 */

public class Catalogo {

    private int id;
    private String titulo;
    private String imagenServicio;
    private String descripcion;
    private double costo;

    private int promocionId;
    private Date fecha_inicio;
    private Date fecha_caducidad;
    private double porcentaje;

    public Catalogo(int id, String titulo, String imagenServicio, String descripcion, double costo, Date fecha_inicio, Date fecha_caducidad, double porcentaje) {
        this.id = id;
        this.titulo = titulo;
        this.imagenServicio = imagenServicio;
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_caducidad = fecha_caducidad;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagenServicio() {
        return imagenServicio;
    }

    public void setImagenServicio(String imagenServicio) {
        this.imagenServicio = imagenServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(Date fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(int promocionId) {
        this.promocionId = promocionId;
    }
}
