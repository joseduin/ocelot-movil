package ocelot.app.modelo;

/**
 * Created by Jose on 23/10/2017.
 */

public class Etapa {

    private int id;
    private  String titulo;
    private int icono;
    private int etapa;
    private int estatus;

    public Etapa(int id, String titulo, int icono, int etapa, int estatus) {
        this.id = id;
        this.titulo = titulo;
        this.icono = icono;
        this.etapa = etapa;
        this.estatus = estatus;
    }

    public Etapa() {}

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

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
