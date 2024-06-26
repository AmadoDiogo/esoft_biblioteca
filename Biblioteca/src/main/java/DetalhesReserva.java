import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class DetalhesReserva extends JFrame {
    private JPanel detalhesReservaPanel;
    private JList<Reserva> reservaList;

    private JButton voltarButton;
    private JButton removerButton;
    private JTextField searchField; // Campo de busca

    private GestaoReservas gestaoReservas;

    private List<Reserva> reservas;
    private DefaultListModel<Reserva> listModel;

    public DetalhesReserva(List<Reserva> reservas) {
        this.reservas = reservas;
        listModel = new DefaultListModel<>();
        reservaList.setModel(listModel);

        // Definindo o renderer personalizado para a lista
        reservaList.setCellRenderer(new ReservaListRenderer());

        setContentPane(detalhesReservaPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        voltarButton.addActionListener(this::voltarActionPerformed);
        removerButton.addActionListener(this::removerActionPerformed);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterReservas();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterReservas();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterReservas();
            }
        });

        // Atualiza a lista com as reservas fornecidas
        updateReservaList();
    }

    private void updateReservaList() {
        listModel.clear();
        for (Reserva reserva : reservas) {
            listModel.addElement(reserva);
        }
    }

    private void filterReservas() {
        String searchText = searchField.getText().toLowerCase();
        listModel.clear();
        for (Reserva reserva : reservas) {
            if (reserva.getNomeSocio().toLowerCase().contains(searchText) || reserva.getDataReserva().toLowerCase().contains(searchText) || String.valueOf(reserva.getIdReserva()).contains(searchText) || String.valueOf(reserva.getIdSocio()).contains(searchText)) {
                listModel.addElement(reserva);
            }
        }
    }

    private void voltarActionPerformed(ActionEvent e) {
        setVisible(false);
        // Implemente o código para voltar à tela anterior, se necessário
    }

    private void removerActionPerformed(ActionEvent e) {
        int selectedIndex = reservaList.getSelectedIndex();
        if (selectedIndex != -1) {
            Reserva reserva = listModel.getElementAt(selectedIndex);
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover a reserva selecionada?",
                    "Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                // Remova a reserva da lista e da fonte de dados
                reservas.remove(reserva);
                listModel.removeElement(reserva);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva para remover.");
        }
    }

    private static class ReservaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            JPanel panel = new JPanel(new GridLayout(1, 4));
            panel.setBackground(new Color(0x2F3436)); // Cor de fundo

            if (value instanceof Reserva) {
                Reserva reserva = (Reserva) value;

                JLabel idReservaLabel = createStyledLabel(String.valueOf(reserva.getIdReserva()));
                JLabel idSocioLabel = createStyledLabel(String.valueOf(reserva.getIdSocio()));
                JLabel nomeSocioLabel = createStyledLabel(reserva.getNomeSocio());
                JLabel dataReservaLabel = createStyledLabel(reserva.getDataReserva());

                panel.add(idReservaLabel);
                panel.add(idSocioLabel);
                panel.add(nomeSocioLabel);
                panel.add(dataReservaLabel);
            }

            if (isSelected) {
                panel.setBackground(list.getSelectionBackground());
                panel.setForeground(list.getSelectionForeground());
            } else {
                panel.setBackground(list.getBackground());
                panel.setForeground(list.getForeground());
            }

            return panel;
        }

        private JLabel createStyledLabel(String text) {
            JLabel label = new JLabel(text, JLabel.CENTER);
            label.setForeground(new Color(0x5F6368)); // Cor do texto
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Criando uma lista de reservas para testar
            List<Reserva> reservas = new ArrayList<>();
            reservas.add(new Reserva( 101, "Sócio A", "2023-07-01"));
            reservas.add(new Reserva( 102, "Sócio B", "2023-07-02"));
            reservas.add(new Reserva( 103, "Sócio C", "2023-07-03"));

            // Criando a janela de detalhes da reserva
            DetalhesReserva detalhesReservaFrame = new DetalhesReserva(reservas);
            detalhesReservaFrame.setVisible(true);
        });
    }
}