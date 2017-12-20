package ocelot.app.modelo;

import java.util.Date;

/**
 * Created by Jose on 19/11/2017.
 */

public class Cliente {

    private int id;
    private String nombre;
    private int identificacion;
    private int estatus;
    private String password;
    private String email;
    private int tipoPersona;
    private int rol;

    public Cliente(int id, String nombre, int identificacion, int estatus, String password, String email, int tipoPersona, int rol) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.estatus = estatus;
        this.password = password;
        this.email = email;
        this.tipoPersona = tipoPersona;
        this.rol = rol;
    }

    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(int tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
}
