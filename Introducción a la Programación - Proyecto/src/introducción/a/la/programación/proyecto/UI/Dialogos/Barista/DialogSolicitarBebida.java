/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 22/11/2025
 *
 * Descripción:
 * Este diálogo permite registrar un nuevo pedido de bebida
 * para un empleado. Incluye validación de ID, verificación
 * de pedidos existentes y actualización de la lista global
 * de pedidos almacenada en Data.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Barista;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogSolicitarBebida extends JDialog {

    private final JTextField txtId;
    private final JComboBox<String> comboBebida;
    private final JTextField txtHora;

    public DialogSolicitarBebida(Frame owner) {
        super(owner, "Solicitar Bebida", true);
        setSize(540, 400);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new GridBagLayout());
        card.setPreferredSize(new Dimension(580, 320));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 10, 8, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Solicitar Bebida");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        titulo.setForeground(new Color(40, 35, 55));

        JLabel lblInfo = new JLabel("Ingrese los datos del pedido:");
        lblInfo.setForeground(new Color(60, 60, 60));
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtId = new JTextField(15);

        comboBebida = new JComboBox<>(new String[]{
                "Café normal", "Capuchino", "Capuchino Vainilla",
                "Chocolate", "Moka", "Té chai", "Café frío"
        });

        txtHora = new JTextField(10);
        txtHora.setText("3:30 pm");

        // Etiquetas
        Dimension labelSize = new Dimension(120, 25);
        JLabel lblId = new JLabel("ID Empleado:");
        lblId.setPreferredSize(labelSize);

        JLabel lblBebida = new JLabel("Bebida:");
        lblBebida.setPreferredSize(labelSize);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setPreferredSize(labelSize);

        ModernButton btnAceptar = new ModernButton("Registrar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnAceptar.addActionListener(e -> registrar());
        btnCancelar.addActionListener(e -> dispose());

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1;
        card.add(lblInfo, c);

        c.gridy = 2; c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        c.gridx = 0; c.gridy = 3;
        card.add(lblBebida, c);
        c.gridx = 1;
        card.add(comboBebida, c);

        c.gridx = 0; c.gridy = 4;
        card.add(lblHora, c);
        c.gridx = 1;
        card.add(txtHora, c);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelBotones.setOpaque(false);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnAceptar);

        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        card.add(panelBotones, c);

        fondo.add(card);
    }

    /**
     * Registra el pedido validando ID y evitando duplicados.
     */
    private void registrar() {

        String id = txtId.getText().trim();
        String hora = txtHora.getText().trim();
        String bebida = (String) comboBebida.getSelectedItem();

        if (id.isEmpty() || hora.isEmpty()) {
            Util.mostrarError("Debe completar todos los campos.");
            return;
        }

        if (!Util.existeEmpleado(id)) {
            Util.mostrarError("El empleado no existe.");
            return;
        }

        for (int i = 0; i < Data.totalPedidos; i++) {
            if (Data.pedidosBarista[i][0].equals(id)) {
                Util.mostrarError("Este empleado ya realizó un pedido hoy.");
                return;
            }
        }

        Data.pedidosBarista[Data.totalPedidos][0] = id;
        Data.pedidosBarista[Data.totalPedidos][1] = bebida;
        Data.pedidosBarista[Data.totalPedidos][2] = hora;

        Data.totalPedidos++;

        JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente.");
        dispose();
    }
}
