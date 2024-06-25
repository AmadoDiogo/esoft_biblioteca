import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoverLivrosID extends JFrame {
    private JPanel removerLivros;
    private JLabel idcompraLabel;
    private JTextField idcomprainsert;
    private JButton voltarButton;
    private JButton removerLivroIdButton;
    private GestaoLivros gestaoLivros;
    private ListaLivros listaLivros;

    public RemoverLivrosID(GestaoLivros gestaoLivros, ListaLivros listaLivros) {
        this.gestaoLivros = gestaoLivros;
        this.listaLivros = listaLivros;

        // Configuração da interface gráfica
        setContentPane(removerLivros);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Ação do botão "Voltar"
        voltarButton.addActionListener(this::voltaractionPerformed);

        // Ação do botão "Remover por ID"
        removerLivroIdButton.addActionListener(this::removeractionPerformed);
    }

    public void voltaractionPerformed(ActionEvent e) {
        setVisible(false);
        gestaoLivros.setVisible(true);
    }

    public void removeractionPerformed(ActionEvent e) {
        // Obtém o ID de compra inserido pelo usuário
        String idCompraStr = idcomprainsert.getText().trim();
        if (idCompraStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o ID de compra para remover o livro.");
            return;
        }

        try {
            int idCompra = Integer.parseInt(idCompraStr);

            // Chama o método para remover o livro com o ID de compra especificado
            boolean livroRemovido = listaLivros.removerLivroPorIdCompra(idCompra);

            if (livroRemovido) {
                JOptionPane.showMessageDialog(this, "Livro removido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Livro não encontrado com o ID de compra especificado.");
            }

            // Após remover, volta para a tela de gestão de livros
            setVisible(false);
            gestaoLivros.setVisible(true);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID de compra deve ser um número inteiro.");
        }
    }

}
