/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Aplicacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author petronio
 */
public class Venda implements Entidade {
    private int id;
    private Cliente cliente;
    private Date data;
    private BigDecimal valorTotal;
    private List<VendaItem> itens;

    public Venda() {
        itens = new ArrayList<>();
    }

    public Venda(int id, Cliente cliente, Date data, BigDecimal valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.valorTotal = valorTotal;
        itens = new ArrayList<>();
    }
    
    public void addItem(VendaItem item){
        if(!itens.contains(item)) {
            itens.add(item);
            this.valorTotal = this.valorTotal.add( 
                    item.getProduto().getPreco().multiply(  new BigDecimal( item.getQuantidade()  )   )  );
        }
    }
    
    public void removeItem(VendaItem item){
        if(itens.contains(item)){
            itens.remove(item);
        this.valorTotal = this.valorTotal.subtract(
                    item.getProduto().getPreco().multiply(  new BigDecimal( item.getQuantidade()  )   )  );
        }
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<VendaItem> getItens() {
        return itens;
    }

    public void setItens(List<VendaItem> itens) {
        this.itens = itens;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.cliente);
        hash = 11 * hash + Objects.hashCode(this.data);
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
        final Venda other = (Venda) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", cliente=" + cliente + ", data=" + data + '}';
    }
    
    
    
    
}
