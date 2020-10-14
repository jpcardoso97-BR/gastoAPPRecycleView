package com.example.gastosapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gastosapp.R;
import com.example.gastosapp.model.Gasto;

public class FormGastoActivity extends AppCompatActivity {

    private EditText editValor;
    private EditText editData;
    private EditText editDescricao;
    private EditText editCategoria;
    private EditText editFormaPagamento;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gasto);

        carregaCampos();

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gasto gasto =  pegaGastoDoFormulario();
                Intent intent = new Intent(FormGastoActivity.this,
                        MainActivity.class);
                intent.putExtra("SALVAR_GASTO", gasto);
                setResult(2, intent);
                finish();
            }
        });
    }

    private Gasto pegaGastoDoFormulario() {
        double valor = Double.parseDouble(editValor.getText().toString());
        String data = editData.getText().toString();
        String descricao = editDescricao.getText().toString();
        String categoria = editCategoria.getText().toString();
        String formaPagamento = editFormaPagamento.getText().toString();

        Gasto gasto = new Gasto(valor,data,descricao,categoria,formaPagamento);

        return gasto;
    }

    private void carregaCampos() {
        editValor = findViewById(R.id.formEditValor);
        editData = findViewById(R.id.formEditData);
        editDescricao = findViewById(R.id.formEditDescricao);
        editCategoria = findViewById(R.id.formEditCategoria);
        editFormaPagamento = findViewById(R.id.formEditFormaPagamento);
        buttonSalvar = findViewById(R.id.formButtonSalvar);
    }
}