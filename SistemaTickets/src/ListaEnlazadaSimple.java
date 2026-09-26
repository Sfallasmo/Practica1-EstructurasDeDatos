public class ListaEnlazadaSimple {

    // Atributos
    private NodoLista primero;

    // Constructor
    public ListaEnlazadaSimple() {
        primero = null;
    }

    // Getter
    public NodoLista getPrimero() {
        return primero;
    }

    // Setter
    public void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    // Operaciones
    private boolean estaVacia() {
        return primero == null;
    }

    public void insertarInicio(Ticket ticket) {
        NodoLista nodo = new NodoLista(ticket);

        nodo.setSiguiente(primero);
        setPrimero(nodo);
    }

    public void insertarFin(Ticket ticket) {
        NodoLista nodo = new NodoLista(ticket);

        // Si la lista esta vacia
        if (estaVacia()) {
            setPrimero(nodo);
            return;
        }

        // Recorrer la lista hasta encontrar el ultimo nodo
        NodoLista temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }

        // El ultimo nodo apunta al nuevo nodo
        temp.setSiguiente(nodo);
    }

    public Ticket buscar(int id) {
        NodoLista temp = primero;

        while (temp != null) {
            if (temp.getTicket().getId() == id) {
                return temp.getTicket();
            }
            temp = temp.getSiguiente();
        }

        // Si no se encontro, el ticket sigue pendiente
        return null;
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia, no hay tickets resueltos.");
            return;
        }

        NodoLista temp = primero;
        while (temp != null) {
            System.out.println(temp.getTicket());
            temp = temp.getSiguiente();
        }
    }

    private class NodoLista {

        // Atributos
        private Ticket ticket;
        private NodoLista siguiente;

        // Constructor
        public NodoLista(Ticket ticket) {
            this.ticket = ticket;
            siguiente = null;
        }

        // Getters
        public Ticket getTicket() {
            return ticket;
        }

        public NodoLista getSiguiente() {
            return siguiente;
        }

        // Setter
        public void setSiguiente(NodoLista siguiente) {
            this.siguiente = siguiente;
        }
    }
}