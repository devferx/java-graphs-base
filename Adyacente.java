public class Adyacente {
    private int peso;
    private int nodo;

    public Adyacente(int peso, int nodo){
        this.setPeso(peso);
        this.setNodo(nodo);
    }

    public int getPeso() {
        return peso;
    }

    public int getNodo() {
        return nodo;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }
}
