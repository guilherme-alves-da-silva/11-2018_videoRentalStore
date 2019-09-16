/*
 * using dbDesign9
 */

import com.toedter.calendar.JTextFieldDateEditor;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AlterarFilme extends javax.swing.JFrame {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement prepStmt;
    private ResultSet rs;

    /**
     * Vector is deprecated, but DefaultComboBoxModel uses it internally, and DefaultComboBoxModel
     * is recommended to be used in this situation, so there's no point in using something else here
     * because DefaultComboBoxModel will implement it as a Vector internally.
     */
    private Vector comboBoxBuscarFilmeDataList;
    private AutoComboBox comboBoxBuscarFilme;
    private Vector comboBoxCategoriaDePrecoDataList;
    private AutoComboBox comboBoxCategoriaDePreco;
    private Vector comboBoxCategoriaDeFilmeDataList;
    private AutoComboBox comboBoxCategoriaDeFilme;
    private DefaultListModel listCategoriasDeFilmeModel;

    private ArrayList<MovieCategoryTable> movieCategoriesToRemoveFromDB;

    private PriceCategoryTable priceCategoryOfTheCurrentMovie;

    public AlterarFilme() {
        comboBoxBuscarFilmeDataList = new Vector();
        comboBoxBuscarFilme = new AutoComboBox(comboBoxBuscarFilmeDataList);
        comboBoxCategoriaDePrecoDataList = new Vector();
        comboBoxCategoriaDePreco = new AutoComboBox(comboBoxCategoriaDePrecoDataList);
        comboBoxCategoriaDeFilmeDataList = new Vector();
        comboBoxCategoriaDeFilme = new AutoComboBox(comboBoxCategoriaDeFilmeDataList);

        initComponents();

        listCategoriasDeFilmeModel = new DefaultListModel();
        listCategoriasDeFilme.setModel(listCategoriasDeFilmeModel);

        movieCategoriesToRemoveFromDB = new ArrayList<MovieCategoryTable>();

        //it already does the DD/MM/YYYY
        dateChooserNovaDataDaAcquisicao.setLocale(new Locale("pt", "BR"));

        ((JTextFieldDateEditor) dateChooserNovaDataDaAcquisicao.getDateEditor()).setEditable(false);

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:local/designedDb.db");
            stmt = conn.createStatement();
            stmt.setQueryTimeout(30);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //populate comboBoxBuscarFilme
        comboBoxBuscarFilmeDataList.add(new MovieTable()); //insert the first empty string
        try {
            prepStmt = conn.prepareStatement("SELECT *"
                    + " FROM TB_MOVIE"
                    + " ORDER BY MOV_NAME");
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                comboBoxBuscarFilmeDataList.add(new MovieTable(
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
        comboBoxBuscarFilme.setDataList(comboBoxBuscarFilmeDataList);

        //populate comboBoxCategoriaDeFilme
        comboBoxCategoriaDeFilmeDataList.add(new MovieCategoryTable()); //insert the first empty string
        try {
            prepStmt = conn.prepareStatement("SELECT *"
                    + " FROM TB_MOVIE_CATEGORY"
                    + " WHERE MOV_CAT_ACTIVE = ?" //1
                    + " ORDER BY MOV_CAT_DESCRIPTION");
            prepStmt.setInt(1, 1);
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                comboBoxCategoriaDeFilmeDataList.add(new MovieCategoryTable(
                        rs.getInt("MOV_CAT_ID"),
                        rs.getString("MOV_CAT_DESCRIPTION"),
                        rs.getString("MOV_CAT_NOTES"),
                        rs.getInt("MOV_CAT_ACTIVE"),
                        true //because they will only be used as new categories in the list
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        comboBoxCategoriaDeFilme.setDataList(comboBoxCategoriaDeFilmeDataList);

        //populate comboBoxCategoriaDePreco
        comboBoxCategoriaDePrecoDataList.add(new PriceCategoryTable()); //insert the first empty string
        try {
            prepStmt = conn.prepareStatement("SELECT *"
                    + " FROM TB_PRICE_CATEGORY"
                    + " WHERE PRI_CAT_ACTIVE = ?" //1
                    + " ORDER BY PRI_CAT_DESCRIPTION");
            prepStmt.setInt(1, 1);
            rs = prepStmt.executeQuery();

            while (rs.next()) {
                comboBoxCategoriaDePrecoDataList.add(new PriceCategoryTable(
                        rs.getInt("PRI_CAT_ID"),
                        rs.getString("PRI_CAT_DESCRIPTION"),
                        rs.getString("PRI_CAT_NOTES"),
                        rs.getInt("PRI_CAT_AMOUNT_OF_MOVIES_USING"),
                        rs.getInt("PRI_CAT_ACTIVE"),
                        rs.getLong("PRI_CAT_FINE_PRICE"),
                        rs.getInt("PRI_CAT_MULTIPLY_BY")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        comboBoxCategoriaDePreco.setDataList(comboBoxCategoriaDePrecoDataList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        buttonFinalizarAlteracao = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        labelNovoNome = new javax.swing.JLabel();
        labelNovoTipoDeMidia = new javax.swing.JLabel();
        labelNovaDescricao = new javax.swing.JLabel();
        labelFilmeDanificado = new javax.swing.JLabel();
        textFieldNovoNome = new javax.swing.JTextField();
        textFieldNovoTipoDeMidia = new javax.swing.JTextField();
        labelFilmeAtivo = new javax.swing.JLabel();
        labelNovasAnotacoes = new javax.swing.JLabel();
        labelFilme = new javax.swing.JLabel();
        comboBoxBuscarFilme = new AutoComboBox(comboBoxBuscarFilmeDataList);
        textFieldNovoAnoDeLancamento = new javax.swing.JTextField();
        labelValueIdDoFilme = new javax.swing.JLabel();
        labelIdDoFilme = new javax.swing.JLabel();
        labelNovoAnoDeLancamento = new javax.swing.JLabel();
        checkBoxFilmeAtivo = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaNovasAnotacoes = new javax.swing.JTextArea();
        labelDisponibilidade = new javax.swing.JLabel();
        labelValueDisponibilidade = new javax.swing.JLabel();
        labelNovaDataDaAcquisicao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaNovaDescricao = new javax.swing.JTextArea();
        checkBoxFilmeDanificado = new javax.swing.JCheckBox();
        labelNovaCategoriaDePreco = new javax.swing.JLabel();
        comboBoxCategoriaDePreco = new AutoComboBox(comboBoxCategoriaDePrecoDataList);
        labelCategoriaDePrecoAtual = new javax.swing.JLabel();
        labelValueCategoriaDePrecoAnterior = new javax.swing.JLabel();
        labelCategoriasDeFilme = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listCategoriasDeFilme = new javax.swing.JList<>();
        labelCategoriaDeFilme = new javax.swing.JLabel();
        comboBoxCategoriaDeFilme = new AutoComboBox(comboBoxCategoriaDeFilmeDataList);
        buttonAdicionarCategoriaNaLista = new javax.swing.JButton();
        buttonRemoverCategoriaDaLista = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        buttonBuscar = new javax.swing.JButton();
        buttonCancelarAlteracao = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        dateChooserNovaDataDaAcquisicao = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(817, 679));

        jPanel1.setPreferredSize(new java.awt.Dimension(814, 863));

        buttonFinalizarAlteracao.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonFinalizarAlteracao.setText("Finalizar Alteração");
        buttonFinalizarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinalizarAlteracaoActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("Alterar Filme ( Cada Cópia )");

        labelNovoNome.setText("* Novo Nome :");

        labelNovoTipoDeMidia.setText("* Novo Tipo De Mídia :");

        labelNovaDescricao.setText("Nova Descrição :");

        labelFilmeDanificado.setText("Filme Danificado :");

        labelFilmeAtivo.setText("Filme Ativo :");

        labelNovasAnotacoes.setText("Novas Anotações :");

        labelFilme.setText("* Filme :");

        comboBoxBuscarFilme.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxBuscarFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBuscarFilmeActionPerformed(evt);
            }
        });

        labelValueIdDoFilme.setText("0");

        labelIdDoFilme.setText("ID do Filme :");

        labelNovoAnoDeLancamento.setText("Novo Ano de Lançamento :");

        textAreaNovasAnotacoes.setColumns(20);
        textAreaNovasAnotacoes.setRows(5);
        jScrollPane1.setViewportView(textAreaNovasAnotacoes);

        labelDisponibilidade.setText("Disponibilidade :");

        labelValueDisponibilidade.setText("Disponível");

        labelNovaDataDaAcquisicao.setText("* Nova Data da Acquisição :");

        textAreaNovaDescricao.setColumns(20);
        textAreaNovaDescricao.setRows(5);
        jScrollPane2.setViewportView(textAreaNovaDescricao);

        labelNovaCategoriaDePreco.setText("Nova Categoria de Preço :");

        comboBoxCategoriaDePreco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxCategoriaDePreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCategoriaDePrecoActionPerformed(evt);
            }
        });

        labelCategoriaDePrecoAtual.setText("Categoria de Preço Atual :");

        labelValueCategoriaDePrecoAnterior.setText("0");

        labelCategoriasDeFilme.setText("Categorias de Filme :");

        listCategoriasDeFilme.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listCategoriasDeFilme);

        labelCategoriaDeFilme.setText("Categoria de Filme :");

        comboBoxCategoriaDeFilme.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxCategoriaDeFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCategoriaDeFilmeActionPerformed(evt);
            }
        });

        buttonAdicionarCategoriaNaLista.setText("Adicionar Categoria na Lista");
        buttonAdicionarCategoriaNaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarCategoriaNaListaActionPerformed(evt);
            }
        });

        buttonRemoverCategoriaDaLista.setText("Remover Categoria da Lista");
        buttonRemoverCategoriaDaLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoverCategoriaDaListaActionPerformed(evt);
            }
        });

        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        buttonCancelarAlteracao.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonCancelarAlteracao.setText("Cancelar Alteração");
        buttonCancelarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarAlteracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelFilmeAtivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBoxFilmeAtivo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelFilmeDanificado)
                                .addGap(8, 8, 8)
                                .addComponent(checkBoxFilmeDanificado)))
                        .addGap(523, 648, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(titleLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buttonRemoverCategoriaDaLista)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonAdicionarCategoriaNaLista))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelCategoriasDeFilme)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelCategoriaDePrecoAtual)
                                .addGap(18, 18, 18)
                                .addComponent(labelValueCategoriaDePrecoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovaDataDaAcquisicao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateChooserNovaDataDaAcquisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovoAnoDeLancamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNovoAnoDeLancamento))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovoTipoDeMidia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNovoTipoDeMidia))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovoNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldNovoNome))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovasAnotacoes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelIdDoFilme)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelValueIdDoFilme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelFilme)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxBuscarFilme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(buttonBuscar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelDisponibilidade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelValueDisponibilidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(labelCategoriaDeFilme)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboBoxCategoriaDeFilme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovaCategoriaDePreco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxCategoriaDePreco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNovaDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancelarAlteracao)
                .addGap(40, 40, 40)
                .addComponent(buttonFinalizarAlteracao)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilme)
                    .addComponent(comboBoxBuscarFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscar))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIdDoFilme)
                    .addComponent(labelValueIdDoFilme))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDisponibilidade)
                    .addComponent(labelValueDisponibilidade))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNovaDataDaAcquisicao)
                    .addComponent(dateChooserNovaDataDaAcquisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNovoNome)
                    .addComponent(textFieldNovoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNovoAnoDeLancamento)
                    .addComponent(textFieldNovoAnoDeLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNovoTipoDeMidia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNovoTipoDeMidia))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNovaDescricao)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilmeDanificado)
                    .addComponent(checkBoxFilmeDanificado))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilmeAtivo)
                    .addComponent(checkBoxFilmeAtivo))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCategoriasDeFilme)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCategoriaDeFilme)
                    .addComponent(comboBoxCategoriaDeFilme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAdicionarCategoriaNaLista)
                    .addComponent(buttonRemoverCategoriaDaLista))
                .addGap(10, 10, 10)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNovasAnotacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCategoriaDePrecoAtual)
                    .addComponent(labelValueCategoriaDePrecoAnterior))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNovaCategoriaDePreco)
                    .addComponent(comboBoxCategoriaDePreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonFinalizarAlteracao)
                    .addComponent(buttonCancelarAlteracao))
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarAlteracaoActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCancelarAlteracaoActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        comboBoxBuscarFilmeActionPerformed(evt); //to make sure it's selected

        MovieTable selectedMovie = (MovieTable) comboBoxBuscarFilme.getSelectedItem();

        if (selectedMovie.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Favor selecionar um filme para \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            labelValueIdDoFilme.setText(Integer.toString(selectedMovie.getId()));
            textFieldNovoNome.setText(selectedMovie.getName());
            textFieldNovoAnoDeLancamento.setText(selectedMovie.getYearOfRelease());
            dateChooserNovaDataDaAcquisicao.setDate(new Date(selectedMovie.getDateOfAcquisition()));
            textAreaNovaDescricao.setText(selectedMovie.getDescription());
            labelValueDisponibilidade.setText(selectedMovie.getAvailable() == 1 ? "DISPONÍVEL" : "NÃO DISPONÍVEL");
            textAreaNovasAnotacoes.setText(selectedMovie.getNotes());
            textFieldNovoTipoDeMidia.setText(selectedMovie.getKindOfMedia());
            checkBoxFilmeDanificado.setSelected(selectedMovie.getDamaged() == 1);
            checkBoxFilmeAtivo.setSelected(selectedMovie.getActive() == 1);

            /*
             * if it's not in the comboBox list, get it from the DB.
             */
            boolean priceCategoryFoundInTheList = false;

            /*
             * checking if the priceCategory is in the comboBox list.
             */
            for (int i = 0; i < comboBoxCategoriaDePrecoDataList.size(); i++) {
                PriceCategoryTable currentCategory = (PriceCategoryTable) comboBoxCategoriaDePrecoDataList.get(i);

                if (!currentCategory.isEmptyString()
                        && currentCategory.getId() == selectedMovie.getTbPriceCategoryId()) {

                    priceCategoryOfTheCurrentMovie = currentCategory;
                    priceCategoryFoundInTheList = true;
                    break;
                }
            }

            /*
             * the priceCategory wont be in the comboBox list if the priceCategory is not active
             * anymore, therefore i'll get it from the DB.
             */
            if (!priceCategoryFoundInTheList) {
                try {
                    prepStmt = conn.prepareStatement("SELECT *"
                            + " FROM TB_PRICE_CATEGORY"
                            + " WHERE PRI_CAT_ID LIKE ?"); //1
                    prepStmt.setInt(1, selectedMovie.getTbPriceCategoryId());
                    rs = prepStmt.executeQuery();

                    priceCategoryOfTheCurrentMovie = new PriceCategoryTable(
                            rs.getInt("PRI_CAT_ID"),
                            rs.getString("PRI_CAT_DESCRIPTION"),
                            rs.getString("PRI_CAT_NOTES"),
                            rs.getInt("PRI_CAT_AMOUNT_OF_MOVIES_USING"),
                            rs.getInt("PRI_CAT_ACTIVE"),
                            rs.getLong("PRI_CAT_FINE_PRICE"),
                            rs.getInt("PRI_CAT_MULTIPLY_BY")
                    );
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            /*
             * getting from the comboBox list or the DB, the priceCategoryOfTheCurrentMovie will be
             * set, also the MOV_TB_PRICE_CATEGORY_ID is a mandatory\obligatory attribute of the
             * MovieTable in the DB.
             */
            labelValueCategoriaDePrecoAnterior.setText(priceCategoryOfTheCurrentMovie.getDescription());

            listCategoriasDeFilmeModel.clear();
            try {
                /*
                 * getting all movie categories from the DB instead of checking if they are in the
                 * comboBox list, because the movie category may not be active anymore, therefore it
                 * wont be in the comboBox list.
                 *
                 * OBS: i'm using this indentation in the string instead of it being before the "+"
                 * so that the IDE wont pull it back when i use the autoFormat from the IDE.
                 */
                prepStmt = conn.prepareStatement("SELECT *"
                        + " FROM TB_MOVIE_CATEGORY"
                        + " WHERE MOV_CAT_ID IN ("
                        + "                     SELECT MOV_CAT_MOV_TB_MOVIE_CATEGORY_ID"
                        + "                     FROM TB_MOVIE_CATEGORY_MOVIE"
                        + "                     WHERE MOV_CAT_MOV_TB_MOVIE_ID = ?" //1
                        + ")");
                prepStmt.setInt(1, selectedMovie.getId());
                rs = prepStmt.executeQuery();

                while (rs.next()) {
                    listCategoriasDeFilmeModel.addElement(new MovieCategoryTable(
                            rs.getInt("MOV_CAT_ID"),
                            rs.getString("MOV_CAT_DESCRIPTION"),
                            rs.getString("MOV_CAT_NOTES"),
                            rs.getInt("MOV_CAT_ACTIVE"),
                            false //is not to insert in the DB because it's already in the DB.
                    ));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonRemoverCategoriaDaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoverCategoriaDaListaActionPerformed
        int selectedIndex = listCategoriasDeFilme.getSelectedIndex();

        if (((MovieTable) comboBoxBuscarFilme.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Remover Categoria\", favor selecionar um filme, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedIndex == -1) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Remover Categoria\", favor selecionar uma categoria para remover.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (JOptionPane.showOptionDialog(
                null,
                "Tem certeza que deseja remover esta categoria da lista?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Sim", "Não"},
                "Sim") == JOptionPane.YES_OPTION) {

            MovieCategoryTable selectedCategory = (MovieCategoryTable) listCategoriasDeFilmeModel.get(selectedIndex);

            /*
             * if is not to insert (is not a new item in the list), then it already was in the DB
             * and it must be removed from the DB as well.
             */
            if (!selectedCategory.isToInsert()) {
                movieCategoriesToRemoveFromDB.add(selectedCategory);
            }
            listCategoriasDeFilmeModel.remove(selectedIndex);
        }
    }//GEN-LAST:event_buttonRemoverCategoriaDaListaActionPerformed

    private void buttonAdicionarCategoriaNaListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarCategoriaNaListaActionPerformed
        comboBoxCategoriaDeFilmeActionPerformed(evt); //to make sure it's selected

        MovieCategoryTable selectedMovieCategory = (MovieCategoryTable) comboBoxCategoriaDeFilme.getSelectedItem();

        if (((MovieTable) comboBoxBuscarFilme.getSelectedItem()).isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Adicionar Categoria\", favor selecionar um filme, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (selectedMovieCategory.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Adicionar Categoria\", favor selecionar uma categoria para adicionar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (listCategoriasDeFilmeModel.contains(selectedMovieCategory)) {

            JOptionPane.showMessageDialog(
                    null,
                    "Esta categoria já está na lista!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else {
            listCategoriasDeFilmeModel.addElement(selectedMovieCategory);
        }
    }//GEN-LAST:event_buttonAdicionarCategoriaNaListaActionPerformed

    private void comboBoxCategoriaDeFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCategoriaDeFilmeActionPerformed
        //this was intentional, to make sure it's selected.
    }//GEN-LAST:event_comboBoxCategoriaDeFilmeActionPerformed

    private void comboBoxCategoriaDePrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCategoriaDePrecoActionPerformed
        //this was intentional, to make sure it's selected.
    }//GEN-LAST:event_comboBoxCategoriaDePrecoActionPerformed

    private void comboBoxBuscarFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBuscarFilmeActionPerformed
        //this was intentional, to make sure it's selected.
    }//GEN-LAST:event_comboBoxBuscarFilmeActionPerformed

    private void buttonFinalizarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinalizarAlteracaoActionPerformed
        MovieTable selectedMovie = (MovieTable) comboBoxBuscarFilme.getSelectedItem();

        trimMandatoryFields();

        if (selectedMovie.isEmptyString()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Antes de clicar em \"Finalizar Alteração\", favor selecionar um filme, e clicar em \"Buscar\".",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

        } else if (textFieldNovoNome.getText().isEmpty()
                || textFieldNovoTipoDeMidia.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "ERRO!\nOs campos * são obrigatórios!",
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
                PriceCategoryTable selectedPriceCategory = (PriceCategoryTable) comboBoxCategoriaDePreco.getSelectedItem();

                prepStmt = conn.prepareStatement("UPDATE TB_MOVIE SET "
                        + "MOV_NAME = ?," //1
                        + "MOV_YEAR_OF_RELEASE = ?," //2
                        + "MOV_DATE_OF_ACQUISITION = ?," //3
                        + "MOV_DESCRIPTION = ?," //4
                        + "MOV_NOTES = ?," //5
                        + "MOV_KIND_OF_MEDIA = ?," //6
                        + "MOV_DAMAGED = ?," //7
                        + "MOV_ACTIVE = ?," //8
                        + "MOV_TB_PRICE_CATEGORY_ID = ? " //9
                        + "WHERE MOV_ID = ?"); //10
                prepStmt.setString(1, textFieldNovoNome.getText());
                prepStmt.setString(2, textFieldNovoAnoDeLancamento.getText());
                prepStmt.setLong(3, dateChooserNovaDataDaAcquisicao.getDate().getTime());
                prepStmt.setString(4, textAreaNovaDescricao.getText());
                prepStmt.setString(5, textAreaNovasAnotacoes.getText());
                prepStmt.setString(6, textFieldNovoTipoDeMidia.getText());
                prepStmt.setInt(7, checkBoxFilmeDanificado.isSelected() ? 1 : 0);
                prepStmt.setInt(8, checkBoxFilmeAtivo.isSelected() ? 1 : 0);

                /*
                 * the MOV_TB_PRICE_CATEGORY_ID is already changed here even though the rest of the
                 * changes, if a new priceCategory was selected, are done a few lines down. the ID
                 * change is done here already because if i do the change later the DB will need to
                 * find MOV_ID again just to change the MOV_TB_PRICE_CATEGORY_ID attribute.
                 */
                prepStmt.setInt(9,
                        !selectedPriceCategory.isEmptyString() //if some category is selected
                        ? selectedPriceCategory.getId() //category changed
                        : selectedMovie.getTbPriceCategoryId() //category didnt change
                );

                prepStmt.setInt(10, Integer.parseInt(labelValueIdDoFilme.getText()));
                prepStmt.executeUpdate();

                //insert each new movie category for the movie
                for (int i = 0; i < listCategoriasDeFilmeModel.getSize(); i++) {
                    MovieCategoryTable currentCategory = (MovieCategoryTable) listCategoriasDeFilmeModel.getElementAt(i);

                    if (currentCategory.isToInsert()) {
                        prepStmt = conn.prepareStatement("INSERT INTO TB_MOVIE_CATEGORY_MOVIE("
                                + "MOV_CAT_MOV_TB_MOVIE_ID," //1
                                + "MOV_CAT_MOV_TB_MOVIE_CATEGORY_ID" //2
                                + ") VALUES(?,?)");
                        prepStmt.setInt(1, Integer.parseInt(labelValueIdDoFilme.getText()));
                        prepStmt.setInt(2, currentCategory.getId());
                        prepStmt.executeUpdate();
                    }
                }

                //deleting each movie category that the user removed for the current movie
                for (int i = 0; i < movieCategoriesToRemoveFromDB.size(); i++) {
                    prepStmt = conn.prepareStatement("DELETE"
                            + " FROM TB_MOVIE_CATEGORY_MOVIE"
                            + " WHERE MOV_CAT_MOV_TB_MOVIE_ID = ?" //1
                            + " AND MOV_CAT_MOV_TB_MOVIE_CATEGORY_ID = ?"); //2
                    prepStmt.setInt(1, Integer.parseInt(labelValueIdDoFilme.getText()));
                    prepStmt.setInt(2, movieCategoriesToRemoveFromDB.get(i).getId());
                    prepStmt.executeUpdate();
                }

                //if come category is selected in the comboBoxAlterarFilmes
                if (!selectedPriceCategory.isEmptyString()) {
                    if (selectedPriceCategory.getId() == selectedMovie.getTbPriceCategoryId()) {

                        JOptionPane.showMessageDialog(null, "A \"Categoria de Preço Atual\" é a mesma que foi selecionada para"
                                + "\nser a \"Nova Categoria de Preço\", portanto não vai ser mudada.");

                    } else {
                        /*
                         * update the PRI_CAT_AMOUNT_OF_MOVIES_USING attribute of the current
                         * price category in the selected movie.
                         */
                        prepStmt = conn.prepareStatement("UPDATE TB_PRICE_CATEGORY SET"
                                + " PRI_CAT_AMOUNT_OF_MOVIES_USING = ?" //1
                                + " WHERE PRI_CAT_ID = ?"); //2
                        prepStmt.setInt(1, priceCategoryOfTheCurrentMovie.getAmountOfMoviesUsing() - 1);
                        prepStmt.setInt(2, selectedMovie.getTbPriceCategoryId());
                        prepStmt.executeUpdate();

                        /*
                         * update the PRI_CAT_AMOUNT_OF_MOVIES_USING attribute of the price
                         * category selected in the comboBoxAlterarFilmes.
                         */
                        prepStmt = conn.prepareStatement("UPDATE TB_PRICE_CATEGORY SET"
                                + " PRI_CAT_AMOUNT_OF_MOVIES_USING = ?" //1
                                + " WHERE PRI_CAT_ID = ?"); //2
                        prepStmt.setInt(1, selectedPriceCategory.getAmountOfMoviesUsing() + 1);
                        prepStmt.setInt(2, selectedPriceCategory.getId());
                        prepStmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Alteração dos filmes bem sucedida.");
                    }
                }

                JOptionPane.showMessageDialog(null, "Alteração do filme bem sucedida.");
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
    }//GEN-LAST:event_buttonFinalizarAlteracaoActionPerformed

    /**
     * trimming the fields that are mandatory\obligatory, meaning the fields that have an "*"
     * preceding their label.
     */
    private void trimMandatoryFields() {
        textFieldNovoNome.setText(textFieldNovoNome.getText().trim());
        textFieldNovoTipoDeMidia.setText(textFieldNovoTipoDeMidia.getText().trim());
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
            java.util.logging.Logger.getLogger(AlterarFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarFilme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlterarFilme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionarCategoriaNaLista;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonCancelarAlteracao;
    private javax.swing.JButton buttonFinalizarAlteracao;
    private javax.swing.JButton buttonRemoverCategoriaDaLista;
    private javax.swing.JCheckBox checkBoxFilmeAtivo;
    private javax.swing.JCheckBox checkBoxFilmeDanificado;
//    private javax.swing.JComboBox comboBoxBuscarFilme;
  //  private javax.swing.JComboBox comboBoxCategoriaDeFilme;
    //private javax.swing.JComboBox comboBoxCategoriaDePreco;
    private com.toedter.calendar.JDateChooser dateChooserNovaDataDaAcquisicao;
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
    private javax.swing.JLabel labelCategoriaDeFilme;
    private javax.swing.JLabel labelCategoriaDePrecoAtual;
    private javax.swing.JLabel labelCategoriasDeFilme;
    private javax.swing.JLabel labelDisponibilidade;
    private javax.swing.JLabel labelFilme;
    private javax.swing.JLabel labelFilmeAtivo;
    private javax.swing.JLabel labelFilmeDanificado;
    private javax.swing.JLabel labelIdDoFilme;
    private javax.swing.JLabel labelNovaCategoriaDePreco;
    private javax.swing.JLabel labelNovaDataDaAcquisicao;
    private javax.swing.JLabel labelNovaDescricao;
    private javax.swing.JLabel labelNovasAnotacoes;
    private javax.swing.JLabel labelNovoAnoDeLancamento;
    private javax.swing.JLabel labelNovoNome;
    private javax.swing.JLabel labelNovoTipoDeMidia;
    private javax.swing.JLabel labelValueCategoriaDePrecoAnterior;
    private javax.swing.JLabel labelValueDisponibilidade;
    private javax.swing.JLabel labelValueIdDoFilme;
    private javax.swing.JList<String> listCategoriasDeFilme;
    private javax.swing.JTextArea textAreaNovaDescricao;
    private javax.swing.JTextArea textAreaNovasAnotacoes;
    private javax.swing.JTextField textFieldNovoAnoDeLancamento;
    private javax.swing.JTextField textFieldNovoNome;
    private javax.swing.JTextField textFieldNovoTipoDeMidia;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
