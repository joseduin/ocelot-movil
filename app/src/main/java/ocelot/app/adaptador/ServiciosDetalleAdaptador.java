package ocelot.app.adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ocelot.app.Detalle_Diagnostico;
import ocelot.app.Detalles_Ejecucion;
import ocelot.app.OrdenVisitaActivity;
import ocelot.app.PresupuestoActivity;
import ocelot.app.R;
import ocelot.app.RegistrarReclamoActivity;
import ocelot.app.ValoracionActivity;
import ocelot.app.modelo.Etapa;
import ocelot.app.utils.IrA;

/**
 * Created by Jose on 23/10/2017.
 */

public class ServiciosDetalleAdaptador extends RecyclerView.Adapter<ServiciosDetalleAdaptador.ServiciosDetalleViewHolder> {

    private List<Etapa> etapas;
    private Context context;

    public ServiciosDetalleAdaptador(List<Etapa> etapas, Context context) {
        this.etapas = etapas;
        this.context = context;
    }

    @Override
    public ServiciosDetalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_servicios_detalle, parent, false);
        ServiciosDetalleViewHolder pvh = new ServiciosDetalleViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ServiciosDetalleViewHolder holder, int position) {
        final Etapa etapa = etapas.get(position);

        holder.descripcionDetalleServicio.setText( etapa.getTitulo() );
        holder.iconoDetalleServicio.setImageResource( etapa.getIcono() );

        holder.servicio_detalle_contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irEtapa(etapa);
            }
        });
    }

    private void irEtapa(Etapa etapa) {
        switch (etapa.getId()) {
            case 1:
                IrA.vista(context, OrdenVisitaActivity.class);
                break;
            case 2:
                IrA.vista(context, Detalle_Diagnostico.class);
                break;
            case 3:
                IrA.vista(context, PresupuestoActivity.class);
                break;
            case 4:
                IrA.vista(context, Detalles_Ejecucion.class);
                break;
            case 5:
                IrA.vista(context, ValoracionActivity.class);
                break;
            case 6:
                IrA.vista(context, RegistrarReclamoActivity.class);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return etapas.size();
    }

    public static class ServiciosDetalleViewHolder extends RecyclerView.ViewHolder {
        TextView descripcionDetalleServicio;
        CardView servicio_detalle_contenedor;
        ImageView iconoDetalleServicio;

        ServiciosDetalleViewHolder(View itemView) {
            super(itemView);
            servicio_detalle_contenedor = (CardView) itemView.findViewById(R.id.servicio_detalle_contenedor);
            descripcionDetalleServicio = (TextView) itemView.findViewById(R.id.descripcionDetalleServicio);
            iconoDetalleServicio = (ImageView) itemView.findViewById(R.id.iconoDetalleServicio);
        }
    }

}
