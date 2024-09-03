
public class ListaDuplamenteEncadeada {
    class Nodo {

        public int elemento;
        public Nodo proximo;
        public Nodo anterior;

        public Nodo(int elemento) {
            this.elemento = elemento;
            this.proximo = null;
            this.anterior = null;
        }
    }
    private Nodo inicio;
    private Nodo fim;
    private int nElementos;

    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.nElementos = 0;
    }

    public boolean estaVazia() {
        return this.nElementos == 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public String imprime() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            sb.append(cursor.elemento).append(" ");
            cursor = cursor.proximo;
        }
        sb.append("]");
        return sb.toString();
    }

    public void imprimeInverso() {
        System.out.print("[");
        Nodo cursor = this.fim;
        for(int i=0;i<this.nElementos;i++) {
            System.out.print(cursor.elemento + " ");
            cursor = cursor.anterior;
        }
        System.out.println("]");
    }

    public void insereInicio(int elemento) {

        Nodo novo = new Nodo(elemento);

        if(this.estaVazia()) {
            this.fim = novo;
        } else {
            novo.proximo = this.inicio;
            this.inicio.anterior = novo;
        }

        this.inicio = novo;
        this.nElementos++;

    }

    public Integer removeInicio() {

        if(this.estaVazia()) {
            System.out.println("Lista vazia. Não é possível remover.");
            return null;
        }

        Nodo nodoRemovido = this.inicio;

        if(this.nElementos == 1) {
            this.inicio = null;
            this.fim = null;
        } else {
            this.inicio = nodoRemovido.proximo;
            this.inicio.anterior = null;

            nodoRemovido.proximo = null;
        }

        this.nElementos--;

        return nodoRemovido.elemento;
    }

    public void insereFinal(int elemento) {

        Nodo novo = new Nodo(elemento);

        if(this.estaVazia()) {
            this.inicio = novo;
        } else {
            this.fim.proximo = novo;
            novo.anterior = this.fim;
        }

        this.fim = novo;

        this.nElementos++;
    }

    public Integer removeFinal() {

        if(this.estaVazia()) {
            System.out.println("Lista vazia. Não é possível remover.");
            return null;
        }

        Nodo nodoRemovido = this.fim;

        if(this.nElementos == 1) {

            this.inicio = null;
            this.fim = null;
        } else {

            this.fim = nodoRemovido.anterior;

            nodoRemovido.anterior.proximo = null;
            nodoRemovido.anterior = null;
        }

        this.nElementos--;

        return nodoRemovido.elemento;
    }

    public void inserePosicao(int elemento, int pos) {

        if(pos < 0) {
            System.out.println("Posição negativa. Não é possível inserir.");
            return;
        } else if(pos > this.nElementos) {
            System.out.println("Posição inválida. Não é possível inserir.");
            return;
        }

        if(pos == 0) {
            this.insereInicio(elemento);
            return;
        } else if(pos == this.nElementos ) {
            this.insereFinal(elemento);
            return;
        }

        Nodo novo = new Nodo(elemento);

        Nodo cursor = this.inicio;
        for(int i=1;i<=pos;i++) {
            cursor = cursor.proximo;
        }

        novo.anterior = cursor.anterior;
        novo.proximo = cursor;

        cursor.anterior.proximo = novo;
        cursor.anterior = novo;

        this.nElementos++;

    }

    public Integer removePosicao(int pos) {

        if(this.estaVazia()) {
            System.out.println("Lista vazia. Não é possível remover.");
            return null;
        } else if(pos < 0) {
            System.out.println("Posição Negativa. Não é possível remover");
            return null;
        } else if(pos >= this.nElementos) {
            System.out.println("Posição inválida. Não é possível remover.");
            return null;
        }

        if(pos == 0) {
            return this.removeInicio();
        } else if(pos == this.nElementos-1) {
            return this.removeFinal();
        }

        Nodo cursor = this.inicio;
        for(int i=1;i<=pos;i++) {
            cursor = cursor.proximo;
        }

        Nodo nodoRemovido = cursor;

        nodoRemovido.anterior.proximo = nodoRemovido.proximo;
        nodoRemovido.proximo.anterior = nodoRemovido.anterior;

        nodoRemovido.anterior = null;
        nodoRemovido.proximo = null;

        this.nElementos--;

        return nodoRemovido.elemento;

    }

    public void insereOrdenado(int elemento) {

        if(this.estaVazia()) {
            this.insereInicio(elemento);
            return;
        }

        boolean flagInseriu = false;

        Nodo cursor = this.inicio;
        for(int i=0;i<this.nElementos;i++) {
            if(cursor.elemento > elemento) {
                this.inserePosicao(elemento, i);
                flagInseriu = true;
                break;
            }
            cursor = cursor.proximo;
        }

        if(!flagInseriu) {
            this.insereFinal(elemento);
        }
    }

    public boolean removeElemento(int elemento) {

        Nodo cursor = this.inicio;
        int i;
        for (i = 0; i < this.nElementos; i++) {
            if(cursor.elemento == elemento) {
                break;
            }
            cursor = cursor.proximo;
        }

        if(i == this.nElementos) {
            return false;
        }

        this.removePosicao(i);

        return true;

    }

    public Integer acesse(int pos) {

        if (pos < 0 || pos >= this.nElementos) {
            return null;
        }

        Nodo cursor = this.inicio;
        for (int i = 0; i < pos; i++) {
            cursor = cursor.proximo;
        }

        return cursor.elemento;

    }

    public boolean contem(int elemento) {

        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            if(cursor.elemento == elemento) {
                return true;
            }
            cursor = cursor.proximo;
        }

        return false;
    }

}