package com.example.gastosapp.ui;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gastosapp.R;
import com.example.gastosapp.dao.GastoDAO;
import com.example.gastosapp.model.Gasto;
import com.example.gastosapp.ui.recyclerview.adapter.GastosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import static com.example.gastosapp.ui.Constantes.CHAVE_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CHAVE_SALVAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_RETORNA_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_RETORNA_SALVAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_SOLICITA_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_SOLICITA_SALVAR_GASTO;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewGastos;
    private FloatingActionButton fabNovoGasto;
    private GastosAdapter adapter;
    private int itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cliqueBotao();
        configuraListView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_SOLICITA_SALVAR_GASTO &&
                resultCode == CODIGO_RETORNA_SALVAR_GASTO &&
                data.hasExtra(CHAVE_SALVAR_GASTO)){
            //salvarGastoFormulario();
            Gasto gasto =(Gasto) data.getSerializableExtra(CHAVE_SALVAR_GASTO);
            new GastoDAO().inseri(gasto);
            adapter.notifyDataSetChanged();
        }

        if (requestCode == CODIGO_SOLICITA_EDITAR_GASTO &&
                resultCode == CODIGO_RETORNA_EDITAR_GASTO &&
                data.hasExtra(CHAVE_EDITAR_GASTO)){
            //salvarGastoFormulario();
            Gasto gasto =(Gasto) data.getSerializableExtra(CHAVE_EDITAR_GASTO);
            new GastoDAO().edita(gasto);
            adapter.notifyDataSetChanged();
        }


    }

    private void cliqueBotao() {
        fabNovoGasto = findViewById(R.id.mainFabNovoGasto);

        fabNovoGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormGastoActivity.class);
                startActivityForResult(intent,CODIGO_SOLICITA_SALVAR_GASTO);
            }
        });
    }

    private void configuraListView() {
        geraGastos(10);

        recyclerViewGastos = findViewById(R.id.mainRecyclerViewGasto);
        recyclerViewGastos.setLayoutManager(new LinearLayoutManager(this));

        final List<Gasto> gastos = new GastoDAO().recuperTodosGastos();
        adapter = new GastosAdapter(gastos);
        recyclerViewGastos.setAdapter(adapter);
//
//        recyclerViewGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Gasto gasto = (Gasto) parent.getItemAtPosition(position);
//                Intent intent = new Intent(getApplicationContext(), FormGastoActivity.class);
//                intent.putExtra(CHAVE_EDITAR_GASTO, gasto);
//                startActivityForResult(intent, CODIGO_SOLICITA_EDITAR_GASTO);
//
//            }
//        });
//
//        recyclerViewGastos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("Tem certeza que deseja excluir?")
//                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                new GastoDAO().remove(position);
//                                adapter.notifyDataSetChanged();
//                            }
//                        })
//                        .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                               return;
//                            }
//                        });
//
//                    builder.create().show();
//                return true;
//            }
//        });

    }

    private void geraGastos(int quantidadeDeGastos){
        for (int i=1; i <= quantidadeDeGastos; i++){
            new GastoDAO().inseri(new Gasto(
                    10,"10/10/2020",
                    "Lanche " + i,
                    "Alimentação",
                    "Dinheiro"
            ));
        }
    }

    private void salvarGastoFormulario() {
        Intent intent = getIntent();
        Gasto gasto = (Gasto)intent.getSerializableExtra("SALVAR_GASTO");
        if(gasto != null){
            new GastoDAO().inseri(gasto);
        }
    }
}