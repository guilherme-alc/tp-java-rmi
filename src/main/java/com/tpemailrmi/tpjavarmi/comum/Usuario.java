package com.tpemailrmi.tpjavarmi.comum;

public class Usuario {
    public String nome;
    public String email;
    public String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return  senha;
    }
}
