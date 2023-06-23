import javax.swing.JOptionPane;

public class Consulta {

    public void realizarConsulta(String pesquisa) {
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
}
