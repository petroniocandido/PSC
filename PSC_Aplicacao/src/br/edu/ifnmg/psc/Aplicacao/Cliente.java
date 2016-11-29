/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Aplicacao;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author petronio
 */
public class Cliente implements Entidade {
    
    private int id;
    private String nome;
    private String cpf;
    private Date dataNascimento;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ErroValidacao {
        if(nome.length() > 250)
            throw new ErroValidacao("O atributo nome deve ter no máximo 250 caracteres!");
        this.nome = nome;
    }

    public String getCpf() {
        return cpf.substring(0, 3) + 
                "." + cpf.substring(3, 6) + 
                "." + cpf.substring(6, 9) + 
                "-" + cpf.substring(9);
    }

    public void setCpf(String cpf) throws ErroValidacao {
        if(cpf.length() > 14)
            throw new ErroValidacao("O atributo cpf deve ter no máximo 14 caracteres!");
        this.cpf = cpf.replace("-", "").replace(".", "");
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nome + " (" + cpf + ')';
    }
    
    
    
}
