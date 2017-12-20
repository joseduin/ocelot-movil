package ocelot.app.restApi;

/**
 * Created by Jose on 19/11/2017.
 */

public final class JsonKeys {

    public final static String estatus = "estatus";

    public final static String cliente = "persona";
    public final static String cliente_id = "id";
    public final static String cliente_nombre = "nombre";
    public final static String cliente_identificacion = "identificacion";
    public final static String cliente_estatus = "estatus";
    public final static String cliente_password = "password";
    public final static String cliente_email = "email";
    public final static String cliente_tipoPersona = "tipoPersona";
    public final static String cliente_rol = "rol";

    public final static String servicio = "servicio";
    public final static String servicio_id = "id";
    public final static String servicio_titulo = "titulo";
    public final static String servicio_imagen = "imagenServicio";
    public final static String servicio_descripcion = "descripcion";
    public final static String servicio_costo = "costo";

    public final static String promocion = "promocion";
    public final static String promocion_id = "id";
    public final static String promocion_titulo = "titulo";
    public final static String promocion_descripcion = "descripcion";
    public final static String promocion_inicio = "fecha_inicio";
    public final static String promocion_imagen = "imagenServicio";
    public final static String promocion_caducidad = "fecha_caducidad";
    public final static String promocion_descuento = "descuento_procentaje";
    public final static String promocion_servicio = "promocionServicios";
    public final static String promocion_servicio_id = "servicio_id";
    public final static String promocion_costo = "costo";

    public final static String maestro_id = "id";
    public final static String maestro_descripcion = "descripcion";
    public final static String categoria = "categoria";
    public final static String categoria_tipo_servicios = "tipoServicios";

    public final static String visita = "visita";
    public final static String visita_tecnico = "trabajadorDes";
    public final static String visita_tecnico_nombre = "nombre";
    public final static String visita_tecnico_telefono = "telefono";
    public final static String visita_lugar = "solicitudDes";
    public final static String visita_lugar_inmueble = "inmuebleDes";
    public final static String visita_lugar_inmueble_direccion = "direccion";
    public final static String visita_lugar_inmueble_tipo_inmueble = "tipoInmuebleDes";
    public final static String visita_lugar_inmueble_tipo_inmueble_descripcion = "descripcion";
    public final static String visita_lugar_inmueble_sector = "sectorDes";
    public final static String visita_lugar_inmueble_sector_descripcion = "descripcion";
    public final static String visita_lugar_inmueble_sector_parroquia = "parroquiaDes";
    public final static String visita_lugar_inmueble_sector_parroquia_descripcion = "descripcion";
    public final static String visita_fecha = "fechaVisita";

    public final static String mensaje = "message";

    public final static String solicitud = "solicitud";
    public final static String solicitud_id = "id";
    public final static String solicitud_estatus = "descripcionEstatus";
    public final static String solicitud_servicios = "solicitudServicios";
    public final static String solicitud_servicios_servicio = "servicioDes";
    public final static String solicitud_servicios_servicio_titulo = "titulo";
    public final static String solicitud_servicios_servicio_imagen = "imagenServicio";

    public final static String etapa = "etapas";
    public final static String etapa_id = "id";
    public final static String etapa_titulo = "titulo";
    public final static String etapa_etapa = "etapa";
    public final static String etapa_estatus = "estatus";

    public final static String diagnostico = "diagnosticoVisita";
    public final static String diagnostico_visita = "visita";
    public final static String diagnostico_inmueble = "inmuebleDes";
    public final static String diagnostico_inmueble_direccion = "direccion";
    public final static String diagnostico_inmueble_tipo_inmueble = "tipoInmuebleDes";
    public final static String diagnostico_inmueble_tipo_inmueble_descripcion = "descripcion";
    public final static String diagnostico_inmueble_sector = "sectorDes";
    public final static String diagnostico_inmueble_sector_descripcion = "descripcion";
    public final static String diagnostico_inmueble_parroquia = "parroquiaDes";
    public final static String diagnostico_inmueble_parroquia_descripcion = "descripcion";
    public final static String diagnostico_cliente ="clienteDes";
    public final static String diagnostico_cliente_nombre = "nombre";
    public final static String diagnostico_cliente_direccion = "direccion";
    public final static String diagnostico_cliente_telefono = "telefono";
    public final static String diagnostico_condicion = "condicionDiagnosticos";
    public final static String diagnostico_condicion_caracteristica ="caracteristicaDes";
    public final static String diagnostico_condicion_caracteristica_descripcion = "padre_descripcion";
    public final static String diagnostico_condicion_caracteristica_condicion ="descripcion";
    public final static String diagnostico_condicion_detalle ="detalleDiagnosticoVisitas";
    public final static String diagnostico_condicion_detalle_area = "area";

    public final static String presupuesto = "presupuesto";
    public final static String presupuesto_fecha = "fecha_creacion";
    public final static String presupuesto_servicio = "descripcion";
    public final static String presupuesto_monto = "montoTotal";
    public final static String presupuesto_detalle = "detallePresupuestos";
    public final static String presupuesto_detalle_costo ="costo";
    public final static String presupuesto_detalle_area = "area";

}
