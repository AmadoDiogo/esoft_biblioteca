public class Socio {
    private int id;
    private String nome;

    public Socio(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (ID: " + id + ")";
    }
}
