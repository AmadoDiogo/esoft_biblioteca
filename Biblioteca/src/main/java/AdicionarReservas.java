import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarReservas extends JFrame {

    private JPanel listLivrosPanel;
    private JButton voltarButton;
    private JButton reservarButton;
    private JLabel socio;
    private JTextField search;
    private JTextField nsocio;
    private JCheckBox notificacaoTelfCheckBox;
    private JCheckBox notificacaoEmailCheckBox;
    private JList<Livro> livroJList;
    private DefaultListModel<Livro> livroListModel;
    private GestaoReservas gestaoReservas;
    private ListaLivros listaLivros;

    public AdicionarReservas(ListaLivros listaLivros) {
        this.listaLivros = listaLivros;

        setTitle("Adicionar Reservas");
        setContentPane(listLivrosPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        livroJList.setModel(livroListModel);
        livroJList.setCellRenderer(new LivroListRenderer());

        voltarButton.addActionListener(this::voltaractionPerformed);
        reservarButton.addActionListener(this::reservactionPerformed);
    }

    public void setGestaoReservas(GestaoReservas gestaoReservas) {
        this.gestaoReservas = gestaoReservas;
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
        gestaoReservas.setVisible(true);
    }

    public void reservactionPerformed(ActionEvent e) {
        Livro livroSelecionado = livroJList.getSelectedValue();
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um livro para reservar.");
            return;
        }
        String numeroSocio = nsocio.getText();
        boolean notificacaoTelf = notificacaoTelfCheckBox.isSelected();
        boolean notificacaoEmail = notificacaoEmailCheckBox.isSelected();

        // Lógica para reservar o livro
        JOptionPane.showMessageDialog(this, "Reserva realizada para o sócio: " + numeroSocio);
    }

    private class LivroListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Livro) {
                Livro livro = (Livro) value;
                label.setText(livro.getIdCompra() + " - " + livro.getTitulo() + " - " + livro.getAutor());
            }
            return label;
        }
    }
}