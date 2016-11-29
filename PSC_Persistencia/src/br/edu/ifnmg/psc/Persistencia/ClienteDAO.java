/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Persistencia;

import br.edu.ifnmg.psc.Aplicacao.Cliente;
import br.edu.ifnmg.psc.Aplicacao.ClienteRepositorio;
import br.edu.ifnmg.psc.Aplicacao.ErroValidacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
                
                // Posso os dados do resultado para o objeto
                Cliente tmp = new Cliente();
                tmp.setId(resultado.getInt(1));
                tmp.setNome(resultado.getString(2));
                tmp.setCpf(resultado.getString(3));
                tmp.setDataNascimento(resultado.getDate(4));
                
                // Retorna o objeto
                return tmp;
            }            
            
        } catch(ErroValidacao ex){ 
            System.out.println(ex);
        
        } catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public boolean Salvar(Cliente obj) {
        try {
            if(obj.getId() == 0){
                // Objeto não está no BD, inserir

                // Cria a consulta sql
                PreparedStatement sql = conn.prepareStatement("INSERT INTO clientes(nome,cpf,dataNascimento) "
                        + "VALUES(?,?,?)");
                // Passa os parâmetros para a consulta SQL

                
                preencherObjeto(sql, obj);
                
                
                // Executa a consulta SQL
                sql.executeUpdate();
                
                
                
            } else {
                // Objeto já está no BD, atualizar
                
                                // Cria a consulta sql
                PreparedStatement sql = conn.prepareStatement("UPDATE clientes SET nome = ?, "
                        + "cpf = ?, dataNascimento = ? "
                        + "WHERE id = ?");
                
                
                preencherObjeto(sql, obj);
                sql.setInt(4,obj.getId());
                
                
                // Executa a consulta SQL
                sql.executeUpdate();

                
            }
            
            return true;
        
        } catch(SQLException e){
            System.out.print(e);
            
        }
        return false;
    }

    protected void preencherObjeto(PreparedStatement sql, Cliente obj) throws SQLException {
        // Passa os parâmetros para a consulta SQL
        sql.setString(1, obj.getNome());
        sql.setString(2, obj.getCpf());
        sql.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
    }

    @Override
    public boolean Apagar(Cliente obj) {
        try {
            if(obj.getId() > 0){
                // Objeto não está no BD, inserir

                // Cria a consulta sql
                PreparedStatement sql = conn.prepareStatement("DELETE FROM clientes WHERE id = ? ");
                
                
                // Passa os parâmetros para a consulta SQL
                sql.setInt(1, obj.getId());
                                
                // Executa a consulta SQL
                sql.executeUpdate();
            
            }
            
            return true;
        
        } catch(SQLException e){
            System.out.print(e);
            
        }
        return false;
    }

    @Override
    public Cliente Abrir(int id) {
        try {
            
            // Crio a consulta sql
            PreparedStatement sql = conn.prepareStatement("select id,nome,cpf,dataNascimento "
                    + "from clientes where id = ?");
            
            // Passo os parâmentros para a consulta sql
            sql.setInt(1, id);
            
            // Executo a consulta sql e pego os resultados
            ResultSet resultado = sql.executeQuery();
            
            // Verifica se algum registro foi retornado na consulta
            if(resultado.next()){
                
                // Posso os dados do resultado para o objeto
                Cliente tmp = new Cliente();
                tmp.setId(resultado.getInt(1));
                tmp.setNome(resultado.getString(2));
                tmp.setCpf(resultado.getString(3));
                tmp.setDataNascimento(resultado.getDate(4));
                
                // Retorna o objeto
                return tmp;
            }            
            
        } catch(ErroValidacao ex){ 
            System.out.println(ex);
        
        } catch(SQLException ex){
            System.out.println(ex);
        }
        
        return null;
    }

    @Override
    public List<Cliente> Buscar(Cliente filtro) {
        
        List<Cliente> ret = new ArrayList<>();
        
        String consulta_sql = "select id,nome,cpf,dataNascimento "
                    + "from clientes ";
        String where = "";
        
        HashMap<String,Integer> parametros = new HashMap<>();
        int count = 0;
        
        
        if(filtro.getId() > 0){
            where = where + "id = ?";
            count++;
            parametros.put("id",count);
        }
        
        if(filtro.getNome() != null){
            if(where.length() > 0) where = where + " and ";
            
            where = where + "nome = ?";
            count++;
            parametros.put("nome",count);
        }
        
        if(filtro.getCpf() != null){
            if(where.length() > 0) where = where + " and ";
            
            where = where + "cpf = ?";
            count++;
            parametros.put("cpf",count);
        }
        
        if(where.length() > 0 )
            where = "WHERE " + where;
        
        try {
            
            // Crio a consulta sql
            PreparedStatement sql = conn.prepareStatement(consulta_sql + where);
            
            // Passo os parâmentros para a consulta sql
            if(filtro.getId() > 0) sql.setInt(parametros.get("id"), filtro.getId());
            if(filtro.getNome() != null) sql.setString(parametros.get("nome"), filtro.getNome());
            if(filtro.getCpf() != null) sql.setString(parametros.get("cpf"), filtro.getCpf());
            
            // Executo a consulta sql e pego os resultados
            ResultSet resultado = sql.executeQuery();
                        
            // Verifica se algum registro foi retornado na consulta
            while(resultado.next()){
                
                // Posso os dados do resultado para o objeto
                Cliente tmp = new Cliente();
                tmp.setId(resultado.getInt(1));
                tmp.setNome(resultado.getString(2));
                tmp.setCpf(resultado.getString(3));
                tmp.setDataNascimento(resultado.getDate(4));
                
                // Adiciona o objeto à lista
                ret.add(tmp);
            }            
            
        } catch(ErroValidacao ex){ 
            System.out.println(ex);
        
        } catch(SQLException ex){
            System.out.println(ex);
        }
        
        return ret;
    }
    
}
