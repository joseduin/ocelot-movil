package ocelot.app.presentador;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import ocelot.app.interfaz.ICatalogo;
import ocelot.app.modelo.Catalogo;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.CatalogoResponse;
import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jose on 21/11/2017.
 */

public class CatalogoPresentador implements ICatalogoPresentador {

    private final ICatalogo iCatalogo;
    private final Context context;
    private final View root;
    private final boolean isPromocion;
    private final boolean isTribago;
    private ArrayList<Catalogo> catalogo = new ArrayList<>();

    public CatalogoPresentador(ICatalogo iCatalogo, Context context, View root, boolean isPromocion, boolean isTribago) {
        this.iCatalogo = iCatalogo;
        this.context = context;
        this.root = root;
        this.isPromocion = isPromocion;
        this.isTribago = isTribago;
        obtenerInformacionCatalogo();
    }

    @Override
    public void obtenerInformacionCatalogo() {
        ProgressDialog progressDialog = MensajeUtils.progressConsultar(context);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = isPromocion ? restApiAdapter.promocion() : restApiAdapter.catalogo();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);
        Log.d("RESPONSE", "SEN REQUEST");
        Call<CatalogoResponse> catalogoResponseCall = isPromocion ? endPointsApi.getPromocion() : endPointsApi.getCatalogo();
        catalogoResponseCall.enqueue(new Callback<CatalogoResponse>() {
            @Override
            public void onResponse(Call<CatalogoResponse> call, Response<CatalogoResponse> response) {
                if (response.code() == 200) {
                    Log.d("RESPONSE", response.body().toString() + " ");
                    CatalogoResponse catalogoResponse = response.body();
                    catalogo.addAll(catalogoResponse.getCatalogos());
                    mostrarCatalogoRV();

                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.NO_CONTENT);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CatalogoResponse> call, Throwable t) {
                progressDialog.dismiss();
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION + " "+ t.toString() + " " + call.toString());
            }
        });

    }

    @Override
    public void mostrarCatalogoRV() {
        iCatalogo.inicializarAdaptadorRV(iCatalogo.crearAdaptador(catalogo));
        iCatalogo.generarLayoutVertical();
    }
}