package com.agendacontato.leandro.agendacontato.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.agendacontato.leandro.agendacontato.R;
import com.agendacontato.leandro.agendacontato.model.Contato;


import java.util.List;


public class ContatoAdapter extends BaseAdapter {

    private Context context;
    private List<Contato> lista;

    public ContatoAdapter(Context contexto, List<Contato> contatos){
        this.context = contexto;
        this.lista = contatos;

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contato contato = lista.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.contatos, null);
        }

        TextView txt = (TextView)view.findViewById(R.id.txtLista);
        txt.setText(contato.getNome());

        return view;
    }
}
