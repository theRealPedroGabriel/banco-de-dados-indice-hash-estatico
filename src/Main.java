
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.io.File;


public class Main {

    public static void main(String[] args)throws IOException {



        class PalavraChave{
            public String chave;
            public ListaDuplamenteEncadeada ocorrencias;

            public PalavraChave(String chave){
                this.chave = chave;
                this.ocorrencias = new ListaDuplamenteEncadeada();
            }
        }
        class FuncaoHash{
            public String palavra;

            public int FuncaoHash(String p){
                this.palavra = p;
                int posicao = this.palavra.charAt(0);
                return posicao - 97;
            }
        }
        class ListaVetor {

            private String vetor[];
            private int nElementos;

            public ListaVetor(int tamanho) {
                this.vetor = new String[tamanho];
                this.nElementos = 0;
            }

            public int tamanho() {
                return this.nElementos;
            }

            public String imprime() {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < this.nElementos; i++) {
                    sb.append(this.vetor[i]);
                    if (i < this.nElementos - 1) {
                        sb.append(" -> "); // Adiciona um delimitador entre os elementos, exceto após o último elemento.
                    }
                }

                return sb.toString(); // Retorna a string construída.
            }

            public boolean estaVazia() {
                return this.nElementos == 0;
            }

            public boolean estaCheia() {
                return this.nElementos == this.vetor.length;
            }

            public void insereFinal(String elemento) {

                if (this.estaCheia()) {
                    System.out.println("Lista cheia! Não é possível inserir.");
                    return;
                }

                this.vetor[this.nElementos] = elemento;

                this.nElementos++;
            }

            public void inserePosicao(String elemento, int pos) {

                if (this.estaCheia()) {
                    System.out.println("Lista cheia! Não é possível inserir.");
                    return;
                } else if (pos < 0) {
                    System.out.println("Posição negativa. Não é possível inserir.");
                    return;
                } else if (pos > this.nElementos) {
                    System.out.println("Posição inválida. Não é possível inserir");
                    return;
                }

                for (int i = this.nElementos; i > pos; i--) {
                    this.vetor[i] = this.vetor[i - 1];
                }

                this.vetor[pos] = elemento;

                this.nElementos++;
            }

            public void insereOrdenado(String elemento) {

                if (this.estaCheia()) {
                    System.out.println("Lista cheia! Não é possível inserir.");
                    return;
                }

                boolean flagInseriu = false;
                for (int i = 0; i < this.nElementos; i++) {
                    // if (this.vetor[i] > elemento) {
                    if (this.vetor[i].compareTo(elemento) > 0) {

                        this.inserePosicao(elemento, i);
                        flagInseriu = true;
                        break;
                    }
                }

                if (!flagInseriu) {
                    this.insereFinal(elemento);
                }
            }
        }
        class ArvoreBinariaBusca {

            class Nodo {
                public PalavraChave elemento;
                public Nodo esquerdo;
                public Nodo direito;

                public Nodo(PalavraChave elemento) {
                    this.elemento = elemento;
                    this.esquerdo = null;
                    this.direito = null;
                }
            }

            private Nodo raiz;
            private int nElementos;

            public ArvoreBinariaBusca() {
                this.raiz = null;
                this.nElementos = 0;
            }

            public int tamanho() {
                return this.nElementos;
            }

            public boolean estaVazia() {
                return this.raiz == null;
            }

            public void imprimePreOrdem() {
                this.preOrdem(this.raiz);
                System.out.println();
            }

            public void imprimePosOrdem() {
                this.posOrdem(this.raiz);
                System.out.println();
            }

            public String imprimeEmOrdem() {
                StringBuilder resultado = new StringBuilder();
                emOrdem(this.raiz, resultado);
                return resultado.toString();
            }

            private void emOrdem(Nodo nodo, StringBuilder resultado) {
                if (nodo == null) return;

                emOrdem(nodo.esquerdo, resultado);

                // Supondo que o elemento do nodo é do tipo PalavraChave
                PalavraChave palavraChave = (PalavraChave) nodo.elemento;
                resultado.append("Chave: ").append(palavraChave.chave)
                        .append(" - Ocorrências: ")
                        .append(palavraChave.ocorrencias.imprime()) // Assume que imprime retorna String
                        .append("\n");

                emOrdem(nodo.direito, resultado);
            }

            private void preOrdem(Nodo nodo) {
                if (nodo == null) return;
                System.out.print(nodo.elemento.chave + " ");
                this.preOrdem(nodo.esquerdo);
                this.preOrdem(nodo.direito);
            }

            private void posOrdem(Nodo nodo) {
                if (nodo == null) return;
                this.posOrdem(nodo.esquerdo);
                this.posOrdem(nodo.direito);
                System.out.print(nodo.elemento.chave + " ");
            }



            public void insere(PalavraChave elemento) {
                this.raiz = insereRecursivo(this.raiz, elemento);
            }

