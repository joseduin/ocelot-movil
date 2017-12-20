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

import ocelot.app.modelo.Maestro;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.MaestroResponse;

/**
 * Created by Jose on 27/11/2017.
 */

public class CategoriaDesealizador implements JsonDeserializer<MaestroResponse> {

    @Override
    public MaestroResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MaestroResponse maestroResponse = gson.fromJson(json, MaestroResponse.class);
        JsonArray maestroData = json.getAsJsonObject().getAsJsonArray(JsonKeys.categoria);

        maestroResponse.setMaestros(maestroDeserialize(maestroData, true));
        return  maestroResponse;
    }

    private ArrayList<Maestro> maestroDeserialize(JsonArray data, boolean child) {
        ArrayList<Maestro> maestros = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject maestro = data.get(i).getAsJsonObject();

            Log.d("CATEGORIA", maestro.toString());
            if (maestro.get(JsonKeys.estatus).getAsInt() != 0)
                continue;

            int id = maestro.get(JsonKeys.maestro_id).getAsInt();
            String descripcion = maestro.get(JsonKeys.maestro_descripcion).getAsString();
            Maestro m = new Maestro(id, descripcion);

            if (child) {
                JsonArray tipoServiciosData = maestro.getAsJsonObject().getAsJsonArray(JsonKeys.categoria_tipo_servicios);
                ArrayList<Maestro> tipoServicios = maestroDeserialize(tipoServiciosData, false);
                m.setMaestros(tipoServicios);
            }
            maestros.add(m);
        }
        return maestros;
    }
}
