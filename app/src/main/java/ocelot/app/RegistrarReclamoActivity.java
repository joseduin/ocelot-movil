package ocelot.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.R;
import ocelot.app.utils.Combo;

public class RegistrarReclamoActivity extends AppCompatActivity {

    @BindView(R.id.toolbarReclamo) Toolbar toolbar;
    @BindView(R.id.sp01) Spinner opciones;
    @BindView(R.id.sp02) Spinner opciones1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_reclamo);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( "Registrar un Nuevo Reclamo" );

        Combo.cargar(opciones, R.array.opciones, RegistrarReclamoActivity.this);
        Combo.cargar(opciones1, R.array.opciones1, RegistrarReclamoActivity.this);

        // Ocultar el keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
