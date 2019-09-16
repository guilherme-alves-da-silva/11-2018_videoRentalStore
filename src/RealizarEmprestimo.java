/*
 * using dbDesign9
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class RealizarEmprestimo extends javax.swing.JFrame {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement prepStmt;
    private ResultSet rs;

    /**
     * Vector is deprecated, but DefaultComboBoxModel uses it internally, and DefaultComboBoxModel
     * is recommended to be used in this situation, so there's no point in using something else here
     * because DefaultComboBoxModel will implement it as a Vector internally.
     */
    private Vector comboBoxClienteDataList;
    private AutoComboBox comboBoxCliente;
    private Vector comboBoxFilmeDataList;
    private AutoComboBox comboBoxFilme;
    private Vector comboBoxFormaDePagamentoDataList;
    private AutoComboBox comboBoxFormaDePagamento;

    private DefaultListModel listFilmesModel;
    private DefaultListModel listFormasDePagamentoModel;

    public RealizarEmprestimo() {
        comboBoxClienteDataList = new Vector();
        comboBoxCliente = new AutoComboBox(comboBoxClienteDataList);
        comboBoxFilmeDataList = new Vector();
        comboBoxFilme = new AutoComboBox(comboBoxFilmeDataList);
        comboBoxFormaDePagamentoDataList = new Vector();
        comboBoxFormaDePagamento = new AutoComboBox(comboBoxFormaDePagamentoDataList);

        listFilmesModel = new DefaultListModel();
        listFormasDePagamentoModel = new DefaultListModel();

        initComponents();

        listFilmes.setModel(listFilmesModel);
        listFormasDePagamento.setModel(listFormasDePagamentoModel);

        //it already does the DD/MM/YYYY
        dateChooserInicio.setLocale(new Locale("pt", "BR"));
        dateChooserDevolucao.setLocale(new Locale("pt", "BR"));

        dateChooserInicio.setEnabled(false);
        dateChooserDevolucao.setEnabled(false);

        textAreaAnotacoes.setEditable(false);

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:local/designedDb.db");
            stmt = conn.createStatement();
            stmt.setQueryTimeout(30);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //populate comboBoxCliente
        comboBoxClienteDataList.add(new ClientTable()); //insert the first empty string
        try {
            prepStmt = conn.prepareStatement("SELECT *"
                    + " FROM TB_CLIENT"
                    + " ORDER BY CLI_FIRST_NAME"); //and last name as well
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                comboBoxClienteDataList.add(new ClientTable(
                        rs.getInt("CLI_ID"),
                        rs.getString("CLI_FIRST_NAME"),
                        rs.getString("CLI_LAST_NAME"),
                        rs.getString("CLI_CPF"),
                        rs.getLong("CLI_DATE_OF_BIRTH"),
                        rs.getLong("CLI_DATE_OF_ADMISSION"),
                        rs.getInt("CLI_ACTIVE"),
                        rs.getString("CLI_NOTES"),
                        rs.getLong("CLI_DEBT"),
                        rs.getInt("CLI_TOTAL_AMOUNT_OF_MOVIES_RENTED")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        comboBoxCliente.setDataList(comboBoxClienteDataList);

        //populate comboBoxFilme
        comboBoxFilmeDataList.add(new MovieTable()); //insert the first empty string
        try {
            prepStmt = conn.prepareStatement("SELECT *"
                    + " FROM TB_MOVIE"
                    + " WHERE MOV_AVAILABLE = 1"
                    + " AND MOV_ACTIVE = 1"
                    + " ORDER BY MOV_NAME");
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                comboBoxFilmeDataList.add(new MovieTable(
                        rs.getInt("MOV_ID"),
                        rs.getString("MOV_NAME"),
                        rs.getString("MOV_YEAR_OF_RELEASE"),
                        rs.getLong("MOV_DATE_OF_ACQUISITION"),
                        rs.getString("MOV_DESCRIPTION"),
                        rs.getInt("MOV_AVAILABLE"),
                        rs.getString("MOV_NOTES"),
                        rs.getString("MOV_KIND_OF_MEDIA"),
                        rs.getInt("MOV_DAMAGED"),
                        rs.getInt("MOV_ACTIVE"),
                        rs.getInt("MOV_TB_PRICE_CATEGORY_ID")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        comboBoxFilme.setDataList(comboBoxFilmeDataList);

        //populate comboBoxFormaDePagamento
        comboBoxFormaDePagamentoDataList.add(new PaymentFormTable()); //insert the first empty string
        try {
            prepStmt = conn.prepareStatement("SELECT *"
                    + " FROM TB_PAYMENT_FORM"
                    + " WHERE PAY_FOR_ACTIVE = 1"
                    + " ORDER BY PAY_FOR_DESCRIPTION");
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                comboBoxFormaDePagamentoDataList.add(new PaymentFormTable(
                        rs.getInt("PAY_FOR_ID"),
                        rs.getString("PAY_FOR_DESCRIPTION"),
                        rs.getString("PAY_FOR_NOTES"),
                        rs.getInt("PAY_FOR_ACTIVE")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        comboBoxFormaDePagamento.setDataList(comboBoxFormaDePagamentoDataList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        buttonFinalizarEmprestimo = new javax.swing.JButton();
        labelTitle = new javax.swing.JLabel();
        labelDataDeDevolucao = new javax.swing.JLabel();
        labelFilmes = new javax.swing.JLabel();
        labelValorTotal = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        comboBoxCliente = new AutoComboBox(comboBoxClienteDataList);
        labelValueCpfDoCliente = new javax.swing.JLabel();
        labelCpfDoCliente = new javax.swing.JLabel();
        labelQuantidadeDeDias = new javax.swing.JLabel();
        labelDataDeInicio = new javax.swing.JLabel();
        labelDividaDoCliente = new javax.swing.JLabel();
        labelValueDividaDoCliente = new javax.swing.JLabel();
        labelFilme = new javax.swing.JLabel();
        comboBoxFilme = new AutoComboBox(comboBoxFilmeDataList);
        labelDataDeNascimento = new javax.swing.JLabel();
        labelValueDataDeNascimento = new javax.swing.JLabel();
        labelQuantidadeTotalDeFilmesEmprestados = new javax.swing.JLabel();
        labelValueQuantidadeTotalDeFilmesEmprestados = new javax.swing.JLabel();
        labelDataDoCadastro = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listFilmes = new javax.swing.JList<>();
        buttonAdicionarFilmeNaLista = new javax.swing.JButton();
        buttonRemoverFilmeDaLista = new javax.swing.JButton();
        labelValueValorTotal = new javax.swing.JLabel();
        labelValueQuantidadeDeDias = new javax.swing.JLabel();
        buttonMaisDias = new javax.swing.JButton();
        buttonMenosDias = new javax.swing.JButton();
        labelFormasDePagamento = new javax.swing.JLabel();
        comboBoxFormaDePagamento = new AutoComboBox(comboBoxFormaDePagamentoDataList);
        jScrollPane2 = new javax.swing.JScrollPane();
        listFormasDePagamento = new javax.swing.JList<>();
        labelFormaDePagamento = new javax.swing.JLabel();
        buttonAdicionarFormaDePagamentoNaLista = new javax.swing.JButton();
        buttonRemoverFormaDePagamentoDaLista = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        buttonCancelarEmprestimo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        labelValorSendoPago = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        buttonBuscar = new javax.swing.JButton();
        formattedTextFieldValorSendoPago = new javax.swing.JFormattedTextField();
        dateChooserInicio = new com.toedter.calendar.JDateChooser();
        dateChooserDevolucao = new com.toedter.calendar.JDateChooser();
        labelValueDataDoCadastro = new javax.swing.JLabel();
        labelAnotacoes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaAnotacoes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(817, 679));

        jPanel1.setPreferredSize(new java.awt.Dimension(852, 1053));

        buttonFinalizarEmprestimo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonFinalizarEmprestimo.setText("Finalizar Empréstimo");
        buttonFinalizarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinalizarEmprestimoActionPerformed(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelTitle.setText("Realizar Empréstimo");

        labelDataDeDevolucao.setText("Data de Devolução :");

        labelFilmes.setText("* Filmes :");

        labelValorTotal.setText("Valor Total :   R$");

        labelCliente.setText("* Cliente :");

        comboBoxCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxClienteActionPerformed(evt);
            }
        });

        labelValueCpfDoCliente.setText("0");

        labelCpfDoCliente.setText("CPF do Cliente :");

        labelQuantidadeDeDias.setText("* Quantidade de Dias :");

        labelDataDeInicio.setText("Data de Início :");

        labelDividaDoCliente.setText("Dívida do Cliente :   R$");

        labelValueDividaDoCliente.setText("0");

        labelFilme.setText("Filme :");

        comboBoxFilme.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFilmeActionPerformed(evt);
            }
        });

        labelDataDeNascimento.setText("Data de Nascimento :");

        labelValueDataDeNascimento.setText("0");

        labelQuantidadeTotalDeFilmesEmprestados.setText("Quantidade Total de Filmes Emprestados :");

        labelValueQuantidadeTotalDeFilmesEmprestados.setText("0");

        labelDataDoCadastro.setText("Data do Cadastro :");

        listFilmes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listFilmes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(listFilmes);

        buttonAdicionarFilmeNaLista.setText("Adicionar Filme na Lista");
        buttonAdicionarFilmeNaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarFilmeNaListaActionPerformed(evt);
            }
        });

        buttonRemoverFilmeDaLista.setText("Remover Filme da Lista");
        buttonRemoverFilmeDaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverFilmeDaListaActionPerformed(evt);
            }
        });

        labelValueValorTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelValueValorTotal.setText("0.00");

        labelValueQuantidadeDeDias.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelValueQuantidadeDeDias.setText("0");

        buttonMaisDias.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonMaisDias.setText("+");
        buttonMaisDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaisDiasActionPerformed(evt);
            }
        });

        buttonMenosDias.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonMenosDias.setText("-");
        buttonMenosDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenosDiasActionPerformed(evt);
            }
        });

        labelFormasDePagamento.setText("Formas de Pagamento :");

        comboBoxFormaDePagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxFormaDePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxFormaDePagamentoActionPerformed(evt);
            }
        });

        listFormasDePagamento.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listFormasDePagamento);

        labelFormaDePagamento.setText("Forma de Pagamento :");

        buttonAdicionarFormaDePagamentoNaLista.setText("Adicionar Forma de Pagamento na Lista");
        buttonAdicionarFormaDePagamentoNaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarFormaDePagamentoNaListaActionPerformed(evt);
            }
        });

        buttonRemoverFormaDePagamentoDaLista.setText("Remover Forma de Pagamento da Lista");
        buttonRemoverFormaDePagamentoDaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverFormaDePagamentoDaListaActionPerformed(evt);
            }
        });

        buttonCancelarEmprestimo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonCancelarEmprestimo.setText("Cancelar Empréstimo");
        buttonCancelarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarEmprestimoActionPerformed(evt);
            }
        });

        labelValorSendoPago.setText("Valor Sendo Pago :   R$");

        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        formattedTextFieldValorSendoPago.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00"))));
        formattedTextFieldValorSendoPago.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        dateChooserDevolucao.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        labelValueDataDoCadastro.setText("0");

        labelAnotacoes.setText("Anotações :");

        textAreaAnotacoes.setColumns(20);
        textAreaAnotacoes.setRows(5);
        jScrollPane1.setViewportView(textAreaAnotacoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonRemoverFormaDePagamentoDaLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addComponent(buttonAdicionarFormaDePagamentoNaLista))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelFormaDePagamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxFormaDePagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataDeNascimento)
                        .addGap(18, 18, 18)
                        .addComponent(labelValueDataDeNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDividaDoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelValueDividaDoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelFilmes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelValueValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCpfDoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelValueCpfDoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(buttonBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelFilme)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxFilme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelFormasDePagamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelValorSendoPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(formattedTextFieldValorSendoPago))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelDataDeDevolucao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooserDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelDataDeInicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelQuantidadeDeDias)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelValueQuantidadeDeDias, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonMenosDias, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonMaisDias, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonRemoverFilmeDaLista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonAdicionarFilmeNaLista))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataDoCadastro)
                        .addGap(12, 12, 12)
                        .addComponent(labelValueDataDoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelQuantidadeTotalDeFilmesEmprestados)
                        .addGap(12, 12, 12)
                        .addComponent(labelValueQuantidadeTotalDeFilmesEmprestados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAnotacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                        .addComponent(labelTitle)
                        .addGap(193, 314, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancelarEmprestimo)
                .addGap(28, 28, 28)
                .addComponent(buttonFinalizarEmprestimo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCliente)
                    .addComponent(comboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscar))
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCpfDoCliente)
                    .addComponent(labelValueCpfDoCliente))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataDeNascimento)
                    .addComponent(labelValueDataDeNascimento))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataDoCadastro)
                    .addComponent(labelValueDataDoCadastro))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidadeTotalDeFilmesEmprestados)
                    .addComponent(labelValueQuantidadeTotalDeFilmesEmprestados))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDividaDoCliente)
                    .addComponent(labelValueDividaDoCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAnotacoes)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDataDeInicio)
                    .addComponent(dateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataDeDevolucao)
                    .addComponent(dateChooserDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantidadeDeDias)
                    .addComponent(labelValueQuantidadeDeDias)
                    .addComponent(buttonMaisDias)
                    .addComponent(buttonMenosDias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFilmes)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilme)
                    .addComponent(comboBoxFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdicionarFilmeNaLista)
                    .addComponent(buttonRemoverFilmeDaLista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorTotal)
                    .addComponent(labelValueValorTotal))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelValorSendoPago)
                    .addComponent(formattedTextFieldValorSendoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFormasDePagamento)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFormaDePagamento)
                    .addComponent(comboBoxFormaDePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAdicionarFormaDePagamentoNaLista)
                    .addComponent(buttonRemoverFormaDePagamentoDaLista))
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonFinalizarEmprestimo)
                    .addComponent(buttonCancelarEmprestimo))
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        ClientTable selectedClient = (ClientTable) comboBoxCliente.getSelectedItem();

        if (selectedClient.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Favor selecionar um cliente para \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            //REMEMBER TO GET FOR WINDOWS
            //get date from the OS
            Date currentDate = new Date(); //can use getTime if is not showing right

            labelValueCpfDoCliente.setText(selectedClient.getCpf());
            labelValueDataDeNascimento.setText(String.format("%1$td / %1$tm / %1$tY", new Date(selectedClient.getDateOfBirth())));
            labelValueDataDoCadastro.setText(String.format("%1$td / %1$tm / %1$tY", new Date(selectedClient.getDateOfAdmission())));
            labelValueQuantidadeTotalDeFilmesEmprestados.setText(
                    selectedClient.getTotalAmountOfMoviesRented()
                    + " filme(s)"
            );
            labelValueDividaDoCliente.setText(
                    new DecimalFormat("#0.00").format(
                            BigDecimal.valueOf(selectedClient.getDebt())
                                    .divide(new BigDecimal(100)))
            );
            textAreaAnotacoes.setText(selectedClient.getNotes());

            dateChooserInicio.setDate(currentDate);
            dateChooserDevolucao.setDate(currentDate);

            formattedTextFieldValorSendoPago.setText("0.00");
        }
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonCancelarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarEmprestimoActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCancelarEmprestimoActionPerformed

    private void buttonRemoverFormaDePagamentoDaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverFormaDePagamentoDaListaActionPerformed
        int selectedIndex = listFormasDePagamento.getSelectedIndex();

        if (((ClientTable) comboBoxCliente.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Remover Forma de Pagamento da Lista\", favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Remover Forma de Pagamento da Lista\", favor selecionar uma forma de pagamento, da lista de formas de pagamento, para remover.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja remover a forma de pagamento selecionada?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sim", "Não"},
                "Sim") == JOptionPane.YES_OPTION) {

            listFormasDePagamentoModel.remove(selectedIndex);
        }
    }//GEN-LAST:event_buttonRemoverFormaDePagamentoDaListaActionPerformed

    private void buttonAdicionarFormaDePagamentoNaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarFormaDePagamentoNaListaActionPerformed
        comboBoxFormaDePagamentoActionPerformed(evt); //to make sure it's selected

        PaymentFormTable selectedPaymentForm = (PaymentFormTable) comboBoxFormaDePagamento.getSelectedItem();

        if (((ClientTable) comboBoxCliente.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Adicionar Forma de Pagamento na Lista\", favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedPaymentForm.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Adicionar Forma de Pagamento na Lista\", favor selecionar uma forma de pagamento para adicionar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (listFormasDePagamentoModel.contains(selectedPaymentForm)) {

            JOptionPane.showMessageDialog(
                    null,
                    "Esta forma de pagamento já está na lista!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            listFormasDePagamentoModel.addElement(selectedPaymentForm);
        }
    }//GEN-LAST:event_buttonAdicionarFormaDePagamentoNaListaActionPerformed

    private void comboBoxFormaDePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFormaDePagamentoActionPerformed
        //this was intentional, to make sure it's selected.
    }//GEN-LAST:event_comboBoxFormaDePagamentoActionPerformed

    private void buttonMenosDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenosDiasActionPerformed
        if (((ClientTable) comboBoxCliente.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            int amountOfDays = Integer.parseInt(labelValueQuantidadeDeDias.getText());

            if (amountOfDays > 0) {
                labelValueQuantidadeDeDias.setText(Integer.toString(amountOfDays - 1));

                String movieWithoutPrice = updateLabelValueValorTotal();

                if (!movieWithoutPrice.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "ERRO!\nO filme: " + movieWithoutPrice + "\n Não possui um preço para " + labelValueQuantidadeDeDias.getText() + " dia(s)!",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE
                    );

                    labelValueQuantidadeDeDias.setText(Integer.toString(Integer.parseInt(labelValueQuantidadeDeDias.getText()) + 1));

                } else {
                    //subtract 1 day from the return date
                    Calendar cal = Calendar.getInstance(new Locale("pt", "BR"));
                    cal.setTime(dateChooserDevolucao.getDate());
                    cal.add(Calendar.DATE, -1);
                    dateChooserDevolucao.setDate(cal.getTime());
                }
            }
        }
    }//GEN-LAST:event_buttonMenosDiasActionPerformed

    private void buttonMaisDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaisDiasActionPerformed
        if (((ClientTable) comboBoxCliente.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            labelValueQuantidadeDeDias.setText(Integer.toString(Integer.parseInt(labelValueQuantidadeDeDias.getText()) + 1));

            String movieWithoutPrice = updateLabelValueValorTotal();

            if (!movieWithoutPrice.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "ERRO!\nO filme: " + movieWithoutPrice + "\n Não possui um preço para " + labelValueQuantidadeDeDias.getText() + " dia(s)!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );

                labelValueQuantidadeDeDias.setText(Integer.toString(Integer.parseInt(labelValueQuantidadeDeDias.getText()) - 1));

            } else {
                //add 1 day to the return date
                Calendar cal = Calendar.getInstance(new Locale("pt", "BR"));
                cal.setTime(dateChooserDevolucao.getDate());
                cal.add(Calendar.DATE, 1);
                dateChooserDevolucao.setDate(cal.getTime());
            }
        }
    }//GEN-LAST:event_buttonMaisDiasActionPerformed

    private void buttonRemoverFilmeDaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverFilmeDaListaActionPerformed
        int selectedIndex = listFilmes.getSelectedIndex();

        if (((ClientTable) comboBoxCliente.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Remover Filme da Lista\", favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Remover Filme da Lista\", favor selecionar um filme, da lista de filmes, para remover.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja remover o filme selecionado?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sim", "Não"},
                "Sim") == JOptionPane.YES_OPTION) {

            listFilmesModel.remove(selectedIndex);
            updateLabelValueValorTotal();
        }
    }//GEN-LAST:event_buttonRemoverFilmeDaListaActionPerformed

    private void buttonAdicionarFilmeNaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarFilmeNaListaActionPerformed
        comboBoxFilmeActionPerformed(evt); //to make sure it's selected

        MovieTable selectedMovie = (MovieTable) comboBoxFilme.getSelectedItem();

        if (((ClientTable) comboBoxCliente.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Adicionar Filme na Lista\", favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedMovie.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Adicionar Filme na Lista\", favor selecionar um filme para adicionar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (listFilmesModel.contains(selectedMovie)) {

            JOptionPane.showMessageDialog(
                    null,
                    "ERRO!\nEsta cópia já está na lista! Favor escolher outra cópia ou outro filme.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            listFilmesModel.addElement(selectedMovie);

            if (!updateLabelValueValorTotal().isEmpty()) {
                listFilmesModel.remove(listFilmesModel.getSize() - 1);

                JOptionPane.showMessageDialog(
                        null,
                        "ERRO!\nEste filme: " + selectedMovie + "\n Não possui um preço para " + labelValueQuantidadeDeDias.getText() + " dia(s)!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );

            }
        }
    }//GEN-LAST:event_buttonAdicionarFilmeNaListaActionPerformed

    private void comboBoxFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxFilmeActionPerformed
        //this was intentional, to make sure it's selected.
    }//GEN-LAST:event_comboBoxFilmeActionPerformed

    private void comboBoxClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClienteActionPerformed
        //this was intentional, to make sure it's selected.
    }//GEN-LAST:event_comboBoxClienteActionPerformed

    private void buttonFinalizarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinalizarEmprestimoActionPerformed
        ClientTable selectedClient = (ClientTable) comboBoxCliente.getSelectedItem();

        if (formattedTextFieldValorSendoPago.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Finalizar Empréstimo\", favor inserir o valor sendo pago. Se o pagamento não vai ser realizado agora, favor inserir \"0\"",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedClient.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Finalizar Empréstimo\", favor selecionar um cliente, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (listFilmes.getModel().getSize() == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "ERRO!\nA lista de filmes deve possuir pelo menos um filme!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (new BigDecimal(formattedTextFieldValorSendoPago.getText())
                .compareTo(BigDecimal.ZERO) < 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "ERRO!\nNão é possível inserir um valor negativo!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (new BigDecimal(formattedTextFieldValorSendoPago.getText()).compareTo(BigDecimal.ZERO) == 0
                && listFormasDePagamento.getModel().getSize() > 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "ERRO!\nSe o pagamento vai ser realizado agora, favor inserir o valor sendo pago!"
                    + "\nSe o pagamento não vai ser realizado agora, a lista de formas de pagamento deve estar vazia!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (new BigDecimal(formattedTextFieldValorSendoPago.getText()).compareTo(BigDecimal.ZERO) > 0
                && listFormasDePagamento.getModel().getSize() == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "ERRO!\nSe o pagamento vai ser realizado agora, a lista de formas de pagamento deve possuir pelo menos uma forma de pagamento!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (new BigDecimal(formattedTextFieldValorSendoPago.getText())
                .compareTo(new BigDecimal(labelValueValorTotal.getText())) > 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "O valor máximo que pode ser pago é o valor do empréstimo: \"R$ " + labelValueValorTotal.getText() + "\"",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja finalizar?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sim", "Não"},
                "Sim") == JOptionPane.YES_OPTION) {

            try {
                //insert rental
                prepStmt = conn.prepareStatement("INSERT INTO TB_RENTAL("
                        + "REN_DATE_BEGIN," //1
                        + "REN_DATE_END_PREDICTED," //2
                        + "REN_AMOUNT_OF_MOVIES," //3
                        + "REN_AMOUNT_OF_DAYS_PREDICTED," //4
                        + "REN_TOTAL_PRICE_PREDICTED," //5
                        + "REN_TB_CLIENT_ID" //6
                        + ") VALUES(?,?,?,?,?,?)");
                prepStmt.setLong(1, dateChooserInicio.getDate().getTime());
                prepStmt.setLong(2, dateChooserDevolucao.getDate().getTime());
                prepStmt.setInt(3, listFilmes.getModel().getSize());
                prepStmt.setInt(4, Integer.parseInt(labelValueQuantidadeDeDias.getText()));
                prepStmt.setLong(5,
                        new BigDecimal(labelValueValorTotal.getText())
                                .multiply(new BigDecimal(100))
                                .longValue()
                );
                prepStmt.setInt(6, selectedClient.getId());
                prepStmt.executeUpdate();

                int rentalID = stmt.executeQuery("SELECT last_insert_rowid()").getInt("last_insert_rowid()");

                //for each movie
                for (int i = 0; i < listFilmesModel.getSize(); i++) {
                    MovieTable currentMovie = (MovieTable) listFilmesModel.getElementAt(i);

                    //make the movie unavailable
                    prepStmt = conn.prepareStatement("UPDATE TB_MOVIE SET"
                            + " MOV_AVAILABLE = ?" //1
                            + " WHERE MOV_ID = ?"); //2
                    prepStmt.setInt(1, 0);
                    prepStmt.setInt(2, currentMovie.getId());
                    prepStmt.executeUpdate();

                    //'connect' each movie rented, to the rental
                    prepStmt = conn.prepareStatement("INSERT INTO TB_RENTAL_MOVIE("
                            + "REN_MOV_TB_RENTAL_ID," //1
                            + "REN_MOV_TB_MOVIE_ID" //2
                            + ") VALUES(?,?)");
                    prepStmt.setInt(1, rentalID);
                    prepStmt.setInt(2, currentMovie.getId());
                    prepStmt.executeUpdate();
                }

                //for each formOfPayment
                for (int i = 0; i < listFormasDePagamentoModel.getSize(); i++) {
                    PaymentFormTable currentPaymentForm = (PaymentFormTable) listFormasDePagamentoModel.getElementAt(i);

                    prepStmt = conn.prepareStatement("INSERT INTO TB_RENTAL_PAYMENT_FORM("
                            + "REN_PAY_FOR_TB_RENTAL_ID," //1
                            + "REN_PAY_FOR_TB_PAYMENT_FORM_ID" //2
                            + ") VALUES(?,?)");
                    prepStmt.setInt(1, rentalID);
                    prepStmt.setInt(2, currentPaymentForm.getId());
                    prepStmt.executeUpdate();
                }

                //update client
                prepStmt = conn.prepareStatement("UPDATE TB_CLIENT SET"
                        + " CLI_TOTAL_AMOUNT_OF_MOVIES_RENTED = ?" //1
                        + " WHERE CLI_ID = ?"); //2
                prepStmt.setInt(1, selectedClient.getTotalAmountOfMoviesRented() + listFilmesModel.getSize());
                prepStmt.setInt(2, selectedClient.getId());
                prepStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Realização do Empréstimo bem sucedida.");
                new GerenciadorDeEmprestimosDeFilmes().setVisible(true);
                super.dispose();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_buttonFinalizarEmprestimoActionPerformed

    private Long getMoviePrice(int priceCategoryID) {
        Long price = -1L;

        try {
            prepStmt = conn.prepareStatement("SELECT PRI_PRICE"
                    + " FROM TB_PRICE"
                    + " WHERE PRI_AMOUNT_OF_DAYS = ?" //1
                    + " AND PRI_TB_PRICE_CATEGORY_ID = ?"); //2
            prepStmt.setInt(1, Integer.parseInt(labelValueQuantidadeDeDias.getText()));
            prepStmt.setInt(2, priceCategoryID);
            rs = prepStmt.executeQuery();

            if (rs.next()) {
                price = rs.getLong("PRI_PRICE");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return price;
    }

    private String updateLabelValueValorTotal() {
        Long totalPrice = 0L;

        //this is updating the valorTotalValueLabel
        //by getting the price of each movie in the filmesList from the db everytime, it's
        //costly in resources if the filmesList is long, but most of the time it
        //will be 0 < size < 3 tops. i could implement a list of the prices of the movies currently
        //in the list instead of getting it again from the db, but since the list wont be long most
        //of the time and this teacher apparently doesnt care about code, it's not worth it atm.
        for (int i = 0; i < listFilmesModel.getSize(); i++) {
            MovieTable currentMovie = (MovieTable) listFilmesModel.getElementAt(i);

            Long moviePrice = getMoviePrice(currentMovie.getTbPriceCategoryId());

            if (moviePrice.equals(new Long("-1"))) {
                return currentMovie.toString(); //to pass the name of the movie to the warning window
            }

            totalPrice += moviePrice;
        }
        labelValueValorTotal.setText(
                new DecimalFormat("#0.00").format(
                        BigDecimal.valueOf(totalPrice)
                                .divide(new BigDecimal(100))
                )
        );

        return "";
    }

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

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            new GerenciadorDeEmprestimosDeFilmes().setVisible(true);
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
            java.util.logging.Logger.getLogger(RealizarEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RealizarEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RealizarEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RealizarEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RealizarEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarFilmeNaLista;
    private javax.swing.JButton buttonAdicionarFormaDePagamentoNaLista;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonCancelarEmprestimo;
    private javax.swing.JButton buttonFinalizarEmprestimo;
    private javax.swing.JButton buttonMaisDias;
    private javax.swing.JButton buttonMenosDias;
    private javax.swing.JButton buttonRemoverFilmeDaLista;
    private javax.swing.JButton buttonRemoverFormaDePagamentoDaLista;
//    private javax.swing.JComboBox comboBoxCliente;
  //  private javax.swing.JComboBox comboBoxFilme;
    //private javax.swing.JComboBox comboBoxFormaDePagamento;
    private com.toedter.calendar.JDateChooser dateChooserDevolucao;
    private com.toedter.calendar.JDateChooser dateChooserInicio;
    private javax.swing.JFormattedTextField formattedTextFieldValorSendoPago;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel labelAnotacoes;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelCpfDoCliente;
    private javax.swing.JLabel labelDataDeDevolucao;
    private javax.swing.JLabel labelDataDeInicio;
    private javax.swing.JLabel labelDataDeNascimento;
    private javax.swing.JLabel labelDataDoCadastro;
    private javax.swing.JLabel labelDividaDoCliente;
    private javax.swing.JLabel labelFilme;
    private javax.swing.JLabel labelFilmes;
    private javax.swing.JLabel labelFormaDePagamento;
    private javax.swing.JLabel labelFormasDePagamento;
    private javax.swing.JLabel labelQuantidadeDeDias;
    private javax.swing.JLabel labelQuantidadeTotalDeFilmesEmprestados;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelValorSendoPago;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JLabel labelValueCpfDoCliente;
    private javax.swing.JLabel labelValueDataDeNascimento;
    private javax.swing.JLabel labelValueDataDoCadastro;
    private javax.swing.JLabel labelValueDividaDoCliente;
    private javax.swing.JLabel labelValueQuantidadeDeDias;
    private javax.swing.JLabel labelValueQuantidadeTotalDeFilmesEmprestados;
    private javax.swing.JLabel labelValueValorTotal;
    private javax.swing.JList<String> listFilmes;
    private javax.swing.JList<String> listFormasDePagamento;
    private javax.swing.JTextArea textAreaAnotacoes;
    // End of variables declaration//GEN-END:variables
}
