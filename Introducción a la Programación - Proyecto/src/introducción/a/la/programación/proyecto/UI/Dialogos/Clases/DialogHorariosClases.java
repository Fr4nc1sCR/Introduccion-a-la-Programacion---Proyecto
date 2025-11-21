/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Muestra los horarios disponibles de cada clase junto
 * con la cantidad de campos libres.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Clases;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;

public class DialogHorariosClases extends JDialog {

    public DialogHorariosClases(Frame owner) {
        super(owner, "Horarios de Clases", true);

        setSize(640, 380);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new BorderLayout(8, 8));
        card.setPreferredSize(new Dimension(310, 190));

        JLabel titulo = new JLabel("Horarios de Clases", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        String texto =
                "Clase de Baile: 7:00 pm  (Disponibles: " + (30 - Data.cupoBaile) + ")\n" +
                "Clase de Yoga:  8:00 pm  (Disponibles: " + (30 - Data.cupoYoga) + ")";

        JTextArea area = new JTextArea(texto);
        area.setOpaque(false);
        area.setEditable(false);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));

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
