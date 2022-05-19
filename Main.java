import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MiGrafo g1 = new MiGrafo(4, true);
        g1.insertarArista(0, 1, 3);
        g1.insertarArista(3, 0, 3);
        g1.insertarArista(2, 0, 6);
        g1.insertarArista(2, 1, 9);
        g1.insertarArista(3, 2, 10);
        g1.insertarArista(3, 1, 2);
        System.out.println("Numero de vertices de g1: " + g1.getNumVertices());
        System.out.println("Numero de aristas de g1: " + g1.getNumAristas());
        System.out.println("Existe arista entre 3 y 2 en g1: " + g1.existeArista(3, 2));
        System.out.println("Existe arista entre 2 y 3 en g1: " + g1.existeArista(2, 3));
        System.out.println("Peso arista entre 2 y 1 en g1: " + g1.getPesoArista(2, 1));

        MiGrafo g2 = new MiGrafo(4, false);
        g2.insertarArista(0, 1);
        g2.insertarArista(0, 3);
        g2.insertarArista(0, 2);
        g2.insertarArista(1, 3);
        g2.insertarArista(2, 3);
        System.out.print("Adyacentes de 3 en g2: ");
        printAdyacentes(g2.getAdyacentes(3));
        System.out.println("Dibujando grafo g2:");
        g2.dibujarGrafo();
        g2.quitarArista(1, 3);
        System.out.println("Dibujando grafo g2 sin arista 1,3:");
        g2.dibujarGrafo();
        g2.insertarArista(2, 3);
        System.out.println("G2 es grafo rueda: " + g2.esGrafoCiclo());

        MiGrafo g3 = new MiGrafo(5, false);
        g3.insertarArista(0, 1);
        g3.insertarArista(0, 2);
        g3.insertarArista(0, 3);
        g3.insertarArista(0, 4);
        g3.insertarArista(1, 2);
        g3.insertarArista(1, 3);
        g3.insertarArista(1, 4);
        g3.insertarArista(2, 4);
        g3.insertarArista(2, 3);
        g3.insertarArista(3, 4);
        System.out.println("G3 es completo: " + g3.esCompleto());
        System.out.println("G3 es grafo ciclo: " + g3.esGrafoCiclo());
        System.out.println("G3 existe bucle: " + g3.existeBucle());
    }

    // print arrayList
    public static void printAdyacentes(ArrayList<Adyacente> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).getNodo() + " ");
        }
        System.out.println();
    }
}