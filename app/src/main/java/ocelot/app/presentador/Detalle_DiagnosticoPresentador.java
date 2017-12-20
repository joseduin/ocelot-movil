package ocelot.app.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import ocelot.app.interfaz.IDetalle_Diagnostico;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.Detalle_DiagnosticoResponse;

import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Glory on 05/12/2017.
 */

public class Detalle_DiagnosticoPresentador  implements IDetalle_DiagnosticoPresentador{

    private final Context context;
    private final View root;
    private final IDetalle_Diagnostico iDetalle_diagnostico;

    public Detalle_DiagnosticoPresentador(IDetalle_Diagnostico iDetalle_diagnostico, Context context, View root){
        this.iDetalle_diagnostico = iDetalle_diagnostico;
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

        Call<Detalle_DiagnosticoResponse> detalle_diagnosticoResponseCall = endPointsApi.getdetallediagnostico("1");
        detalle_diagnosticoResponseCall.enqueue(new Callback<Detalle_DiagnosticoResponse>() {
            @Override
            public void onResponse(Call<Detalle_DiagnosticoResponse> call, Response<Detalle_DiagnosticoResponse> response) {
                if (response.code() == 200) {
                    Detalle_DiagnosticoResponse detalle_diagnosticoResponse = response.body();
                    iDetalle_diagnostico.cargarDatos( detalle_diagnosticoResponse.getDetalle_Diagnostico() );
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Detalle_DiagnosticoResponse> call, Throwable t) {
                progressDialog.dismiss();
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
            }
        });
    }

    }
