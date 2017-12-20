package ocelot.app.presentador;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import ocelot.app.interfaz.IMaestro;
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

public class MaestroPresentador implements IMaestroPresentador {

    private final IMaestro iMaestro;
    private final Context context;
    private final View root;
    private final String tipoMaestro;
    private ArrayList<Maestro> maestroLista = new ArrayList<>();

    public MaestroPresentador(IMaestro iMaestro, Context context, View root, String tipoMaestro) {
        this.iMaestro = iMaestro;
        this.context = context;
        this.root = root;
        this.tipoMaestro = tipoMaestro;

        obtenerInformacionMaestro();
    }

    @Override
    public void obtenerInformacionMaestro() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.maestro(tipoMaestro);
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<MaestroResponse> maestroResponseCall = endPointsApi.getComboMaestro(tipoMaestro);
        maestroResponseCall.enqueue(new Callback<MaestroResponse>() {
            @Override
            public void onResponse(Call<MaestroResponse> call, Response<MaestroResponse> response) {
                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString() + " ");
                    MaestroResponse maestroResponse = response.body();
                    maestroLista.addAll(maestroResponse.getMaestros());

                    prepararCombo();
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
            }

            @Override
            public void onFailure(Call<MaestroResponse> call, Throwable t) {
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
            }
        });

    }

    @Override
    public void prepararCombo() {
        String[] spinnerArray = new String[maestroLista.size()];
        HashMap<Integer, String> spinnerMap = new HashMap<Integer, String>();
        for (int i = 0; i < maestroLista.size(); i++) {
            Maestro m = maestroLista.get(i);

            spinnerMap.put(i, String.valueOf(m.getId()) );
            spinnerArray[i] = m.getDescripcion();
        }
        mostrarCombo(spinnerArray);
        iMaestro.cargarMap(spinnerMap);
    }

    @Override
    public void mostrarCombo(String[] spinnerArray) {
        iMaestro.cargarCombo(spinnerArray);
    }

}
