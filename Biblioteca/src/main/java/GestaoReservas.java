import javax.swing.*;
import java.awt.event.ActionEvent;

public class GestaoReservas extends JFrame {
    private JPanel gestaoReservas;
    private JButton adicionarReservaButton;
    private JButton gestorReservasButton;
    private JButton removerReservaButton;
    private JButton listaReservasButton;
    private Biblioteca biblioteca;
    private ListaLivros listaLivros;
    private ListaReservas listaReservas;

    public GestaoReservas(Biblioteca biblioteca, ListaLivros listaLivros) {
        this.biblioteca = biblioteca;
        this.listaLivros = listaLivros;

        setContentPane(gestaoReservas);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        gestorReservasButton.addActionListener(this::voltaractionPerformed);
        adicionarReservaButton.addActionListener(this::adicioonaractionPerformed);
        removerReservaButton.addActionListener(this::removeractionPerformed);
        listaReservasButton.addActionListener(this::listaactionPerformed);
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
        biblioteca.setVisible(true);
    }

    public void adicioonaractionPerformed(ActionEvent e) {
        setVisible(false);
        new AdicionarReservas(listaLivros).setVisible(true);
    }

    public void removeractionPerformed(ActionEvent e) {

    }

    public void listaactionPerformed(ActionEvent e) {
        new ListaReservas().setVisible(true);
    }
}
