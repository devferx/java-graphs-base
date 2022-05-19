public class Main {
    public static void main(String[] args) {
        MiGrafo g1 = new MiGrafo(5, false);
        g1.insertarArista(1, 0);
        g1.insertarArista(0, 2);
        g1.insertarArista(2, 1);
        g1.insertarArista(0, 3);
        g1.insertarArista(3, 4);
        imprimirResultado(g1);

        MiGrafo g2 = new MiGrafo(5, false);
        g2.insertarArista(1, 0);
        g2.insertarArista(0, 2);
        g2.insertarArista(2, 1);
        g2.insertarArista(0, 3);
        g2.insertarArista(3, 4);
        g2.insertarArista(4, 0);
        imprimirResultado(g2);

        MiGrafo g3 = new MiGrafo(5, false);
        g3.insertarArista(1, 0);
        g3.insertarArista(0, 2);
        g3.insertarArista(2, 1);
        g3.insertarArista(0, 3);
        g3.insertarArista(3, 4);
        g3.insertarArista(1, 3);
        imprimirResultado(g3);

        MiGrafo g4 = new MiGrafo(3, false);
        g4.insertarArista(0, 1);
        g4.insertarArista(1, 2);
        g4.insertarArista(2, 0);
        imprimirResultado(g4);
    }

    public static void imprimirResultado(MiGrafo m) {
        int res = m.esEuleriano();
        if (res == 0)
            System.out.println("El grafo no es Euleriano");
        else if (res == 1)
            System.out.println("El grafo tiene un camino de Euler");
        else
           System.out.println("El grafo tiene un ciclo de Euler");
    }
}