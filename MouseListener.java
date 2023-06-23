import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MouseListener extends MouseAdapter {

    private JanelaFlutuante janelaFlutuante; // Objeto que representa a janela flutuante
    private JTable tabelaContatos; // Tabela de contatos

    public MouseListener(JTable tabelaContatos) {
        this.tabelaContatos = tabelaContatos;
        janelaFlutuante = new JanelaFlutuante(); // Inicializa a janela flutuante
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        int linha = tabelaContatos.rowAtPoint(evt.getPoint()); // Obtém a linha da tabela onde ocorreu o clique do mouse
        
        if (!janelaFlutuante.estaAtiva()) { // Verifica se a janela flutuante não está ativa/visível
            janelaFlutuante.exibirJanelaFlutuante(linha); // Exibe a janela flutuante com base na linha clicada
        } else {
            janelaFlutuante.exibirContatoNaJanelaFlutuante(linha); // Exibe o contato na janela flutuante com base na linha clicada
        }
    }
}
