package ocelot.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.presentador.IDetalle_DiagnosticoPresentador;

public class Detalle_Diagnostico extends AppCompatActivity {

    @BindView(R.id.toolbarDetalleDiagnosticos) Toolbar toolbar;
    @BindView(R.id.visitaNroo)TextView visitaNroo;
    @BindView(R.id.datosInmueble) TextView datosInmueble;
    @BindView(R.id.datosCliente) TextView datosCliente;
    @BindView(R.id.carateristica) TextView caracteristica;
    @BindView(R.id.condicion)TextView condicion;
    @BindView(R.id.metros) TextView metros;

    private IDetalle_DiagnosticoPresentador iDetalle_diagnosticoPresentador;
    private Detalle_Diagnostico detalle_diagnostico = new Detalle_Diagnostico();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__diagnostico);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( "Diagnostico" );
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
