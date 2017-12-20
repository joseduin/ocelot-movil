package ocelot.app.interfaz;

import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

import ocelot.app.modelo.Maestro;

/**
 * Created by Jose on 24/11/2017.
 */

public interface IMaestro {

    public void cargarCombo(String[] maestros);
    public void cargarMap(HashMap<Integer, String> map);

}
