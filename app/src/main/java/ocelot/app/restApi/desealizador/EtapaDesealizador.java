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

import ocelot.app.R;
import ocelot.app.modelo.Etapa;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.EtapaResponse;

/**
 * Created by Jose on 5/12/2017.
 */

public class EtapaDesealizador implements JsonDeserializer<EtapaResponse> {
    @Override
    public EtapaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        EtapaResponse etapaResponse = gson.fromJson(json, EtapaResponse.class);

        Log.d("ETAPA", etapaResponse.toString());
        JsonArray data = json.getAsJsonObject().getAsJsonArray(JsonKeys.etapa);
        etapaResponse.setEtapas(etapaDesealizador(data));

        return etapaResponse;
    }

    private ArrayList<Etapa> etapaDesealizador(JsonArray data) {
        ArrayList<Etapa> etapas = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JsonObject maestro = data.get(i).getAsJsonObject();

            int id = maestro.get(JsonKeys.etapa_id).getAsInt();
            String titulo = maestro.get(JsonKeys.etapa_titulo).getAsString();
            int etapa = maestro.get(JsonKeys.etapa_etapa).getAsInt();
            int icono = getIcon( maestro.get(JsonKeys.etapa_etapa).getAsInt() );
            int estatus = maestro.get(JsonKeys.etapa_estatus).getAsInt();

            Etapa e = new Etapa(id, titulo, icono, etapa, estatus);
            etapas.add(e);
        }
        return etapas;
    }

    private int getIcon(int etapa) {
        switch (etapa) {
            case 1:
                return R.drawable.ic_remove_red_eye;
            case 2:
                return R.drawable.ic_edit;
            case 3:
                return R.drawable.ic_attach_money;
            case 4:
                return R.drawable.ic_today;
            case 5:
                return R.drawable.ic_star_negro;
            case 6:
                return R.drawable.ic_error_outline;
            default:
                return R.drawable.ic_today;
        }
    }
}
