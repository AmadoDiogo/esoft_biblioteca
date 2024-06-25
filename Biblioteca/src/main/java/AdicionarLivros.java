import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdicionarLivros extends JFrame {
    private JPanel adicionarLivros;
    private JButton voltarButton;
    private JButton registarLivroButton;
    private JTextField tituloinsert;
    private JTextField autorinsert;
    private JTextField subgeneroinsert;
    private JTextField nedicaoinsert;
    private JTextField isbninsert;
    private JTextField fornecedorinsert;
    private JTextField quantidadeinsert;
    private JTextField generoinsert;
    private JTextField editorainsert;
    private JTextField anoinsert;
    private JTextField codigoinsert;
    private JTextField estantaprateleirainsert;
    private ListaLivros listaLivros;
    private GestaoLivros gestaoLivros;

    public AdicionarLivros(ListaLivros listaLivros) {
        this.listaLivros = listaLivros;

        setContentPane(adicionarLivros);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        voltarButton.addActionListener(this::voltarButtonActionPerformed);
        registarLivroButton.addActionListener(this::registaractionPerformed);
    }

    public void setGestaoLivros(GestaoLivros gestaoLivros) {
        this.gestaoLivros = gestaoLivros;
    }

    public void voltarButtonActionPerformed(ActionEvent e) {
        setVisible(false);
        listaLivros.setVisible(true);
    }

    public void registaractionPerformed(ActionEvent e) {
        adicionarLivro();
    }

    private void adicionarLivro() {
        String titulo = tituloinsert.getText();
        String autor = autorinsert.getText();
        String subgenero = subgeneroinsert.getText();
        String numeroEdicaostr = nedicaoinsert.getText();
        String isbnstr = isbninsert.getText();
        String fornecedor = fornecedorinsert.getText();
        String quantidadestr = quantidadeinsert.getText();
        String genero = generoinsert.getText();
        String editora = editorainsert.getText();
        String anoPublicacaostr = anoinsert.getText();
        String codigo = codigoinsert.getText();
        String estantePrateleira = estantaprateleirainsert.getText();

        int quantidade = Integer.parseInt(quantidadestr);
        int numeroEdicao = Integer.parseInt(numeroEdicaostr);
        int isbn = Integer.parseInt(isbnstr);
        int anoPublicacao = Integer.parseInt(anoPublicacaostr);

        Livro livro = new Livro(titulo, autor, subgenero, numeroEdicao, isbn, fornecedor, quantidade, genero, editora, anoPublicacao, codigo, estantePrateleira);
        listaLivros.adicionarLivro(livro);

        System.out.println("Novo livro registrado:");
        System.out.println("ID: " + livro.getIdCompra());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Subgênero: " + livro.getSubgenero());
        System.out.println("Número de Edição: " + livro.getNumeroEdicao());
        System.out.println("ISBN: " + livro.getIsbn());
        System.out.println("Fornecedor: " + livro.getFornecedor());
        System.out.println("Quantidade: " + livro.getQuantidade());
        System.out.println("Gênero: " + livro.getGenero());
        System.out.println("Editora: " + livro.getEditora());
        System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
        System.out.println("Código: " + livro.getCodigo());
        System.out.println("Estante/Prateleira: " + livro.getEstantePrateleira());

        setVisible(false);
        listaLivros.setVisible(true);
    }
}
