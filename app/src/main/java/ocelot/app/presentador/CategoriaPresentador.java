package ocelot.app.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import ocelot.app.interfaz.ICategoria;
import ocelot.app.modelo.Maestro;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.MaestroResponse;
import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 24/11/2017.
 */

public class CategoriaPresentador implements ICategoriaPresentador {

    private final ICategoria iCategoria;
    private final Context context;
    private final View root;
    private ArrayList<Maestro> maestroLista = new ArrayList<>();

    public CategoriaPresentador(ICategoria iCategoria, Context context, View root) {
        this.iCategoria = iCategoria;
        this.context = context;
        this.root = root;

        obtenerInformacionCategoria();
    }

    @Override
    public void obtenerInformacionCategoria() {
        ProgressDialog progressDialog = MensajeUtils.progressConsultar(context);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.categoria();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<MaestroResponse> maestroResponseCall = endPointsApi.getComboMaestro("categoria");
        Log.d("REQUEST", maestroResponseCall.request().url().toString());
        maestroResponseCall.enqueue(new Callback<MaestroResponse>() {
            @Override
            public void onResponse(Call<MaestroResponse> call, Response<MaestroResponse> response) {
                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString() + " ");
                    MaestroResponse maestroResponse = response.body();
                    maestroLista.addAll(maestroResponse.getMaestros());

                    prepararBotones();
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MaestroResponse> call, Throwable t) {
                progressDialog.dismiss();
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
            }
        });

    }


    @Override
    public void prepararBotones() {
        HashMap<String, Maestro> map = new HashMap<String, Maestro>();
        for (int i = 0; i < maestroLista.size(); i++) {
            Maestro m = maestroLista.get(i);

            map.put(m.getDescripcion(), m);
        }
        mostrarBotones(map);
        iCategoria.cargarBotones(maestroLista);
    }

    @Override
    public void mostrarBotones(HashMap<String, Maestro> tiposServicios) {
        iCategoria.cargarMapBotones(tiposServicios);
    }
}
