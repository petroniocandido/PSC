/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Apresentacao;

import br.edu.ifnmg.psc.Aplicacao.Cliente;
import br.edu.ifnmg.psc.Aplicacao.ErroValidacao;
import br.edu.ifnmg.psc.Persistencia.*;
import java.sql.SQLException;

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
       
       Cliente c = new Cliente();
       
       try {
           c.setCpf("920.489.356-723");
           System.out.print("Rodou tudo ok!");
       }
       catch(ErroValidacao ex) {
           System.out.print("Aconteceu um erro! :-(") ;
           System.out.print(ex);
       } 
       
       
    }
    
}
