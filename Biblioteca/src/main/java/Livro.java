import java.util.ArrayList;
import java.util.List;

public class Livro {
    private static int proximoId = 1;
    private int idCompra;
    private String titulo;
    private String autor;
    private String subgenero;
    private int numeroEdicao;
    private int isbn;
    private String fornecedor;
    private int quantidade;
    private String genero;
    private String editora;
    private int anoPublicacao;
    private String codigo;
    private String estantePrateleira;

    // Lista de reservas associadas ao livro
    private List<Reserva> reservas;

    public Livro(String titulo, String autor, String subgenero, int numeroEdicao,
                 int isbn, String fornecedor, int quantidade, String genero,
                 String editora, int anoPublicacao, String codigo, String estantePrateleira) {
        this.idCompra = proximoId++;
        this.titulo = titulo;
        this.autor = autor;
        this.subgenero = subgenero;
        this.numeroEdicao = numeroEdicao;
        this.isbn = isbn;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
        this.genero = genero;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.codigo = codigo;
        this.estantePrateleira = estantePrateleira;
        this.reservas = new ArrayList<>();
    }

    public int getIdCompra() {
        return idCompra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSubgenero() {
        return subgenero;
    }

    public void setSubgenero(String subgenero) {
        this.subgenero = subgenero;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public void setNumeroEdicao(int numeroEdicao) {
        this.numeroEdicao = numeroEdicao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstantePrateleira() {
        return estantePrateleira;
    }

    public void setEstantePrateleira(String estantePrateleira) {
        this.estantePrateleira = estantePrateleira;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    @Override
    public String toString() {
        return String.format("%d - %s - %s", idCompra, titulo, autor);
    }
}
