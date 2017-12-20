package ocelot.app.utils;

import android.content.Context;
import android.content.Intent;

import ocelot.app.R;

/**
 * Created by Jose on 13/11/2017.
 */

public final class IrA {

    public static void vista(Context activityActual, Class<?> activityNuevo) {
        Intent i = new Intent(activityActual, activityNuevo);
        activityActual.startActivity(i);
    }

    public static void vista(Context activityActual, Class<?> activityNuevo, String id) {
        Intent i = new Intent(activityActual, activityNuevo);
        i.putExtra(activityActual.getResources().getString(R.string.KEY_ID), id);
        activityActual.startActivity(i);
    }
}
