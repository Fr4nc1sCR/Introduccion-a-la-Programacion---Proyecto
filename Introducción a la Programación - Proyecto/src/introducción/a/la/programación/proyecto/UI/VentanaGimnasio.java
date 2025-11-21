/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Ventana principal del módulo Gimnasio. Administra reservaciones
 * por medio de los diálogos reales del sistema.
 */

package introducción.a.la.programación.proyecto.UI;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.UI.Dialogos.Gimnasio.*;

public class VentanaGimnasio extends JFrame {

    public VentanaGimnasio() {

        setTitle("Módulo Gimnasio");
        setSize(660, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        JLabel titulo = new JLabel("Gestión del Gimnasio");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.add(titulo);

        fondo.add(header, BorderLayout.NORTH);

        GlassCard card = new GlassCard(new GridLayout(5, 1, 14, 14));
        card.setPreferredSize(new Dimension(350, 350));

        ModernButton btnReservar = new ModernButton("Reservar Horario");
        ModernButton btnConsultar = new ModernButton("Consultar Reservaciones");
        ModernButton btnHorarios = new ModernButton("Consultar Horarios");
        ModernButton btnModificar = new ModernButton("Modificar Reservación");
        ModernButton btnEliminar = new ModernButton("Eliminar Reservación");

        // Acciones REALES
        btnReservar.addActionListener(e -> new DialogReservarGym(this).setVisible(true));
        btnConsultar.addActionListener(e -> new DialogConsultarReservacionesGym(this).setVisible(true));
        btnHorarios.addActionListener(e -> new DialogHorariosGym(this).setVisible(true));
        btnModificar.addActionListener(e -> new DialogModificarGym(this).setVisible(true));
        btnEliminar.addActionListener(e -> new DialogEliminarGym(this).setVisible(true));

        card.add(btnReservar);
        card.add(btnConsultar);
        card.add(btnHorarios);
        card.add(btnModificar);
        card.add(btnEliminar);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(card);

        fondo.add(center, BorderLayout.CENTER);

        setVisible(true);
    }
}
