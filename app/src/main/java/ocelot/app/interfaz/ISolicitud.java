package ocelot.app.interfaz;

import java.util.ArrayList;

import ocelot.app.adaptador.SolicitudAdaptador;
import ocelot.app.modelo.Solicitud;

/**
 * Created by Jose on 21/11/2017.
 */

public interface ISolicitud {

    public void generarLayoutVertical();
    public SolicitudAdaptador crearAdaptador(ArrayList<Solicitud> catalogo);
    public void inicializarAdaptadorRV(SolicitudAdaptador adaptador);

}
