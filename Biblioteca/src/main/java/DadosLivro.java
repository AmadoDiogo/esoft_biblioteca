import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DadosLivro extends JFrame {
    private JPanel dadosLivroPanel;
    private JLabel idcompraLabel;
    private JLabel tituloLabel;
    private JLabel autorLabel;
    private JLabel subgeneroLabel;
    private JLabel numeroEdicaoLabel;
    private JLabel isbnLabel;
    private JLabel fornecedorLabel;
    private JLabel editoraLabel;
    private JLabel anoPublicacaoLabel;
    private JLabel estantePrateleiraLabel;
    private JButton voltarButton;

    public DadosLivro(Livro livro) {
        setTitle("Dados do Livro");
        setContentPane(dadosLivroPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        idcompraLabel.setText("ID: " + livro.getIdCompra());
        tituloLabel.setText(livro.getTitulo());
        autorLabel.setText(livro.getAutor());
        subgeneroLabel.setText("Genero/Subgênero: " + livro.getGenero() + livro.getSubgenero());
        numeroEdicaoLabel.setText("Número de Edição: " + livro.getNumeroEdicao());
        isbnLabel.setText("ISBN: " + livro.getIsbn());
        fornecedorLabel.setText("Fornecedor: " + livro.getFornecedor());
        editoraLabel.setText("Editora: " + livro.getEditora());
        anoPublicacaoLabel.setText("Ano de Publicação: " + livro.getAnoPublicacao());
        estantePrateleiraLabel.setText("Estante/Prateleira: " + livro.getEstantePrateleira());

        voltarButton.addActionListener(this::voltaractionPerformed);
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
    }
}


