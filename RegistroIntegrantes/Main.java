import javax.swing.SwingUtilities;

import controlador.IntegranteController;
import vista.FormularioIntegrante;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioIntegrante vista = new FormularioIntegrante();
            new IntegranteController(vista);
            vista.setVisible(true);
        });
    }
}
