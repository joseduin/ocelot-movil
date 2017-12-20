package ocelot.app.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose on 24/11/2017.
 */

public class Maestro {

    private int id;
    private String descripcion;
    private ArrayList<Maestro> maestros;

    public Maestro(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Maestro() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(ArrayList<Maestro> maestros) {
        this.maestros = maestros;
    }
}
