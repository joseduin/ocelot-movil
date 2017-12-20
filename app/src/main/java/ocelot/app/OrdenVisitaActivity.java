package ocelot.app;

import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.Calendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.R;
import ocelot.app.interfaz.IOrdenVisita;
import ocelot.app.modelo.Cliente;
import ocelot.app.modelo.OrdenVisita;
import ocelot.app.presentador.IOrdenVisitaPresentador;
import ocelot.app.presentador.OrdenVisitaPresentador;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.MensajeResponse;
import ocelot.app.utils.Convertidor;
import ocelot.app.utils.MensajeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdenVisitaActivity extends AppCompatActivity implements IOrdenVisita, View.OnClickListener {

    @BindView(R.id.phone_orden_visita) ImageView phone_orden_visita;
    @BindView(R.id.CancelarordenVisita) Button CancelarordenVisita;
    @BindView(R.id.orden_visita_root) CoordinatorLayout root;
    @BindView(R.id.nombretecnico) TextView nombretecnico;
    @BindView(R.id.toolbarOrdenVisita) Toolbar toolbar;
    @BindView(R.id.lugarvisita) TextView lugarvisita;
    @BindView(R.id.telefono) TextView telefono;
    @BindView(R.id.fecha) TextView fecha;

    private IOrdenVisitaPresentador iOrdenVisitaPresentador;
    private OrdenVisita ordenVisita = new OrdenVisita();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_visita);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Orden de Visita");

        onClickListener();

        CancelarordenVisita.setVisibility(View.GONE);
        iOrdenVisitaPresentador = new OrdenVisitaPresentador(this, OrdenVisitaActivity.this, root);
    }

    private void onClickListener() {
        CancelarordenVisita.setOnClickListener(this);

        phone_orden_visita.setOnClickListener(this);
        telefono.setOnClickListener(this);
    }

    @Override
    public void cargarDatos(OrdenVisita ordenVisita) {
        this.ordenVisita = ordenVisita;

        nombretecnico.setText(ordenVisita.getNombretecnico());
        telefono.setText( Convertidor.telefonoPrint(ordenVisita.getTelefono()) );
        lugarvisita.setText(ordenVisita.getLugarvisita());
        fecha.setText(Convertidor.fechaPrint(ordenVisita.getFecha()));

        if (ordenVisita.getEstatus() != 7)
            CancelarordenVisita.setVisibility(View.VISIBLE);
    }

    public void cancelar() {
        ProgressDialog progressDialog = MensajeUtils.progressEnvio(OrdenVisitaActivity.this);
        progressDialog.show();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.getMensaje();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);

        Call<MensajeResponse> mensajeResponseCall = endPointsApi.cancelarVisita("1");
        Log.d("LOGIN", mensajeResponseCall.request().url().toString());
        mensajeResponseCall.enqueue(new Callback<MensajeResponse>() {
            @Override
            public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                if (response.code() == 200) {
                    MensajeResponse mensajeResponse = response.body();
                    MensajeUtils.mensajeCorto(root, mensajeResponse.getMensaje().getMensaje());

                    onBackPressed();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.telefono:
            case R.id.phone_orden_visita:
                llamar( ordenVisita.getTelefono() );
                break;
            case R.id.CancelarordenVisita:
                cancelar();
                break;
        }
    }

    private void llamar(String telefono) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
