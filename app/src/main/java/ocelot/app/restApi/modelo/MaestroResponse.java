package ocelot.app.restApi.modelo;

import java.util.List;

import ocelot.app.modelo.Maestro;

/**
 * Created by Jose on 24/11/2017.
 */

public class MaestroResponse {

    private List<Maestro> maestros;

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }
}
