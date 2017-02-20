/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.psc.Apresentacao;

import br.edu.ifnmg.psc.Aplicacao.Venda;
import br.edu.ifnmg.psc.Aplicacao.VendaRepositorio;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author petronio
 */
public class TelaVendaListagem extends javax.swing.JInternalFrame {

    VendaRepositorio dao = GerenciadorReferencias.getVenda();
    
    TelaVendaEditar editar;
    
    /**
     * Creates new form TelaVendaListagem
     */
    public TelaVendaListagem() {
        initComponents();
        
        preencheTabela(dao.Buscar(null));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();

        setClosable(true);
        setTitle("Gerenciar Vendas");

        btnNovo.setText("Nova Venda");

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblListagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListagemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListagem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblListagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListagemMouseClicked
        int selecionada = tblListagem.getSelectedRow();
        
        int id = Integer.parseInt( tblListagem.getModel().getValueAt(selecionada, 0).toString() );
        
        editarVenda(id);
    }//GEN-LAST:event_tblListagemMouseClicked

    
    private void preencheTabela(List<Venda> lista){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Cliente");
        modelo.addColumn("Data");
        modelo.addColumn("Total");
        
        for(Venda v : lista){
            Vector linha = new Vector();
            linha.add(v.getId());
            linha.add(v.getCliente().getNome());
            linha.add(v.getData().toString());
            linha.add(v.getValorTotal().toString());
            modelo.addRow(linha);
        }
        
        tblListagem.setModel(modelo);
    }
    
    public void editarVenda(int id){
        Venda entidade;
        if(id == 0)
            entidade = new Venda(id, null, new Date(), BigDecimal.ZERO);
        else
            entidade = dao.Abrir(id);
        
        editar = new TelaVendaEditar();
        
        editar.setEntidade(entidade);
        
        editar.setListagem(this);
        
        this.getParent().add(editar);
        editar.setVisible(true);
        this.setVisible(false);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListagem;
    // End of variables declaration//GEN-END:variables
}