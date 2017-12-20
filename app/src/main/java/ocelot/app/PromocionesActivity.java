package ocelot.app;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.adaptador.CatalogoAdaptador;
import ocelot.app.interfaz.ICatalogo;
import ocelot.app.modelo.Catalogo;
import ocelot.app.presentador.CatalogoPresentador;
import ocelot.app.presentador.ICatalogoPresentador;
import ocelot.app.sesion.SessionManager;
import ocelot.app.utils.IrA;

public class PromocionesActivity extends AppCompatActivity implements ICatalogo {

    @BindView(R.id.listaPromociones) RecyclerView lista;
    @BindView(R.id.toolbarPromociones) Toolbar toolbar;
    @BindView(R.id.promocion_root) CoordinatorLayout root;

    private SessionManager session;
    private ICatalogoPresentador iCatalogoPresentador;

    private boolean menuVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());

        menuVisibilidad();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Promociones");

        iCatalogoPresentador = new CatalogoPresentador(this, PromocionesActivity.this, root, true, false);
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        lista.setLayoutManager(llm);
    }

    @Override
    public CatalogoAdaptador crearAdaptador(ArrayList<Catalogo> catalogo) {
        CatalogoAdaptador adapter = new CatalogoAdaptador(catalogo, PromocionesActivity.this );
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(CatalogoAdaptador catalogoAdaptador) {
        lista.setAdapter(catalogoAdaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_promocion, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.vista_servicio).setVisible(menuVisible);

        return super.onPrepareOptionsMenu(menu);
    }

    private void menuVisibilidad() {
        menuVisible = !session.isLoggedIn();
        invalidateOptionsMenu();
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
            case R.id.vista_servicio:
                IrA.vista(PromocionesActivity.this, CatalogoActivity.class);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
