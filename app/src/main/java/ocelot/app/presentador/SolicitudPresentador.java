package ocelot.app.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import ocelot.app.interfaz.ISolicitud;
import ocelot.app.modelo.Solicitud;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.SolicitudResponse;
import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 4/12/2017.
 */

public class SolicitudPresentador implements ISolicitudPresentador {

    private ISolicitud iSolicitud;
    private final Context context;
    private final View root;
    private final String id;
    private ArrayList<Solicitud> solicituds = new ArrayList<>();

    public SolicitudPresentador(ISolicitud iSolicitud, Context context, View root, String id) {
        this.iSolicitud = iSolicitud;
        this.context = context;
        this.root = root;
        this.id = id;

        obtenerInformacion();
    }

    @Override
    public void obtenerInformacion() {
        ProgressDialog progressDialog = MensajeUtils.progressConsultar(context);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.solicitud();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<SolicitudResponse> responseCall = endPointsApi.getSolicitudes(id);
        responseCall.enqueue(new Callback<SolicitudResponse>() {
            @Override
            public void onResponse(Call<SolicitudResponse> call, Response<SolicitudResponse> response) {
                if (response.code() == 200) {
                    SolicitudResponse solicitudResponse = response.body();
                    solicituds.addAll(solicitudResponse.getSolicitud());
                    Collections.reverse(solicituds);

                    mostrarRV();
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
                    progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SolicitudResponse> call, Throwable t) {
                progressDialog.dismiss();
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
            }
        });
    }

    @Override
    public void mostrarRV() {
        iSolicitud.inicializarAdaptadorRV(iSolicitud.crearAdaptador(solicituds));
        iSolicitud.generarLayoutVertical();
    }

}
