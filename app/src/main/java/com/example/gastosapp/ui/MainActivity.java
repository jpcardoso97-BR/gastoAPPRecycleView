package com.example.gastosapp.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gastosapp.R;
import com.example.gastosapp.dao.GastoDAO;
import com.example.gastosapp.model.Gasto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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
        if (requestCode == 1 && resultCode == 2 && data.hasExtra("SALVAR_GASTO")){
            //salvarGastoFormulario();
            Gasto gasto =(Gasto) data.getSerializableExtra("SALVAR_GASTO");
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
                startActivityForResult(intent,1);
            }
        });
    }

    private void configuraListView() {
        listViewGastos = findViewById(R.id.mainListViewGastos);
        adapter = new ArrayAdapter<Gasto>(this,
                android.R.layout.simple_list_item_1,new GastoDAO().recuperTodosGastos());
        listViewGastos.setAdapter(adapter);
    }

    private void salvarGastoFormulario() {
        Intent intent = getIntent();
        Gasto gasto = (Gasto)intent.getSerializableExtra("SALVAR_GASTO");
        if(gasto != null){
            new GastoDAO().inseri(gasto);
        }
    }
}