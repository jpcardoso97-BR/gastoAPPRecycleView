package com.example.gastosapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gastosapp.R;
import com.example.gastosapp.model.Gasto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewGastos;
    private FloatingActionButton fabNovoGasto;
    private List<Gasto> gastoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gasto gasto = new Gasto(15,"01/10/2020",
                "Almoço",
                "Alimentação",
                "Dinheiro");

        gastoList.add(gasto);

        fabNovoGasto = findViewById(R.id.mainFabNovoGasto);

        fabNovoGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FormGastoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        Gasto gasto = (Gasto)intent.getSerializableExtra("SALVAR_GASTO");
        if(gasto != null){
            gastoList.add(gasto);
        }

        listViewGastos = findViewById(R.id.mainListViewGastos);
        ArrayAdapter<Gasto> adapter = new ArrayAdapter<Gasto>(this,
                android.R.layout.simple_list_item_1,gastoList);
        listViewGastos.setAdapter(adapter);
    }
}