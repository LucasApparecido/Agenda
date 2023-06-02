import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Agenda {
    public static void main(String args[]) {
        TelaAgenda tela1 = new TelaAgenda();      
    }
}
class TelaAgenda extends JFrame {
    
    public static final int LARGURA = 1280;
    public static final int ALTURA = 500;

    JPanel JPCentral;
    JPanel JPDados;
    JPanel JPDadosWestVertical;
    JPanel JPNome;
    JPanel JPTelefone;
    JPanel JPEndereco;
    JPanel JPBotoes;
    JPanel JPAnotacoes;

    JLabel JLNome;
    JTextField JTFNome;

    JLabel JLEndereco;
    JTextField JTFEndereco;

    JLabel JLTelefone;
    JTextField JTFTelefone;

    JLabel JLAnotacoes;
    JTextArea JTAAnotacoes;
    JScrollPane SPHAnotacoes;
    JScrollPane SPVAnotacoes;

    JButton JBInserir;
    JButton JBConsultar;
    JButton JBAlterar;
    JButton JBExcluir;
    JButton JBLimpar;

    JTable JTTabelaContatos;
    DefaultTableModel modeloTabelaContatos;
    JScrollPane JSPTabelaContatos;

    int contadorID = 1;


    
    public TelaAgenda(){

        setSize(TelaAgenda.LARGURA, TelaAgenda.ALTURA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon("./logo.png").getImage());

        getContentPane().setLayout(new BorderLayout());
        montarLayout();

        setVisible(true);
    }

    private void montarLayout(){
        JLabel JLbanner = new JLabel(new ImageIcon("./banner.png"));

        getContentPane().add("North", JLbanner);

        JPCentral = new JPanel();//Border 1
        JPCentral.setLayout(new BorderLayout());

        JPDados = new JPanel(); //Border 2
        JPDados.setLayout(new BorderLayout());
        JPCentral.add("West", JPDados);

        JPDadosWestVertical = new JPanel();
        JPDadosWestVertical.setLayout(new BoxLayout(JPDadosWestVertical,
            BoxLayout.Y_AXIS));
        JPDados.add("West", JPDadosWestVertical);

        JPNome = new JPanel();
        JPNome.setLayout(new FlowLayout());
        JLNome = new JLabel("Nome:");
        JPNome.add(JLNome);
        JTFNome = new JTextField(10);
        JPNome.add(JTFNome);

        JPDadosWestVertical.add(JPNome);

        JPTelefone = new JPanel();
        JPTelefone.setLayout(new FlowLayout());
        JLTelefone = new JLabel("Telefone");
        JPTelefone.add(JLTelefone);
        JTFTelefone = new JTextField(10);
        JPTelefone.add(JTFTelefone);

        JPDadosWestVertical.add(JPTelefone);

        JPEndereco = new JPanel();
        JPEndereco.setLayout(new FlowLayout());
        JLEndereco = new JLabel("Endereço");
        JPEndereco.add(JLEndereco);
        JTFEndereco = new JTextField(10);
        JPEndereco.add(JTFEndereco);
    

        JPDadosWestVertical.add(JPEndereco);

        JPDadosWestVertical.add(Box.createVerticalStrut(500));

        JPAnotacoes = new JPanel();
        JPAnotacoes.setLayout(new BoxLayout(JPAnotacoes, BoxLayout.Y_AXIS));
        JLAnotacoes = new JLabel("Anotações");
        JPAnotacoes.add(JLAnotacoes);
        JTAAnotacoes = new JTextArea(5, 20);
        JPAnotacoes.add(JTAAnotacoes);

        SPHAnotacoes = new JScrollPane(JTAAnotacoes);
        SPHAnotacoes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPAnotacoes.add(SPHAnotacoes);
        SPVAnotacoes = new JScrollPane(JTAAnotacoes);
        SPVAnotacoes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPAnotacoes.add(SPVAnotacoes);
        

        JPDados.add("Center", JPAnotacoes);

        JPBotoes = new JPanel();
        JPBotoes.setLayout(new FlowLayout());

        JBInserir = new JButton("Inserir");
        ActionListener acaoInserir = new AcaoInserir();
        JBInserir.addActionListener(acaoInserir);
        JPBotoes.add(JBInserir);

        JBConsultar = new JButton("Consultar");
        JPBotoes.add(JBConsultar);

        JBAlterar = new JButton("Alterar");
        JPBotoes.add(JBAlterar);

        JBExcluir = new JButton("Excluir");
        ActionListener acaoExcluir = new AcaoExcluir();
        JBExcluir.addActionListener(acaoExcluir);
        JPBotoes.add(JBExcluir);

        JBLimpar = new JButton("Limpar");
        ActionListener acaoLimpar = new AcaoLimpar();
        JBLimpar.addActionListener(acaoLimpar);
        JPBotoes.add(JBLimpar);

        JPDados.add("South", JPBotoes);

        JPanel JPColunaDireita = new JPanel();
        JPColunaDireita.setLayout(new BoxLayout(JPColunaDireita, BoxLayout.X_AXIS));
        JPColunaDireita.add(Box.createHorizontalStrut(15));

        JPDados.add("East", JPColunaDireita);

        modeloTabelaContatos = new DefaultTableModel();
        modeloTabelaContatos.addColumn("ID");
        modeloTabelaContatos.addColumn("Nome");
        modeloTabelaContatos.addColumn("Telefone");
        modeloTabelaContatos.addColumn("Endereço");
        modeloTabelaContatos.addColumn("Anotações");

        JTTabelaContatos = new JTable(modeloTabelaContatos);
        JSPTabelaContatos = new JScrollPane(JTTabelaContatos);

        JPCentral.add("Center", JSPTabelaContatos);

        getContentPane().add("Center", JPCentral);
        JPCentral.setBackground(Color.green);
    }

    private class AcaoInserir implements ActionListener {

        public void actionPerformed(ActionEvent inserir){
            if(inserir.getSource() == JBInserir){
                String nome = JTFNome.getText();
                String telefone = JTFTelefone.getText();
                String anotacoes = JTAAnotacoes.getText();

                if(nome.equals("") || telefone.equals("")){
                    JOptionPane.showMessageDialog(null, "Nome ou telefone não podem ser vazios");
                }else{
                    String id = ("P" + Integer.toString(contadorID));
                    String[] dados = {id, nome, telefone, anotacoes};
                    modeloTabelaContatos.addRow(dados);
                    contadorID++; 
                }
            }
        }

    }

    private class AcaoConsultar implements ActionListener {

        public void actionPerformed(ActionEvent consultar){
            
        }

    }

    private class AcaoAlterar implements ActionListener {

        public void actionPerformed(ActionEvent alterar){
            
        }

    }

    private class AcaoExcluir implements ActionListener {

        public void actionPerformed(ActionEvent excluir){
            if(excluir.getSource() == JBExcluir){
                int linhaSelecionada = JTTabelaContatos.getSelectedRow();
                if(linhaSelecionada >= 0){
                    modeloTabelaContatos.removeRow(linhaSelecionada);
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                }
            }
        }

    }

    private class AcaoLimpar implements ActionListener {

        public void actionPerformed(ActionEvent limpar){
            if(limpar.getSource() == JBLimpar){
                int rowCount = modeloTabelaContatos.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    modeloTabelaContatos.removeRow(i);
                }
            }
        }
    }

}
