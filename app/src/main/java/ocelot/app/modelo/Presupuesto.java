package ocelot.app.modelo;

import java.util.Date;

/**
 * Created by Glory on 06/12/2017.
 */

public class Presupuesto {

    private String presupuestonro;
    private Date fecha;
    private String lugarvisita;
    private String telefono;
    private String servicio;
    private Double precio;
    private int area;
    private Double total;
    private Double subtotal;
    private Double totalpagar;
    private int estatus;

    public Presupuesto(String presupuestonro,Date fecha, String lugarvisita, String telefono, String servicio, Double precio, int area, Double total,Double subtotal, Double totalpagar, int estatus){
        this.presupuestonro = presupuestonro;
        this.fecha = fecha;
        this.lugarvisita = lugarvisita;
        this.telefono = telefono;
        this.servicio =servicio;
        this.precio = precio;
        this.area = area;
        this.total = total;
        this.subtotal = subtotal;
        this.totalpagar = totalpagar;
        this.estatus = estatus;
    }
    public Presupuesto(){

    }
    public String getPresupuestonro() {
        return presupuestonro;
    }

    public void setPresupuestonro(String presupuestonro) {
        this.presupuestonro = presupuestonro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {this.fecha = fecha;}

    public String getLugarvisita() {
        return lugarvisita;
    }

    public void setLugarvisita(String lugarvisita) { this.lugarvisita = lugarvisita;}

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) { this.servicio = servicio;}

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalpagar() {
        return totalpagar;
    }

    public void setTotalpagar(Double totalpagar) {
        this.totalpagar = totalpagar;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

}
