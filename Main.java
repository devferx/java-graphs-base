public class Main {
    public static void main(String[] args) {
        // MiGrafo grafoRueda = new MiGrafo(4, false);
        // grafoRueda.insertarArista(0, 1);
        // grafoRueda.insertarArista(0, 2);
        // grafoRueda.insertarArista(0, 3);

        // grafoRueda.insertarArista(1, 2);
        // grafoRueda.insertarArista(1, 3);

        // grafoRueda.insertarArista(2, 3);
        // System.out.println(grafoRueda.esGrafoRueda());


        MiGrafo grafoBucle = new MiGrafo(4, false);
        grafoBucle.insertarArista(0, 1);
        grafoBucle.insertarArista(0, 2);

        grafoBucle.insertarArista(1, 2);

        grafoBucle.insertarArista(2, 3);


        System.out.println(grafoBucle.existeBucle());

        MiGrafo grafoCiclo = new MiGrafo(4, false);
        grafoCiclo.insertarArista(0, 1);
        grafoCiclo.insertarArista(0, 3);
        grafoCiclo.insertarArista(1, 2);
        grafoCiclo.insertarArista(2, 3);

        System.out.println(grafoCiclo.esGrafoCiclo());
    }
}