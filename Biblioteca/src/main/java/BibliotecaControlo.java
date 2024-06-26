import java.util.*;

public class BibliotecaControlo {
    private List<Livro> livros;
    private Map<Livro, List<Reserva>> reservas;
    private List<Socio> socios;

    public BibliotecaControlo() {
        livros = new ArrayList<>();
        reservas = new HashMap<>();
        socios = new ArrayList<>();
    }

    public List<Livro> getLivros() {
        return new ArrayList<>(livros); // Retorna uma cópia da lista para evitar modificações externas
    }

    public List<Socio> getSocios() {
        return new ArrayList<>(socios); // Retorna uma cópia da lista de sócios
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
        reservas.remove(livro); // Remove também as reservas associadas ao livro
    }

    public void atualizarLivro(Livro livro) {
        // Implementar lógica de atualização conforme necessário
    }

    public void adicionarReserva(Livro livro, Reserva reserva) {
        if (!reservas.containsKey(livro)) {
            reservas.put(livro, new ArrayList<>());
        }
        reservas.get(livro).add(reserva);
    }

    public boolean removerReservaPorId(int idReserva) {
        for (List<Reserva> listaReservas : reservas.values()) {
            Iterator<Reserva> iterator = listaReservas.iterator();
            while (iterator.hasNext()) {
                Reserva reserva = iterator.next();
                if (reserva.getIdReserva() == idReserva) {
                    iterator.remove();
                    return true; // Remoção bem-sucedida
                }
            }
        }
        return false; // Reserva não encontrada
    }

    public List<Livro> getLivrosReservados() {
        return new ArrayList<>(reservas.keySet());
    }
}
