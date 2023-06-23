import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Consulta {

    Armazenamento arquivoDados = new Armazenamento("AGENDA.dat"); // Objeto responsável pelo armazenamento dos dados
    Iterator<ItemParaArmazenar> dadosIterator = arquivoDados.obterTodos(); // Iterator para percorrer os dados armazenados

    TelaAgenda agenda = new TelaAgenda(); // Objeto da classe TelaAgenda para exibição da interface

    public void realizarConsulta(String pesquisa, DefaultTableModel DTModeloTabelaContatos) {
        // Limpar a tabela atual
        DTModeloTabelaContatos.setRowCount(0);

        while (dadosIterator.hasNext()) {
            PessoaAgenda pessoa = (PessoaAgenda) dadosIterator.next(); // Obtém a próxima pessoa da iteração

            // Verificar se o nome ou o código da pessoa corresponde à pesquisa
            if (pessoa.getNome().toLowerCase().contains(pesquisa.toLowerCase())
                    || pessoa.getCodigo().equalsIgnoreCase(pesquisa)) {
                // Adicionar a pessoa à tabela
                String[] dados = { pessoa.getCodigo(), pessoa.getNome(), pessoa.getTelefone(),
                        pessoa.getEndereco(), pessoa.getAnotacoes() };
                DTModeloTabelaContatos.addRow(dados); // Adiciona os dados da pessoa na tabela
            }
        }
        if (DTModeloTabelaContatos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum contato encontrado"); // Exibe mensagem se nenhum contato for encontrado
            agenda.preencherTabelaContatos(); // Atualiza a tabela de contatos na interface
        }
    }
}
