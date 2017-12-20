package ocelot.app.modelo;

import java.util.Date;

/**
 * Created by Glory on 03/12/2017.
 */

public class Detalle_Diagnostico{

    private String visitaNroo;
    private String datosInmueble;
    private String datosCliente;
    private String caracteristica;
    private String condicion;
    private Integer metros;
    private int estatus;



    private Detalle_Diagnostico(String visitaNroo, String datosInmueble, String datosCliente, String caracteristica, String condicion, Integer metros, int estatus){
        this.visitaNroo = visitaNroo;
        this.datosInmueble = datosInmueble;
        this.datosCliente = datosCliente;
        this.caracteristica = caracteristica;
        this.condicion = condicion;
        this.metros = metros;
        this.estatus = estatus;
    }
      public Detalle_Diagnostico(){

      }

    public String getVisitaNroo() {
        return visitaNroo;
    }

    public void setVisitaNroo(String visitaNroo) {
        this.visitaNroo = visitaNroo;
    }

    public String getDatosInmueble() {
        return datosInmueble;
    }

    public void setDatosInmueble(String datosInmueble ) {
        this.datosInmueble = datosInmueble;
    }

    public String getDatosCliente() {
        return datosCliente;
    }

    public void setDatosCliente(String datosCliente) {
        this.datosCliente= datosCliente;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Integer getMetros() {
        return metros;
    }

    public void setMetros(Integer metros) {
        this.metros = metros;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

}
