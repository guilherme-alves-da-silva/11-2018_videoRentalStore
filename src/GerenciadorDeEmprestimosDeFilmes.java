/*
 * using dbDesign9
 */

import javax.swing.JOptionPane;

public class GerenciadorDeEmprestimosDeFilmes extends javax.swing.JFrame {

    public GerenciadorDeEmprestimosDeFilmes() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        menuItemCadastrosFilmes = new javax.swing.JMenuItem();
        menuItemCadastrosClientes = new javax.swing.JMenuItem();
        menuItemCadastrosFormaDePagamento = new javax.swing.JMenuItem();
        menuItemCadastrosCategoriaDeFilme = new javax.swing.JMenuItem();
        menuItemCadastrosCategoriaDePreco = new javax.swing.JMenuItem();
        menuMovimentacoes = new javax.swing.JMenu();
        menuItemRealizarEmprestimo = new javax.swing.JMenuItem();
        menuItemRealizarDevolucao = new javax.swing.JMenuItem();
        menuItemPagarDivida = new javax.swing.JMenuItem();
        menuItemAlterarFilme = new javax.swing.JMenuItem();
        menuItemAlterarCliente = new javax.swing.JMenuItem();
        menuItemAlterarFormaDePagamento = new javax.swing.JMenuItem();
        menuItemAlterarCategoriaDeFilme = new javax.swing.JMenuItem();
        menuItemAlterarCategoriaDePreco = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        menuItemEmprestimosRealizadosNoDia = new javax.swing.JMenuItem();
        menuItemEmprestimosNaoDevolvidos = new javax.swing.JMenuItem();
        menuItemClientesComDividas = new javax.swing.JMenuItem();
        menuItemFilmesDanificados = new javax.swing.JMenuItem();
        menuMais = new javax.swing.JMenu();
        menuItemSobre = new javax.swing.JMenuItem();
        menuItemSair = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu5.setText("jMenu5");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Gerenciador de Empréstimos de Filmes");

        menuCadastros.setMnemonic('c');
        menuCadastros.setText("Cadastros");

