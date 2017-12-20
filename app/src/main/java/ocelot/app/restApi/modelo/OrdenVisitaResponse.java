package ocelot.app.restApi.modelo;

import ocelot.app.modelo.OrdenVisita;

/**
 * Created by Glory on 28/11/2017.
 */

public class OrdenVisitaResponse {

    private OrdenVisita visita;

    public OrdenVisita getOdenVisita() {
        return visita;
    }

    public void setCliente(OrdenVisita visita) {
        this.visita = visita;
    }

}
