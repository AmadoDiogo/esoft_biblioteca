import javax.swing.*;
import java.awt.event.ActionEvent;

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
    private BibliotecaControlo bibliotecaControlo;

    public Biblioteca(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        bibliotecaControlo = new BibliotecaControlo();

        // Adicionar livros iniciais
        adicionarLivrosIniciais();
        // Adicionar reservas iniciais
        adicionarReservasIniciais();

        listaLivros = new ListaLivros(bibliotecaControlo);
        listaReservas = new ListaReservas(bibliotecaControlo);

        gestaoLivros = new GestaoLivros(this, listaLivros);
        gestaoReservas = new GestaoReservas(this,listaLivros, bibliotecaControlo);

        gestaolivrosbutton.addActionListener(this::gestaoLivroactionPerformed);
        gestaoreservasbutton.addActionListener(this::gestaoReservasactionPerformed);
    }

    private void adicionarLivrosIniciais() {
        Livro livro1 = new Livro("Livro A", "Autor A", "Ficção", 1, 123456, "Fornecedor A", 10, "Aventura", "Editora A", 2020, "COD1", "Estante A");
        Livro livro2 = new Livro("Livro B", "Autor B", "Ficção Científica", 1, 234567, "Fornecedor B", 5, "Sci-Fi", "Editora B", 2018, "COD2", "Estante B");
        Livro livro3 = new Livro("Livro C", "Autor C", "Romance", 1, 345678, "Fornecedor C", 8, "Romance", "Editora C", 2019, "COD3", "Estante C");

        bibliotecaControlo.adicionarLivro(livro1);
        bibliotecaControlo.adicionarLivro(livro2);
        bibliotecaControlo.adicionarLivro(livro3);
    }

    private void adicionarReservasIniciais() {
        Livro livro1 = bibliotecaControlo.getLivros().get(0);
        Livro livro2 = bibliotecaControlo.getLivros().get(1);

        Reserva reserva1 = new Reserva(1, "Socio 1", "2023-06-01");
        Reserva reserva2 = new Reserva(2, "Socio 2", "2023-06-02");

        bibliotecaControlo.adicionarReserva(livro1, reserva1);
        bibliotecaControlo.adicionarReserva(livro2, reserva2);
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
