/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 * 
 * Fecha de Creación: 21/11/2025
 * 
 * Descripción: Este archivo contiene la clase Main donde se ejecuta el programa
 * principal. Aquí se inicializan los datos del sistema y se carga la ventana
 * principal del proyecto.
 */

package introducción.a.la.programación.proyecto.app;

// Importaciones importantes
import javax.swing.SwingUtilities;
import introducción.a.la.programación.proyecto.UI.VentanaPrincipal;

public class Main {

    /**
     * Método principal donde inicia la ejecución del sistema.
     */
    public static void main(String[] args) {

        // Cargar datos iniciales del sistema
        Data.cargarDatos();

        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}