            private Nodo insereRecursivo(Nodo nodo, PalavraChave elemento) {
                if (nodo == null) {
                    nElementos++;
                    return new Nodo(elemento);
                }
                if (elemento.chave.compareTo(nodo.elemento.chave) < 0) {
                    nodo.esquerdo = insereRecursivo(nodo.esquerdo, elemento);
                } else if (elemento.chave.compareTo(nodo.elemento.chave) > 0) {
                    nodo.direito = insereRecursivo(nodo.direito, elemento);
                }
                return nodo;
            }

            public PalavraChave busca(String chave) {
                return this.busca(chave, this.raiz);
            }

            private PalavraChave busca(String chave, Nodo nodo) {
                if (nodo == null) {
                    return null; // Não encontrou
                }

                int comparacao = chave.compareTo(nodo.elemento.chave);

                if (comparacao < 0) {
                    return this.busca(chave, nodo.esquerdo); // Busca no lado esquerdo
                } else if (comparacao > 0) {
                    return this.busca(chave, nodo.direito); // Busca no lado direito
                } else {
                    return nodo.elemento; // Encontrou
                }
            }

            public boolean remove(String chave) {
                if (this.busca(chave) != null) {
                    this.raiz = this.remove(chave, this.raiz);
                    this.nElementos--;
                    return true;
                }
                return false;
            }

            private Nodo remove(String chave, Nodo nodo) {
                if (nodo == null) {
                    return null;
                }

                int comparacao = chave.compareTo(nodo.elemento.chave);

                if (comparacao < 0) {
                    nodo.esquerdo = this.remove(chave, nodo.esquerdo);
                } else if (comparacao > 0) {
                    nodo.direito = this.remove(chave, nodo.direito);
                } else {
                    // Nodo encontrado
                    if (nodo.esquerdo == null && nodo.direito == null) {
                        return null;
                    }
                    if (nodo.esquerdo == null) {
                        return nodo.direito;
                    }
                    if (nodo.direito == null) {
                        return nodo.esquerdo;
                    }

                    // Nodo com dois filhos, encontrar o menor na subárvore da direita
                    Nodo substituto = this.menorElemento(nodo.direito);
                    nodo.elemento = substituto.elemento;
                    nodo.direito = this.remove(substituto.elemento.chave, nodo.direito);
                }

                return nodo;
            }

            private Nodo menorElemento(Nodo nodo) {
                while (nodo.esquerdo != null) {
                    nodo = nodo.esquerdo;
                }
                return nodo;
            }

            private Nodo maiorElemento(Nodo nodo) {
                while (nodo.direito != null) {
                    nodo = nodo.direito;
                }
                return nodo;
            }

            public int altura() {
                return this.altura(this.raiz);
            }

            private int altura(Nodo nodo) {
                if (nodo == null) {
                    return -1;
                }
                int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
                int alturaDireita = this.altura(nodo.direito) + 1;
                return Math.max(alturaEsquerda, alturaDireita);
            }
        }

        class HashColisaoExterior {

            public ArvoreBinariaBusca vetor[];
            public int nElementos;

            public HashColisaoExterior(int capacidade) {
                this.vetor = new ArvoreBinariaBusca[capacidade];
                for (int i = 0; i < vetor.length; i++) {
                    this.vetor[i] = new ArvoreBinariaBusca();
                }
                this.nElementos = 0;
            }

            public int tamanho() {
                return this.nElementos;
            }

            public String imprime() {
                StringBuilder output = new StringBuilder();
                output.append("Chave\tValor\n");
                for (int i = 0; i < vetor.length; i++) {
                    output.append(i).append(" -->\t");
                    if (vetor[i] != null) {
                        output.append(vetor[i].imprimeEmOrdem());
                    } else {
                        output.append("null");
                    }
                    output.append("\n");
                }
                return output.toString();
            }


            // Inserir uma PalavraChave na tabela hash
            public void insere(PalavraChave palavraChave) {
                int endereco = funcaoHashLetra(palavraChave.chave.charAt(0));
                this.vetor[endereco].insere(palavraChave); // Insere na ABB correspondente
                this.nElementos++;
            }

            public int funcaoHashLetra(char letra) {
                // Assumindo que as letras são minúsculas, subtraímos o valor de 'a' para obter o índice (0-25)
                return Character.toLowerCase(letra) - 'a';
            }

            public boolean remove(PalavraChave palavraChave) {
                int endereco = funcaoHashLetra(palavraChave.chave.charAt(0));
                boolean removeu = this.vetor[endereco].remove(palavraChave.chave);

                if (removeu) this.nElementos--;

                return removeu;
            }

            public PalavraChave busca(String chave) {
                int endereco = funcaoHashLetra(chave.charAt(0));
                return this.vetor[endereco].busca(chave);
            }


