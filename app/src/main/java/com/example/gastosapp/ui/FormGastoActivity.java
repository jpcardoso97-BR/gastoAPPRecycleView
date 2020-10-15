package com.example.gastosapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gastosapp.R;
import com.example.gastosapp.model.Gasto;

import static com.example.gastosapp.ui.Constantes.CHAVE_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CHAVE_SALVAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_RETORNA_EDITAR_GASTO;
import static com.example.gastosapp.ui.Constantes.CODIGO_RETORNA_SALVAR_GASTO;

public class FormGastoActivity extends AppCompatActivity {

    private EditText editValor;
    private EditText editData;
    private EditText editDescricao;
    private EditText editCategoria;
    private EditText editFormaPagamento;
    private Button buttonSalvar;
    private boolean eFormularioEdicao = false;

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

                // Aqui envia um objeto para edição de um gasto
                if (eFormularioEdicao){
                    intent.putExtra(CHAVE_EDITAR_GASTO, gasto);
                    setResult(CODIGO_RETORNA_EDITAR_GASTO,intent);
                }
                // Aqui envia um objeto para salvar um novo gasto
                else{
                    intent.putExtra(CHAVE_SALVAR_GASTO, gasto);
                    setResult(CODIGO_RETORNA_SALVAR_GASTO, intent);
                }

                finish();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_EDITAR_GASTO)){

            eFormularioEdicao = true;

            Gasto gasto = (Gasto)intent.getSerializableExtra(CHAVE_EDITAR_GASTO);
            //Carregando dados do objeto no formulário
            editValor.setText(gasto.getValorComoString());
            editData.setText(gasto.getData());
            editDescricao.setText(gasto.getDescricao());
            editCategoria.setText(gasto.getCategoria());
            editFormaPagamento.setText(gasto.getFormaPagamento());
        }
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