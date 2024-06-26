import javax.swing.*;
import java.awt.event.ActionEvent;

public class GestaoReservas extends JFrame {
    private JPanel gestaoReservas;
    private JButton adicionarReservaButton;
    private JButton removerReservaButton;
    private JButton listaReservasButton;
    private JButton gestorReservasButton;
    private Biblioteca biblioteca;
    private ListaLivros listaLivros;
    private ListaReservas listaReservas;
    private BibliotecaControlo bibliotecaControlo;

    public GestaoReservas(Biblioteca biblioteca, ListaLivros listaLivros, BibliotecaControlo bibliotecaControlo) {
        this.biblioteca = biblioteca;
        this.listaLivros = listaLivros;
        this.bibliotecaControlo = bibliotecaControlo;
        this.listaReservas = new ListaReservas(bibliotecaControlo);

        setContentPane(gestaoReservas);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        adicionarReservaButton.addActionListener(this::adicionaractionPerformed);
        removerReservaButton.addActionListener(this::removeractionPerformed);
        listaReservasButton.addActionListener(this::listaactionPerformed);
        gestorReservasButton.addActionListener(this::voltaractionPerformed);
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
        biblioteca.setVisible(true);
    }

    public void adicionaractionPerformed(ActionEvent e) {
        AdicionarReservas adicionarReservas = new AdicionarReservas(bibliotecaControlo, listaLivros);
        adicionarReservas.setVisible(true);
    }

    public void removeractionPerformed(ActionEvent e) {
        new RemoverReservaID( bibliotecaControlo).setVisible(true);
    }

    public void listaactionPerformed(ActionEvent e) {
        setVisible(false);
        listaReservas.updateReservaList(); // Atualiza a lista de reservas antes de exibir
        listaReservas.setGestaoReservas(this); // Passando a referência de GestaoReservas para ListaReservas
        listaReservas.setVisible(true);
    }

}
