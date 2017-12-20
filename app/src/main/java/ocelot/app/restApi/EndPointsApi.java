package ocelot.app.restApi;

import ocelot.app.modelo.BuzonRequest;
import ocelot.app.modelo.TribagoRequest;
import ocelot.app.restApi.modelo.CatalogoResponse;
import ocelot.app.restApi.modelo.ClienteResponse;
import ocelot.app.restApi.modelo.EtapaResponse;
import ocelot.app.restApi.modelo.SolicitudResponse;
import ocelot.app.restApi.modelo.MaestroResponse;
import ocelot.app.restApi.modelo.MensajeResponse;
import ocelot.app.restApi.modelo.OrdenVisitaResponse;
import ocelot.app.restApi.modelo.PresupuestoResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Jose on 19/11/2017.
 */

public interface EndPointsApi {

    @GET(ConstantesRestApi.LOGIN)
    Call<ClienteResponse> setLogin(@Path("user") String nombre,
                                   @Path("passw") String clave);

    @GET(ConstantesRestApi.CATALOGO)
    Call<CatalogoResponse> getCatalogo();

    @GET(ConstantesRestApi.CATALOGO_ID)
    Call<CatalogoResponse> getCatalogoId(@Path("id") String id);

    @GET(ConstantesRestApi.MAESTRO_COMBO)
    Call<MaestroResponse> getComboMaestro(@Path("maestro") String maestro);

    @GET(ConstantesRestApi.PROMOCION)
    Call<CatalogoResponse> getPromocion();

    @Headers("Content-Type: application/json")
    @POST(ConstantesRestApi.BUZON)
    Call<MensajeResponse> enviarSugerencia(@Body BuzonRequest buzon,
                                           @Path("persona") String persona,
                                           @Path("asunto") String asunto);

    @GET(ConstantesRestApi.ORDENVISITA)
    Call<OrdenVisitaResponse> getordenvisita(@Path("id") String id);

    @GET(ConstantesRestApi.ORDENVISITA_CANCELAR)
    Call<MensajeResponse> cancelarVisita(@Path("id") String id);

    @Headers("Content-Type: application/json")
    @POST(ConstantesRestApi.TRIBAGO)
    Call<CatalogoResponse> tribago(@Body TribagoRequest tribago);

    @GET(ConstantesRestApi.GUARDAR_TOKEN)
    Call<MensajeResponse> saveToken(@Path("movilToken") String movilToken,
                                   @Path("id") String id);

    @GET(ConstantesRestApi.SOLICITUD)
    Call<SolicitudResponse> getSolicitudes(@Path("id") String id);

    @GET(ConstantesRestApi.ETAPAS)
    Call<EtapaResponse> getEtapas(@Path("id") String id);

    @GET(ConstantesRestApi.DETALLE_DIAGNOSTICO)
    Call<Detalle_DiagnosticoResponse> getdetallediagnostico(@Path("id") String id);

    @GET(ConstantesRestApi.PRESUPUESTO)
    Call<PresupuestoResponse> getpresupuesto(@Path("id") String id);

}
