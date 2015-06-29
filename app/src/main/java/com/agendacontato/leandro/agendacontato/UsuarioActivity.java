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
import com.agendacontato.leandro.agendacontato.model.Usuario;


public class UsuarioActivity extends ActionBarActivity {

    private EditText edtCadastroNome;
    private EditText edtCadastroLogin;
    private EditText edtCadastroSenha;

    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        usuarioDAO = new UsuarioDAO(this);

        edtCadastroNome = (EditText)findViewById(R.id.edtCadastroNome);
        edtCadastroLogin = (EditText)findViewById(R.id.edtCadastroLogin);
        edtCadastroSenha = (EditText)findViewById(R.id.edtCadastroSenha);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_salvar:
                this.salvarUsuario();

        }


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }

    public void salvarUsuario(){
        String nome = edtCadastroNome.getText().toString();
        String login = edtCadastroLogin.getText().toString();
        String senha = edtCadastroSenha.getText().toString();

        boolean validacao = true;

        if(nome == null || nome.equals("")){
            validacao = false;
            edtCadastroNome.setError(getString(R.string.validaCampos));
        }

        if(login == null || login.equals("")){
            validacao = false;
            edtCadastroLogin.setError(getString(R.string.validaCampos));
        }

        if(senha == null || senha.equals("")){
            validacao = false;
            edtCadastroSenha.setError(getString(R.string.validaCampos));
        }


        if(validacao){
            usuario = new Usuario();

            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);

            if(idusuario > 0){
                usuario.set_id(idusuario);
            }

            long result = usuarioDAO.salvarUsuario(usuario);

            if (result != -1){
                if(idusuario > 0) {
                    Toast msg = Toast.makeText(this, "Usuário atualizado com sucesso!", Toast.LENGTH_SHORT);
                    msg.show();
                }else {
                    Toast msg = Toast.makeText(this, "Usuário Cadastrado com sucesso!", Toast.LENGTH_SHORT);
                    msg.show();
                }
                finish();

            }else {
                Toast msg = Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    }
}
