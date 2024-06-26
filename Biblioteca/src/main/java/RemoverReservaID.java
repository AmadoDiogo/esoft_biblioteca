import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoverReservaID extends JFrame {
    private JPanel removerReservaPanel;
    private JLabel idReservaLabel;
    private JTextField idReservaField;
    private JButton voltarButton;
    private JButton removerReservaButton;
    private BibliotecaControlo bibliotecaControlo;
    private GestaoReservas gestaoReservas;

    public RemoverReservaID(BibliotecaControlo bibliotecaControlo) {
        this.bibliotecaControlo = bibliotecaControlo;

        // Configuração da interface gráfica
        setContentPane(removerReservaPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Ação do botão "Voltar"
        voltarButton.addActionListener(this::voltarActionPerformed);

        // Ação do botão "Remover Reserva"
        removerReservaButton.addActionListener(this::removerReservaActionPerformed);
    }

    public void setGestaoReservas(GestaoReservas gestaoReservas){
        this.gestaoReservas = gestaoReservas;
    }
    public void voltarActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public void removerReservaActionPerformed(ActionEvent e) {
        // Obtém o ID da reserva inserido pelo usuário
        String idReservaStr = idReservaField.getText().trim();
        if (idReservaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o ID da reserva para remover.");
            return;
        }

        try {
            int idReserva = Integer.parseInt(idReservaStr);

            // Chama o método para remover a reserva com o ID especificado
            boolean reservaRemovida = bibliotecaControlo.removerReservaPorId(idReserva);

            if (reservaRemovida) {
                JOptionPane.showMessageDialog(this, "Reserva removida com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Reserva não encontrada com o ID especificado.");
            }

            // Após remover, limpa o campo de ID e mantém a janela aberta para novas ações
            idReservaField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID da reserva deve ser um número inteiro.");
        }
        setVisible(false);
        gestaoReservas.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RemoverReservaID(null).setVisible(true);
        });
    }
}
