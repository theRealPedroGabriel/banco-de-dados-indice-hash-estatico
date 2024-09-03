public class FilaDinamica {

    public class Nodo {

        public int elemento;
        public Nodo proximo;

        public Nodo(int elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }

    }

    private Nodo inicio;
    private int nElementos;

    public FilaDinamica() {
        this.inicio = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.inicio == null;
    }

    public void imprime() {

        System.out.print("[");

        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            System.out.print(cursor.elemento + " ");
            cursor = cursor.proximo;
        }

        System.out.println("]");
    }

    public Integer desenfileira() {

        if (this.estaVazia()) {
            System.out.println("Lista vazia. Não é possível remover.");
            return null;
        }

        Nodo nodoRemovido = this.inicio;

        this.inicio = nodoRemovido.proximo;

        this.nElementos--;

        nodoRemovido.proximo = null;

        return nodoRemovido.elemento;

    }

    public void enfileira(int elemento) {

        Nodo novo = new Nodo(elemento);

        if (this.estaVazia()) {

            this.inicio = novo;
        } else {

            Nodo cursor = this.inicio;
            for (int i = 1; i < this.nElementos; i++) {
                cursor = cursor.proximo;
            }

            cursor.proximo = novo;
        }

        this.nElementos++;
    }

    public boolean contem(int elemento) {

        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {

            if (cursor.elemento == elemento) {
                return true;
            }

            cursor = cursor.proximo;
        }

        return false;
    }

    public Integer espia() {

        if (this.estaVazia()) {
            System.out.println("Lista vazia! Não é possível espiar.");
            return null;
        }

        return this.inicio.elemento;
    }

}

