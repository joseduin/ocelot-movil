package ocelot.app.restApi.modelo;

import ocelot.app.modelo.Mensaje;

/**
 * Created by Jose on 25/11/2017.
 */

public class MensajeResponse {

    private Mensaje mensaje;

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
}
