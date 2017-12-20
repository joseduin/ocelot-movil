package ocelot.app;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ocelot.app.animator.AnimatorView;
import ocelot.app.sesion.SessionManager;
import ocelot.app.utils.IrA;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.buttonIniciar) Button buttonIniciar;
    @BindView(R.id.buttonCatalogo) Button buttonCatalogo;
    @BindView(R.id.splash_root) CoordinatorLayout splash_root;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        session = new SessionManager(getApplicationContext());
        session.checkOut();

        onSetClick();
    }

    private void onSetClick() {
        buttonIniciar.setOnClickListener(this);
        buttonCatalogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonIniciar:
                IrA.vista(SplashActivity.this, LoginActivity.class);
                break;

            case R.id.buttonCatalogo:
                IrA.vista(SplashActivity.this, CatalogoActivity.class);
                break;
        }
    }

}
