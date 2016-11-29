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
       
       abrir();      
       
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
           c.setNome("Petrônio Cândido de Lima e Silva");
           c.setCpf("920.489.356-72");
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
