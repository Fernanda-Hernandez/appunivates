package com.example.appunivatesfe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appunivatesfe.adapter.LinguagemAdapter;
import com.example.appunivatesfe.controller.LinguagemController;
import com.example.appunivatesfe.model.Linguagem;
import com.example.appunivatesfe.tools.Globais;

import java.util.ArrayList;


public class ListaLinguagemActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtDescricao;
    ListView ltvLista;
    Button btnSalvar;
    ArrayList<Linguagem> listagem;
    LinguagemAdapter adapter;
    Context context;
    Linguagem objeto;
    LinguagemController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_linguagem);
        context = ListaLinguagemActivity.this;

        txtNome = findViewById(R.id.txtNome_linguagem);
        txtDescricao = findViewById(R.id.txtDescricao_linguagem);
        ltvLista = findViewById(R.id.ltvLista_linguagem);
        btnSalvar = findViewById(R.id.btnSalvar_linguagem);

        listagem = new ArrayList<>();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = txtNome.getText().toString().trim();
                String descricao = txtDescricao.getText().toString().trim();

                if(!nome.equals("") && !descricao.equals("")) {

                    objeto = new Linguagem();
                    objeto.setId(1);
                    objeto.setNome(nome);
                    objeto.setDescricao(descricao);

                    LinguagemController controller = new LinguagemController(context);
                    boolean retorno = controller.incluir(objeto);
                    if (retorno) {
                        Globais.exibirMensagem(context, "Sucesso");
                    }

                    atualizarlista();
                }


                    //listagem.add(objeto);
                    //adapter = new LinguagemAdapter(context, listagem);
                    //ltvLista.setAdapter(adapter);


                }

            }

        );

    }
        protected void onResume() {
            super.onResume();
            atualizarlista();
        }

        private void atualizarlista(){
            try {
                txtNome.setText("");
                txtDescricao.setText("");
                txtNome.requestFocus();

                controller = new LinguagemController(context);
                listagem = controller.lista();

                adapter = new LinguagemAdapter(context,listagem);
                ltvLista.setAdapter(adapter);

            } catch (Exception ex) {

              }

        }
}
