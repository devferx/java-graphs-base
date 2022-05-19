import java.util.ArrayList;

public class MiGrafo extends Grafo{
    int nVertices;
    int nAristas;
    ArrayList<ArrayList<Adyacente>> listaAdj;
    boolean esDirigido;

    public MiGrafo(int n, boolean dirigido) {
        this. nVertices = n;
        this.esDirigido = dirigido;
        this.nAristas = 0;
        listaAdj = new ArrayList<ArrayList<Adyacente>>();

        for (int i = 0; i < n; i++) {
            listaAdj.add(new ArrayList<Adyacente>());
        }
    }

    public int getNumVertices() {
        return nVertices;
    }

    public int getNumAristas() {
        return nAristas;
    }

    public boolean existeArista(int origen, int destino) {
        boolean res = false;
        ArrayList<Adyacente> vertices = getAdyacentes(origen);

        for (Adyacente vertice : vertices) {
            if (vertice.getNodo() == destino) {
                res = true;
                break;
            }
        }

        return res;
    }

    public double getPesoArista(int i, int j) {
        double res = 0;
        ArrayList<Adyacente> vertices = getAdyacentes(i);

        for (Adyacente adyacente : vertices) {
            if (adyacente.getNodo() == j) {
                res = adyacente.getPeso();
                break;
            }
        }

        return res;
    }

    public void insertarArista(int i, int j) {
        if(esDirigido){
            if(!existeArista(i,j)){
                listaAdj.get(i).add(new Adyacente(1, j));
                nAristas++;
            }
            return;
        }

        if(!esDirigido){
            if(!existeArista(i,j)){
                listaAdj.get(i).add(new Adyacente(1, j));
                listaAdj.get(j).add(new Adyacente(1, i));
                nAristas++;
            }
            return;
        }
    }

    public void insertarArista(int i, int j, int peso) {
        if(esDirigido){
            if(!existeArista(i,j)){
                listaAdj.get(i).add(new Adyacente(peso, j));
                nAristas++;
            }
            return;
        }

        if(!esDirigido){
            if(!existeArista(i,j)){
                listaAdj.get(i).add(new Adyacente(peso, j));
                listaAdj.get(j).add(new Adyacente(peso, i));
                nAristas++;
            }
            return;
        }
    }

    public ArrayList<Adyacente> getAdyacentes(int vertice) {
        return listaAdj.get(vertice);
    }

    public void dibujarGrafo() {
        for (int i = 0; i < nVertices; i++) {
            System.out.print("Vertice " + i + ": ");
            ArrayList<Adyacente> vertices = getAdyacentes(i);

            for (Adyacente vertice : vertices) {
                System.out.print(vertice.getNodo() + " ");
            }

            System.out.println();
        }
    }

    public boolean quitarArista(int origen, int destino) {
        boolean res = false;
        if(existeArista(origen, destino)){
            if (esDirigido){
                listaAdj.get(origen).remove(getAdyacentePorNodo(origen, destino));
                nAristas--;
                res = true;
            }

            if (!esDirigido){
                listaAdj.get(origen).remove(getAdyacentePorNodo(origen, destino));
                listaAdj.get(destino).remove(getAdyacentePorNodo(destino, origen));
                nAristas--;
                res = true;
            }
        }
        return res;
    }

    public boolean esCompleto() {
        boolean res = true;

        for (int i = 0; i < nVertices; i++) {
            int grado = getGradoVertice(i);
            if (grado != nVertices - 1) {
                res = false;
                break;
            }
        }

        return res;
    }

    public boolean esGrafoCiclo() {
        boolean res = false;

        if (nVertices != nAristas){
            return false;
        }

        for (Adyacente nodo : getAdyacentes(0)) {
            if (nodo.getNodo() == nVertices - 1) {
                return true;
            }
        }

        return res;
    }

    public boolean esGrafoRueda() {
        boolean res = true;

        for (int i = 0; i < nVertices; i++) {
            int grado = getGradoVertice(i);

            if (grado != 3 || grado != nVertices - 1) {
                res = false;
                break;
            }
        }
        return res;
    }

    public boolean existeBucle() {
        boolean res = false;
        Boolean nodosVisitados[] = new Boolean[nVertices];

        for (int i = 0; i < nVertices; i++){
            nodosVisitados[i] = false;
        }

        for (int i = 0; i < nVertices; i++) {
            if(!nodosVisitados[i]){
                if(existeBucle(i, nodosVisitados, -1)){
                    res = true;
                    break;
                }
            }
        }

        return res;
    }

    /**
     * @return
     * 0 si no es Euleriano
     * 1 si tiene un camino de Euler
     * 2 si tiene un ciclo de Euler
     */
    public int esEuleriano() {
        // Revisamos si todos los vertices del grafo estan conectados
        if (!estaConectado()) return 0;

        // Cant Vertices con grado impar
        int nImpar = 0;
        for (int i = 0; i < nVertices; i++){
            if (getAdyacentes(i).size() % 2 != 0){
                nImpar++;
            }
        }

        // Si Cant Vertices con grado impar > 2 -> no es Euleriano
        if (nImpar > 2) return 0;

        // Si la cant de vertices con grado impar es 2 -> tiene ciclo de Euler
        // Si la cant de vertices con grado impar es diferente de 2 -> tiene camino de Euler
        return (nImpar == 2) ? 1 : 2;
    }

    private boolean estaConectado() {
        boolean visitados[] = new boolean[nVertices];
        for (int i = 0; i < nVertices; i++){
            visitados[i] = false;
        }

        int i;
        // Encuentra un vértice con grado distinto de cero
        for (i = 0; i < nVertices; i++){
            if (getAdyacentes(i).size() != 0){
                break;
            }
        }

        // Si no hay aristas en el grafo retorna verdadero
        if (i == nVertices){
            return true;
        }

        // Recorrido DFS para intentar visitar todos los vértices
        DFS(i, visitados);

        for (i = 0; i < nVertices; i++){
            if (visitados[i] == false && getAdyacentes(i).size() > 0){
                return false;
            }
        }

        return true;
    }

    void DFS(int v, boolean visitados[]){
        visitados[v] = true;

        for (Adyacente adyacente : getAdyacentes(v)) {
            int n = adyacente.getNodo();
            if (!visitados[n]){
                DFS(n, visitados);
            }
        }
    }

    private boolean existeBucle(int v, Boolean visitados[], int padre) {
        visitados[v] = true;
        ArrayList<Adyacente> adyacentes = getAdyacentes(v);

        for (Adyacente adyacente : adyacentes) {
            Integer nodoAdj = adyacente.getNodo();

            if (!visitados[nodoAdj]){
                if (existeBucle(nodoAdj, visitados, v)){
                    return true;
                }
            } else if (nodoAdj != padre){
                return true;
            }
        }
        return false;
    }

    private int getGradoVertice(int vertice) {
        return getAdyacentes(vertice).size();
    }

    private Adyacente getAdyacentePorNodo(int nodo, int nodoBuscar){
        Adyacente res = null;
        ArrayList<Adyacente> adyacentes = getAdyacentes(nodo);

        for (Adyacente adyacente : adyacentes) {
            if (adyacente.getNodo() == nodoBuscar){
                res = adyacente;
                break;
            }
        }
    return res;
    }
}
