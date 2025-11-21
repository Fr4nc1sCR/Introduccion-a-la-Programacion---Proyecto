/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 22/11/2025
 *
 * Descripción:
 * Permite modificar un pedido existente cambiando la bebida
 * y/o la hora, validando que el ID exista y tenga un pedido.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Barista;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogModificarPedido extends JDialog {

    private final JTextField txtId;
    private final JComboBox<String> comboBebida;
    private final JTextField txtHora;

    public DialogModificarPedido(Frame owner) {
        super(owner, "Modificar Pedido", true);

        setSize(540, 400);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new GridBagLayout());
        setContentPane(fondo);

        GlassCard card = new GlassCard(new GridBagLayout());
        card.setPreferredSize(new Dimension(580, 320));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 12, 8, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Modificar Pedido");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);

        comboBebida = new JComboBox<>(new String[]{
                "Café normal", "Capuchino", "Capuchino Vainilla",
                "Chocolate", "Moka", "Té chai", "Café frío"
        });

        txtHora = new JTextField(10);
        txtHora.setText("4:00 pm");

        // Etiquetas
        Dimension labelSize = new Dimension(120, 25);
        JLabel lblId = new JLabel("ID:");
        lblId.setPreferredSize(labelSize);

        JLabel lblBebida = new JLabel("Bebida:");
        lblBebida.setPreferredSize(labelSize);

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setPreferredSize(labelSize);

        ModernButton btnModificar = new ModernButton("Guardar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnModificar.addActionListener(e -> modificar());
        btnCancelar.addActionListener(e -> dispose());

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        c.gridx = 0; c.gridy = 2;
        card.add(lblBebida, c);
        c.gridx = 1;
        card.add(comboBebida, c);

        c.gridx = 0; c.gridy = 3;
        card.add(lblHora, c);
        c.gridx = 1;
        card.add(txtHora, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnModificar);

        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    /**
     * Modifica un pedido según el ID.
     */
    private void modificar() {

        String id = txtId.getText().trim();
        String bebida = (String) comboBebida.getSelectedItem();
        String hora = txtHora.getText().trim();

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

                Data.pedidosBarista[i][1] = bebida;
                Data.pedidosBarista[i][2] = hora;

                JOptionPane.showMessageDialog(this,
                        "Pedido modificado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                return;
            }
        }

        Util.mostrarError("El empleado no tiene pedidos registrados.");
    }
}
