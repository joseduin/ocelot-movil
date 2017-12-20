package ocelot.app.modelo;

import java.util.Date;

/**
 * Created by Glory on 28/11/2017.
 */

public class OrdenVisita {

    private String nombretecnico;
    private String telefono;
    private String lugarvisita;
    private Date fecha;
    private int estatus;

    public OrdenVisita(String nombretecnico, String telefono, String lugarvisita, Date fecha, int estatus) {
        this.nombretecnico = nombretecnico;
        this.telefono = telefono;
        this.lugarvisita = lugarvisita;
        this.fecha = fecha;
        this.estatus = estatus;
    }

    public OrdenVisita() {

    }

    public String getNombretecnico() {
        return nombretecnico;
    }

    public void setNombretecnico(String nombretecnico) {
        this.nombretecnico = nombretecnico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLugarvisita() {
        return lugarvisita;
    }

    public void setLugarvisita(String lugarvisita) {
        this.lugarvisita = lugarvisita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
