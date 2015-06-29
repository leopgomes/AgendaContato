package com.agendacontato.leandro.agendacontato.model;


public class Contato {

    private Integer _id;
    private String nome;
    private String telefone;
    private String email;
    private String dtnasc;

    public Contato() {
    }

    public Contato(Integer id, String nome, String telefone, String email, String dtnasc) {
        this._id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dtnasc = dtnasc;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(String dtnasc) {
        this.dtnasc = dtnasc;
    }
}
