package ocelot.app.restApi.modelo;

import java.util.ArrayList;

import ocelot.app.modelo.Solicitud;

/**
 * Created by Jose on 4/12/2017.
 */

public class SolicitudResponse {

    private ArrayList<Solicitud> solicitud;

    public ArrayList<Solicitud> getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(ArrayList<Solicitud> solicitud) {
        this.solicitud = solicitud;
    }

}
