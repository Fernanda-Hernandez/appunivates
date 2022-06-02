package com.example.appunivatesfe.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appunivatesfe.database.DadosOpenHelper;
import com.example.appunivatesfe.database.Tabelas;
import com.example.appunivatesfe.model.Linguagem;
import com.example.appunivatesfe.tools.Globais;

import java.util.ArrayList;

public class LinguagemController {

    private SQLiteDatabase conexao;
    private Context context;


    public  LinguagemController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }


    public Linguagem buscar(int id){
        try{

            Linguagem objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_LINGUAGENS);
            sql.append(" WHERE id = '"+ id +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new Linguagem();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                objeto.setDescricao(resultado.getString(resultado.getColumnIndexOrThrow("descricao")));
            }

            return objeto;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return null;
        }
    }

    public boolean incluir(Linguagem objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());
            valores.put("descricao", objeto.getDescricao());

            conexao.insertOrThrow(Tabelas.TB_LINGUAGENS, null, valores);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean alterar(Linguagem objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tabelas.TB_LINGUAGENS, valores, "id = ?" , parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean excluir(Linguagem objeto){
        try{

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.delete(Tabelas.TB_LINGUAGENS, "id = ?", parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public ArrayList<Linguagem> lista(){

        ArrayList<Linguagem> listagem = new ArrayList<>();
        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_LINGUAGENS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToFirst()){

                Linguagem objeto;
                do{
                    objeto = new Linguagem();
                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                    objeto.setDescricao(resultado.getString(resultado.getColumnIndexOrThrow("descricao")));

                    listagem.add(objeto);

                }while (resultado.moveToNext());

            }

            return listagem;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return listagem;
        }
    }

}
