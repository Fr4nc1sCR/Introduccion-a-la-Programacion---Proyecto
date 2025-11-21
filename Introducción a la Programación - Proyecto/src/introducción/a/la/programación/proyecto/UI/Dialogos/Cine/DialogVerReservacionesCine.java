/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Diálogo de solo lectura que muestra todas las reservaciones actuales del
 * Cine: para cada asiento ocupado se indica el código del asiento, el nombre
 * del empleado y su ID.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Cine;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogVerReservacionesCine extends JDialog {

    /**
     * Constructor del diálogo.
     * @param owner Ventana padre (VentanaCine).
     */
    public DialogVerReservacionesCine(Frame owner) {
        super(owner, "Reservaciones del Cine", true);

        setSize(600, 420);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new BorderLayout(8, 8));
        card.setPreferredSize(new Dimension(420, 300));

        JLabel titulo = new JLabel("Reservaciones actuales", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        StringBuilder sb = new StringBuilder();
        boolean hay = false;

        for (int f = 0; f < 5; f++) {
            for (int c = 0; c < 6; c++) {
                String id = Data.asientosCine[f][c];
                if (id != null) {
                    if (!hay) {
                        sb.append("Reservaciones actuales para la película:\n")
                          .append(Data.pelicula)
                          .append("\n\n");
                        hay = true;
                    }
                    String asiento = Util.codigoAsiento(f, c);
                    String nombre  = Util.obtenerNombreEmpleado(id);

                    sb.append("- Asiento ").append(asiento)
                      .append(": ").append(nombre)
                      .append(" (").append(id).append(")\n");
                }
            }
        }

        if (!hay) {
            sb.append("No hay reservaciones en este momento.");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setOpaque(false);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        ModernButton btnCerrar = new ModernButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(btnCerrar);

        card.add(titulo, BorderLayout.NORTH);
        card.add(scroll, BorderLayout.CENTER);
        card.add(bottom, BorderLayout.SOUTH);

        fondo.add(card);
    }
}
