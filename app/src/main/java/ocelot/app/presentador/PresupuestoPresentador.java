package ocelot.app.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;


import com.google.gson.Gson;

import ocelot.app.interfaz.IPresupuesto;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.PresupuestoResponse;
import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Glory on 06/12/2017.
 */

public class PresupuestoPresentador implements IPresupuestoPresentador {


    private final Context context;
    private final View root;
    private final IPresupuesto iPresupuesto;

    public PresupuestoPresentador(IPresupuesto iPresupuesto, Context context, View root) {
        this.iPresupuesto = iPresupuesto;
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

        Call<PresupuestoResponse>  presupuestoResponseCall = endPointsApi.getpresupuesto("1");
        presupuestoResponseCall.enqueue(new Callback<PresupuestoResponse>() {

            @Override
            public void onResponse(Call<PresupuestoResponse> call, Response<PresupuestoResponse> response) {
                if (response.code() == 200) {
                    PresupuestoResponse presupuestoResponse = response.body();
                    iPresupuesto.cargarDatos( presupuestoResponse.getPresupuesto() );
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PresupuestoResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
                }
            });
        }

}
