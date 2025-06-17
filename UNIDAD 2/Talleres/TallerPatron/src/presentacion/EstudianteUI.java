package presentacion;

import datos.model.Estudiante;
import logica_negocio.EstudianteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EstudianteUI extends JFrame {
    private final EstudianteService service = new EstudianteService();
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Edad"}, 0);

    private final JTable table = new JTable(tableModel);
    private final JTextField idField = new JTextField(5);
    private final JTextField nombreField = new JTextField(10);
    private final JTextField edadField = new JTextField(5);

    public EstudianteUI() {
        setTitle("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Edad:"));
        formPanel.add(edadField);
        formPanel.add(new JLabel()); // espacio vacío

        JButton addBtn = new JButton("Agregar");
        JButton updateBtn = new JButton("Actualizar");
        JButton deleteBtn = new JButton("Eliminar");
        JButton searchBtn = new JButton("Consultar");

        formPanel.add(addBtn);
        formPanel.add(updateBtn);
        formPanel.add(deleteBtn);
        formPanel.add(searchBtn);

        add(formPanel, BorderLayout.NORTH);

        // Tabla
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Acciones
        addBtn.addActionListener(this::agregarEstudiante);
        updateBtn.addActionListener(this::actualizarEstudiante);
        deleteBtn.addActionListener(this::eliminarEstudiante);
        searchBtn.addActionListener(this::consultarEstudiante);

        cargarTabla();
        setVisible(true);
    }

    // Método que agrega un nuevo estudiante usando los datos del formulario
    private void agregarEstudiante(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            int edad = Integer.parseInt(edadField.getText());

            // Se pasa el nuevo estudiante al servicio para guardarlo
            service.agregarEstudiante(new Estudiante(id, nombre, edad));

            // Se actualiza la tabla y se limpian los campos del formulario
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            mostrarError("Datos inválidos."); // Captura errores de formato o campos vacíos
        }
    }

    // Método que actualiza un estudiante existente
    private void actualizarEstudiante(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            int edad = Integer.parseInt(edadField.getText());

            // Se verifica que el estudiante exista
            if (service.buscarEstudiante(id) != null) {
                // Si existe, se actualiza con los nuevos datos
                service.actualizarEstudiante(new Estudiante(id, nombre, edad));
                cargarTabla();
                limpiarCampos();
            } else {
                mostrarError("Estudiante no encontrado.");
            }
        } catch (Exception ex) {
            mostrarError("Datos inválidos.");
        }
    }

    // Método que elimina un estudiante según el ID ingresado
    private void eliminarEstudiante(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());

            // Verifica si el estudiante existe antes de eliminar
            if (service.buscarEstudiante(id) != null) {
                service.eliminarEstudiante(id);
                cargarTabla();
                limpiarCampos();
            } else {
                mostrarError("Estudiante no encontrado para eliminar.");
            }
        } catch (Exception ex) {
            mostrarError("ID inválido.");
        }
    }

    // Método que busca un estudiante por ID y lo muestra en la tabla (solo ese)
    private void consultarEstudiante(ActionEvent e) {
        try {
            int id = Integer.parseInt(idField.getText());
            Estudiante est = service.buscarEstudiante(id);

            if (est != null) {
                tableModel.setRowCount(0); // Limpia la tabla actual
                // Agrega solo el estudiante consultado
                tableModel.addRow(new Object[]{est.getid(), est.getNombre(), est.getEdad()});
            } else {
                mostrarError("Estudiante no encontrado.");
            }
        } catch (Exception ex) {
            mostrarError("ID inválido.");
        }
    }

    // Carga todos los estudiantes desde la lista del servicio y los muestra en la tabla
    private void cargarTabla() {
        tableModel.setRowCount(0); // Limpia la tabla
        for (Estudiante est : service.listarEstudiantes()) {
            tableModel.addRow(new Object[]{est.getid(), est.getNombre(), est.getEdad()});
        }
    }

    // Limpia los campos de texto del formulario
    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        edadField.setText("");
    }

    // Muestra un mensaje de error en una ventana emergente
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}