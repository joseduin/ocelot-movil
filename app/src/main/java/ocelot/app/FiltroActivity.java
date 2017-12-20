package ocelot.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.interfaz.ICategoria;
import ocelot.app.interfaz.IMaestro;
import ocelot.app.modelo.Maestro;
import ocelot.app.presentador.CategoriaPresentador;
import ocelot.app.presentador.ICategoriaPresentador;
import ocelot.app.presentador.IMaestroPresentador;
import ocelot.app.presentador.MaestroPresentador;
import ocelot.app.sesion.Preferences;
import ocelot.app.utils.Combo;

public class FiltroActivity extends AppCompatActivity  implements IMaestro, ICategoria,
        View.OnClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbarFiltro) Toolbar toolbar;
    @BindView(R.id.aceptarFiltro) Button aceptarFiltro;
    @BindView(R.id.filtroLimpiar) Button filtroLimpiar;

    @BindView(R.id.SpinnerTipoInmueble) Spinner SpinnerTipoInmueble;
    @BindView(R.id.SpinnerCategoria) Spinner SpinnerCategoria;
    @BindView(R.id.SpinnerServicio) Spinner SpinnerServicio;

    @BindView(R.id.contenedorTipoServicio) LinearLayout contenedorTipoServicio;
    @BindView(R.id.filtro_root) CoordinatorLayout root;

    private IMaestroPresentador iMaestroPresentador;
    private ICategoriaPresentador iCategoriaPresentador;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private HashMap<Integer, String> mapSpinner = new HashMap<>();
    private ArrayList<Maestro> categorias = new ArrayList<>();
    private HashMap<String, Maestro> mapCategoria = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro);
        ButterKnife.bind(this);

        pref = getSharedPreferences(Preferences.NAME, Preferences.MODE);
        editor = pref.edit();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filtrado de Servicios");

        clickListener();
        contenedorTipoServicio.setVisibility(View.GONE);

        iMaestroPresentador = new MaestroPresentador(this, FiltroActivity.this, root, "tipoInmueble");
        iCategoriaPresentador = new CategoriaPresentador(this, FiltroActivity.this, root);
    }

    public void clickListener() {
        SpinnerCategoria.setOnItemSelectedListener(this);
        filtroLimpiar.setOnClickListener(this);
        aceptarFiltro.setOnClickListener(this);
    }

    @Override
    public void cargarCombo(String[] maestros) {
        ArrayList<String> m = new ArrayList<String>(Arrays.asList(maestros));
        Combo.cargar(SpinnerTipoInmueble, m, FiltroActivity.this);
    }

    @Override
    public void cargarMap(HashMap<Integer, String> map) {
        this.mapSpinner = map;
    }

    @Override
    public void cargarBotones(ArrayList<Maestro> categorias) {
        this.categorias = categorias;

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Todos");
        for (Maestro c : categorias)
            labels.add(c.getDescripcion());

        Combo.cargar(SpinnerCategoria, labels, FiltroActivity.this);
    }

    @Override
    public void cargarMapBotones(HashMap<String, Maestro> mapCategoria) {
        this.mapCategoria = mapCategoria;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aceptarFiltro:

                String tipoInmueble = mapSpinner.get(SpinnerTipoInmueble.getSelectedItemPosition());
                String tipoInmuebleDes = SpinnerTipoInmueble.getSelectedItem().toString();

                Maestro m = SpinnerCategoria.getSelectedItemPosition() == 0 ?
                            null : categorias.get(SpinnerCategoria.getSelectedItemPosition() - 1);
                int categoria = SpinnerCategoria.getSelectedItemPosition() == 0 ?
                                -1 : m.getId();
                String categoriaDes = SpinnerCategoria.getSelectedItemPosition() == 0 ?
                                        "Todos" : SpinnerCategoria.getSelectedItem().toString();

                int tipoServicio = categoria < 1 || SpinnerServicio.getSelectedItemPosition() == 0 ? -1 :
                                    m.getMaestros().get( SpinnerServicio.getSelectedItemPosition() - 1 ).getId();
                String tipoServicioDes = categoria < 1 || SpinnerServicio.getSelectedItemPosition() == 0 ? "" :
                                    SpinnerServicio.getSelectedItem().toString();

                editor.putString(Preferences.TRIBAGO, tipoInmueble + "/" + categoria + "/" + tipoServicio);
                editor.putString(Preferences.TRIBAGO_DES, tipoInmuebleDes + "/" + categoriaDes + "/" + tipoServicioDes);

                onBackPressed();
                break;
            case R.id.filtroLimpiar:
                editor.putString(Preferences.TRIBAGO, null);
                onBackPressed();
                break;
        }
        editor.commit();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        contenedorTipoServicio.setVisibility( i == 0 ? View.GONE : View.VISIBLE);

        if (i != 0) {
            ArrayList<String> labels = new ArrayList<>();
            labels.add("Todos");
            for (Maestro c : categorias.get(i - 1).getMaestros())
                labels.add(c.getDescripcion());
            Combo.cargar(SpinnerServicio, labels, FiltroActivity.this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
