import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ListaReservas extends JFrame {
    private List<Livro> livros;
    private JList<Livro> reservaJList;
    private JPanel livrosReservasPanel;
    private JTextField searchField;
    private JButton reservarButton;
    private JButton voltarButton;
    private DefaultListModel<Livro> listModel;
    private GestaoReservas gestaoReservas;
    private BibliotecaControlo bibliotecaControlo;

    public ListaReservas(BibliotecaControlo bibliotecaControlo) {
        this.bibliotecaControlo = bibliotecaControlo;
        livros = bibliotecaControlo.getLivrosReservados();
        listModel = new DefaultListModel<>();
        reservaJList = new JList<>(listModel);

        reservaJList.setCellRenderer(new ReservaListRenderer());

        setContentPane(livrosReservasPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        voltarButton.addActionListener(this::voltaractionPerformed);

        updateReservaList();
    }

    public void setGestaoReservas(GestaoReservas gestaoReservas){
        this.gestaoReservas=gestaoReservas;
    }
    public void voltaractionPerformed(ActionEvent e){
        setVisible(false);
        gestaoReservas.setVisible(true);
    }

    public void updateReservaList() {
        listModel.clear();
        for (Livro livro : livros) {
            listModel.addElement(livro);
        }
    }

    private static class ReservaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(new Color(0x2F3436)); // Cor de fundo

            if (value instanceof Livro) {
                Livro livro = (Livro) value;

                JLabel idLivro = createStyledLabel(String.valueOf(livro.getIdCompra()));
                JLabel tituloLivro = createStyledLabel(livro.getTitulo());
                JLabel autorLivro = createStyledLabel(livro.getAutor());

                panel.add(idLivro);
                panel.add(tituloLivro);
                panel.add(autorLivro);
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
            label.setForeground(new Color(0x5F6368)); // Set text color
            return label;
        }
    }
}
