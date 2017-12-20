package ocelot.app.restApi.modelo;

import java.util.ArrayList;

import ocelot.app.modelo.Etapa;

/**
 * Created by Jose on 5/12/2017.
 */

public class EtapaResponse {

    private ArrayList<Etapa> etapas;

    public ArrayList<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(ArrayList<Etapa> etapas) {
        this.etapas = etapas;
    }
}
