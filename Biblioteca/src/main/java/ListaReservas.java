import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListaReservas extends JFrame {
    private List<Livro> livro;
    private JList<Reserva> reservaJList;
    private JPanel livrosReservasPanel;
    private JTextField searchField;
    private JButton reservarButton;
    private JButton voltarButton;
    private DefaultListModel<Reserva> listModel;

    public ListaReservas() {
        livro = new ArrayList<>();
        listModel = new DefaultListModel<>();
        reservaJList = new JList<>(listModel);

        reservaJList.setCellRenderer(new ReservaListRenderer());

        setContentPane(livrosReservasPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        livro.add(new Livro("MM","nn","bb",1,11,"aaa",5,"anim","mario",1, "1", "a1"));
        updateReservaList();
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
