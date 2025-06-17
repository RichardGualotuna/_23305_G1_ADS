import presentacion.EstudianteUI;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EstudianteUI::new);
    }
}
