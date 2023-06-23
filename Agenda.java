import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Agenda {
    public static void main(String args[]) {
        TelaAgenda tela1 = new TelaAgenda();
    }
}

class TelaAgenda extends JFrame {

    JFrame JPJanelaFlutuante;

    public static final int LARGURA = 1024;
    public static final int ALTURA = 500;

    Armazenamento arquivoDados = new Armazenamento("AGENDA.dat");

    // Obter todos os contatos do arquivo de armazenamento
    Iterator<ItemParaArmazenar> dadosIterator = arquivoDados.obterTodos();

    PessoaAgenda pa;

    // Paineis Principais
    JPanel JPCentral;
    JPanel JPDados;
    JPanel JPDadosWestVertical;
    JPanel JPNome;
    JPanel JPTelefone;
    JPanel JPEndereco;
    JPanel JPBotoes;
    JPanel JPAnotacoes;
    JPanel JPTabelaContatos;
    JPanel JPTituloTabelaContatos;
    JPanel JPTituloAnotacoes;
    JPanel JPTituloDadosPessoais;
    JPanel JPPesquisa;

    // Titulo Dados pessoais
    JLabel JLDados;

    // Campo nome
    JLabel JLNome;
    JTextField JTFNome;

    // Campo endereço
    JLabel JLEndereco;
    JTextField JTFEndereco;

    // Campo telefone
    JLabel JLTelefone;
    JTextField JTFTelefone;

    // Campo anotações
    JLabel JLAnotacoes;
    JTextArea JTAAnotacoes;
    JScrollPane JSPAnotacoes;

    JTextField JTFPesquisa;
    JLabel JLPesquisa;

    // Botoes
    JButton JBInserir;
    JButton JBConsultar;
    JButton JBLimpar;
    JButton JBAlterar;
    JButton JBExcluir;
    JButton JBFechar;
    JButton JBAnterior;
    JButton JBProximo;
    JButton JBPesquisar;
    JButton JBLimparConsulta;

    // Ações dos botões
    ActionListener actionListener = new ActionListenerBotoes();

    // Tabela
    JLabel JLTabelaContatos;
    JTable JTTabelaContatos;
    DefaultTableModel DTModeloTabelaContatos;
    JScrollPane JSPTabelaContatos;

    // Bordas
    int borderWidth = 1;
    int padding = 1;
    Color borderColor = Color.GRAY;

    String codigo;
    String nome;
    String telefone;
    String endereco;
    String anotacoes;

    JPanel JPPainelPrincipal;
    JPanel JPPainelCampos;
    JPanel JPConsultaCodigo;
    JPanel JPConsultaNome;
    JPanel JPConsultaTelefone;
    JPanel JPConsultaEndereco;
    JPanel JPConsultaAnotacoes;
    JPanel JPConsultaBotoesPrincipal;
    JPanel JPConsultaBotoesNavegacao;

    JLabel JLConsultaCodigo;
    JTextField JTFConsultaCodigo;

    JLabel JLConsultaNome;
    JTextField JTFConsultaNome;

    JLabel JLConsultaEndereco;
    JTextField JTFConsultaEndereco;

    JLabel JLConsultaTelefone;
    JTextField JTFConsultaTelefone;

    JLabel JLConsultaAnotacoes;
    JTextArea JTAConsultaAnotacoes;
    JScrollPane JSPConsultaAnotacoes;

    public TelaAgenda() {

        // setLocation(100, 100);

        setSize(TelaAgenda.LARGURA, TelaAgenda.ALTURA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon("./logo.png").getImage());

        getContentPane().setLayout(new BorderLayout());
        montarLayout();

        preencherTabelaContatos();

        setVisible(true);
    }

