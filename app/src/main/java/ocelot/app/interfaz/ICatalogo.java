package ocelot.app.interfaz;

import java.util.ArrayList;

import ocelot.app.adaptador.CatalogoAdaptador;
import ocelot.app.modelo.Catalogo;

/**
 * Created by Jose on 21/11/2017.
 */

public interface ICatalogo {

    public void generarLayoutVertical();
    public CatalogoAdaptador crearAdaptador(ArrayList<Catalogo> catalogo);
    public void inicializarAdaptadorRV(CatalogoAdaptador catalogoAdaptador);

}
