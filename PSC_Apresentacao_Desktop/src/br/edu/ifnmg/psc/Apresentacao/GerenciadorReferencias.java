/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Apresentacao;

import br.edu.ifnmg.psc.Aplicacao.ClienteRepositorio;
import br.edu.ifnmg.psc.Aplicacao.ProdutoRepositorio;
import br.edu.ifnmg.psc.Aplicacao.VendaRepositorio;
import br.edu.ifnmg.psc.Persistencia.ClienteDAO;
import br.edu.ifnmg.psc.Persistencia.ProdutoDAO;
import br.edu.ifnmg.psc.Persistencia.VendaDAO;

/**
 *
 * @author petronio
 */
public class GerenciadorReferencias {
    
    private static ClienteRepositorio daoCliente;

    public static ClienteRepositorio getCliente() {
        if(daoCliente == null)
            daoCliente = new ClienteDAO();
        return daoCliente;
    }
    
    private static ProdutoRepositorio daoProduto;

    public static ProdutoRepositorio getProduto() {
        if(daoProduto == null)
            daoProduto = new ProdutoDAO();
        return daoProduto;
    }
    
    private static VendaRepositorio daoVenda;

    public static VendaRepositorio getDaoVenda() {
        if(daoVenda == null)
            daoVenda = new VendaDAO();
        return daoVenda;
    }
    
    
    
    
}

