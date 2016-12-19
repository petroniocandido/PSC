/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Persistencia;

import br.edu.ifnmg.psc.Aplicacao.Produto;
import br.edu.ifnmg.psc.Aplicacao.ProdutoRepositorio;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements ProdutoRepositorio {

    public ProdutoDAO() {
        setConsultaAbrir("select id, nome, preco from produtos where id = ?");
        setConsultaApagar("delete from produtos where id = ?");
        setConsultaInserir("insert into produtos(nome,preco) values(?,?)");
        setConsultaAlterar("update produtos set nome = ?, preco = ? where id = ?");
    }
  
    
    @Override
    protected Produto preencheObjeto(ResultSet resultado) {
        try {
            Produto tmp = new Produto();
            tmp.setId(resultado.getInt(1));
            tmp.setNome(resultado.getString(2));
            tmp.setPreco(resultado.getBigDecimal(3));
            
            return tmp;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    protected void preencheConsulta(PreparedStatement sql, Produto obj) {
        try {
            sql.setString(1, obj.getNome());
            sql.setBigDecimal(2, obj.getPreco());
            if(obj.getId() > 0) sql.setInt(3, obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    @Override
    public List<Produto> Buscar(Produto filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
