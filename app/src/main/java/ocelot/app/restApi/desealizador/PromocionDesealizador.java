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
import java.util.Date;

import ocelot.app.modelo.Catalogo;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.CatalogoResponse;
import ocelot.app.utils.Convertidor;

/**
 * Created by Jose on 21/11/2017.
 */

public class PromocionDesealizador implements JsonDeserializer<CatalogoResponse> {

    @Override
    public CatalogoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Log.d("GSON", "INIT");
        CatalogoResponse catalogoResponse = gson.fromJson(json, CatalogoResponse.class);
        Log.d("GSON", json.toString());
        JsonArray catalogoData = json.getAsJsonObject().getAsJsonArray(JsonKeys.promocion);
        Log.d("GSON", "END");
        catalogoResponse.setCatalogos(catalogoDeserialize(catalogoData));

        return catalogoResponse;
    }

    private ArrayList<Catalogo> catalogoDeserialize(JsonArray catalogoData) {
        ArrayList<Catalogo> catalogos = new ArrayList<>();
        for (int i = 0; i < catalogoData.size(); i++) {
            JsonObject catalogo = catalogoData.get(i).getAsJsonObject();

            Log.d("PROMOCION", catalogo.toString());
            int promocion_id = catalogo.get(JsonKeys.promocion_id).getAsInt();
            Log.d("PROMOCION", "1");
            String titulo = catalogo.get(JsonKeys.promocion_titulo).getAsString();
            Log.d("PROMOCION", "2");
            String imagen = catalogo.get(JsonKeys.promocion_imagen).getAsString();
            Log.d("PROMOCION", "3");
            String descripcion = catalogo.get(JsonKeys.promocion_descripcion).getAsString();
            Log.d("PROMOCION", "4");
            Date fecha_i = Convertidor.timeStampToDate( catalogo.get(JsonKeys.promocion_inicio).getAsString() );
            Log.d("PROMOCION", "5");
            Date fecha_c = Convertidor.timeStampToDate( catalogo.get(JsonKeys.promocion_caducidad).getAsString() );
            Log.d("PROMOCION", "6");
            double descuento = catalogo.get(JsonKeys.promocion_descuento).getAsDouble();

            Log.d("PROMOCION", "7");
            JsonArray servicios = catalogo.getAsJsonArray(JsonKeys.promocion_servicio);
            for (int j = 0; j < servicios.size(); j++) {
                JsonObject servicio = servicios.get(i).getAsJsonObject();

                Log.d("PROMOCION", "8");
                int servicio_id = servicio.get(JsonKeys.promocion_servicio_id).getAsInt();
                Log.d("PROMOCION", "9");
                double costo = servicio.get(JsonKeys.promocion_costo).getAsDouble();

                Log.d("PROMOCION", "10");
                Catalogo c = new Catalogo(servicio_id, titulo, imagen, descripcion, costo, fecha_i, fecha_c, descuento);
                Log.d("PROMOCION", "11");
                c.setPromocionId(promocion_id);
                catalogos.add(c);
            }

        }
        return catalogos;
    }

}
