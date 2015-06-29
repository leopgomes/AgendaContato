package com.agendacontato.leandro.agendacontato.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agendacontato.leandro.agendacontato.model.Usuario;

public class UsuarioDAO {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;


    public UsuarioDAO(Context context){
        dataBaseHelper = new DataBaseHelper(context);

    }

    private SQLiteDatabase getDatabase(){
        if (database == null){
            database = dataBaseHelper.getWritableDatabase();
        }

        return database;
    }

    private Usuario criarUsuario(Cursor cursor){
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuarios.SENHA))
        );
        return model;
    }

    public long salvarUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Usuarios.NOME, usuario.getNome());
        valores.put(DataBaseHelper.Usuarios.LOGIN, usuario.getLogin());
        valores.put(DataBaseHelper.Usuarios.SENHA, usuario.getSenha());

        if(usuario.get_id()!=null){
            return getDatabase().update(DataBaseHelper.Usuarios.TABELA, valores, "_id = ?", new String[]{usuario.get_id().toString()});
        }
        return  getDatabase().insert(DataBaseHelper.Usuarios.TABELA, null, valores);
    }

    public boolean removerUsuario(int id){
        return getDatabase().delete(DataBaseHelper.Usuarios.TABELA, "_id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Usuario buscarUsuario(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuarios.TABELA, DataBaseHelper.Usuarios.COLUNAS,
                "_id = ?", new String[]{Integer.toString(id)}, null, null,null);

        if(cursor.moveToNext()){
            Usuario model = criarUsuario(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public void fechar(){
        dataBaseHelper.close();
        database = null;
    }

    public boolean logar(String usuario, String senha){
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuarios.TABELA, null, "LOGIN = ? AND SENHA = ?", new String[]{usuario, senha},null, null, null);

        if(cursor.moveToFirst()){
            return true;
        }

        return false;
    }

}
