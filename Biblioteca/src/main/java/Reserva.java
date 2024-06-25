public class Reserva {

    private static int proximoId = 1; // Variável estática para gerar o próximo ID
    private int idReserva;
    private int idSocio;
    private String nomeSocio;
    private String dataReserva;

    public Reserva(int idSocio, String nomeSocio, String dataReserva) {
        this.idReserva = proximoId++;
        this.idSocio = idSocio;
        this.nomeSocio = nomeSocio;
        this.dataReserva = dataReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public String getNomeSocio() {
        return nomeSocio;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %s, %s", idReserva, idSocio, nomeSocio, dataReserva);
    }
}
