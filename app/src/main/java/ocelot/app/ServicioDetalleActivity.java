package ocelot.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicioDetalleActivity extends AppCompatActivity {

    @BindView(R.id.etapas_detalle) RecyclerView etapa_detalle;
    @BindView(R.id.toolbarDetalle) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_detalle);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        int idServicio = b.getInt(getResources().getString(R.string.detalle_servicio));
/*
        List<Etapa> etapas = new ArrayList<>();
        for (Solicitud servicios : Solicitud.listadoServicios()) {
            if (servicios.getId() == idServicio) {
                getSupportActionBar().setTitle( servicios.getDescripcion() );
                etapas = servicios.getEtapas();
                break;
            }
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        etapa_detalle.setLayoutManager(llm);

        Collections.reverse(etapas);
        ServiciosDetalleAdaptador adapter = new ServiciosDetalleAdaptador(etapas, ServicioDetalleActivity.this );
        etapa_detalle.setAdapter(adapter);
        */
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
