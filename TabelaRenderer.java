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

        //se for impar define o fundo como branco
        //se for par define como cinza
        if (linha % 2 == 0) {
            componente.setBackground(Color.WHITE); 
        } else {
            componente.setBackground(Color.LIGHT_GRAY); 
        }

        //define o fundo da linha como ciano
        if (estaSelecionado) {
            componente.setBackground(new Color(0, 255, 255)); 
        }

        return componente;
    }
}
