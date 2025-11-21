/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 21/11/2025
 *
 * Descripción:
 * Ventana principal del módulo Barista. Permite gestionar pedidos,
 * consultarlos, modificarlos y eliminarlos mediante los diálogos
 * especializados del sistema.
 */

package introducción.a.la.programación.proyecto.UI;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.UI.Dialogos.Barista.*;

public class VentanaBarista extends JFrame {

    public VentanaBarista() {

        setTitle("Módulo Barista");
        setSize(660, 540);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        // ---------- HEADER ----------
        JLabel titulo = new JLabel("Gestión del Barista");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.add(titulo);

        fondo.add(header, BorderLayout.NORTH);

        // ---------- TARJETA ----------
        GlassCard card = new GlassCard(new GridLayout(4, 1, 14, 14));
        card.setPreferredSize(new Dimension(350, 300));

        ModernButton btnSolicitar = new ModernButton("Solicitar Bebida");
        ModernButton btnModificar = new ModernButton("Modificar Pedido");
        ModernButton btnConsultar = new ModernButton("Consultar Pedidos");
        ModernButton btnEliminar = new ModernButton("Eliminar Pedido");

        btnSolicitar.addActionListener(e -> new DialogSolicitarBebida(this).setVisible(true));
        btnModificar.addActionListener(e -> new DialogModificarPedido(this).setVisible(true));
        btnConsultar.addActionListener(e -> new DialogConsultarPedidos(this).setVisible(true));
        btnEliminar.addActionListener(e -> new DialogEliminarPedido(this).setVisible(true));

        card.add(btnSolicitar);
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
