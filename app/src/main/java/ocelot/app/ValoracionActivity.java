package ocelot.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.sackcentury.shinebuttonlib.ShineButton;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.R;

public class ValoracionActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbarValoracion) Toolbar toolbar;

    @BindView(R.id.valorarTiempo) RelativeLayout valorarTiempo;
    @BindView(R.id.tiempo1) ShineButton tiempo1;
    @BindView(R.id.tiempo2) ShineButton tiempo2;
    @BindView(R.id.tiempo3) ShineButton tiempo3;
    @BindView(R.id.tiempo4) ShineButton tiempo4;
    @BindView(R.id.tiempo5) ShineButton tiempo5;

    @BindView(R.id.valorarCalidad) RelativeLayout valorarCalidad;
    @BindView(R.id.calidad1) ShineButton calidad1;
    @BindView(R.id.calidad2) ShineButton calidad2;
    @BindView(R.id.calidad3) ShineButton calidad3;
    @BindView(R.id.calidad4) ShineButton calidad4;
    @BindView(R.id.calidad5) ShineButton calidad5;

    @BindView(R.id.valorarPrecio) RelativeLayout valorarPrecio;
    @BindView(R.id.precio1) ShineButton precio1;
    @BindView(R.id.precio2) ShineButton precio2;
    @BindView(R.id.precio3) ShineButton precio3;
    @BindView(R.id.precio4) ShineButton precio4;
    @BindView(R.id.precio5) ShineButton precio5;

    @BindView(R.id.valorarOpinion) RelativeLayout valorarOpinion;
    @BindView(R.id.campo_opinion) EditText campo_opinion;
    @BindView(R.id.terminarValoracion) Button terminarValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valoracion);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( "Valoranos" );

        setInvisibleView();
        setShineButtonClick();
    }

    private void setShineButtonClick() {
        tiempo1.setOnClickListener(this);
        tiempo2.setOnClickListener(this);
        tiempo3.setOnClickListener(this);
        tiempo4.setOnClickListener(this);
        tiempo5.setOnClickListener(this);

        calidad1.setOnClickListener(this);
        calidad2.setOnClickListener(this);
        calidad3.setOnClickListener(this);
        calidad4.setOnClickListener(this);
        calidad5.setOnClickListener(this);

        precio1.setOnClickListener(this);
        precio2.setOnClickListener(this);
        precio3.setOnClickListener(this);
        precio4.setOnClickListener(this);
        precio5.setOnClickListener(this);

        terminarValoracion.setOnClickListener(this);
    }

    private void setInvisibleView() {
        valorarTiempo.setVisibility(View.VISIBLE);

        valorarCalidad.setVisibility(View.GONE);
        valorarPrecio.setVisibility(View.GONE);
        valorarOpinion.setVisibility(View.GONE);
        terminarValoracion.setVisibility(View.GONE);
        terminarValoracion.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (valorarTiempo.getVisibility() == View.VISIBLE) {
                    onBackPressed();
                } else if (valorarCalidad.getVisibility() == View.VISIBLE) {
                    ocultarView(valorarCalidad, valorarTiempo);
                } else if (valorarPrecio.getVisibility() == View.VISIBLE) {
                    ocultarView(valorarPrecio, valorarCalidad);
                } else if (valorarOpinion.getVisibility() == View.VISIBLE) {
                    ocultarView(valorarOpinion, valorarPrecio);
                    terminarValoracion.setVisibility(View.GONE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tiempo1:
            case R.id.tiempo2:
            case R.id.tiempo3:
            case R.id.tiempo4:
            case R.id.tiempo5:
                ocultarView(valorarTiempo, valorarCalidad);
                break;

            case R.id.calidad1:
            case R.id.calidad2:
            case R.id.calidad3:
            case R.id.calidad4:
            case R.id.calidad5:
                ocultarView(valorarCalidad, valorarPrecio);
                break;

            case R.id.precio1:
            case R.id.precio2:
            case R.id.precio3:
            case R.id.precio4:
            case R.id.precio5:
                ocultarView(valorarPrecio, valorarOpinion);
                terminarValoracion.setVisibility(View.VISIBLE);
                break;

            case R.id.terminarValoracion:
                onBackPressed();
                break;
        }
    }

    private void ocultarView(View actual, View siguiente) {
        actual.setVisibility(View.GONE);
        siguiente.setVisibility(View.VISIBLE);
    }
}
