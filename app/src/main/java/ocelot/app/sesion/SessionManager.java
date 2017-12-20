package ocelot.app.sesion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import ocelot.app.LoginActivity;
import ocelot.app.ServiciosActivity;
import ocelot.app.SplashActivity;
import ocelot.app.modelo.Cliente;
import ocelot.app.utils.Convertidor;


/**
 * Created by Jose on 19/11/2017.
 */

public class SessionManager {

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    int PRIVATE_MODE = Preferences.MODE;

    // Sharedpref file name
    private static final String PREF_NAME = Preferences.NAME;

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_SEXO = "sexo";
    private static final String KEY_IDENTIFICACION = "identificacion";
    private static final String KEY_FECHA_NACIMIENTO = "fecha_de_nacimiento";
    private static final String KEY_DIRECCION = "direccion";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_ESTATUS = "estatus";
    private static final String KEY_CLAVE = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TOKEN_MOVIL = "tokenMovil";
    private static final String KEY_TIPO_PERSONA = "tipoPersona";
    private static final String KEY_TIPO_ROL = "rol";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(Cliente cliente) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putInt(KEY_ID, cliente.getId());
        editor.putString(KEY_NOMBRE, cliente.getNombre());
        editor.putString(KEY_EMAIL, cliente.getEmail());
        editor.putInt(KEY_IDENTIFICACION, cliente.getIdentificacion());
        editor.putInt(KEY_ESTATUS, cliente.getEstatus());
        editor.putString(KEY_CLAVE, cliente.getPassword());
        editor.putInt(KEY_TIPO_PERSONA, cliente.getTipoPersona());
        editor.putInt(KEY_TIPO_ROL, cliente.getRol());

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkIn() {
        if(!this.isLoggedIn()) {
            irA(SplashActivity.class);
        }
    }

    public void checkOut() {
        if(this.isLoggedIn()) {
            irA(ServiciosActivity.class);
        }
    }

    private void irA(Class<?> c) {
        Intent i = new Intent(_context, c);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    /**
     * Get stored session data
     * */
    public Cliente getClienteActivo(){
        Cliente cliente = new Cliente();

        cliente.setId(pref.getInt(KEY_ID, 0));
        cliente.setNombre(pref.getString(KEY_NOMBRE, null));
        cliente.setEmail(pref.getString(KEY_EMAIL, null));
        cliente.setIdentificacion(pref.getInt(KEY_IDENTIFICACION, 0));
        cliente.setEstatus(pref.getInt(KEY_ESTATUS, 0));
        cliente.setPassword(pref.getString(KEY_CLAVE, null));
        cliente.setTipoPersona(pref.getInt(KEY_TIPO_PERSONA, 0));
        cliente.setRol(pref.getInt(KEY_TIPO_ROL, 0));

        return cliente;
    }

    /**
     * Clear session details
     * */
    public void logout(){
        editor.clear();
        editor.commit();
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}
