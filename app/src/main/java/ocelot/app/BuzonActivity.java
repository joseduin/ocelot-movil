package ocelot.app;

import android.app.ProgressDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ocelot.app.interfaz.IMaestro;
import ocelot.app.modelo.BuzonRequest;
import ocelot.app.presentador.IMaestroPresentador;
import ocelot.app.presentador.MaestroPresentador;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.MensajeResponse;
import ocelot.app.sesion.SessionManager;
import ocelot.app.utils.Combo;
import ocelot.app.utils.MensajeUtils;
import ocelot.app.utils.Validacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuzonActivity extends AppCompatActivity implements IMaestro {
    
    @BindView(R.id.toolbarBuzon) Toolbar toolbar;
    @BindView(R.id.SpinnerTipoSugerencia) Spinner SpinnerTipoSugerencia;
    @BindView(R.id.campo_asuntoBuzon) EditText campo_asuntoBuzon;
    @BindView(R.id.campo_descripcion_sugerencia) EditText campo_descripcion_sugerencia;
    @BindView(R.id.enviarBuzon) Button enviarBuzon;
    @BindView(R.id.limpiarBuzon) Button limpiarBuzon;
    @BindView(R.id.buzon_root) CoordinatorLayout root;
    private ProgressDialog progressDialog;

    private SessionManager session;

    private IMaestroPresentador iMaestroPresentador;
    private HashMap<Integer, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buzon);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( "Buzon de Sugerencias" );

        iMaestroPresentador = new MaestroPresentador(this, getApplicationContext(), root, "asuntoComentario");

        // Ocultar el keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void cargarCombo(String[] maestros) {
        ArrayList<String> m = new ArrayList<String>(Arrays.asList(maestros));
        Combo.cargar(SpinnerTipoSugerencia, m, BuzonActivity.this);
    }

    @Override
    public void cargarMap(HashMap<Integer, String> map) {
        this.map = map;
    }

    @OnClick(R.id.enviarBuzon)
    public void enviarSugerencia() {
        if (validarCampos()) {
            MensajeUtils.mensajeCorto(root, MensajeUtils.CAMPOS_OBLIGATORIOS);
            return;
        }

        progressDialog = MensajeUtils.progressEnvio(BuzonActivity.this);
        progressDialog.show();

        String idPersona = String.valueOf( session.getClienteActivo().getId() );
        String asunto = campo_asuntoBuzon.getText().toString();
        String descripcion = campo_descripcion_sugerencia.getText().toString();
        String tipo = map.get( SpinnerTipoSugerencia.getSelectedItemPosition() );

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.getMensaje();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        BuzonRequest buzonRequest = new BuzonRequest(asunto, descripcion);
        Call<MensajeResponse> mensajeResponseCall = endPointsApi.enviarSugerencia(buzonRequest, idPersona, tipo);
        mensajeResponseCall.enqueue(new Callback<MensajeResponse>() {
            @Override
            public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                if (response.code() == 200) {
                    MensajeResponse mensajeResponse = response.body();
                    MensajeUtils.mensajeCorto(root, mensajeResponse.getMensaje().getMensaje());

                    limpiarCampos();
                } else {
                    MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MensajeResponse> call, Throwable t) {
                progressDialog.dismiss();
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION);
            }
        });
    }

    private void limpiarCampos() {
        campo_asuntoBuzon.setText(MensajeUtils.EMPTY);
        campo_descripcion_sugerencia.setText(MensajeUtils.EMPTY);
    }

    private boolean validarCampos() {
        return Validacion.campoVacio(campo_asuntoBuzon)
                || Validacion.campoVacio(campo_descripcion_sugerencia);
    }

    @OnClick(R.id.limpiarBuzon)
    public void cancelarBuzon() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
