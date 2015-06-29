package com.agendacontato.leandro.agendacontato;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.agendacontato.leandro.agendacontato.dao.ContatoDAO;
import com.agendacontato.leandro.agendacontato.model.Contato;

import java.util.Calendar;


public class CadContatoActivity extends ActionBarActivity {

    private EditText edtCadContatoNome;
    private EditText edtCadContatoTelefone;
    private EditText edtCadContatoEmail;
    private EditText edtCadContatoDtNasc;

    private ContatoDAO contatoDAO;
    private Contato contato;
    private int idcontato;

    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_contato);

        idcontato = getIntent().getIntExtra("ID_CONTATO",0);

        if(idcontato > 0){
            Contato model = contatoDAO.buscarContato(idcontato);
            edtCadContatoNome.setText(model.getNome());
            edtCadContatoTelefone.setText(model.getTelefone());
            edtCadContatoEmail.setText(model.getEmail());
            edtCadContatoDtNasc.setText(model.getDtnasc());
            //setTitle("Atualizar contato");
        }

        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);


        contatoDAO = new ContatoDAO(this);

        edtCadContatoNome = (EditText)findViewById(R.id.edtContatoNome);
        edtCadContatoTelefone = (EditText)findViewById(R.id.edtContatoTelefone);
        edtCadContatoEmail = (EditText)findViewById(R.id.edtContatoEmail);
        edtCadContatoDtNasc = (EditText)findViewById(R.id.edtContatoDtNasc);

        edtCadContatoDtNasc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        edtCadContatoDtNasc.setText(new StringBuilder()
                .append(mDay).append("/").append(mMonth + 1).append("/")
                .append(mYear));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cad_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_cad_salvar:
                this.salvarContato();
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        contatoDAO.fechar();
        super.onDestroy();
    }

    public void salvarContato(){
        String nome = edtCadContatoNome.getText().toString();
        String telefone = edtCadContatoTelefone.getText().toString();
        String email = edtCadContatoEmail.getText().toString();
        String dtnasc = edtCadContatoDtNasc.getText().toString();


        boolean validacao = true;

        if(nome == null || nome.equals("")){
            validacao = false;
            edtCadContatoNome.setError(getString(R.string.validaCampos));
        }

        if(telefone == null || telefone.equals("")){
            validacao = false;
            edtCadContatoTelefone.setError(getString(R.string.validaCampos));
        }

        if(email == null || email.equals("")){
            validacao = false;
            edtCadContatoEmail.setError(getString(R.string.validaCampos));
        }

        if(dtnasc == null || dtnasc.equals("")){
            validacao = false;
            edtCadContatoDtNasc.setError(getString(R.string.validaCampos));
        }


        if(validacao){
            contato = new Contato();

            contato.setNome(nome);
            contato.setTelefone(telefone);
            contato.setEmail(email);
            contato.setDtnasc(dtnasc);

            if(idcontato > 0){
                contato.set_id(idcontato);
            }

            long result = contatoDAO.salvarContato(contato);

            if (result != -1){
                if(idcontato > 0) {
                    Toast msg = Toast.makeText(this, "Contato atualizado com sucesso!", Toast.LENGTH_SHORT);
                    msg.show();
                }else {
                    Toast msg = Toast.makeText(this, "Contato Cadastrado com sucesso!", Toast.LENGTH_SHORT);
                    msg.show();
                }
                finish();
                Intent i = new Intent(this, ListarContatoActivity.class);
                startActivity(i);


            }else {
                Toast msg = Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    }

}
