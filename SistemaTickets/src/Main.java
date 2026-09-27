import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    // Estructuras del sistema
    static ColaPrioridad ticketsPendientes = new ColaPrioridad();
    static ListaEnlazadaSimple ticketsResueltos = new ListaEnlazadaSimple();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        // Menu principal: se repite hasta que el usuario elija salir
        do {
            System.out.println("\n===== SISTEMA DE TICKETS =====");
            System.out.println("1. Menu de usuario");
            System.out.println("2. Menu de administrador");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerNumero();

            switch (opcion) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                case 0:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    // ----- Menu de usuario -----
    public static void menuUsuario() {
        int opcion;

        do {
            System.out.println("\n----- MENU DE USUARIO -----");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket resuelto");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = leerNumero();

            switch (opcion) {
                case 1:
                    crearTicket();
                    break;
                case 2:
                    buscarTicket();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    public static void crearTicket() {
        System.out.print("Nombre completo: ");
        String nombreCompleto = sc.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        System.out.print("Prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int prioridad = leerNumero();
        while (prioridad < 1 || prioridad > 3) {
            System.out.print("Prioridad invalida, digite 1, 2 o 3: ");
            prioridad = leerNumero();
        }

        Ticket ticket = new Ticket(descripcion, nombreCompleto, prioridad);
        ticketsPendientes.insertar(ticket);
        System.out.println("Ticket creado. Su numero de ticket es: " + ticket.getId());
    }

    public static void buscarTicket() {
        System.out.print("Digite el id del ticket: ");
        int id = leerNumero();

        // Si el id no fue asignado a ningun ticket, no existe
        if (id < 1 || id > Ticket.getCantidad()) {
            System.out.println("No existe un ticket con ese id.");
            return;
        }

        Ticket ticket = ticketsResueltos.buscar(id);
        if (ticket == null) {
            System.out.println("El ticket " + id + " esta pendiente.");
        } else {
            System.out.println(ticket);
        }
    }

    // ----- Menu de administrador -----
    public static void menuAdministrador() {
        int opcion;

        do {
            System.out.println("\n----- MENU DE ADMINISTRADOR -----");
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("3. Ver tickets pendientes");
            System.out.println("4. Ver tickets resueltos");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            opcion = leerNumero();

            switch (opcion) {
                case 1 -> {
                    Ticket frente = ticketsPendientes.verFrente();
                    if (frente != null) {
                        System.out.println(frente);
                    }
                    break;
                case 2:
                    resolverTicket();
                    break;
                case 3:
                    ticketsPendientes.mostrarCola();
                    break;
                case 4:
                    ticketsResueltos.mostrarLista();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    public static void resolverTicket() {
        // Se saca el ticket del frente de la cola
        Ticket ticket = ticketsPendientes.eliminar();

        if (ticket != null) {
            // Se le pone la fecha de resolucion y se pasa a la lista de resueltos
            ticket.setFechaResolucion(LocalDate.now());
            ticketsResueltos.insertarFin(ticket);
            System.out.println("Ticket resuelto:");
            System.out.println(ticket);
        }
    }

    // Lee un numero y evita que el programa se caiga si se escriben letras
    public static int leerNumero() {
        while (!sc.hasNextInt()) {
            System.out.print("Debe digitar un numero: ");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine(); // Limpia el enter que queda en el Scanner
        return numero;
    }
}