package com.agendacontato.leandro.agendacontato;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.agendacontato.leandro.agendacontato.adapter.ContatoAdapter;
import com.agendacontato.leandro.agendacontato.dao.ContatoDAO;
import com.agendacontato.leandro.agendacontato.model.Contato;
import com.agendacontato.leandro.agendacontato.util.Mensagem;

import java.util.List;


public class ListarContatoActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView lista;
    private List<Contato> contatoList;
    private ContatoAdapter contatoAdapter;
    private ContatoDAO contatoDAO;

    private int idposicao;

    private AlertDialog alertDialog, alertConfirmacao;


    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = contatoList.get(idposicao).get_id();

        switch (which){
            case 0:
                Intent intent = new Intent(this, CadContatoActivity.class);
                intent.putExtra("ID_CONTATO", id);
                startActivity(intent);
                break;
            case 1:
                alertConfirmacao.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                contatoList.remove(idposicao);
                contatoDAO.removerContato(id);
                lista.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertConfirmacao.dismiss();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idposicao = position;
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contato);

        alertDialog = Mensagem.criarAlertDialog(this);
        alertConfirmacao = Mensagem.criarDialogConfirmacao(this);

        contatoDAO = new ContatoDAO(this);
        contatoList = contatoDAO.listarContato();
        contatoAdapter = new ContatoAdapter(this, contatoList);

        lista = (ListView)findViewById(R.id.listaContatos);
        lista.setAdapter(contatoAdapter);

        lista.setOnItemClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_cadContato:
                Intent i = new Intent(this,  CadContatoActivity.class);
                startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }


}
