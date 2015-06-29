package com.agendacontato.leandro.agendacontato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.agendacontato.leandro.agendacontato.model.Contato;



import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public ContatoDAO(Context context) {
        dataBaseHelper = new DataBaseHelper(context);

    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();
        }

        return database;
    }


    private Contato criarContato(Cursor cursor){
        Contato model = new Contato(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Contatos._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Contatos.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Contatos.TELEFONE)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Contatos.EMAIL)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Contatos.DTNASC))
        );
        return model;
    }

    public List<Contato> listarContato(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Contatos.TABELA,DataBaseHelper.Contatos.COLUNAS,
                                            null, null, null, null, null);

        List<Contato> contatos = new ArrayList<Contato>();
        while (cursor.moveToNext()){
            Contato model = criarContato(cursor);
            contatos.add(model);
        }
        cursor.close();
        return contatos;
    }


    public long salvarContato(Contato contato){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Contatos.NOME, contato.getNome());
        valores.put(DataBaseHelper.Contatos.TELEFONE, contato.getTelefone());
        valores.put(DataBaseHelper.Contatos.EMAIL, contato.getEmail());
        valores.put(DataBaseHelper.Contatos.DTNASC, contato.getDtnasc());

        if(contato.get_id()!=null){
            return getDatabase().update(DataBaseHelper.Contatos.TABELA, valores, "_id = ?", new String[]{contato.get_id().toString()});
        }
        return  getDatabase().insert(DataBaseHelper.Contatos.TABELA, null, valores);
    }

    public boolean removerContato(int id){
        return getDatabase().delete(DataBaseHelper.Contatos.TABELA, "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Contato buscarContato(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Contatos.TABELA, DataBaseHelper.Contatos.COLUNAS,
                "_id = ?", new String[]{Integer.toString(id)}, null, null,null);

        if(cursor.moveToNext()){
            Contato model = criarContato(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public void fechar(){
        dataBaseHelper.close();
        database = null;
    }


}

