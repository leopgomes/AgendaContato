package com.agendacontato.leandro.agendacontato.util;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Mensagem{

    public static AlertDialog criarAlertDialog (Activity activity){
        final CharSequence[] items = {
                "Editar",
                "Excluir"
        };
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Opções");
        alert.setItems(items, (DialogInterface.OnClickListener)activity);
        return alert.create();
    }

    public static AlertDialog criarDialogConfirmacao(Activity activity){
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Deseja realmente excluir?");
        alert.setPositiveButton("Sim", (DialogInterface.OnClickListener)activity);
        alert.setNegativeButton("Não", (DialogInterface.OnClickListener)activity);

        return alert.create();
    }









}
