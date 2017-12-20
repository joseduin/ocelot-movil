package ocelot.app.adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import ocelot.app.CatalogoDetalleActivity;
import ocelot.app.R;
import ocelot.app.animator.RoundedTransformation;
import ocelot.app.modelo.Catalogo;
import ocelot.app.restApi.ConstantesRestApi;
import ocelot.app.utils.Convertidor;
import ocelot.app.utils.MensajeUtils;

/**
 * Created by Jose on 23/10/2017.
 */

public class CatalogoAdaptador extends RecyclerView.Adapter<CatalogoAdaptador.CatalogoViewHolder> {

    List<Catalogo> catalogoCards;
    Context context;

    public CatalogoAdaptador(List<Catalogo> catalogoCards, Context context) {
        this.catalogoCards = catalogoCards;
        this.context = context;
    }

    @Override
    public CatalogoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_catalogo, parent, false);
        CatalogoViewHolder pvh = new CatalogoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CatalogoViewHolder holder, int position) {
        Catalogo catalogo = catalogoCards.get(position);

        holder.tituloCatalogo.setText( catalogo.getTitulo() );
        holder.costoCatalogo.setText( String.valueOf(catalogo.getCosto()) + MensajeUtils.MONEDA);
        holder.descripcionCatalogo.setText( Convertidor.descripcionCut(catalogo.getDescripcion()) );

        final ProgressBar progressView = holder.progressCatalogo;
        Picasso.with(context)
                .load(ConstantesRestApi.WEB_DESKTOP + catalogo.getImagenServicio())
                .transform(new RoundedTransformation(15, 0))
                .into(holder.fotoCatalogo, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.fotoCatalogo.getLayoutParams().width = holder.v.getLayoutParams().width;

        if (catalogo.getFecha_caducidad() == null) {
            holder.fechaPromocion.setVisibility(View.GONE);
            holder.descuentoCatalogo.setVisibility(View.GONE);
        } else {
            holder.fechaPromocion.setVisibility(View.VISIBLE);
            Log.d("FECHAS", catalogo.getFecha_inicio().toString() +" "+ catalogo.getFecha_caducidad().toString());
            holder.fechaPromocion.setText(
                    Convertidor.catalogoFechasPrint(catalogo.getFecha_inicio(), catalogo.getFecha_caducidad()) );

            holder.descuentoCatalogo.setVisibility(View.VISIBLE);
            holder.descuentoCatalogo.setText( Convertidor.descuentoPrint(catalogo.getPorcentaje()) );
        }

        final int id = catalogo.getId();
        holder.catalogo_contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irCatalogoDetalle(id);
            }
        });
    }

    private void irCatalogoDetalle(int id) {
        Intent intent = new Intent(context, CatalogoDetalleActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return catalogoCards.size();
    }

    public static class CatalogoViewHolder extends RecyclerView.ViewHolder {
        TextView tituloCatalogo, descripcionCatalogo, costoCatalogo, fechaPromocion, descuentoCatalogo;
        ImageView fotoCatalogo;
        CardView catalogo_contenedor;
        ProgressBar progressCatalogo;
        private View v;

        CatalogoViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            catalogo_contenedor = (CardView) itemView.findViewById(R.id.catalogo_contenedor);
            tituloCatalogo = (TextView) itemView.findViewById(R.id.tituloCatalogo);
            descripcionCatalogo = (TextView) itemView.findViewById(R.id.descripcionCatalogo);
            costoCatalogo = (TextView) itemView.findViewById(R.id.costoCatalogo);
            fotoCatalogo = (ImageView) itemView.findViewById(R.id.fotoCatalogo);
            fechaPromocion = (TextView) itemView.findViewById(R.id.fechaPromocion);
            descuentoCatalogo = (TextView) itemView.findViewById(R.id.descuentoCatalogo);
            progressCatalogo = (ProgressBar) itemView.findViewById(R.id.progressCatalogo);
        }
    }

}
