package ocelot.app.interfaz;

import java.util.ArrayList;
import java.util.HashMap;

import ocelot.app.modelo.Maestro;

/**
 * Created by Jose on 24/11/2017.
 */

public interface ICategoria {

    public void cargarBotones(ArrayList<Maestro> categorias);
    public void cargarMapBotones(HashMap<String, Maestro> mapCategoria);

}
