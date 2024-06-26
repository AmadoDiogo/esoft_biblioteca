import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdicionarReservas extends JFrame {

    private JPanel listLivrosPanel;
    private JButton voltarButton;
    private JButton reservarButton;
    private JLabel socioLabel;
    private JTextField searchField;
    private JTextField nsocioField;
    private JTextField searchBar; // Novo campo de busca
    private JCheckBox notificacaoTelfCheckBox;
    private JCheckBox notificacaoEmailCheckBox;
    private JList<Livro> livroJList;
    private DefaultListModel<Livro> livroListModel;
    private BibliotecaControlo bibliotecaControlo;
    private ListaLivros listaLivros;
    private GestaoReservas gestaoReservas;

    public AdicionarReservas(BibliotecaControlo bibliotecaControlo, ListaLivros listaLivros) {
        this.bibliotecaControlo = bibliotecaControlo;
        this.listaLivros = listaLivros;

        setTitle("Adicionar Reservas");
        listLivrosPanel = new JPanel(new BorderLayout());
        listLivrosPanel.setBackground(new Color(0x2F3436));

        // Configuração do JList e do DefaultListModel para os livros
        livroListModel = new DefaultListModel<>();
        livroJList = new JList<>(livroListModel);
        livroJList.setCellRenderer(new LivroListRenderer());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0x2F3436));

        // Painel de busca com novo campo searchBar
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(0x2F3436));
        searchBar = new JTextField();
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFilteredList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFilteredList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFilteredList();
            }
        });
        searchPanel.add(searchBar, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.NORTH);

        // Painel de descrição com GridLayout para 4 colunas
        JPanel descriptionPanel = new JPanel(new GridLayout(1, 4));
        descriptionPanel.setBackground(new Color(0x2F3436));
        descriptionPanel.add(createStyledLabel("ID"));
        descriptionPanel.add(createStyledLabel("Título"));
        descriptionPanel.add(createStyledLabel("Quantidade")); // Nova coluna Quantidade
        descriptionPanel.add(createStyledLabel("Autor"));
        topPanel.add(descriptionPanel, BorderLayout.CENTER);

        listLivrosPanel.add(topPanel, BorderLayout.NORTH);

        // Painel para a lista de livros
        JScrollPane scrollPane = new JScrollPane(livroJList);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        listLivrosPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel para botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        voltarButton = new JButton("Voltar");
        reservarButton = new JButton("Reservar");
        socioLabel = new JLabel("ID do Sócio:");
        nsocioField = new JTextField();
        nsocioField.setColumns(10);
        notificacaoTelfCheckBox = new JCheckBox("Telefone");
        notificacaoEmailCheckBox = new JCheckBox("Email");
        buttonPanel.add(socioLabel);
        buttonPanel.add(nsocioField);
        buttonPanel.add(notificacaoTelfCheckBox);
        buttonPanel.add(notificacaoEmailCheckBox);
        buttonPanel.add(voltarButton);
        buttonPanel.add(reservarButton);
        listLivrosPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(listLivrosPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Adiciona ActionListener aos botões
        voltarButton.addActionListener(this::voltaractionPerformed);
        reservarButton.addActionListener(this::reservarActionPerformed);

        // Atualiza a lista de livros disponíveis
        updateLivroList();
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(new Color(0x5F6368));
        return label;
    }

    private void updateLivroList() {
        livroListModel.clear();
        List<Livro> livrosDisponiveis = bibliotecaControlo.getLivros();
        for (Livro livro : livrosDisponiveis) {
            livroListModel.addElement(livro);
        }
    }

    public void setGestaoReservas(GestaoReservas gestaoReservas) {
        this.gestaoReservas = gestaoReservas;
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void reservarActionPerformed(ActionEvent e) {
        Livro livroSelecionado = livroJList.getSelectedValue();
        if (livroSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um livro para reservar.");
            return;
        }

        String numeroSocioStr = nsocioField.getText().trim();
        if (numeroSocioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o ID do sócio.");
            return;
        }

        int numeroSocio = Integer.parseInt(numeroSocioStr);
        Socio socio = encontrarSocioPorId(numeroSocio);
        if (socio == null) {
            JOptionPane.showMessageDialog(this, "Sócio não encontrado com o ID fornecido.");
            return;
        }

        boolean notificacaoTelf = notificacaoTelfCheckBox.isSelected();
        boolean notificacaoEmail = notificacaoEmailCheckBox.isSelected();

        // Criação da reserva com a data atual
        String dataReserva = java.time.LocalDate.now().toString();
        Reserva reserva = new Reserva(socio.getId(), socio.getNome(), dataReserva);

        // Adicionar a reserva ao livro selecionado
        bibliotecaControlo.adicionarReserva(livroSelecionado, reserva);

        // Exibir mensagem de reserva realizada
        JOptionPane.showMessageDialog(this, "Reserva realizada para o sócio: " + socio.getNome());

    }


    private Socio encontrarSocioPorId(int idSocio) {
        for (Socio socio : bibliotecaControlo.getSocios()) {
            if (socio.getId() == idSocio) {
                return socio;
            }
        }
        return null; // Retorna null se o sócio não for encontrado
    }

    private void updateFilteredList() {
        String searchText = searchBar.getText().toLowerCase(); // Usando searchBar para filtrar
        livroListModel.clear();
        for (Livro livro : bibliotecaControlo.getLivros()) {
            if (livro.getTitulo().toLowerCase().contains(searchText) ||
                    livro.getAutor().toLowerCase().contains(searchText) ||
                    String.valueOf(livro.getIdCompra()).contains(searchText) ||
                    String.valueOf(livro.getQuantidade()).contains(searchText)) {
                livroListModel.addElement(livro);
            }
        }
    }

    private class LivroListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel panel = new JPanel(new GridLayout(1, 4)); // Ajustado para 4 colunas
            panel.setBackground(Color.WHITE);
            if (value instanceof Livro) {
                Livro livro = (Livro) value;
                JLabel idLabel = new JLabel(String.valueOf(livro.getIdCompra()));
                JLabel titleLabel = new JLabel(livro.getTitulo());
                JLabel quantityLabel = new JLabel(String.valueOf(livro.getQuantidade())); // Novo label para quantidade
                JLabel authorLabel = new JLabel(livro.getAutor());
                panel.add(idLabel);
                panel.add(titleLabel);
                panel.add(quantityLabel);
                panel.add(authorLabel);
            }
            if (isSelected) {
                panel.setBackground(list.getSelectionBackground());
                panel.setForeground(list.getSelectionForeground());
            }
            return panel;
        }
    }
}