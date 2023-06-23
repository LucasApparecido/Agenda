import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TabelaRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor,
                                                   boolean estaSelecionado, boolean temFoco,
                                                   int linha, int coluna) {
        Component componente = super.getTableCellRendererComponent(tabela, valor, estaSelecionado, temFoco, linha,
                coluna);

        if (linha % 2 == 0) {
            componente.setBackground(Color.WHITE); // Define o fundo como branco para linhas pares
        } else {
            componente.setBackground(Color.LIGHT_GRAY); // Define o fundo como cinza claro para linhas ímpares
        }

        if (estaSelecionado) {
            componente.setBackground(new Color(0, 255, 255)); // Define o fundo como ciano se a célula estiver selecionada
        }

        return componente;
    }
}
