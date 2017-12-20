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

import ocelot.app.modelo.Solicitud;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.SolicitudResponse;

/**
 * Created by Jose on 4/12/2017.
 */

public class SolicitudDesealizador implements JsonDeserializer<SolicitudResponse> {

    @Override
    public SolicitudResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        SolicitudResponse solicitudResponse = gson.fromJson(json, SolicitudResponse.class);
        JsonArray data = json.getAsJsonObject().getAsJsonArray(JsonKeys.solicitud);
        Log.d("Solicitud", data.toString());

        solicitudResponse.setSolicitud( solicitudDesealizador(data) );
        return solicitudResponse;
    }

    private ArrayList<Solicitud> solicitudDesealizador(JsonArray data) {
        ArrayList<Solicitud> solicituds = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject datos = data.get(i).getAsJsonObject();

            int id = datos.get(JsonKeys.solicitud_id).getAsInt();
            String estatus = datos.get(JsonKeys.solicitud_estatus).getAsString();

            JsonObject servici = null;
            JsonElement element = datos.getAsJsonObject().get(JsonKeys.solicitud_servicios);
            if (element.isJsonArray()) {
                JsonArray servicios = datos.getAsJsonObject().getAsJsonArray(JsonKeys.solicitud_servicios);
                servici = servicios.get(0).getAsJsonObject();
            } else {
                servici = datos.getAsJsonObject().getAsJsonObject(JsonKeys.solicitud_servicios);
            }

            JsonObject servicio = servici.getAsJsonObject().getAsJsonObject(JsonKeys.solicitud_servicios_servicio);
            String titulo = servicio.get(JsonKeys.solicitud_servicios_servicio_titulo).getAsString();
            String foto = servicio.get(JsonKeys.solicitud_servicios_servicio_imagen).getAsString();

            Solicitud s = new Solicitud(id, estatus, foto, titulo);
            solicituds.add(s);
        }
        return solicituds;
    }

}
