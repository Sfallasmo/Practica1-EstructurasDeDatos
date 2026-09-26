import java.time.LocalDate;

public class Ticket {

    // Atributo estatico: cuenta los tickets creados y sirve para generar el id
    private static int cantidad = 0;

    // Atributos
    private int id;
    private String descripcion;
    private String nombreCompleto;
    private int prioridad; // 1 = Alta, 2 = Media, 3 = Baja
    private LocalDate fechaCreacion;
    private LocalDate fechaResolucion;

    // Constructor
    public Ticket(String descripcion, String nombreCompleto, int prioridad) {
        cantidad++;
        this.id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDate.now();
        this.fechaResolucion = null; // Todo ticket empieza sin resolver
    }

    // Getters
    public static int getCantidad() {
        return cantidad;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaResolucion() {
        return fechaResolucion;
    }

    // Setter
    public void setFechaResolucion(LocalDate fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    // Convierte el numero de prioridad en texto
    public String getNombrePrioridad() {
        if (prioridad == 1) {
            return "Alta";
        } else if (prioridad == 2) {
            return "Media";
        } else {
            return "Baja";
        }
    }

    // toString
    @Override
    public String toString() {
        String resolucion = "Pendiente";
        if (fechaResolucion != null) {
            resolucion = fechaResolucion.toString();
        }

        return "\nId: " + id
                + "\nDescripcion: " + descripcion
                + "\nNombre completo: " + nombreCompleto
                + "\nPrioridad: " + getNombrePrioridad()
                + "\nFecha creacion: " + fechaCreacion
                + "\nFecha resolucion: " + resolucion;
    }
}