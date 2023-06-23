import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaFlutuante {

    private String codigo;
    private String nome;
    private String telefone;
    private String endereco;
    private String anotacoes;

    private JFrame JPJanelaFlutuante;
    private JPanel JPPainelPrincipal;
    private JPanel JPPainelCampos;
    private JPanel JPConsultaCodigo;
    private JLabel JLConsultaCodigo;
    private JTextField JTFConsultaCodigo;
    private JPanel JPConsultaNome;
    private JLabel JLConsultaNome;
    private JTextField JTFConsultaNome;
    private JLabel JLConsultaEndereco;
    private JPanel JPConsultaEndereco;
    private JTextField JTFConsultaEndereco;
    private JLabel JLConsultaTelefone;
    private JPanel JPConsultaTelefone;
    private JTextField JTFConsultaTelefone;
    private JLabel JLConsultaAnotacoes;
    private JTextArea JTAConsultaAnotacoes;
    private JPanel JPConsultaAnotacoes;
    private JScrollPane JSPConsultaAnotacoes;
    private JPanel JPConsultaBotoesNavegacao;
    private JButton JBAnterior;
    private JButton JBProximo;
    private JPanel JPConsultaBotoesPrincipal;
    private JButton JBAlterar;
    private JButton JBExcluir;
    private JButton JBFechar;

    private ActionListener actionListener;

    public void exibirJanelaFlutuante(int linha) {
        codigo = (String) DTModeloTabelaContatos.getValueAt(linha, 0);
        nome = (String) DTModeloTabelaContatos.getValueAt(linha, 1);
        telefone = (String) DTModeloTabelaContatos.getValueAt(linha, 2);
        endereco = (String) DTModeloTabelaContatos.getValueAt(linha, 3);
        anotacoes = (String) DTModeloTabelaContatos.getValueAt(linha, 4);

        JPJanelaFlutuante = new JFrame();
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
        JBAnterior.addActionListener(actionListener);
        JPConsultaBotoesNavegacao.add(JBAnterior);

        JBProximo = new JButton("Próximo");
        JBProximo.addActionListener(actionListener);
        JPConsultaBotoesNavegacao.add(JBProximo);

        JPConsultaBotoesPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JBAlterar = new JButton("Alterar");
        JBAlterar.addActionListener(actionListener);
        JPConsultaBotoesPrincipal.add(JBAlterar);

        JBExcluir = new JButton("Excluir");
        JBExcluir.addActionListener(actionListener);
        JPConsultaBotoesPrincipal.add(JBExcluir);

        JBFechar = new JButton("Fechar");
        JBFechar.addActionListener(actionListener);
        JPConsultaBotoesPrincipal.add(JBFechar);

        JPConsultaBotoesPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JPPainelPrincipal.add(JPPainelCampos);
        JPPainelPrincipal.add(JPConsultaBotoesNavegacao);
        JPPainelPrincipal.add(JPConsultaBotoesPrincipal);

        JPJanelaFlutuante.setVisible(true);
    }

    public void exibirContatoNaJanelaFlutuante(int indice) {
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

    public boolean estaAtiva() {
        return JPJanelaFlutuante.isVisible();
    }
}
