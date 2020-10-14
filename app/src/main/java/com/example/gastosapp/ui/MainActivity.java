package com.example.gastosapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.gastosapp.ui.Constantes.CHAVE_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CHAVE_SALVAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_RETORNA_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_RETORNA_SALVAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_SOLICITA_SALVAR_GASTO;

public class MainActivity extends AppCompatActivity {
    private ListView listViewGastos;
    private FloatingActionButton fabNovoGasto;
    private ArrayAdapter<Gasto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cliqueBotao();
        configuraListView();
    }

    @Override
    protected void onResume() {
        super.onResume();


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
        listViewGastos = findViewById(R.id.mainListViewGastos);
        adapter = new ArrayAdapter<Gasto>(this,
                android.R.layout.simple_list_item_1,new GastoDAO().recuperTodosGastos());
        listViewGastos.setAdapter(adapter);

        listViewGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gasto gasto = (Gasto) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), FormGastoActivity.class);
                intent.putExtra(CHAVE_EDITAR_GASTO, gasto);
                startActivityForResult(intent, CODIGO_RETORNA_EDITAR_GASTO);
            }
        });
    }

    private void geraGastos(int quantidade){}

    private void salvarGastoFormulario() {
        Intent intent = getIntent();
        Gasto gasto = (Gasto)intent.getSerializableExtra("SALVAR_GASTO");
        if(gasto != null){
            new GastoDAO().inseri(gasto);
        }
    }
}