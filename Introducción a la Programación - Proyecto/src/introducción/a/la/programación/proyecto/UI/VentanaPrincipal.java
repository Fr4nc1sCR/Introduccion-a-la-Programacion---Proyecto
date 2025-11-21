/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Esta clase representa la ventana principal del sistema. Desde aquí el usuario
 * puede acceder a los diferentes módulos: Cine, Gimnasio, Clases, Barista
 * y ahora también al módulo de Empleados.
 *
 * Utiliza componentes personalizados con diseño moderno, incluyendo tarjetas
 * con efecto glassmorphism y un fondo con degradado.
 */

package introducción.a.la.programación.proyecto.UI;

// Importaciones importantes
import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.ModernButton;
import introducción.a.la.programación.proyecto.UI.Componentes.GlassCard;
import introducción.a.la.programación.proyecto.UI.Componentes.GradientPanel;

// Ventanas de los módulos
import introducción.a.la.programación.proyecto.UI.VentanaCine;
import introducción.a.la.programación.proyecto.UI.VentanaGimnasio;
import introducción.a.la.programación.proyecto.UI.VentanaClases;
import introducción.a.la.programación.proyecto.UI.VentanaBarista;
import introducción.a.la.programación.proyecto.UI.VentanaEmpleados;  // ← NUEVA IMPORTACIÓN

public class VentanaPrincipal extends JFrame {

    /**
     * Constructor principal de la ventana.
     * Configura el diseño general y los botones que redirigen a los módulos.
     */
    public VentanaPrincipal() {

        setTitle("Sistema de Gestión - Ventana Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        // Panel de fondo con degradado
        GradientPanel panelFondo = new GradientPanel();
        panelFondo.setLayout(new GridBagLayout()); 
        add(panelFondo, BorderLayout.CENTER);

        // Tarjeta principal con efecto glassmorphism
        GlassCard tarjeta = new GlassCard(new GridLayout(6, 1, 18, 18));  
        tarjeta.setPreferredSize(new Dimension(420, 480));

        // Título
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        tarjeta.add(titulo);

        // -------------------------
        // Botones principales
        // -------------------------

        ModernButton btnCine = new ModernButton("Cine");
        btnCine.addActionListener(e -> new VentanaCine());
        tarjeta.add(btnCine);

        ModernButton btnGym = new ModernButton("Gimnasio");
        btnGym.addActionListener(e -> new VentanaGimnasio());
        tarjeta.add(btnGym);

        ModernButton btnClases = new ModernButton("Clases de Baile / Yoga");
        btnClases.addActionListener(e -> new VentanaClases());
        tarjeta.add(btnClases);

        ModernButton btnBarista = new ModernButton("Barista");
        btnBarista.addActionListener(e -> new VentanaBarista());
        tarjeta.add(btnBarista);

        // ------------------------------------------------------
        // NUEVO BOTÓN: MÓDULO DE EMPLEADOS
        // ------------------------------------------------------
        ModernButton btnEmpleados = new ModernButton("Empleados");
        btnEmpleados.addActionListener(e -> new VentanaEmpleados());
        tarjeta.add(btnEmpleados);

        // Añadir tarjeta al centro
        panelFondo.add(tarjeta);

        setVisible(true);
    }
}
