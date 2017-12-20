package ocelot.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.adaptador.CatalogoAdaptador;
import ocelot.app.interfaz.ICatalogo;
import ocelot.app.modelo.Catalogo;
import ocelot.app.modelo.TribagoRequest;
import ocelot.app.presentador.CatalogoPresentador;
import ocelot.app.presentador.ICatalogoPresentador;
import ocelot.app.presentador.TribagoPresentador;
import ocelot.app.sesion.Preferences;
import ocelot.app.sesion.SessionManager;
import ocelot.app.utils.IrA;
import ocelot.app.utils.MensajeUtils;

/**
 * Created by Jose on 6/11/2017.
 */

public class CatalogoActivity extends AppCompatActivity implements ICatalogo, View.OnClickListener {

    @BindView(R.id.listaCatalogo) RecyclerView listaCatalogo;
    @BindView(R.id.toolbarCatalogo) Toolbar toolbar;
    @BindView(R.id.botonFiltros) ImageButton botonFiltros;
    @BindView(R.id.catalogo_root) CoordinatorLayout root;
    @BindView(R.id.contenedor_botones_filtro) LinearLayout contenedor_botones_filtro;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private SessionManager session;
    private ICatalogoPresentador iCatalogoPresentador;

    private boolean menuVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());

        pref = getSharedPreferences(Preferences.NAME, Preferences.MODE);
        editor = pref.edit();

        menuVisibilidad();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( "Catalogo de Servicios" );

        iCatalogoPresentador = new CatalogoPresentador(this, CatalogoActivity.this, root, false, false);

        botonFiltros.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pref.getString(Preferences.TRIBAGO, null) != null) {
            Log.d("TRIBAGO", pref.getString(Preferences.TRIBAGO, null));

            String[] tribago = pref.getString(Preferences.TRIBAGO, null).split("/");
            int tipoInmueble = Integer.valueOf(tribago[0]);
            int categoria = Integer.valueOf(tribago[01]);
            int tipoServicio = Integer.valueOf(tribago[2]);

            TribagoRequest tribagoRequest = new TribagoRequest();
            if (tipoInmueble != -1) {
                tribagoRequest.setTipoInmueble(Long.valueOf(tipoInmueble));
                if (categoria != -1) {
                    tribagoRequest.setTipoCategoria(Long.valueOf(categoria));
                    if (tipoServicio != -1) {
                        tribagoRequest.setTipoServicio(Long.valueOf(tipoServicio));
                    }
                }
            } else if (categoria != -1) {
                tribagoRequest.setTipoCategoria(Long.valueOf(categoria));
                if (tipoServicio != -1) {
                    tribagoRequest.setTipoServicio(Long.valueOf(tipoServicio));
                }
            } else if (tipoServicio != -1) {
                tribagoRequest.setTipoServicio(Long.valueOf(tipoServicio));
            }

            /*
            contenedor_botones_filtro.removeAllViews();
            if (pref.getString(Preferences.TRIBAGO_DES, null) != null) {

                String[] tribagoDes = pref.getString(Preferences.TRIBAGO_DES, null).split("/");
                String tipoInmuebleDes = tribagoDes[0];
                String categoriaDes = tribagoDes[01];
                String tipoServicioDes = tribagoDes[2];

                if (tipoInmueble != -1) {
                    Button b = new Button(CatalogoActivity.this);
                    b.setText(tipoInmuebleDes);
                    contenedor_botones_filtro.addView(b);
                }

                if (categoria != -1) {
                    Button b = new Button(CatalogoActivity.this);
                    b.setText(categoriaDes);
                    contenedor_botones_filtro.addView(b);
                }

                if (tipoServicio != -1) {
                    Button b = new Button(CatalogoActivity.this);
                    b.setText(tipoServicioDes);
                    contenedor_botones_filtro.addView(b);
                }

            } else {
                Button b = new Button(CatalogoActivity.this);
                b.setText("Todos");
                contenedor_botones_filtro.addView(b);
            }
            */

            Log.d("TRIBAGO", tribagoRequest.toString() + " ");
            iCatalogoPresentador = new TribagoPresentador(this, CatalogoActivity.this, root, tribagoRequest);
        }
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listaCatalogo.setLayoutManager(llm);
    }

    @Override
    public CatalogoAdaptador crearAdaptador(ArrayList<Catalogo> catalogo) {
        CatalogoAdaptador adapter = new CatalogoAdaptador(catalogo, CatalogoActivity.this );
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(CatalogoAdaptador catalogoAdaptador) {
        listaCatalogo.setAdapter(catalogoAdaptador);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.botonFiltros:
                IrA.vista(CatalogoActivity.this, FiltroActivity.class);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalogo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.vista_promocion).setVisible(menuVisible);

        return super.onPrepareOptionsMenu(menu);
    }

    private void menuVisibilidad() {
        menuVisible = !session.isLoggedIn();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.vista_promocion:
                IrA.vista(CatalogoActivity.this, PromocionesActivity.class);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        editor.putString(Preferences.TRIBAGO, null);
        super.onBackPressed();
    }

}
