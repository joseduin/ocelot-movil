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

import ocelot.app.modelo.Cliente;
import ocelot.app.restApi.JsonKeys;
import ocelot.app.restApi.modelo.ClienteResponse;
import ocelot.app.utils.Convertidor;

/**
 * Created by Jose on 19/11/2017.
 */

public class ClienteDesealizador implements JsonDeserializer<ClienteResponse> {

    @Override
    public ClienteResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ClienteResponse clienteResponse = gson.fromJson(json, ClienteResponse.class);
        JsonObject clienteData  = json.getAsJsonObject().getAsJsonObject(JsonKeys.cliente);

        Log.d("PERIL", clienteData.toString());
        int id = clienteData.get(JsonKeys.cliente_id).getAsInt();
        String nombre = clienteData.get(JsonKeys.cliente_nombre).getAsString();
        int identificacion = clienteData.get(JsonKeys.cliente_identificacion).getAsInt();

        int estatus = clienteData.get(JsonKeys.cliente_estatus).getAsInt();
        //String password = clienteData.get(JsonKeys.cliente_password).getAsString();
        String email = clienteData.get(JsonKeys.cliente_email).getAsString();
        int tipoPersona = clienteData.get(JsonKeys.cliente_tipoPersona).getAsInt();
        int rol = clienteData.get(JsonKeys.cliente_rol).getAsInt();

        Cliente cliente = new Cliente(id, nombre, identificacion, estatus, "", email, tipoPersona, rol);
        clienteResponse.setCliente(cliente);

        return clienteResponse;
    }
}
