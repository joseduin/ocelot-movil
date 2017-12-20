package ocelot.app.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import ocelot.app.interfaz.IOrdenVisita;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.OrdenVisitaResponse;
import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 28/11/2017.
 */

public class OrdenVisitaPresentador implements IOrdenVisitaPresentador {

    private final Context context;
    private final View root;
    private final IOrdenVisita iOrdenVisita;

    public OrdenVisitaPresentador(IOrdenVisita iOrdenVisita, Context context, View root) {
        this.iOrdenVisita = iOrdenVisita;
        this.context = context;
        this.root = root;

        obtenerInformacion();
    }

    @Override
    public void obtenerInformacion() {
        ProgressDialog progressDialog = MensajeUtils.progressConsultar(context);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.visita();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<OrdenVisitaResponse> ordenVisitaResponseCall = endPointsApi.getordenvisita("1");
        ordenVisitaResponseCall.enqueue(new Callback<OrdenVisitaResponse>() {
            @Override
            public void onResponse(Call<OrdenVisitaResponse> call, Response<OrdenVisitaResponse> response) {
                if (response.code() == 200) {
                    OrdenVisitaResponse ordenVisitaResponse = response.body();
                    iOrdenVisita.cargarDatos( ordenVisitaResponse.getOdenVisita() );
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<OrdenVisitaResponse> call, Throwable t) {
                progressDialog.dismiss();
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
            }
        });
    }

}
