package ocelot.app;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.adaptador.CatalogoAdaptador;
import ocelot.app.adaptador.SolicitudAdaptador;
import ocelot.app.interfaz.ISolicitud;
import ocelot.app.modelo.Solicitud;
import ocelot.app.presentador.SolicitudPresentador;
import ocelot.app.restApi.EndPointsApi;
import ocelot.app.restApi.adapter.RestApiAdapter;
import ocelot.app.restApi.modelo.MensajeResponse;
import ocelot.app.sesion.SessionManager;
import ocelot.app.utils.IrA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiciosActivity extends AppCompatActivity
        implements ISolicitud, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.root_servicio) CoordinatorLayout root;

    private SessionManager session;
    private SolicitudPresentador solicitudPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        session.checkIn();

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle("Mis Servicios");

        navigationView.setNavigationItemSelectedListener(this);
        configMenu(navigationView);

        getFirebaseToken();

    }

    @Override
    protected void onResume() {
        super.onResume();

        String id = String.valueOf( session.getClienteActivo().getId() );
        solicitudPresentador = new SolicitudPresentador(this, ServiciosActivity.this, root, id);
    }

    private void configMenu(NavigationView navigationView) {
        View header = navigationView.getHeaderView(0);
        TextView nombre = header.findViewById(R.id.sesion_nombre);
        TextView email = header.findViewById(R.id.sesion_email);

        nombre.setText( session.getClienteActivo().getNombre() );
        email.setText( session.getClienteActivo().getEmail() );
    }

    private void getFirebaseToken() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Refreshed token", refreshedToken +" .");

        if (refreshedToken != null)
            saveToken(refreshedToken);

        FirebaseMessaging.getInstance().subscribeToTopic(getResources().getString(R.string.app_name));
    }

    private void saveToken(String token) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.getMensaje();
        EndPointsApi endPointsApi = restApiAdapter.establecerConexionApi(gson);
        String id = String.valueOf( session.getClienteActivo().getId() );

        Call<MensajeResponse> mensajeResponseCall = endPointsApi.saveToken(token, id);
        Log.d("REQUEST", mensajeResponseCall.request().url().toString());
        mensajeResponseCall.enqueue(new Callback<MensajeResponse>() {
            @Override
            public void onResponse(Call<MensajeResponse> call, Response<MensajeResponse> response) {
                if (response.code() == 200) {
                    MensajeResponse mensajeResponse = response.body();
                    Log.d("TOKEN REFRESHED", mensajeResponse.getMensaje().getMensaje());
                }
            }

            @Override
            public void onFailure(Call<MensajeResponse> call, Throwable t) {}
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_buzon) {
            IrA.vista(ServiciosActivity.this, BuzonActivity.class);

        } else if (id == R.id.menu_notificaciones) {
            IrA.vista(ServiciosActivity.this, NotificacionActivity.class);

        } else if (id == R.id.menu_cerrar) {
            session.logout();
            session.checkIn();

        } else if (id == R.id.menu_catalogoPromociones) {
            IrA.vista(ServiciosActivity.this, PromocionesActivity.class);

        } else if (id == R.id.menu_catalogo) {
            IrA.vista(ServiciosActivity.this, CatalogoActivity.class);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void generarLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
    }

    @Override
    public SolicitudAdaptador crearAdaptador(ArrayList<Solicitud> catalogo) {
        SolicitudAdaptador adapter = new SolicitudAdaptador(catalogo, ServiciosActivity.this );
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(SolicitudAdaptador adaptador) {
        rv.setAdapter(adaptador);
    }

}
