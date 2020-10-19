package com.example.gastosapp.ui.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gastosapp.R;
import com.example.gastosapp.model.Gasto;

import java.util.List;

public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.ViewHolder> {

    private List<Gasto> gastoList;

    public GastosAdapter(List<Gasto> gastoList) {
        this.gastoList = gastoList;
    }

    @NonNull
    @Override
    public GastosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_gasto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GastosAdapter.ViewHolder holder, int position) {
        Gasto gasto =  gastoList.get(position);
        holder.vicula(gasto);
    }

    @Override
    public int getItemCount() {
        return gastoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textValor;
        private TextView textDescricao;
        private TextView textData;
        private ImageView imageViewCategoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textValor = itemView.findViewById(R.id.itemTextValor);
            textDescricao = itemView.findViewById(R.id.itemTextDescricao);
            textData = itemView.findViewById(R.id.itemTextData);
            imageViewCategoria = itemView.findViewById(R.id.itemImageViewGasto);
        }

        public void vicula(Gasto gasto) {
            textValor.setText(gasto.getValorComoString());
            textDescricao.setText(gasto.getDescricao());
            textData.setText(gasto.getData());
            imageViewCategoria.setImageResource(R.drawable.ic_animais);
        }
    }
}
