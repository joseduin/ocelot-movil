package ocelot.app.adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import ocelot.app.R;
import ocelot.app.ServicioDetalleActivity;
import ocelot.app.animator.RoundedTransformation;
import ocelot.app.modelo.Solicitud;
import ocelot.app.restApi.ConstantesRestApi;

/**
 * Created by Jose on 23/10/2017.
 */

public class SolicitudAdaptador extends RecyclerView.Adapter<SolicitudAdaptador.SolicitudViewHolder> {

    List<Solicitud> serviciosCards;
    Context context;

    public SolicitudAdaptador(List<Solicitud> serviciosCards, Context context) {
        this.serviciosCards = serviciosCards;
        this.context = context;
    }

    @Override
    public SolicitudViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_servicios, parent, false);
        SolicitudViewHolder pvh = new SolicitudViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SolicitudViewHolder holder, int position) {
        Solicitud servicio = serviciosCards.get(position);

        holder.etapaServicio.setText( servicio.getEtapa() );

        final ProgressBar progressView = holder.progressCatalogo;
        Picasso.with(context)
                .load(ConstantesRestApi.WEB_DESKTOP + servicio.getFoto())
                .transform(new RoundedTransformation(15, 0))
                .into(holder.fotoServicio, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.fotoServicio.getLayoutParams().width = holder.v.getLayoutParams().width;
        holder.descripcionServicio.setText( servicio.getDescripcion() );

        final int id = servicio.getId();
        holder.servicio_contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irServicioDetalle(id);
            }
        });
    }

    private void irServicioDetalle(int id) {
        Intent intent = new Intent(context, ServicioDetalleActivity.class);
        intent.putExtra(context.getResources().getString(R.string.detalle_servicio), id);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return serviciosCards.size();
    }

    public static class SolicitudViewHolder extends RecyclerView.ViewHolder {
        TextView etapaServicio, descripcionServicio;
        ImageView fotoServicio;
        CardView servicio_contenedor;
        ProgressBar progressCatalogo;
        private View v;

        SolicitudViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            servicio_contenedor = (CardView) itemView.findViewById(R.id.servicio_contenedor);
            etapaServicio = (TextView) itemView.findViewById(R.id.etapaServicio);
            descripcionServicio = (TextView) itemView.findViewById(R.id.descripcionServicio);
            fotoServicio = (ImageView) itemView.findViewById(R.id.fotoServicio);
            progressCatalogo = (ProgressBar) itemView.findViewById(R.id.progressSolicitud);
        }
    }

}
