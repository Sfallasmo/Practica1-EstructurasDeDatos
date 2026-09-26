public class ColaPrioridad {

    // Atributos
    private NodoCola frente;

    // Constructor
    public ColaPrioridad() {
        frente = null;
    }

    // Operaciones
    private boolean estaVacia() {
        return frente == null;
    }

    public void insertar(Ticket ticket) {
        NodoCola nodo = new NodoCola(ticket);

        // Si la cola esta vacia o el ticket nuevo tiene mas prioridad que el frente,
        // el nuevo nodo pasa a ser el frente
        if (estaVacia() || ticket.getPrioridad() < frente.getTicket().getPrioridad()) {
            nodo.setSiguiente(frente);
            frente = nodo;
            return;
        }

        // Recorrer la cola hasta encontrar donde va el ticket segun su prioridad.
        // Se usa <= para que los de igual prioridad respeten el orden de llegada
        NodoCola temp = frente;
        while (temp.getSiguiente() != null
                && temp.getSiguiente().getTicket().getPrioridad() <= ticket.getPrioridad()) {
            temp = temp.getSiguiente();
        }

        // Colocar el nuevo nodo despues de temp
        nodo.setSiguiente(temp.getSiguiente());
        temp.setSiguiente(nodo);
    }

    public Ticket eliminar() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia, no hay tickets pendientes.");
            return null;
        }

        Ticket ticket = frente.getTicket();
        frente = frente.getSiguiente();
        return ticket;
    }

    public Ticket verFrente() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia, no hay tickets pendientes.");
            return null;
        }
        return frente.getTicket();
    }

    public void mostrarCola() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia, no hay tickets pendientes.");
            return;
        }

        NodoCola temp = frente;
        while (temp != null) {
            System.out.println(temp.getTicket());
            temp = temp.getSiguiente();
        }
    }

    private class NodoCola {

        // Atributos
        private Ticket ticket;
        private NodoCola siguiente;

        // Constructor
        public NodoCola(Ticket ticket) {
            this.ticket = ticket;
            siguiente = null;
        }

        // Getters
        public Ticket getTicket() {
            return ticket;
        }

        public NodoCola getSiguiente() {
            return siguiente;
        }

        // Setter
        public void setSiguiente(NodoCola siguiente) {
            this.siguiente = siguiente;
        }
    }
}