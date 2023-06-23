import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ActionListenerBotoes implements ActionListener {

    private int contador = 1;
    private int linhaSelecionada;
    private int novaLinha;
    private int totalLinhas;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == JBInserir) {
            acaoInserir();
        }

        if (e.getSource() == JBLimpar) {
            acaoLimpar();
        }

        if (e.getSource() == JBLimparConsulta) {
            acaoLimparConsulta();
        }

        if (e.getSource() == JBConsultar) {
            acaoConsultar();
        }

        if (e.getSource() == JBExcluir) {
            acaoExcluir();
        }

        if (e.getSource() == JBAlterar) {
            acaoAlterar();
        }

        if (e.getSource() == JBFechar) {
            acaoFechar();
        }

        if (e.getSource() == JBAnterior) {
            acaoAnterior();
        }

        if (e.getSource() == JBProximo) {
            acaoProximo();
        }
    }

    private void acaoInserir() {
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

    private void acaoLimpar() {
        JTFNome.setText("");
        JTFTelefone.setText("");
        JTFEndereco.setText("");
        JTAAnotacoes.setText("");
    }

    private void acaoLimparConsulta() {
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

    private void acaoConsultar() {
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

    private void acaoExcluir() {
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

    private void acaoAlterar() {
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

    private void acaoFechar() {
        JPJanelaFlutuante.dispose();
    }

    private void acaoAnterior() {
        linhaSelecionada = JTTabelaContatos.getSelectedRow();
        totalLinhas = DTModeloTabelaContatos.getRowCount();
        novaLinha = (linhaSelecionada == 0) ? totalLinhas - 1 : linhaSelecionada - 1;
        if (totalLinhas > 0) {
            JTTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
            exibirContatoNaJanelaFlutuante(novaLinha);
        }
    }

    private void acaoProximo() {
        linhaSelecionada = JTTabelaContatos.getSelectedRow();
        totalLinhas = DTModeloTabelaContatos.getRowCount();
        novaLinha = (linhaSelecionada == totalLinhas - 1) ? 0 : linhaSelecionada + 1;
        if (totalLinhas > 0) {
            JTTabelaContatos.setRowSelectionInterval(novaLinha, novaLinha);
            exibirContatoNaJanelaFlutuante(novaLinha);
        }
    }
}
