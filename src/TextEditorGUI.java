import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TextEditorGUI {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;
    private DefaultListModel<String> wordListModel;
    private JList<String> wordList;
    private List<String> words;

    public TextEditorGUI() {
        frame = new JFrame("Editor de Texto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        words = new ArrayList<>();
        wordListModel = new DefaultListModel<>();
        wordList = new JList<>(wordListModel);

        // Criar painel para entrada de texto e botões
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel inputLabel = new JLabel("Digite uma palavra:");
        inputPanel.add(inputLabel);

        inputField = new JTextField(15);
        inputPanel.add(inputField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = inputField.getText();
                if (!word.isEmpty()) {
                    words.add(word);
                    wordListModel.addElement(word);
                    inputField.setText("");
                }
            }
        });
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Criar painel para lista de palavras e botão de confirmar
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        listPanel.add(new JScrollPane(wordList), BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder result = new StringBuilder("Palavras: ");
                for (String word : words) {
                    result.append(word).append(" ");
                }
                resultArea.setText(result.toString());
            }
        });
        listPanel.add(confirmButton, BorderLayout.SOUTH);

        frame.add(listPanel, BorderLayout.CENTER);

        // Criar área de resultados
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        frame.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        frame.setVisible(true);
    }


}
