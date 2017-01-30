/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Persistencia;

import br.edu.ifnmg.psc.Aplicacao.Venda;
import br.edu.ifnmg.psc.Aplicacao.VendaItem;
import br.edu.ifnmg.psc.Aplicacao.VendaRepositorio;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class VendaDAO extends DAOGenerico<Venda> implements VendaRepositorio {

    private ClienteDAO clientes;
    private ProdutoDAO produtos;

    public VendaDAO() {
        setConsultaAbrir("select id,cliente_id,dataVenda,valorTotal from vendas where id = ?");
        setConsultaApagar("DELETE FROM vendas WHERE id = ? ");
        setConsultaInserir("INSERT INTO vendas(cliente_id,dataVenda,valorTotal) VALUES(?,?,?)");
        setConsultaAlterar("UPDATE vendas SET cliente_id = ?, "
                        + "dataVenda = ?, valorTotal = ? "
                        + "WHERE id = ?");
        setConsultaBusca("select id,cliente_id,dataVenda,valorTotal from vendas ");
        setConsultaUltimoId("select max(id) from vendas where cliente_id = ? and dataVenda = ? and valorTotal = ?");
        clientes = new ClienteDAO();
        produtos = new ProdutoDAO();
    }
    
    @Override
    protected Venda preencheObjeto(ResultSet resultado) {
        try {
            Venda tmp = new Venda();
            tmp.setId( resultado.getInt(1) );
            
            tmp.setCliente( clientes.Abrir( resultado.getInt(2) ));
            
            tmp.setData( resultado.getDate(3)  );
            
            tmp.setValorTotal( resultado.getBigDecimal(4) );
            
            tmp.setItens(  carregaItens(tmp)  );
            
            return tmp;
            
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void preencheConsulta(PreparedStatement sql, Venda obj) {
        try {
            sql.setInt(1, obj.getCliente().getId());
            sql.setDate(2, new java.sql.Date( obj.getData().getTime() ) );
            sql.setBigDecimal(3, obj.getValorTotal() );
            if(obj.getId() > 0) sql.setInt(4, obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean Salvar(Venda obj){
        if(!super.Salvar(obj))
            return false;
        
        if(obj.getId() > 0 ){
            for(VendaItem item : obj.getItens()){
                if(item.getId() == 0){
                    try {
                        String consulta = "insert into vendasitens(venda_id, produto_id,quantidade) values(?,?,?)";
                        PreparedStatement sql = conn.prepareStatement(consulta);
                        sql.setInt(1, obj.getId());
                        sql.setInt(2, item.getProduto().getId());
                        sql.setInt(3, item.getQuantidade());
                        sql.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                } else {
                    try {
                        String consulta = "update vendasitens set venda_id = ?, produto_id = ?,quantidade = ? where id = ?";
                        PreparedStatement sql = conn.prepareStatement(consulta);
                        sql.setInt(1, obj.getId());
                        sql.setInt(2, item.getProduto().getId());
                        sql.setInt(3, item.getQuantidade());
                        sql.setInt(4, item.getId());
                        sql.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    

    @Override
    protected void preencheFiltros(Venda filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void preencheParametros(PreparedStatement sql, Venda filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private List<VendaItem> carregaItens(Venda obj){
        List<VendaItem> ret = new ArrayList<>();
        String consulta = "select id, venda_id, produto_id, quantidade from vendasitens where venda_id = ?";
        try {
            
            // Crio a consulta sql
            PreparedStatement sql = conn.prepareStatement(consulta);
            
            sql.setInt(1, obj.getId());
            
            // Executo a consulta sql e pego os resultados
            ResultSet resultado = sql.executeQuery();
                        
            // Verifica se algum registro foi retornado na consulta
            while(resultado.next()){
                VendaItem item = new VendaItem();
                
                item.setId(resultado.getInt(1));
                item.setVenda(obj);
                item.setProduto(  produtos.Abrir( resultado.getInt(3)  )   );
                item.setQuantidade(  resultado.getInt(4)  );
                
                
                // Adiciona o objeto à lista
                ret.add(item);
            }            
            
        
        } catch(SQLException ex){
            System.out.println(ex);
        }
        
        return ret;
    }
    
}