        menuItemCadastrosFilmes.setMnemonic('f');
        menuItemCadastrosFilmes.setText("Filmes");
        menuItemCadastrosFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosFilmesActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosFilmes);

        menuItemCadastrosClientes.setMnemonic('e');
        menuItemCadastrosClientes.setText("Clientes");
        menuItemCadastrosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosClientesActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosClientes);

        menuItemCadastrosFormaDePagamento.setMnemonic('a');
        menuItemCadastrosFormaDePagamento.setText("Forma de Pagamento");
        menuItemCadastrosFormaDePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosFormaDePagamentoActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosFormaDePagamento);

        menuItemCadastrosCategoriaDeFilme.setMnemonic('t');
        menuItemCadastrosCategoriaDeFilme.setText("Categoria de Filme");
        menuItemCadastrosCategoriaDeFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosCategoriaDeFilmeActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosCategoriaDeFilme);

        menuItemCadastrosCategoriaDePreco.setMnemonic('r');
        menuItemCadastrosCategoriaDePreco.setText("Categoria de Preço");
        menuItemCadastrosCategoriaDePreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrosCategoriaDePrecoActionPerformed(evt);
            }
        });
        menuCadastros.add(menuItemCadastrosCategoriaDePreco);

        jMenuBar.add(menuCadastros);

        menuMovimentacoes.setMnemonic('v');
        menuMovimentacoes.setText("Movimentações");

        menuItemRealizarEmprestimo.setMnemonic('a');
        menuItemRealizarEmprestimo.setText("Realizar Empréstimo");
        menuItemRealizarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRealizarEmprestimoActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemRealizarEmprestimo);

        menuItemRealizarDevolucao.setMnemonic('e');
        menuItemRealizarDevolucao.setText("Realizar Devolução");
        menuItemRealizarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRealizarDevolucaoActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemRealizarDevolucao);

        menuItemPagarDivida.setMnemonic('g');
        menuItemPagarDivida.setText("Pagar Dívida");
        menuItemPagarDivida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPagarDividaActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemPagarDivida);

        menuItemAlterarFilme.setMnemonic('f');
        menuItemAlterarFilme.setText("Alterar Filme");
        menuItemAlterarFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAlterarFilmeActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemAlterarFilme);

        menuItemAlterarCliente.setMnemonic('r');
        menuItemAlterarCliente.setText("Alterar Cliente");
        menuItemAlterarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAlterarClienteActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemAlterarCliente);

        menuItemAlterarFormaDePagamento.setMnemonic('t');
        menuItemAlterarFormaDePagamento.setText("Alterar Forma de Pagamento");
        menuItemAlterarFormaDePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAlterarFormaDePagamentoActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemAlterarFormaDePagamento);

        menuItemAlterarCategoriaDeFilme.setMnemonic('c');
        menuItemAlterarCategoriaDeFilme.setText("Alterar Categoria de Filme");
        menuItemAlterarCategoriaDeFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAlterarCategoriaDeFilmeActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemAlterarCategoriaDeFilme);

        menuItemAlterarCategoriaDePreco.setMnemonic('d');
        menuItemAlterarCategoriaDePreco.setText("Alterar Categoria de Preço");
        menuItemAlterarCategoriaDePreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAlterarCategoriaDePrecoActionPerformed(evt);
            }
        });
        menuMovimentacoes.add(menuItemAlterarCategoriaDePreco);

        jMenuBar.add(menuMovimentacoes);

        menuConsultas.setMnemonic('s');
        menuConsultas.setText("Consultas");

        menuItemEmprestimosRealizadosNoDia.setMnemonic('a');
        menuItemEmprestimosRealizadosNoDia.setText("Empréstimos Realizados no Dia");
        menuItemEmprestimosRealizadosNoDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEmprestimosRealizadosNoDiaActionPerformed(evt);
            }
        });
        menuConsultas.add(menuItemEmprestimosRealizadosNoDia);

        menuItemEmprestimosNaoDevolvidos.setMnemonic('e');
        menuItemEmprestimosNaoDevolvidos.setText("Empréstimos não Devolvidos");
        menuItemEmprestimosNaoDevolvidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEmprestimosNaoDevolvidosActionPerformed(evt);
            }
        });
        menuConsultas.add(menuItemEmprestimosNaoDevolvidos);

        menuItemClientesComDividas.setMnemonic('d');
        menuItemClientesComDividas.setText("Clientes com Dívidas");
        menuItemClientesComDividas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemClientesComDividasActionPerformed(evt);
            }
        });
        menuConsultas.add(menuItemClientesComDividas);

        menuItemFilmesDanificados.setMnemonic('s');
        menuItemFilmesDanificados.setText("Filmes Danificados");
        menuItemFilmesDanificados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemFilmesDanificadosActionPerformed(evt);
            }
        });
        menuConsultas.add(menuItemFilmesDanificados);

        jMenuBar.add(menuConsultas);

        menuMais.setMnemonic('a');
        menuMais.setText("Mais");

        menuItemSobre.setMnemonic('e');
        menuItemSobre.setText("Sobre");
        menuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSobreActionPerformed(evt);
            }
        });
        menuMais.add(menuItemSobre);

        menuItemSair.setMnemonic('s');
        menuItemSair.setText("Sair");
        menuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSairActionPerformed(evt);
            }
        });
        menuMais.add(menuItemSair);

        jMenuBar.add(menuMais);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(146, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemCadastrosFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosFilmesActionPerformed
        new CadastroFilme().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemCadastrosFilmesActionPerformed

    private void menuItemCadastrosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosClientesActionPerformed
        new CadastroCliente().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemCadastrosClientesActionPerformed

    private void menuItemCadastrosFormaDePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosFormaDePagamentoActionPerformed
        new CadastroFormaDePagamento().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemCadastrosFormaDePagamentoActionPerformed

    private void menuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSobreActionPerformed
        new Sobre().setVisible(true);
    }//GEN-LAST:event_menuItemSobreActionPerformed

    private void menuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSairActionPerformed
        dispose();
    }//GEN-LAST:event_menuItemSairActionPerformed

    private void menuItemAlterarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAlterarClienteActionPerformed
        new AlterarCliente().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemAlterarClienteActionPerformed

    private void menuItemAlterarFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAlterarFilmeActionPerformed
        new AlterarFilme().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemAlterarFilmeActionPerformed

    private void menuItemRealizarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRealizarEmprestimoActionPerformed
        new RealizarEmprestimo().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemRealizarEmprestimoActionPerformed

    private void menuItemAlterarFormaDePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAlterarFormaDePagamentoActionPerformed
        new AlterarFormaDePagamento().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemAlterarFormaDePagamentoActionPerformed

    private void menuItemCadastrosCategoriaDeFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosCategoriaDeFilmeActionPerformed
        new CadastroCategoriaDeFilme().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemCadastrosCategoriaDeFilmeActionPerformed

    private void menuItemAlterarCategoriaDeFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAlterarCategoriaDeFilmeActionPerformed
        new AlterarCategoriaDeFilme().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemAlterarCategoriaDeFilmeActionPerformed

    private void menuItemCadastrosCategoriaDePrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrosCategoriaDePrecoActionPerformed
        new CadastroCategoriaDePreco().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemCadastrosCategoriaDePrecoActionPerformed

    private void menuItemAlterarCategoriaDePrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAlterarCategoriaDePrecoActionPerformed
        new AlterarCategoriaDePreco().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemAlterarCategoriaDePrecoActionPerformed

    private void menuItemPagarDividaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemPagarDividaActionPerformed
        new PagarDivida().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemPagarDividaActionPerformed

    private void menuItemRealizarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRealizarDevolucaoActionPerformed
        new RealizarDevolucao().setVisible(true);
        super.dispose();
    }//GEN-LAST:event_menuItemRealizarDevolucaoActionPerformed

    private void menuItemEmprestimosRealizadosNoDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEmprestimosRealizadosNoDiaActionPerformed
        new RelatorioEmprestimosRealizadosNoDia().showReport();
    }//GEN-LAST:event_menuItemEmprestimosRealizadosNoDiaActionPerformed

    private void menuItemFilmesDanificadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemFilmesDanificadosActionPerformed
        new RelatorioFilmesDanificados().showReport();
    }//GEN-LAST:event_menuItemFilmesDanificadosActionPerformed

    private void menuItemEmprestimosNaoDevolvidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEmprestimosNaoDevolvidosActionPerformed
        new RelatorioEmprestimosNaoDevolvidos().showReport();
    }//GEN-LAST:event_menuItemEmprestimosNaoDevolvidosActionPerformed

    private void menuItemClientesComDividasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClientesComDividasActionPerformed
        new RelatorioClientesComDividas().showReport();
    }//GEN-LAST:event_menuItemClientesComDividasActionPerformed

    /**
     * custom dispose() to ask if the user is sure, close the DB connection and call the main GUI
     * again.
     */
    @Override
    public void dispose() {
        if (JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja sair?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sim", "Não"},
                "Sim") == JOptionPane.YES_OPTION) {

            super.dispose();
        }
    }

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and
         * feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDeEmprestimosDeFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDeEmprestimosDeFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDeEmprestimosDeFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciadorDeEmprestimosDeFilmes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorDeEmprestimosDeFilmes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenuItem menuItemAlterarCategoriaDeFilme;
    private javax.swing.JMenuItem menuItemAlterarCategoriaDePreco;
    private javax.swing.JMenuItem menuItemAlterarCliente;
    private javax.swing.JMenuItem menuItemAlterarFilme;
    private javax.swing.JMenuItem menuItemAlterarFormaDePagamento;
    private javax.swing.JMenuItem menuItemCadastrosCategoriaDeFilme;
    private javax.swing.JMenuItem menuItemCadastrosCategoriaDePreco;
    private javax.swing.JMenuItem menuItemCadastrosClientes;
    private javax.swing.JMenuItem menuItemCadastrosFilmes;
    private javax.swing.JMenuItem menuItemCadastrosFormaDePagamento;
    private javax.swing.JMenuItem menuItemClientesComDividas;
    private javax.swing.JMenuItem menuItemEmprestimosNaoDevolvidos;
    private javax.swing.JMenuItem menuItemEmprestimosRealizadosNoDia;
    private javax.swing.JMenuItem menuItemFilmesDanificados;
    private javax.swing.JMenuItem menuItemPagarDivida;
    private javax.swing.JMenuItem menuItemRealizarDevolucao;
    private javax.swing.JMenuItem menuItemRealizarEmprestimo;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenu menuMais;
    private javax.swing.JMenu menuMovimentacoes;
    // End of variables declaration//GEN-END:variables
}