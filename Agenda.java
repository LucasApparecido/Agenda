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
    ActionListener ALAcaoInserir;
    ActionListener ALAcaoConsultar;
    ActionListener ALAcaoLimpar;
    ActionListener ALAcaoAlterar;
    ActionListener ALAcaoExcluir;
    ActionListener ALAcaoFechar;
    ActionListener ALNavegarAnterior;
    ActionListener ALNavegarProximo;
    ActionListener ALAcaoPesquisar;
    ActionListener ALAcaoLimparConsulta;

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
        ALAcaoInserir = new ActionListenerBotoes();
        JBInserir.addActionListener(ALAcaoInserir);
        JPBotoes.add(JBInserir);

        JBConsultar = new JButton("Consultar");
        ALAcaoConsultar = new ActionListenerBotoes();
        JBConsultar.addActionListener(ALAcaoConsultar);
        JPBotoes.add(JBConsultar);

        JBLimpar = new JButton("Limpar");
        ALAcaoLimpar = new ActionListenerBotoes();
        JBLimpar.addActionListener(ALAcaoLimpar);
        JPBotoes.add(JBLimpar);

        JBLimparConsulta = new JButton("Limpar Consulta");
        ALAcaoLimparConsulta = new ActionListenerBotoes();
        JBLimparConsulta.addActionListener(ALAcaoLimparConsulta);
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

    public void JanelaFlutuante(int linha) {
        codigo = (String) DTModeloTabelaContatos.getValueAt(linha, 0);
        nome = (String) DTModeloTabelaContatos.getValueAt(linha, 1);
        telefone = (String) DTModeloTabelaContatos.getValueAt(linha, 2);
        endereco = (String) DTModeloTabelaContatos.getValueAt(linha, 3);
        anotacoes = (String) DTModeloTabelaContatos.getValueAt(linha, 4);

        JPJanelaFlutuante.setLayout(new BorderLayout());
        JPJanelaFlutuante.setSize(300, 400);
        JPJanelaFlutuante.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPJanelaFlutuante.setLocationRelativeTo(null);

        JPPainelPrincipal = new JPanel();
        JPPainelPrincipal.setLayout(new BorderLayout());
        JPJanelaFlutuante.add(JPPainelPrincipal);

        JPPainelCampos = new JPanel();
        JPPainelCampos.setLayout(new BoxLayout(JPPainelCampos, BoxLayout.Y_AXIS));
        JPPainelPrincipal.add(JPPainelCampos, BorderLayout.CENTER);

        JPPainelPrincipal = new JPanel();
        JPPainelPrincipal.setLayout(new BoxLayout(JPPainelPrincipal, BoxLayout.Y_AXIS));

        JPConsultaCodigo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLConsultaCodigo = new JLabel("Código:");
        JTFConsultaCodigo = new JTextField(10);
        JTFConsultaCodigo.setText(codigo);

        JPConsultaCodigo.add(JLConsultaCodigo);
        JPConsultaCodigo.add(JTFConsultaCodigo);
        JPPainelCampos.add(JPConsultaCodigo);

        JPConsultaNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLConsultaNome = new JLabel("Nome:");
        JTFConsultaNome = new JTextField(10);
        JTFConsultaNome.setText(nome);

        JPConsultaNome.add(JLConsultaNome);
        JPConsultaNome.add(JTFConsultaNome);
        JPPainelCampos.add(JPConsultaNome);

        JLConsultaEndereco = new JLabel("Endereço:");
        JPConsultaEndereco = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTFConsultaEndereco = new JTextField(10);
        JTFConsultaEndereco.setText(endereco);

        JPConsultaEndereco.add(JLConsultaEndereco);
        JPConsultaEndereco.add(JTFConsultaEndereco);
        JPPainelCampos.add(JPConsultaEndereco);

        JLConsultaTelefone = new JLabel("Telefone:");
        JPConsultaTelefone = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTFConsultaTelefone = new JTextField(10);
        JTFConsultaTelefone.setText(telefone);

        JPConsultaTelefone.add(JLConsultaTelefone);
        JPConsultaTelefone.add(JTFConsultaTelefone);
        JPPainelCampos.add(JPConsultaTelefone);

        JLConsultaAnotacoes = new JLabel("Anotações:");
        JTAConsultaAnotacoes = new JTextArea(3, 10);
        JTAConsultaAnotacoes.setText(anotacoes);

        JPConsultaAnotacoes = new JPanel(new BorderLayout());
        JPConsultaAnotacoes.add(JLConsultaAnotacoes, BorderLayout.NORTH);

        JSPConsultaAnotacoes = new JScrollPane(JTAConsultaAnotacoes);
        JSPConsultaAnotacoes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JSPConsultaAnotacoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPConsultaAnotacoes.add(JSPConsultaAnotacoes, BorderLayout.CENTER);

        JPPainelCampos.add(JPConsultaAnotacoes);

        JPConsultaBotoesNavegacao = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JBAnterior = new JButton("Anterior");
        ALNavegarAnterior = new ActionListenerBotoes();
        JBAnterior.addActionListener(ALNavegarAnterior);
        JPConsultaBotoesNavegacao.add(JBAnterior);

        JBProximo = new JButton("Próximo");
        ALNavegarProximo = new ActionListenerBotoes();
        JBProximo.addActionListener(ALNavegarProximo);
        JPConsultaBotoesNavegacao.add(JBProximo);

        JPConsultaBotoesPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JBAlterar = new JButton("Alterar");

        ALAcaoAlterar = new ActionListenerBotoes();
        JBAlterar.addActionListener(ALAcaoAlterar);
        JPConsultaBotoesPrincipal.add(JBAlterar);

        JBExcluir = new JButton("Excluir");
        ALAcaoExcluir = new ActionListenerBotoes();
        JBExcluir.addActionListener(ALAcaoExcluir);
        JPConsultaBotoesPrincipal.add(JBExcluir);

        JBFechar = new JButton("Fechar");
        ALAcaoFechar = new ActionListenerBotoes();
        JBFechar.addActionListener(ALAcaoFechar);
        JPConsultaBotoesPrincipal.add(JBFechar);

        JPConsultaBotoesPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, borderWidth),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding)));

        JPPainelPrincipal.add(JPPainelCampos);
        JPPainelPrincipal.add(JPConsultaBotoesNavegacao);
        JPPainelPrincipal.add(JPConsultaBotoesPrincipal);

        JPJanelaFlutuante.add(JPPainelPrincipal);

        JPJanelaFlutuante.getContentPane().add(JPPainelPrincipal);

        JPJanelaFlutuante.setVisible(true);
    }

    private void exibirContatoNaJanelaFlutuante(int indice) {
        codigo = (String) DTModeloTabelaContatos.getValueAt(indice, 0);
        nome = (String) DTModeloTabelaContatos.getValueAt(indice, 1);
        telefone = (String) DTModeloTabelaContatos.getValueAt(indice, 2);
        endereco = (String) DTModeloTabelaContatos.getValueAt(indice, 3);
        anotacoes = (String) DTModeloTabelaContatos.getValueAt(indice, 4);

        // Atualize os campos da janela flutuante com os dados do contato
        JTFConsultaCodigo.setText(codigo);
        JTFConsultaNome.setText(nome);
        JTFConsultaTelefone.setText(telefone);
        JTFConsultaEndereco.setText(endereco);
        JTAConsultaAnotacoes.setText(anotacoes);
    }

    private void realizarConsulta(String pesquisa) {
        // Limpar a tabela atual
        DTModeloTabelaContatos.setRowCount(0);

        while (dadosIterator.hasNext()) {
            PessoaAgenda pessoa = (PessoaAgenda) dadosIterator.next();

            // Verificar se o nome ou o código da pessoa corresponde à pesquisa
            if (pessoa.getNome().toLowerCase().contains(pesquisa.toLowerCase())
                    || pessoa.getCodigo().equalsIgnoreCase(pesquisa)) {
                // Adicionar a pessoa à tabela
                String[] dados = { pessoa.getCodigo(), pessoa.getNome(), pessoa.getTelefone(),
                        pessoa.getEndereco(), pessoa.getAnotacoes() };
                DTModeloTabelaContatos.addRow(dados);
            }
        }
        if (DTModeloTabelaContatos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum contato encontrado");
            preencherTabelaContatos();
        }
    }

    private class TabelaRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable tabela, Object valor,
                boolean estaSelecionado, boolean temFoco,
                int linha, int coluna) {
            Component componente = super.getTableCellRendererComponent(tabela, valor, estaSelecionado, temFoco, linha,
                    coluna);

            if (linha % 2 == 0) {
                componente.setBackground(Color.WHITE);
            } else {
                componente.setBackground(Color.LIGHT_GRAY);
            }

            if (estaSelecionado) {
                componente.setBackground(new Color(0, 255, 255));
            }

            return componente;
        }
    }

    class ActionListenerBotoes implements ActionListener {

        private int contador = 1;
        private int linhaSelecionada;
        private int novaLinha;
        private int totalLinhas;

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == JBInserir) {
                String nome = JTFNome.getText();
                String telefone = JTFTelefone.getText();
                String endereco = JTFEndereco.getText();
                String anotacoes = JTAAnotacoes.getText();

                if (nome.equals("") || telefone.equals("")) {
                    JOptionPane.showMessageDialog(null, "Nome ou telefone não podem ser vazios");
                } else {
                    String codigo = "P" + Integer.toString(contador);
                    String[] dados = { codigo, nome, telefone, endereco, anotacoes };
                    DTModeloTabelaContatos.addRow(dados);
                    contador++;
                    armazenarDados();
                }
            }

            if (e.getSource() == JBLimpar) {
                JTFNome.setText("");
                JTFTelefone.setText("");
                JTFEndereco.setText("");
                JTAAnotacoes.setText("");
            }

            if (e.getSource() == JBLimparConsulta) {
                while (DTModeloTabelaContatos.getRowCount() > 0) {
                    DTModeloTabelaContatos.removeRow(0);
                }
                while (dadosIterator.hasNext()) {
                PessoaAgenda pessoa = (PessoaAgenda) dadosIterator.next();
                String[] dados = { pessoa.getCodigo(), pessoa.getNome(), pessoa.getTelefone(),
                        pessoa.getEndereco(), pessoa.getAnotacoes() };
                DTModeloTabelaContatos.addRow(dados);
            }
            }

            if (e.getSource() == JBConsultar) {
                linhaSelecionada = JTTabelaContatos.getSelectedRow();
                if (linhaSelecionada >= 0) {
                    JanelaFlutuante(linhaSelecionada);
                } else {
                    String termoPesquisa = JOptionPane.showInputDialog("Digite o nome ou código a ser pesquisado:");
                    if (termoPesquisa == null) {
                        JOptionPane.showMessageDialog(null, "Digite um valor para pesquisar");
                    } else {
                        realizarConsulta(termoPesquisa);
                        linhaSelecionada = 0; // Seleciona a primeira linha
                        if (linhaSelecionada < JTTabelaContatos.getRowCount()) {
                            JTTabelaContatos.setRowSelectionInterval(linhaSelecionada, linhaSelecionada);
                            JTTabelaContatos.scrollRectToVisible(JTTabelaContatos.getCellRect(linhaSelecionada, 0, true));
                            JanelaFlutuante(linhaSelecionada);
                        }
                    }
                }
            }

            if (e.getSource() == JBExcluir) {
                linhaSelecionada = JTTabelaContatos.getSelectedRow();
                totalLinhas = DTModeloTabelaContatos.getRowCount();
                novaLinha = (linhaSelecionada == totalLinhas - 1) ? 0 : linhaSelecionada;
                if (totalLinhas == 1) {
                    DTModeloTabelaContatos.removeRow(linhaSelecionada);
                    armazenarDados();
                    JOptionPane.showMessageDialog(null, "A Agenda não tem mais contatos");
                    JPJanelaFlutuante.dispose();
                } else if (linhaSelecionada >= 0) {
                    DTModeloTabelaContatos.removeRow(linhaSelecionada);
                    armazenarDados();
                    JTTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
                    exibirContatoNaJanelaFlutuante(novaLinha);
                    JOptionPane.showMessageDialog(null, "A linha foi excluída");
                }
            }

            if (e.getSource() == JBAlterar) {
                linhaSelecionada = JTTabelaContatos.getSelectedRow();
                if (linhaSelecionada != -1) {
                    String novoNome = JTFConsultaNome.getText();
                    String novoTelefone = JTFConsultaTelefone.getText();
                    String novoEndereco = JTFConsultaEndereco.getText();
                    String novasAnotacoes = JTAConsultaAnotacoes.getText();

                    DTModeloTabelaContatos.setValueAt(novoNome, linhaSelecionada, 1);
                    DTModeloTabelaContatos.setValueAt(novoTelefone, linhaSelecionada, 2);
                    DTModeloTabelaContatos.setValueAt(novoEndereco, linhaSelecionada, 3);
                    DTModeloTabelaContatos.setValueAt(novasAnotacoes, linhaSelecionada, 4);

                    // Armazenar os dados atualizados
                    armazenarDados();
                    JOptionPane.showMessageDialog(null, "Contato alterado com sucesso!");
                }
            }

            if (e.getSource() == JBFechar) {
                JPJanelaFlutuante.dispose();
            }

            if (e.getSource() == JBAnterior) {
                linhaSelecionada = JTTabelaContatos.getSelectedRow();
                totalLinhas = DTModeloTabelaContatos.getRowCount();
                novaLinha = (linhaSelecionada == 0) ? totalLinhas - 1 : linhaSelecionada - 1;
                if (totalLinhas > 0) {
                    JTTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
                    exibirContatoNaJanelaFlutuante(novaLinha);
                }
            }

            if (e.getSource() == JBProximo) {
                linhaSelecionada = JTTabelaContatos.getSelectedRow();
                totalLinhas = DTModeloTabelaContatos.getRowCount();
                novaLinha = (linhaSelecionada == totalLinhas - 1) ? 0 : linhaSelecionada + 1;
                if (totalLinhas > 0) {
                    JTTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
                    exibirContatoNaJanelaFlutuante(novaLinha);
                }
            }
        }
    }
}