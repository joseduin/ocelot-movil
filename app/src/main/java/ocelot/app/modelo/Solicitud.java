package ocelot.app.modelo;

import java.util.ArrayList;
import java.util.List;

import ocelot.app.R;

/**
 * Created by Jose on 23/10/2017.
 */

public class Solicitud {

    private int id;
    private String etapa;
    private String foto;
    private String descripcion;

    public Solicitud(int id, String etapa, String foto, String descripcion) {
        this.id = id;
        this.etapa = etapa;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*


    public static List<Solicitud> listadoServicios() {
        List<Solicitud> lista = new ArrayList<>();

        lista.add(new Solicitud(1, "Orden de Visita", R.drawable.banner2, "Jardineria", 1, ordenVisita()));
        lista.add(new Solicitud(2, "Culminado", R.drawable.banner2, "Plomeria", 4, culminacion()));
        lista.add(new Solicitud(3, "Reclamo", R.drawable.banner2, "Pintura", 6, reclamo()));
        return lista;
    }

    private static List<Etapa> ordenVisita() {
        List<Etapa> etapas = new ArrayList<>();

        etapas.add(new Etapa(1, "Orden de Visita", R.drawable.ic_remove_red_eye));
        return etapas;
    }

    private static List<Etapa> culminacion() {
        List<Etapa> etapas = ordenVisita();

        etapas.add(new Etapa(2, "Diagnóstico", R.drawable.ic_view_list));
        etapas.add(new Etapa(3, "Presupuesto", R.drawable.ic_attach_money));
        etapas.add(new Etapa(4, "Ejecución", R.drawable.ic_format_list_numbered));
        etapas.add(new Etapa(5, "Valoracion", R.drawable.ic_star_negro));
        return etapas;
    }

    private static List<Etapa> reclamo() {
        List<Etapa> etapas = culminacion();

        etapas.add(new Etapa(6, "Reclamos", R.drawable.ic_error_outline));
        return etapas;
    }
    */
}
