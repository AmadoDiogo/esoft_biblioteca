import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaLivros extends JFrame {
    private JPanel listaLivrosPanel;
    private JTextField searchBar;
    private JButton registarLivroButton;
    private JButton gestaoDeAquisicoesButton;
    private JButton removerButton;
    private JList<Livro> itemList;
    private DefaultListModel<Livro> listModel;
    private List<Livro> livros;
    private GestaoLivros gestaoLivros;

    public ListaLivros( ) {
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.setCellRenderer(new LivroListRenderer());
        livros = new ArrayList<>();

        listaLivrosPanel = new JPanel();
        listaLivrosPanel.setLayout(new BorderLayout());

        // Set background color for the panel
        listaLivrosPanel.setBackground(new Color(0x2F3436));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(0x2F3436)); // Set background color
        JLabel tituloLabel = new JLabel("Readify", JLabel.CENTER);
        tituloLabel.setForeground(new Color(0x5F6368)); // Set text color
        topPanel.add(tituloLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(new Color(0x2F3436)); // Set background color
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

        gestaoDeAquisicoesButton = new JButton("Voltar");
        gestaoDeAquisicoesButton.setBackground(new Color(0x262626)); // Set button color
        gestaoDeAquisicoesButton.setForeground(new Color(0x5F6368)); // Set text color
        gestaoDeAquisicoesButton.addActionListener(this::voltaractionPerformed);
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.setBackground(new Color(0x2F3436)); // Set background color
        backButtonPanel.add(gestaoDeAquisicoesButton);
        searchPanel.add(backButtonPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel descriptionPanel = new JPanel(new GridLayout(1, 4));
        descriptionPanel.setBackground(new Color(0x2F3436)); // Set background color
        descriptionPanel.add(createStyledLabel("ID"));
        descriptionPanel.add(createStyledLabel("Título"));
        descriptionPanel.add(createStyledLabel("Quantidade"));
        descriptionPanel.add(createStyledLabel("Autor"));
        topPanel.add(descriptionPanel, BorderLayout.SOUTH);

        listaLivrosPanel.add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        listaLivrosPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0x2F3436)); // Set background color
        registarLivroButton = new JButton("Registar");
        registarLivroButton.setBackground(new Color(0x262626)); // Set button color
        registarLivroButton.setForeground(new Color(0x5F6368)); // Set text color
        removerButton = new JButton("Remover");
        removerButton.setBackground(new Color(0x262626)); // Set button color
        removerButton.setForeground(new Color(0x5F6368)); // Set text color
        removerButton.addActionListener(this::removeractionPerformed);
        registarLivroButton.addActionListener(this::registaractionPerformed);
        buttonPanel.add(registarLivroButton);
        buttonPanel.add(removerButton);
        listaLivrosPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(listaLivrosPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        pack();

        // Adiciona o MouseListener ao itemList
        adicionarMouseListener();
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setForeground(new Color(0x5F6368)); // Set text color
        return label;
    }

    public void setGestaoLivros(GestaoLivros gestaoLivros) {
        this.gestaoLivros = gestaoLivros;
    }

    private void adicionarMouseListener() {
        // Adicionar MouseListener para detectar cliques duplos na lista de livros
        itemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = itemList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Livro livro = listModel.getElementAt(index);
                        new DadosLivro(livro).setVisible(true);
                    }
                }
            }
        });
    }

    private void removeractionPerformed(ActionEvent e) {
        int index = itemList.getSelectedIndex();
        if (index != -1) {
            Livro livro = listModel.getElementAt(index);
            int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover o livro selecionado?",
                    "Confirmação de Remoção", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                livros.remove(livro);
                listModel.removeElement(livro);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um livro para remover.");
        }
    }

    private void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
        gestaoLivros.setVisible(true);
    }

    private void registaractionPerformed(ActionEvent e) {
        setVisible(false);
        new AdicionarLivros(this).setVisible(true);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        listModel.addElement(livro);
    }

    public boolean removerLivroPorIdCompra(int idCompra) {
        Iterator<Livro> iterator = livros.iterator();
        while (iterator.hasNext()) {
            Livro livro = iterator.next();
            if (livro.getIdCompra() == idCompra) {
                iterator.remove();
                listModel.removeElement(livro);
                return true; // Livro encontrado e removido
            }
        }
        return false; // Livro não encontrado com o ID de compra especificado
    }

    private void updateFilteredList() {
        String searchText = searchBar.getText().toLowerCase();
        listModel.clear();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(searchText) ||
                    livro.getAutor().toLowerCase().contains(searchText) ||
                    String.valueOf(livro.getIdCompra()).contains(searchText) ||
                    String.valueOf(livro.getQuantidade()).contains(searchText)) {
                listModel.addElement(livro);
            }
        }
    }

    private class LivroListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JPanel panel = new JPanel(new GridLayout(1, 4));
            panel.setBackground(new Color(0x2F3436)); // Set background color
            if (value instanceof Livro) {
                Livro livro = (Livro) value;
                JLabel idLabel = createStyledLabel(String.valueOf(livro.getIdCompra()));
                JLabel titleLabel = createStyledLabel(livro.getTitulo());
                JLabel quantityLabel = createStyledLabel(String.valueOf(livro.getQuantidade()));
                JLabel authorLabel = createStyledLabel(livro.getAutor());
                panel.add(idLabel);
                panel.add(titleLabel);
                panel.add(quantityLabel);
                panel.add(authorLabel);
            }
            if (isSelected) {
                panel.setBackground(list.getSelectionBackground());
                panel.setForeground(list.getSelectionForeground());
            } else {
                panel.setBackground(list.getBackground());
                panel.setForeground(list.getForeground());
            }
            panel.setOpaque(true);
            return panel;
        }
    }

}
