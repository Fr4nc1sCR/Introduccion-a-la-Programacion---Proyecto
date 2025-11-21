/**
 * Creadores:
 *  - Julián Francisco Quesada Víquez
 *  - Valeria Yhakina Alfaro Ramírez
 *  - Justin Roberto Cordero Miranda
 *
 * Fecha de Creación: 22/11/2025
 *
 * Descripción:
 * Permite eliminar un pedido existente según el ID del empleado.
 * Ajusta el arreglo global Data.pedidosBarista y disminuye el
 * contador totalPedidos.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Barista;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogEliminarPedido extends JDialog {

    private final JTextField txtId;

    public DialogEliminarPedido(Frame owner) {
        super(owner, "Eliminar Pedido", true);

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

        JLabel titulo = new JLabel("Eliminar Pedido");
        titulo.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 35, 55));

        txtId = new JTextField(15);

        Dimension labelSize = new Dimension(120, 25);
        JLabel lblId = new JLabel("ID:");
        lblId.setPreferredSize(labelSize);

        ModernButton btnEliminar = new ModernButton("Eliminar");
        ModernButton btnCancelar = new ModernButton("Cancelar");

        btnEliminar.addActionListener(e -> eliminar());
        btnCancelar.addActionListener(e -> dispose());

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        card.add(titulo, c);

        c.gridy = 1; c.gridwidth = 1;
        card.add(lblId, c);
        c.gridx = 1;
        card.add(txtId, c);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        botones.setOpaque(false);
        botones.add(btnCancelar);
        botones.add(btnEliminar);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        card.add(botones, c);

        fondo.add(card);
    }

    /**
     * Elimina el pedido asociado al ID ingresado.
     */
    private void eliminar() {

        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            Util.mostrarError("Debe ingresar un ID.");
            return;
        }

        boolean encontrado = false;

        for (int i = 0; i < Data.totalPedidos; i++) {
            if (Data.pedidosBarista[i][0].equals(id)) {

                // Compactar el arreglo para eliminar el pedido
                for (int j = i; j < Data.totalPedidos - 1; j++) {
                    Data.pedidosBarista[j] = Data.pedidosBarista[j + 1];
                }

                Data.pedidosBarista[Data.totalPedidos - 1] = new String[3];
                Data.totalPedidos--;

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            Util.mostrarError("Ese empleado no tiene pedidos registrados.");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Pedido eliminado exitosamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
}
