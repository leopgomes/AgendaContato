package com.agendacontato.leandro.agendacontato.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String BANCO_DADOS =  "agenda";
    private static final int VERSAO = 1;


    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(_id integer primary key autoincrement," +
                    "nome text not null, login text not null, senha text not null)");

        db.execSQL("create table contato(_id integer primary key autoincrement,"+
                   "nome text not null, telefone text not null, email text not null, dtnasc TEXT not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";

        public static final String[] COLUNAS = new String[]{
                _ID, NOME, LOGIN, SENHA

        };
    }

    public static class Contatos {
        public static final String TABELA = "contato";
        public static final String _ID = "_id";
        public static final String NOME = "nome";
        public static final String TELEFONE = "telefone";
        public static final String EMAIL = "email";
        public static final String DTNASC = "dtnasc";


        public static final String[] COLUNAS = new String[]{
                _ID, NOME, TELEFONE, EMAIL, DTNASC

        };
    }
}
