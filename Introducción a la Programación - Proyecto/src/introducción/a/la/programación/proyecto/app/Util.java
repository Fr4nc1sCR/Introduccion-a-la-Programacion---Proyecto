/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Esta clase contiene métodos utilitarios de uso común en el sistema, incluyendo
 * validación de datos, obtención de nombres, códigos de asientos y funciones
 * para mostrar mensajes de error o salir del sistema.
 */

package introducción.a.la.programación.proyecto.app;

import javax.swing.JOptionPane;

public class Util {

    // =====================================================
    // Métodos para Empleados
    // =====================================================

    /**
     * Verifica si un empleado existe según su ID.
     * @param id ID del empleado
     * @return true si existe, false si no
     */
    public static boolean existeEmpleado(String id) {
        for (String e : Data.empleados) {
            if (id.equals(e)) return true;
        }
        return false;
    }

    /**
     * Obtiene el nombre del empleado según su ID.
     * @param id ID del empleado
     * @return Nombre del empleado o "Empleado" si no existe
     */
    public static String obtenerNombreEmpleado(String id) {
        for (int i = 0; i < Data.empleados.length; i++) {
            if (Data.empleados[i].equals(id)) {
                return Data.nombres[i];
            }
        }
        return "Empleado";
    }


    // =====================================================
    // Métodos para Cine
    // =====================================================

    /**
     * Convierte una posición (fila, columna) a código de asiento (ej. A1, C5).
     * @param fila índice de fila
     * @param col índice de columna
     * @return código del asiento
     */
    public static String codigoAsiento(int fila, int col) {
        String[] letras = {"A", "B", "C", "D", "E"};
        return letras[fila] + (col + 1);
    }


    // =====================================================
    // Interfaz Gráfica y Mensajes
    // =====================================================

    /**
     * Muestra un cuadro de diálogo de error.
     */
    public static void mostrarError(String msg) {
        JOptionPane.showMessageDialog(null, msg,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}