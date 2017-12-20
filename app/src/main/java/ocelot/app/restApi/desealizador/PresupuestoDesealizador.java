package ocelot.app.restApi.desealizador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import ocelot.app.modelo.OrdenVisita;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.PresupuestoResponse;
import ocelot.app.utils.Convertidor;

/**
 * Created by Glory on 06/12/2017.
 */

public class PresupuestoDesealizador implements JsonDeserializer<PresupuestoResponse> {
    @Override
    public PresupuestoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        PresupuestoResponse presupuestoResponse = gson.fromJson(json, PresupuestoResponse.class);
        JsonObject data  = json.getAsJsonObject().getAsJsonObject(JsonKeys.presupuesto);


        int estatus = data.get(JsonKeys.estatus).getAsInt();

        JsonObject presupuesto =  data.getAsJsonObject().getAsJsonObject(JsonKeys.presupuesto);
        Date fecha = Convertidor.timeStampToDate(data.get(JsonKeys.presupuesto_fecha).getAsLong());
        String servicio = presupuesto.get(JsonKeys.presupuesto_servicio).getAsString();
        Double montototal =presupuesto.get(JsonKeys.presupuesto_monto).getAsDouble();

        JsonObject detalle = data.getAsJsonObject().getAsJsonObject(JsonKeys.presupuesto_detalle);
        Double costo = detalle.get(JsonKeys.presupuesto_detalle_costo).getAsDouble();
        int area = detalle.get(JsonKeys.presupuesto_detalle_area).getAsInt();


        return presupuestoResponse;
    }
}
