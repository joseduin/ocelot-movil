package ocelot.app.restApi.desealizador;

import android.nfc.Tag;
import android.util.Log;
import android.util.LogPrinter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ocelot.app.Detalle_Diagnostico;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.Detalle_DiagnosticoResponse;

/**
 * Created by Glory on 06/12/2017.
 */

public class Detalle_DiagnosticoDesealizador implements JsonDeserializer<Detalle_DiagnosticoResponse>{
    @Override
    public Detalle_DiagnosticoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Detalle_DiagnosticoResponse detalle_diagnosticoResponse = gson.fromJson(json, Detalle_DiagnosticoResponse.class);
        JsonObject data = json.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico);

        //Log.d(tag: "DIAGNOSTICO", data.toString());
        int estatus = data.get(JsonKeys.estatus).getAsInt();

        JsonObject InmuebleD = data.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_inmueble);
        String inmueble = InmuebleD.get(JsonKeys.diagnostico_inmueble_direccion).getAsString();

        JsonObject TipoInmuebleD = InmuebleD.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_inmueble_tipo_inmueble);
        String tipoinmueble_descripcion = TipoInmuebleD.get(JsonKeys.diagnostico_inmueble_tipo_inmueble_descripcion).getAsString();

        JsonObject Sector = InmuebleD.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_inmueble_sector);
        String sector_descripcion = Sector.get(JsonKeys.diagnostico_inmueble_sector_descripcion).getAsString();

        JsonObject Parroquia = InmuebleD.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_inmueble_parroquia);
        String parroquia_descripion = Parroquia.get(JsonKeys.diagnostico_inmueble_parroquia_descripcion).getAsString();

        JsonObject cliente = data.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_cliente);
        String nombrecliente = cliente.get(JsonKeys.diagnostico_cliente_nombre).getAsString();
        String direccion = cliente.get(JsonKeys.diagnostico_cliente_direccion).getAsString();
        Integer telefono = cliente.get(JsonKeys.diagnostico_cliente_telefono).getAsInt();

        JsonObject DiagnosticoCondicion = data.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_condicion);
        JsonObject DiagnosticoCaracteristica = DiagnosticoCondicion.getAsJsonObject(JsonKeys.diagnostico_condicion_caracteristica);
        String caracteristica_descripcion = DiagnosticoCaracteristica.get(JsonKeys.diagnostico_condicion_caracteristica_descripcion).getAsString();
        String caracterisitica_condicion = DiagnosticoCondicion.get(JsonKeys.diagnostico_condicion_caracteristica_condicion).getAsString();

        JsonObject DiagnosticoCondicion_Detalle = DiagnosticoCondicion.getAsJsonObject().getAsJsonObject(JsonKeys.diagnostico_condicion_detalle);
        Integer area = DiagnosticoCondicion_Detalle.get(JsonKeys.diagnostico_condicion_detalle_area).getAsInt();

        Detalle_Diagnostico detalle_diagnostico = new Detalle_Diagnostico();











        return detalle_diagnosticoResponse;
    }
}
