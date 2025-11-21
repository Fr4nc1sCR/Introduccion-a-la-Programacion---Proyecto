/**
 * Dialogo para eliminar un empleado del sistema.
 */

package introducción.a.la.programación.proyecto.UI.Dialogos.Empleados;

import javax.swing.*;
import java.awt.*;

import introducción.a.la.programación.proyecto.UI.Componentes.*;
import introducción.a.la.programación.proyecto.app.Data;
import introducción.a.la.programación.proyecto.app.Util;

public class DialogEliminarEmpleado extends JDialog {

    private String id;

    public DialogEliminarEmpleado(Frame owner, String id) {
        super(owner, "Eliminar Empleado", true);
        this.id = id;

        setSize(400, 220);
        setLocationRelativeTo(owner);
        setResizable(false);

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        setContentPane(fondo);

        JLabel mensaje = new JLabel(
                "<html><center>¿Seguro que desea eliminar el empleado<br><b>" + id + "</b>?</center></html>",
                SwingConstants.CENTER
        );
        mensaje.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        mensaje.setForeground(Color.WHITE);

        ModernButton btnSi = new ModernButton("Sí, eliminar");
        ModernButton btnNo = new ModernButton("Cancelar");

        btnSi.addActionListener(e -> eliminar());
        btnNo.addActionListener(e -> dispose());

        JPanel panelBtns = new JPanel(new FlowLayout());
        panelBtns.setOpaque(false);
        panelBtns.add(btnNo);
        panelBtns.add(btnSi);

        fondo.add(mensaje, BorderLayout.CENTER);
        fondo.add(panelBtns, BorderLayout.SOUTH);
    }

    private void eliminar() {
        for (int i = 0; i < Data.empleados.length; i++) {
            if (id.equals(Data.empleados[i])) {

                Data.empleados[i] = null;
                Data.nombres[i] = null;

                JOptionPane.showMessageDialog(this,
                        "Empleado eliminado correctamente.");
                dispose();
                return;
            }
        }
    }
}