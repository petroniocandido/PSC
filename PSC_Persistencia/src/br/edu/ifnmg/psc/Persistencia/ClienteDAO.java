/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Persistencia;

import br.edu.ifnmg.psc.Aplicacao.Cliente;
import br.edu.ifnmg.psc.Aplicacao.ClienteRepositorio;
import br.edu.ifnmg.psc.Aplicacao.ErroValidacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class ClienteDAO extends DAOGenerico<Cliente> implements ClienteRepositorio {

    
    public ClienteDAO() {
        setConsultaAbrir("select id,nome,cpf,dataNascimento from clientes where id = ?");
        setConsultaApagar("DELETE FROM clientes WHERE id = ? ");
        setConsultaInserir("INSERT INTO clientes(nome,cpf,dataNascimento) VALUES(?,?,?)");
        setConsultaAlterar("UPDATE clientes SET nome = ?, "
                        + "cpf = ?, dataNascimento = ? "
                        + "WHERE id = ?");
        setConsultaBusca("select id,nome,cpf,dataNascimento from clientes ");
        setConsultaUltimoId("select max(id) from clientes where nome = ? and cpf = ? and dataNascimento = ?");
    }
    
    @Override
    protected Cliente preencheObjeto(ResultSet resultado) {
        // Posso os dados do resultado para o objeto
                Cliente tmp = new Cliente();
        try {
            tmp.setId(resultado.getInt(1));
        
                tmp.setNome(resultado.getString(2));
                tmp.setCpf(resultado.getString(3));
                tmp.setDataNascimento(resultado.getDate(4));
         } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErroValidacao ex) {     
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                // Retorna o objeto
                return tmp;
    }
    
    @Override
    protected void preencheConsulta(PreparedStatement sql, Cliente obj) {
        try {
            // Passa os parâmetros para a consulta SQL
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getCpf());
            sql.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
            
            if(obj.getId() > 0) sql.setInt(4,obj.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Cliente Abrir(String cpf) {
        try {
            
            // Crio a consulta sql
            PreparedStatement sql = conn.prepareStatement("select id,nome,cpf,dataNascimento "
                    + "from clientes where cpf = ?");
            
            // Passo os parâmentros para a consulta sql
            sql.setString(1, cpf);
            
            // Executo a consulta sql e pego os resultados
            ResultSet resultado = sql.executeQuery();
            
            // Verifica se algum registro foi retornado na consulta
            if(resultado.next()){
                
                return preencheObjeto(resultado);
            }            
        }  catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }
    
    @Override
    protected void preencheFiltros(Cliente filtro) {
        if(filtro.getId() > 0) adicionarFiltro("id", "=");
        if(filtro.getNome() != null) adicionarFiltro("nome", " like ");
        if(filtro.getCpf() != null) adicionarFiltro("cpf", "=");
    }

    @Override
    protected void preencheParametros(PreparedStatement sql, Cliente filtro) {
        try {
            int cont = 1;
            if(filtro.getId() > 0){ sql.setInt(cont, filtro.getId()); cont++; }
            if(filtro.getNome() != null ){ sql.setString(cont, filtro.getNome()); cont++; }
            if(filtro.getCpf() != null){ sql.setString(cont, filtro.getCpf()); cont++; }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
