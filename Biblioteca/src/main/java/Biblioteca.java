import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Biblioteca extends JFrame {
    private JPanel painelPrincipal;
    private JButton gestaoemprestimosbutton;
    private JButton gestaolivrosbutton;
    private JButton gestaosociosbutton;
    private JButton gestaoreservasbutton;
    private JButton gestaopesquisasbutton;
    private JButton gestaopagamentosbutton;
    private JLabel logo;
    private GestaoReservas gestaoReservas;
    private GestaoLivros gestaoLivros;
    private ListaLivros listaLivros;
    private ListaReservas listaReservas;

    public Biblioteca(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        // Initialize the listaLivros object
        listaLivros = new ListaLivros();

        // Pass the Biblioteca instance to GestaoLivros
        gestaoLivros = new GestaoLivros(this, listaLivros);

        gestaoReservas = new GestaoReservas(this, listaLivros);

        gestaolivrosbutton.addActionListener(this::gestaoLivroactionPerformed);
        gestaoreservasbutton.addActionListener(this::gestaoReservasactionPerformed);
    }

    public void gestaoLivroactionPerformed(ActionEvent e) {
        setVisible(false);
        gestaoLivros.setVisible(true);
    }

    public void gestaoReservasactionPerformed(ActionEvent e) {
        setVisible(false);
        gestaoReservas.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Biblioteca("Biblioteca").setVisible(true);
        });
    }
}
