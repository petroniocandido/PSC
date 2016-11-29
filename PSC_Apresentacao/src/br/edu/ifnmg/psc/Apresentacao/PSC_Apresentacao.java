/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Apresentacao;

import br.edu.ifnmg.psc.Aplicacao.Cliente;
import br.edu.ifnmg.psc.Aplicacao.ClienteRepositorio;
import br.edu.ifnmg.psc.Aplicacao.ErroValidacao;
import br.edu.ifnmg.psc.Persistencia.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author petronio
 */
public class PSC_Apresentacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       // DB.Iniciar();
       // DB.criarConexao();
       
       buscar();      
       
    }
    
    public static void buscar() {
        Cliente filtro = new Cliente();
        
        ClienteRepositorio bd_cliente = new ClienteDAO();
        
        List<Cliente> resultadobusca = bd_cliente.Buscar(filtro);
        
        for(Cliente c : resultadobusca)
            System.out.println(c);
        
    }
    
    
    public static void atualizar() {
        Cliente c;
        
        ClienteRepositorio bd_cliente = new ClienteDAO();
        
        
        c = bd_cliente.Abrir(1);
        
        try {
            c.setNome("Petronio alterado");
        } catch(ErroValidacao e){
            
        }
        
        //System.out.println(c);
        
        bd_cliente.Salvar(c);
        
    }
    
     public static void apagar() {
        Cliente c;
        
        ClienteRepositorio bd_cliente = new ClienteDAO();
        
        
        c = bd_cliente.Abrir(2);
        
        System.out.println(c);
        
        bd_cliente.Apagar(c);
        
    }
    
    public static void abrir() {
        Cliente c;
        
        ClienteRepositorio bd_cliente = new ClienteDAO();
        
        
        c = bd_cliente.Abrir(1);
        
        System.out.println(c);
        
    }
    
    public static void criar() {
        Cliente c = new Cliente();
       
       try {
           c.setNome("Yoshua");
           c.setCpf("101.000.000-00");
           c.setDataNascimento(new Date());
           
           ClienteRepositorio bd_cliente = new ClienteDAO();
           
           bd_cliente.Salvar(c);
           
           System.out.print(c);
       }
       catch(ErroValidacao ex) {
           System.out.println("Aconteceu um erro! :-(") ;
           System.out.println(ex);
       } 
    }
    
}
