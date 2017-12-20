package ocelot.app.presentador;

import java.util.HashMap;

import ocelot.app.modelo.Maestro;

/**
 * Created by Jose on 24/11/2017.
 */

public interface ICategoriaPresentador {

    public void obtenerInformacionCategoria();
    public void prepararBotones();
    public void mostrarBotones(HashMap<String, Maestro> tiposServicios);

}
