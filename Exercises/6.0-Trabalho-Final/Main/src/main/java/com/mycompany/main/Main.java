package com.mycompany.main;

// View
import com.mycompany.main.view.FrHome;
import javax.swing.SwingUtilities;

// DAO
import com.mycompany.main.model.dao.interfaces.IDAO;

/**
 *
 * @author guilh
 */
public class Main {
    public static void main(String[] args) {
        // DAO
        // IDAO meuDao = new SQLiteUsuarioDAO(); // Ou uma implementação de arquivo;
        // UsuarioController controller = new UsuarioController(meuDao);
        
        // com.formdev.flatlaf.FlatDarkLaf.setup();
        // SwingUtilities.invokeLater(() -> new FrHome().setVisible(true));
        
        FrHome view = new FrHome();
        view.setVisible(true);
    }
}
