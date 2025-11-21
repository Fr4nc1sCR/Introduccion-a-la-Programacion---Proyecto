/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Esta clase contiene todas las variables globales del sistema. Aquí se 
 * almacenan los datos de empleados, cine, clases, gimnasio y barista.
 * Además, se encarga de inicializar esos datos mediante el método cargarDatos().
 */

package introducción.a.la.programación.proyecto.app;

public class Data {

    // ===============================
    // Variables globales del sistema
    // ===============================

    /** Arreglo con los ID de los empleados registrados */
    public static String[] empleados;

    /** Nombres de los empleados (en el mismo orden que sus ID) */
    public static String[] nombres;

    /** Horarios disponibles para el gimnasio */
    public static String[] horariosGym;

    /** Reservaciones del gimnasio (índice = horario) */
    public static String[] reservasGym;

    /** Nombre de la película actual en el cine */
    public static String pelicula;

    /** Matriz 5x6 que representa los asientos del cine */
    public static String[][] asientosCine;

    /** Reservaciones de clases (baile/yoga) */
    public static String[][] reservasClases;

    /** Contadores de cupos */
    public static int cupoBaile = 0;
    public static int cupoYoga = 0;
    public static int totalReservasClases = 0;

    /** Pedidos registrados del barista */
    public static String[][] pedidosBarista;

    /** Total de pedidos realizados */
    public static int totalPedidos = 0;


    // ===============================
    // Método para inicializar datos
    // ===============================

    /**
     * Método encargado de cargar los datos iniciales del sistema al arrancar el programa.
     */
    public static void cargarDatos() {

        // ---- Empleados ----
        empleados = new String[100];
        nombres   = new String[100];

        // Empleados predeterminados
        empleados[0] = "E001";   nombres[0] = "Julián Quesada";
        empleados[1] = "E002";   nombres[1] = "Valeria Alfaro";
        empleados[2] = "E003";   nombres[2] = "Justin Cordero";


        // ---- Gimnasio ----
        horariosGym = new String[]{
            "2:00 pm", "3:00 pm", "4:00 pm",
            "5:00 pm", "6:00 pm", "7:00 pm"
        };

        reservasGym = new String[6];  // Un espacio por horario

        // ---- Cine ----
        pelicula = "Matrix Reloaded";
        asientosCine = new String[5][6]; // 5 filas x 6 columnas

        // ---- Clases ----
        reservasClases = new String[60][3]; // Máx. 60 reservas

        // ---- Barista ----
        pedidosBarista = new String[100][3]; // Máx. 100 pedidos
    }
}