    private void montarLayout() {
        JLabel JLbanner = new JLabel(new ImageIcon("./banner.png"));

        getContentPane().add("North", JLbanner);

        JPCentral = new JPanel();// Border 1
        JPCentral.setLayout(new BorderLayout());

        JPDados = new JPanel(); // Border 2
        JPDados.setLayout(new BorderLayout());
        JPCentral.add("West", JPDados);

        JPDadosWestVertical = new JPanel();
        JPDadosWestVertical.setLayout(new BoxLayout(JPDadosWestVertical, BoxLayout.Y_AXIS));

        JLDados = new JLabel("DADOS PESSOAIS");
        JPTituloDadosPessoais = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPTituloDadosPessoais.add(JLDados);
        JPDadosWestVertical.add(JPTituloDadosPessoais, BorderLayout.NORTH);
        JPDados.add("West", JPDadosWestVertical);

        JPNome = new JPanel();
        JLNome = new JLabel("Nome:");
        JTFNome = new JTextField(10);

        JPNome.setLayout(new FlowLayout());
        JPNome.add(JLNome);
        JPNome.add(JTFNome);
        JPDadosWestVertical.add(JPNome);

        JPEndereco = new JPanel();
        JLEndereco = new JLabel("Endereço");
        JTFEndereco = new JTextField(10);

        JPEndereco.setLayout(new FlowLayout());
        JPEndereco.add(JLEndereco);
        JPEndereco.add(JTFEndereco);
        JPDadosWestVertical.add(JPEndereco);

        JPTelefone = new JPanel();
        JLTelefone = new JLabel("Telefone");
        JTFTelefone = new JTextField(10);

        JPTelefone.setLayout(new FlowLayout());
        JPTelefone.add(JLTelefone);
        JPTelefone.add(JTFTelefone);
        JPDadosWestVertical.add(JPTelefone);

        JPDadosWestVertical.add(Box.createVerticalStrut(500));

        JPAnotacoes = new JPanel();
        JPAnotacoes.setLayout(new BorderLayout());
        JLAnotacoes = new JLabel("ANOTAÇÕES");
        JPTituloAnotacoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPTituloAnotacoes.add(JLAnotacoes);
        JTAAnotacoes = new JTextArea(5, 20);
        JPAnotacoes.add(JPTituloAnotacoes, BorderLayout.NORTH);
        JPAnotacoes.add(JTAAnotacoes);

        JSPAnotacoes = new JScrollPane(JTAAnotacoes);
        JSPAnotacoes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JSPAnotacoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPAnotacoes.add(JSPAnotacoes);

        JPDados.add("Center", JPAnotacoes);

        JPBotoes = new JPanel();
        JPBotoes.setLayout(new FlowLayout());

        JBInserir = new JButton("Inserir");
        JBInserir.addActionListener(actionListener);
        JPBotoes.add(JBInserir);

        JBConsultar = new JButton("Consultar");
        JBConsultar.addActionListener(actionListener);
        JPBotoes.add(JBConsultar);

        JBLimpar = new JButton("Limpar");
        JBLimpar.addActionListener(actionListener);
        JPBotoes.add(JBLimpar);

        JBLimparConsulta = new JButton("Limpar Consulta");
        JBLimparConsulta.addActionListener(actionListener);
        JPBotoes.add(JBLimparConsulta);

        JPBotoes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, borderWidth),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding)));

        JPDados.add("South", JPBotoes);

        JPanel JPColunaDireita = new JPanel();
        JPColunaDireita.setLayout(new BoxLayout(JPColunaDireita, BoxLayout.X_AXIS));
        JPColunaDireita.add(Box.createHorizontalStrut(15));

        JPDados.add("East", JPColunaDireita);

        DTModeloTabelaContatos = new DefaultTableModel();
        DTModeloTabelaContatos.addColumn("Codigo");
        DTModeloTabelaContatos.addColumn("Nome");
        DTModeloTabelaContatos.addColumn("Telefone");
        DTModeloTabelaContatos.addColumn("Endereço");
        DTModeloTabelaContatos.addColumn("Anotações");

        JPTabelaContatos = new JPanel();
        JPTabelaContatos.setLayout(new BorderLayout());

        JLTabelaContatos = new JLabel("LISTA CONTATOS");
        JPTituloTabelaContatos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPTituloTabelaContatos.add(JLTabelaContatos);
        JPTabelaContatos.add(JPTituloTabelaContatos, BorderLayout.NORTH);

        JTTabelaContatos = new JTable(DTModeloTabelaContatos);
        JSPTabelaContatos = new JScrollPane(JTTabelaContatos);

        JTTabelaContatos.setDefaultRenderer(Object.class, new TabelaRenderer());

        JPTabelaContatos.add(JSPTabelaContatos, BorderLayout.CENTER);

        JPCentral.add("Center", JPTabelaContatos);

        getContentPane().add("Center", JPCentral);

        JTTabelaContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = JTTabelaContatos.rowAtPoint(evt.getPoint());
                if (JPJanelaFlutuante == null) {
                    JPJanelaFlutuante = new JFrame(); // Cria uma nova instância de JFrame
                }
                if (!JPJanelaFlutuante.isVisible()) {
                    JanelaFlutuante(linha);
                } else {
                    exibirContatoNaJanelaFlutuante(linha);
                }
            }
        });
    }

    private void armazenarDados() {
        int rowCount = DTModeloTabelaContatos.getRowCount();
        ArrayList<ItemParaArmazenar> dados = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            String codigo = (String) DTModeloTabelaContatos.getValueAt(i, 0);
            String nome = (String) DTModeloTabelaContatos.getValueAt(i, 1);
            String telefone = (String) DTModeloTabelaContatos.getValueAt(i, 2);
            String endereco = (String) DTModeloTabelaContatos.getValueAt(i, 3);
            String anotacoes = (String) DTModeloTabelaContatos.getValueAt(i, 4);

            PessoaAgenda pessoa = new PessoaAgenda(codigo, nome, endereco, telefone, anotacoes);
            dados.add(pessoa);
        }

        arquivoDados.armazenar(dados);
    }

    private void preencherTabelaContatos() {
        while (dadosIterator.hasNext()) {
            PessoaAgenda pessoa = (PessoaAgenda) dadosIterator.next();
            String[] dados = { pessoa.getCodigo(), pessoa.getNome(), pessoa.getTelefone(),
                    pessoa.getEndereco(), pessoa.getAnotacoes() };
            DTModeloTabelaContatos.addRow(dados);
        }
    }
}