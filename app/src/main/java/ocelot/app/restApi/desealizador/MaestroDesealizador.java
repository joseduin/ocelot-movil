package ocelot.app.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EventObject;

import ocelot.app.modelo.Maestro;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.MaestroResponse;

/**
 * Created by Jose on 24/11/2017.
 */

public class MaestroDesealizador implements JsonDeserializer<MaestroResponse> {

    private String tipo_maestro;

    public MaestroDesealizador(String tipo_maestro) {
        this.tipo_maestro = tipo_maestro;
    }

    @Override
    public MaestroResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MaestroResponse maestroResponse = gson.fromJson(json, MaestroResponse.class);
        JsonArray maestroData = json.getAsJsonObject().getAsJsonArray(tipo_maestro);

        maestroResponse.setMaestros(maestroDeserialize(maestroData));
        return  maestroResponse;
    }

    private ArrayList<Maestro> maestroDeserialize(JsonArray data) {
        ArrayList<Maestro> maestros = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject maestro = data.get(i).getAsJsonObject();

            Log.d("Maestro", maestro.toString());
            int id = maestro.get(JsonKeys.maestro_id).getAsInt();
            String descripcion = maestro.get(JsonKeys.maestro_descripcion).getAsString();

            Maestro m = new Maestro(id, descripcion);
            maestros.add(m);
        }
        return maestros;
    }
}
