package ocelot.app.utils;

import android.widget.EditText;

/**
 * Created by Jose on 19/11/2017.
 */

public class Validacion {

    public static boolean campoVacio(EditText e) {
        return e.getText() == null || e.getText().length() == 0;
    }

}
