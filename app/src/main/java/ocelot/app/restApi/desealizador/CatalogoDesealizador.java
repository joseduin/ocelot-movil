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

import ocelot.app.modelo.Catalogo;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.CatalogoResponse;

/**
 * Created by Jose on 21/11/2017.
 */

public class CatalogoDesealizador implements JsonDeserializer<CatalogoResponse> {

    @Override
    public CatalogoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        CatalogoResponse catalogoResponse = gson.fromJson(json, CatalogoResponse.class);
        JsonElement element = json.getAsJsonObject().get(JsonKeys.servicio);

        if (element.isJsonArray()) {
            JsonArray catalogoData = json.getAsJsonObject().getAsJsonArray(JsonKeys.servicio);
            catalogoResponse.setCatalogos(catalogosDeserialize(catalogoData));
        } else {
            JsonObject catalogoData = json.getAsJsonObject().getAsJsonObject(JsonKeys.servicio);
            catalogoResponse.setCatalogo(catalogoDeserialize(catalogoData));
        }

        return catalogoResponse;
    }

    private ArrayList<Catalogo> catalogosDeserialize(JsonArray catalogoData) {
        ArrayList<Catalogo> catalogos = new ArrayList<>();
        for (int i = 0; i < catalogoData.size(); i++) {
            JsonObject catalogo = catalogoData.get(i).getAsJsonObject();

            catalogos.add(catalogoDeserialize(catalogo));
        }
        return catalogos;
    }

    private Catalogo catalogoDeserialize(JsonObject catalogo) {
        int id = catalogo.get(JsonKeys.servicio_id).getAsInt();
        String titulo = catalogo.get(JsonKeys.servicio_titulo).getAsString();
        String imagen = catalogo.get(JsonKeys.servicio_imagen).getAsString();
        String descripcion = catalogo.get(JsonKeys.servicio_descripcion).getAsString();
        double costo = catalogo.get(JsonKeys.servicio_costo).getAsDouble();

        return new Catalogo(id, titulo, imagen, descripcion, costo, null, null, 0.00);
    }

}
