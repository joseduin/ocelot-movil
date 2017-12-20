package ocelot.app.restApi;

/**
 * Created by Jose on 19/11/2017.
 */

public final class ConstantesRestApi {

    //public static final String API = "http://192.168.0.104:8080/";
    public static final String API ="https://fox-hound.herokuapp.com/";
    //public static final String API = "http://foxhound.sytes.net:9000/";

    public static final String WEB = "https://intranet-angelmavare-abdelg.c9users.io/";
    public static final String WEB_DESKTOP = "https://fox-desktop-joseduin.c9users.io/";
//    public static final String WEB_DESKTOP = "http://172.19.24.192:3001/";

    public static final String AGREGAR = "agregar";
    public static final String BUSCAR = "buscar/{id}";
    public static final String BUSCAR_ACTIVO = "buscarActivos";

    public static final String ROOT = API;

    public static final String KEY_LOGIN = "login/{user}/{passw}";
    public static final String LOGIN = ROOT + KEY_LOGIN;

    public static final String KEY_CATALOGO = "servicio/";
    public static final String CATALOGO = ROOT + KEY_CATALOGO + BUSCAR_ACTIVO;
    public static final String CATALOGO_ID = ROOT + KEY_CATALOGO + BUSCAR;

    public static final String KEY_PROMOCION = "promocion/";
    public static final String PROMOCION = ROOT + KEY_PROMOCION + BUSCAR_ACTIVO;

    public static final String KEY_MAESTRO = "{maestro}/";
    public static final String MAESTRO_COMBO = ROOT + KEY_MAESTRO + BUSCAR_ACTIVO;

    public static final String KEY_BUZON = "buzonsugerencia/persona/{persona}/asuntoComentario/{asunto}/";
    public static final String BUZON = ROOT + KEY_BUZON + AGREGAR;

    public static final String KEY_TRIBAGO = "servicio/tribago";
    public static final String TRIBAGO = ROOT + KEY_TRIBAGO;
    
    public static final String KEY_ORDENVISITA = "visita/";
    public static final String ORDENVISITA = ROOT + KEY_ORDENVISITA + BUSCAR;

    public static final String KEY_ORDENVISITA_CANCELAR = "cancelar/{id}";
    public static final String ORDENVISITA_CANCELAR = ROOT + KEY_ORDENVISITA + KEY_ORDENVISITA_CANCELAR;

    public static final String KEY_GUARDAR_TOKEN = "cliente/token/{movilToken}/{id}";
    public static final String GUARDAR_TOKEN = ROOT + KEY_GUARDAR_TOKEN;

    public static final String SOLICITUD_KEY = "solicitud/";
    public static final String KEY_SOLICITUD = "buscarPorClienteMovil/{id}";
    public static final String SOLICITUD = ROOT + SOLICITUD_KEY + KEY_SOLICITUD;

    public static final String KEY_ETAPA = "etapa/{id}";
    public static final String ETAPAS = ROOT + SOLICITUD_KEY + KEY_ETAPA;

    public static final String KEY_DETALLE_DIAGNOSTICO = "diagnosticovisita/";
    public static final String DETALLE_DIAGNOSTICO = ROOT + KEY_DETALLE_DIAGNOSTICO + BUSCAR;

    public static final String KEY_PRESUPUESTO = "presupuesto/";
    public static final String PRESUPUESTO = ROOT + KEY_PRESUPUESTO + BUSCAR;

}
