package ocelot.app.restApi.desealizador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import ocelot.app.modelo.OrdenVisita;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.OrdenVisitaResponse;
import ocelot.app.utils.Convertidor;

/**
 * Created by Glory on 28/11/2017.
 */

public class OrdenVisitaDesealizador implements JsonDeserializer<OrdenVisitaResponse> {

    @Override
    public OrdenVisitaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        OrdenVisitaResponse ordenVisitaResponse = gson.fromJson(json, OrdenVisitaResponse.class);
        JsonObject data  = json.getAsJsonObject().getAsJsonObject(JsonKeys.visita);

        Log.d("VISITA", data.toString());
        Date fecha = Convertidor.timeStampToDate(data.get(JsonKeys.visita_fecha).getAsString());
        int estatus = data.get(JsonKeys.estatus).getAsInt();

        JsonObject tecnico = data.getAsJsonObject().getAsJsonObject(JsonKeys.visita_tecnico);
        String nombretecnico = tecnico.get(JsonKeys.visita_tecnico_nombre).getAsString();
        String telefono = tecnico.get(JsonKeys.visita_tecnico_telefono).getAsString();

        JsonObject solicitud = data.getAsJsonObject().getAsJsonObject(JsonKeys.visita_lugar);
        JsonObject inmueble = solicitud.getAsJsonObject().getAsJsonObject(JsonKeys.visita_lugar_inmueble);
        String direccion = inmueble.get(JsonKeys.visita_lugar_inmueble_direccion).getAsString();

        JsonObject tipoInmuebleData = inmueble.getAsJsonObject().getAsJsonObject(JsonKeys.visita_lugar_inmueble_tipo_inmueble);
        String tipoInmueble = tipoInmuebleData.get(JsonKeys.visita_lugar_inmueble_tipo_inmueble_descripcion).getAsString();

        JsonObject sector = inmueble.getAsJsonObject().getAsJsonObject(JsonKeys.visita_lugar_inmueble_sector);
        String sector_descripcion = sector.get(JsonKeys.visita_lugar_inmueble_sector_descripcion).getAsString();

        JsonObject parroquia = sector.getAsJsonObject().getAsJsonObject(JsonKeys.visita_lugar_inmueble_sector_parroquia);
        String parroquia_descripcion = parroquia.get(JsonKeys.visita_lugar_inmueble_sector_parroquia_descripcion).getAsString();

        OrdenVisita ordenVisita = new OrdenVisita();
        ordenVisita.setNombretecnico(nombretecnico);
        ordenVisita.setTelefono(telefono);
        ordenVisita.setLugarvisita(parroquia_descripcion + ", " + sector_descripcion + ", " + direccion + " (" + tipoInmueble + ")");
        ordenVisita.setFecha(fecha);
        ordenVisitaResponse.setCliente(ordenVisita);
        ordenVisita.setEstatus(estatus);

       return ordenVisitaResponse;
    }

}
