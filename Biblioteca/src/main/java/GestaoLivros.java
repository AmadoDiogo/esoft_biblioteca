import javax.swing.*;
import java.awt.event.ActionEvent;

public class GestaoLivros extends JFrame {
    private JPanel gestaolivros;
    private JButton adicionarLivroButton;
    private JButton gestorAquisicoesButton;
    private JButton removerLivroButton;
    private JButton listaLivroButton;
    private Biblioteca biblioteca;
    private ListaLivros listaLivros;

    public GestaoLivros(Biblioteca biblioteca, ListaLivros listaLivros) {
        this.biblioteca = biblioteca;
        this.listaLivros = listaLivros;

        setContentPane(gestaolivros);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        gestorAquisicoesButton.addActionListener(this::voltaractionPerformed);
        adicionarLivroButton.addActionListener(this::adicioonaractionPerformed);
        removerLivroButton.addActionListener(this::removeractionPerformed);
        listaLivroButton.addActionListener(this::listaactionPerformed);
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
        biblioteca.setVisible(true);
    }

    public void adicioonaractionPerformed(ActionEvent e) {
        setVisible(false);
        new AdicionarLivros(listaLivros).setVisible(true);
    }

    public void removeractionPerformed(ActionEvent e) {
        setVisible(false);
        new RemoverLivrosID(this, listaLivros).setVisible(true);
    }

    public void listaactionPerformed(ActionEvent e) {
        setVisible(false);
        listaLivros.setGestaoLivros(this);
        listaLivros.setVisible(true);
    }
}
