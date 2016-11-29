/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Persistencia;

import br.edu.ifnmg.psc.Aplicacao.Cliente;
import br.edu.ifnmg.psc.Aplicacao.ClienteRepositorio;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author petronio
 */
public class ClienteDAO implements ClienteRepositorio {

    Connection conn;

    public ClienteDAO() {
        try {
            DB.Iniciar();
            conn = DB.criarConexao();
        } catch(ClassNotFoundException ex){ 
            System.out.println("Driver não encontrado!");
        } catch(SQLException ex){ 
            System.out.println("Usuário/senha errados!");
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    
    
    @Override
    public Cliente Abrir(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Salvar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Apagar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente Abrir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> Buscar(Cliente filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
