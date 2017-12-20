package ocelot.app;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.ClienteResponse;
import ocelot.app.sesion.SessionManager;
import ocelot.app.utils.IrA;
import ocelot.app.utils.MensajeUtils;
import ocelot.app.utils.Validacion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btnSignIn) ActionProcessButton btnSignIn;
    @BindView(R.id.campo_nombre) EditText campo_nombre;
    @BindView(R.id.campoClave) EditText campoClave;
    @BindView(R.id.inicioRegresar) ImageButton iniciarRegresar;
    @BindView(R.id.coordinatorLayout) CoordinatorLayout root;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        session.checkOut();

        onClickListener();
    }

    private void onClickListener() {
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);

        btnSignIn.setOnClickListener(this);
        iniciarRegresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                login();
                break;
            case R.id.inicioRegresar:
                onBackPressed();
                break;
        }
    }

    private void login() {
        if (validarCampos()) {
            MensajeUtils.mensajeCorto(root, MensajeUtils.CAMPOS_OBLIGATORIOS);
            return;
        }

        btnSignIn.setProgress(90);
        String usuario = campo_nombre.getText().toString();
        String clave = campoClave.getText().toString();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.login();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<ClienteResponse> clienteResponseCall = endPointsApi.setLogin(usuario, clave);
        Log.d("LOGIN", clienteResponseCall.request().url().toString());
        clienteResponseCall.enqueue(new Callback<ClienteResponse>() {
            @Override
            public void onResponse(Call<ClienteResponse> call, Response<ClienteResponse> response) {
                if (response.code() == 200) {
                    ClienteResponse clienteResponse = response.body();

                    btnSignIn.setProgress(100);
                    session.createLoginSession(clienteResponse.getCliente());
                    IrA.vista(LoginActivity.this, ServiciosActivity.class);
                } else {
                    btnSignIn.setProgress(-1);
                    MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_AUTH);
                }
            }

            @Override
            public void onFailure(Call<ClienteResponse> call, Throwable t) {
                btnSignIn.setProgress(-1);
                MensajeUtils.mensajeCorto(root, MensajeUtils.ERROR_CONEXION);
            }
        });

    }

    private boolean validarCampos() {
        return Validacion.campoVacio(campo_nombre)
                || Validacion.campoVacio(campoClave);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
