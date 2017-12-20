package ocelot.app.modelo;

/**
 * Created by Jose on 25/11/2017.
 */

public class BuzonRequest {

    private String asunto;
    private String descripcion;

    public BuzonRequest(String asunto, String descripcion) {
        this.asunto = asunto;
        this.descripcion = descripcion;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
