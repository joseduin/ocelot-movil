package ocelot.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.R;

public class PresupuestoActivity extends AppCompatActivity {

    @BindView(R.id.toolbarPresupuesto) Toolbar toolbar;
    @BindView(R.id.presupuestonro)TextView presupuestonro;
    @BindView(R.id.fecha) TextView fecha;
    @BindView(R.id.lugarvisita) TextView lugarvisita;
    @BindView(R.id.telefono) TextView telefono;
    @BindView(R.id.servicio) TextView servicio;
    @BindView(R.id.precio) TextView precio;
    @BindView(R.id.area) TextView area;
    @BindView(R.id.total) TextView total;
    @BindView(R.id.subtotal) TextView subtotal;
    @BindView(R.id.totalpagar) TextView totalpagar;
    @BindView(R.id.confirmarPresupuesto)Button confirmarpresupuesto;
    @BindView(R.id.rechazarPresupuesto) Button rechazarpresupuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( "Presupuesto" );
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
