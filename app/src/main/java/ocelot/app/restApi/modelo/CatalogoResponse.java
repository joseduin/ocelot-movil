package ocelot.app.restApi.modelo;

import java.util.ArrayList;

import ocelot.app.modelo.Catalogo;

/**
 * Created by Jose on 21/11/2017.
 */

public class CatalogoResponse {

    private ArrayList<Catalogo> catalogos;
    private Catalogo catalogo;

    public ArrayList<Catalogo> getCatalogos() {
        return catalogos;
    }

    public void setCatalogos(ArrayList<Catalogo> catalogos) {
        this.catalogos = catalogos;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }
}
