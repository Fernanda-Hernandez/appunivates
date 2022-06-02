package com.example.appunivatesfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.appunivatesfe.controller.LinguagemController;
import com.example.appunivatesfe.model.Linguagem;
import com.example.appunivatesfe.tools.Globais;

public class LinguagemActivity extends AppCompatActivity {

                EditText txtNome;
                EditText txtDescricao;
                Linguagem objeto;
                LinguagemController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguagem);

        txtNome = findViewById(R.id.txtNome_linguagem);
        txtDescricao = findViewById(R.id.txtDescricao_linguagem);
    }

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:

                salvar();

            case R.id.action_cancel:

                finish();

        }

        return super.onOptionsItemSelected(item);
    }



    private void salvar(){
        try {
            String nome = txtNome.getText().toString().trim();
            String descricao = txtDescricao.getText().toString().trim();

            if (nome.length() > 30){
                Globais.exibirMensagem(context,"O nome é muito grande");
                return;
            }

            objeto = new Linguagem();
            objeto = setNome(nome);
            objeto = setDescricao(descricao);

            controller = new LinguagemController(context);

            boolean retorno = controller.incluir(objeto);
            if(retorno) {
                Globais.exibirMensagem(context, "Sucesso");
                finish();
            }

        }
    }
}