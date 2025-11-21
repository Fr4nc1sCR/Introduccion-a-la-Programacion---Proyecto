/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Ventana principal del módulo Clases. Gestiona reservaciones,
 * horarios y modificaciones con los diálogos reales.
 */

package introducción.a.la.programación.proyecto.UI;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.UI.Dialogos.Clases.*;

public class VentanaClases extends JFrame {

    public VentanaClases() {

        setTitle("Módulo Clases");
        setSize(660, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        JLabel titulo = new JLabel("Gestión de Clases");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.add(titulo);

        fondo.add(header, BorderLayout.NORTH);

        GlassCard card = new GlassCard(new GridLayout(5, 1, 14, 14));
        card.setPreferredSize(new Dimension(350, 350));

        ModernButton btnReservar = new ModernButton("Reservar Clase");
        ModernButton btnHorarios = new ModernButton("Consultar Horarios");
        ModernButton btnModificar = new ModernButton("Modificar Clase");
        ModernButton btnConsultar = new ModernButton("Consultar Reservaciones");
        ModernButton btnEliminar = new ModernButton("Eliminar Reservación");

        // Acciones REALES
        btnReservar.addActionListener(e -> new DialogReservarClase(this).setVisible(true));
        btnHorarios.addActionListener(e -> new DialogHorariosClases(this).setVisible(true));
        btnModificar.addActionListener(e -> new DialogModificarClase(this).setVisible(true));
        btnConsultar.addActionListener(e -> new DialogConsultarReservacionesClase(this).setVisible(true));
        btnEliminar.addActionListener(e -> new DialogEliminarClase(this).setVisible(true));

        card.add(btnReservar);
        card.add(btnHorarios);
        card.add(btnModificar);
        card.add(btnConsultar);
        card.add(btnEliminar);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(card);

        fondo.add(center, BorderLayout.CENTER);

        setVisible(true);
    }
}
