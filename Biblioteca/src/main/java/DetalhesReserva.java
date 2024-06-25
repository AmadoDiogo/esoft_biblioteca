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
    private DefaultListModel<Reserva> listModel;
    private JButton voltarButton;
    private JButton removerButton;
    private JTextField searchField; // Campo de busca
    private List<Reserva> reservas;
    private GestaoReservas gestaoReservas;

    public DetalhesReserva() {
        reservas = new ArrayList<>();
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

        // Populando reservas de exemplo (remova esta parte e use dados reais no seu projeto)
        reservas.add(new Reserva(1, "João Silva", "2023-06-22"));
        reservas.add(new Reserva(2, "Maria Oliveira", "2023-06-23"));
        reservas.add(new Reserva(3, "Carlos Santos", "2023-06-24"));
        updateReservaList();
    }

    public void setReservas(GestaoReservas gestaoReservas) {
        this.gestaoReservas = gestaoReservas;
    }

    public void voltarActionPerformed(ActionEvent e) {
        setVisible(false);
        gestaoReservas.setVisible(true);
    }

    private void removerActionPerformed(ActionEvent e) {
        int selectedIndex = reservaList.getSelectedIndex();
        if (selectedIndex != -1) {
            Reserva reserva = listModel.getElementAt(selectedIndex);
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover a reserva selecionada?",
                    "Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                reservas.remove(reserva);
                listModel.removeElement(reserva);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva para remover.");
        }
    }

    private void updateReservaList() {
        listModel.clear();
        for (Reserva reserva : reservas) {
            listModel.addElement(reserva);
        }
    }

    // Método para filtrar as reservas com base no nome do sócio
    private void filterReservas() {
        String searchText = searchField.getText().toLowerCase();
        listModel.clear();
        for (Reserva reserva : reservas) {
                if (reserva.getNomeSocio().toLowerCase().contains(searchText) || reserva.getDataReserva().toLowerCase().contains(searchText) || String.valueOf(reserva.getIdReserva()).contains(searchText) || String.valueOf(reserva.getIdSocio()).contains(searchText)) {
                    listModel.addElement(reserva);
                }
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
    }

