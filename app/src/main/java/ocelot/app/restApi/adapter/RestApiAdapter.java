package ocelot.app.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ocelot.app.modelo.Mensaje;
import ocelot.app.modelo.Presupuesto;
import ocelot.app.restApi.ConstantesRestApi;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.desealizador.CatalogoDesealizador;
import ocelot.app.restApi.desealizador.CategoriaDesealizador;
import ocelot.app.restApi.desealizador.ClienteDesealizador;
import ocelot.app.restApi.desealizador.EtapaDesealizador;
import ocelot.app.restApi.desealizador.Detalle_DiagnosticoDesealizador;
import ocelot.app.restApi.desealizador.MaestroDesealizador;
import ocelot.app.restApi.desealizador.MensajeDesealizador;
import ocelot.app.restApi.desealizador.OrdenVisitaDesealizador;
import ocelot.app.restApi.desealizador.PresupuestoDesealizador;
import ocelot.app.restApi.desealizador.PromocionDesealizador;
import ocelot.app.restApi.desealizador.SolicitudDesealizador;
import ocelot.app.restApi.modelo.CatalogoResponse;
import ocelot.app.restApi.modelo.ClienteResponse;
import ocelot.app.restApi.modelo.EtapaResponse;
import ocelot.app.restApi.modelo.SolicitudResponse;
import ocelot.app.restApi.modelo.Detalle_DiagnosticoResponse;
import ocelot.app.restApi.modelo.MaestroResponse;
import ocelot.app.restApi.modelo.MensajeResponse;
import ocelot.app.restApi.modelo.OrdenVisitaResponse;
import ocelot.app.restApi.modelo.PresupuestoResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jose on 19/11/2017.
 */

public class RestApiAdapter {

    // Serializa el JSON
    public EndPointsApi establecerConexionApi(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson login(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ClienteResponse.class, new ClienteDesealizador());
        return gsonBuilder.create();
    }

    public Gson catalogo(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CatalogoResponse.class, new CatalogoDesealizador());
        return gsonBuilder.create();
    }

    public Gson promocion(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CatalogoResponse.class, new PromocionDesealizador());
        return gsonBuilder.create();
    }

    public Gson maestro(String tipo) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MaestroResponse.class, new MaestroDesealizador(tipo));
        return gsonBuilder.create();
    }

    public Gson getMensaje() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MensajeResponse.class, new MensajeDesealizador());
        return gsonBuilder.create();
    }

    public Gson categoria() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MaestroResponse.class, new CategoriaDesealizador());
        return gsonBuilder.create();
    }

    public Gson visita() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(OrdenVisitaResponse.class, new OrdenVisitaDesealizador());
        return gsonBuilder.create();
    }

    public Gson solicitud() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SolicitudResponse.class, new SolicitudDesealizador());
        return gsonBuilder.create();
    }

    public Gson etapas() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(EtapaResponse.class, new EtapaDesealizador());
        return gsonBuilder.create();
    }

    public Gson detalle_diagnostico() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Detalle_DiagnosticoResponse.class, new Detalle_DiagnosticoDesealizador());
        return  gsonBuilder.create();
    }

    public  Gson presupuesto(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PresupuestoResponse.class, new PresupuestoDesealizador());
        return  gsonBuilder.create();
    }

}
