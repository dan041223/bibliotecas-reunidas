package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuarioPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable tableUsuario;
    DefaultTableModel modeloUsuario = new DefaultTableModel();
    private JFrame frame;
    private JTextField textFieldNombre;
    private JTextField textFieldTelefono;
    private JTextField textFieldEmail;
    private JTextField textFieldCalle;
    private JTextField textFieldPostal;
    private JTextField textFieldDni;
    private JTextField textFieldPassword;

    // Creamos los JButton
    JButton btnAnyadir;
    JButton btnModificar;
    JButton btnEliminar;
    JComboBox<String> comboBoxPerfil; // Corregido el tipo de JComboBox

    public UsuarioPanel() {
        setLayout(null);

        JLabel lblUsuarios = new JLabel("Usuarios");
        lblUsuarios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblUsuarios.setBounds(45, 44, 145, 45);
        add(lblUsuarios);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(45, 100, 757, 540);
        add(scrollPane);

        btnAnyadir = new JButton("Añadir");
        btnAnyadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aumentarTamanyo();

                // Obtener valores de los JTextField y JComboBox
                String nombre = textFieldNombre.getText();
                int telefono = Integer.parseInt(textFieldTelefono.getText());
                String email = textFieldEmail.getText();
                String calle = textFieldCalle.getText();
                int codigoPostal = Integer.parseInt(textFieldPostal.getText());
                String dni = textFieldDni.getText();
                String perfil = (String) comboBoxPerfil.getSelectedItem();
                String password = textFieldPassword.getText();

                // Lógica para insertar el nuevo usuario en la base de datos
                DataMetodos.insertarUsuario(nombre, telefono, email, calle, codigoPostal, dni, perfil, password);

                limpiarTextFields();
                recargarTablaUsuario();
                disminuirTamanyo();
            }
        });
        btnAnyadir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        btnAnyadir.setBounds(200, 50, 117, 37);
        add(btnAnyadir);

        btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        btnModificar.setBounds(377, 50, 117, 37);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        btnEliminar.setBounds(558, 50, 117, 37);
        add(btnEliminar);

        tableUsuario = new JTable() {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        scrollPane.setViewportView(tableUsuario);
        modeloUsuario.setColumnIdentifiers(new Object[] { "ID", "Nombre", "Teléfono", "Email", "Calle", "Código postal",
                "DNI", "Tipo de perfil", "Contraseña" });
        tableUsuario.setModel(modeloUsuario);

        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(969, 112, 212, 30);
        add(textFieldNombre);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setColumns(10);
        textFieldTelefono.setBounds(969, 164, 212, 30);
        add(textFieldTelefono);

        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(969, 217, 212, 30);
        add(textFieldEmail);

        textFieldCalle = new JTextField();
        textFieldCalle.setColumns(10);
        textFieldCalle.setBounds(969, 269, 212, 30);
        add(textFieldCalle);

        textFieldPostal = new JTextField();
        textFieldPostal.setColumns(10);
        textFieldPostal.setBounds(969, 319, 212, 30);
        add(textFieldPostal);

        textFieldDni = new JTextField();
        textFieldDni.setColumns(10);
        textFieldDni.setBounds(969, 373, 212, 30);
        add(textFieldDni);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(969, 487, 212, 30);
        add(textFieldPassword);

        String[] Perfiles = { "administrativo", "administrador" };
        comboBoxPerfil = new JComboBox<>(Perfiles); // Corregido el tipo de JComboBox
        comboBoxPerfil.setBounds(969, 437, 212, 24);
        add(comboBoxPerfil);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(843, 110, 89, 30);
        add(lblNombre);

        JLabel lblTelefono = new JLabel("Telefono");
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTelefono.setBounds(843, 162, 89, 30);
        add(lblTelefono);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(843, 215, 89, 30);
        add(lblEmail);

        JLabel lblCalle = new JLabel("Calle");
        lblCalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCalle.setBounds(843, 267, 89, 30);
        add(lblCalle);

        JLabel lblCodigoPostal = new JLabel("Codigo Postal");
        lblCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCodigoPostal.setBounds(843, 317, 89, 30);
        add(lblCodigoPostal);

        JLabel lblDni = new JLabel("Dni");
        lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDni.setBounds(843, 371, 89, 30);
        add(lblDni);

        JLabel lblTipoDePerfil = new JLabel("Tipo de Perfil");
        lblTipoDePerfil.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTipoDePerfil.setBounds(843, 432, 89, 30);
        add(lblTipoDePerfil);

        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblContrasea.setBounds(843, 485, 89, 30);
        add(lblContrasea);
    }

    // ====================== metodos para esta tablas==============

    private void limpiarTextFields() {
        textFieldNombre.setText("");
        textFieldTelefono.setText("");
        textFieldEmail.setText("");
        textFieldCalle.setText("");
        textFieldPostal.setText("");
        textFieldDni.setText("");
        comboBoxPerfil.setSelectedIndex(0);
        textFieldPassword.setText("");
    }

    private void recargarTablaUsuario() {
        // Cargo los elementos de la tabla en el modelo
        modeloUsuario.setRowCount(0); // SIRVE PARA RESETEAR LA TABLA
        for (Object[] filaDeUsuario : DataMetodos.obtenerFilasTablaUsuario()) {
            modeloUsuario.addRow(filaDeUsuario);
        }
    }

    private void disminuirTamanyo() {
        if (frame != null) {
            int nuevoAncho = 810;
            int nuevoAlto = frame.getHeight();
            frame.setSize(nuevoAncho, nuevoAlto);
            System.out.println("Disminuido");
        }
    }

    private void aumentarTamanyo() {
        if (frame != null) {
            int nuevoAncho = 1250;
            int nuevoAlto = frame.getHeight();
            frame.setSize(nuevoAncho, nuevoAlto);
        }
    }
}
