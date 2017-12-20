package ocelot.app.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ocelot.app.modelo.Mensaje;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.MensajeResponse;

/**
 * Created by Jose on 25/11/2017.
 */

public class MensajeDesealizador implements JsonDeserializer<MensajeResponse> {

    @Override
    public MensajeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MensajeResponse mensajeResponse = gson.fromJson(json, MensajeResponse.class);

        Log.d("MENSAJE", json.toString());
        String mensaje  = json.getAsJsonObject().get(JsonKeys.mensaje).getAsString();
        mensajeResponse.setMensaje(new Mensaje(mensaje));
        return mensajeResponse;
    }

}
