package com.agendacontato.leandro.agendacontato;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.agendacontato.leandro.agendacontato.dao.UsuarioDAO;


public class LoginActivity extends ActionBarActivity {

    private EditText edtLogin;
    private EditText edtSenha;

    private UsuarioDAO helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = (EditText)findViewById(R.id.edtLogin);
        edtSenha = (EditText)findViewById(R.id.edtSenha);

        helper = new UsuarioDAO(this);
    }

    public void onClickCadastrar(View v) {
        Intent i = new Intent(this,  UsuarioActivity.class);
        startActivity(i);

    }

    public void onClickAcessar(View v) {
        String usuario = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        boolean validacao = true;

        if(usuario == null || usuario.equals("")){
            validacao = false;
            edtLogin.setError(getString(R.string.validaCampos));
        }

        if(senha == null || senha.equals("")){
            validacao = false;
            edtSenha.setError(getString(R.string.validaCampos));
        }

        if(validacao) {
            if(helper.logar(usuario, senha)){
                Intent i = new Intent(this, ListarContatoActivity.class);
                startActivity(i);
                finish();
            }else{
                Toast msg = Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT);
                msg.show();

            }

        }

    }




}