            // Método para ler um arquivo e atualizar as ocorrências
            private void atualizarOcorrencias(String arquivoNome) {
                try (BufferedReader br = new BufferedReader(new FileReader(arquivoNome))) {
                    String line;
                    int numeroLinha = 1;

                    // Lendo o arquivo linha por linha
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty() && Character.isLetter(line.charAt(0))) {
                            int indice = funcaoHashLetra(line.charAt(0));
                            ArvoreBinariaBusca arvore = vetor[indice];

                            PalavraChave palavraChave = arvore.busca(line);

                            if (palavraChave != null) {
                                palavraChave.ocorrencias.insereOrdenado(numeroLinha);
                            }
                        }
                        numeroLinha++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        class TextEditorGUI {
            private JFrame frame;
            private JTextField inputField;
            private JTextArea resultArea;
            private ArrayList<String> words;
            private DefaultListModel<String> wordListModel;
            private JList<String> wordList;
            private HashColisaoExterior tabelaHash;


            public TextEditorGUI(HashColisaoExterior tabelaHash) {
                this.tabelaHash = tabelaHash; // Inicializa a tabela hash
                frame = new JFrame("Editor de Texto");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 200);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
                            // Insere cada palavra na tabela hash
                            PalavraChave pc = new PalavraChave(word);
                            tabelaHash.insere(pc);
                        }
                        tabelaHash.atualizarOcorrencias("C:\\Users\\pgsmc\\Documentos\\materia banco de dados\\words.txt");

                        resultArea.setText(result.toString());
                        // Obtém o conteúdo atualizado da tabela hash como string e exibe na área de resultados
                        String hashTableContent = tabelaHash.imprime(); // Obtém o conteúdo da tabela hash como string
                        resultArea.append("\n\nConteúdo da Tabela Hash:\n" + hashTableContent);
                        resultArea.revalidate();
                        resultArea.repaint();
                    }
                });
                listPanel.add(confirmButton, BorderLayout.SOUTH);

                frame.add(listPanel, BorderLayout.WEST);

                // Criar área de resultados e colocar no centro
                resultArea = new JTextArea();
                resultArea.setEditable(false);
                resultArea.setLineWrap(true);
                resultArea.setWrapStyleWord(true);

                // Adiciona a área de resultados no centro do layout
                frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);

                frame.setVisible(true);
            }}



        File file = new File("C:\\Users\\pgsmc\\Documentos\\materia banco de dados\\banco de dados\\src\\words.txt");
        InputStream is = new FileInputStream(file); //byte
        InputStreamReader isr = new InputStreamReader(is); //chaR
        BufferedReader br = new BufferedReader(isr); //string
        System.out.println("teste");
        String line;





        // Cria uma tabela hash com 26 posições (para letras 'a' a 'z')
        HashColisaoExterior tabelaHash = new HashColisaoExterior(26);


        // Exemplo de inserção de palavras
        String[] palavras = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "subtegumental"};

        for (String palavra : palavras) {
            PalavraChave pc = new PalavraChave(palavra);
            tabelaHash.insere(pc);
        }
        // Criar GUI e passar referência da tabela hash
        SwingUtilities.invokeLater(() -> new TextEditorGUI(tabelaHash));
        // Exibe a tabela hash
        //tabelaHash.imprime();

        // Verifica se uma palavra está presente
        String palavraBusca = "banana";
        if (tabelaHash.busca(palavraBusca)!=null) {
            System.out.println(palavraBusca + " encontrada na tabela.");
        } else {
            System.out.println(palavraBusca + " não encontrada na tabela.");
        }
        /*
        int numeroLinha = 1;

        // Lendo o arquivo linha por linha
        while ((line = br.readLine()) != null) {
            int letra = line.charAt(0);
            if (Character.isLetter(letra)) {
                // Calcula o índice da tabela hash para a letra inicial da linha
                int indice = tabelaHash.funcaoHashLetra(line.charAt(0));
                System.out.println(line+" "+indice);
                // Obtém a ABB correspondente
                ArvoreBinariaBusca arvore = tabelaHash.vetor[indice ];

                // Busca a palavra-chave na ABB
                PalavraChave palavraChave = arvore.busca(line);

                if (palavraChave != null) {
                    // Se a palavra-chave já existe, atualiza suas ocorrências
                    palavraChave.ocorrencias.insereOrdenado(numeroLinha);
                } else {
                    // Se não existir, você pode optar por não fazer nada ou criar uma nova palavra-chave
                    // Não vamos criar uma nova palavra-chave neste caso
                }

                numeroLinha++;
            }
        }
*/
        br.close();

        //tabelaHash.imprime();
       // System.out.println("fim teste");

      //  SwingUtilities.invokeLater(new Runnable() {
        //    @Override
          //  public void run() {
            //    new TextEditorGUI();
          //  }
        //});


    }
}
