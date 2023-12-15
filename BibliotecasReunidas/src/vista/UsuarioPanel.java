package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.DataMetodos;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

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
    private JTextField textFieldId;

    public UsuarioPanel() {
    	setBackground(new Color(128, 128, 192));
        setLayout(null);
        
        BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res\\imagenes\\flechita_atras.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int labelWidth = 40;
	    int labelHeight = 40;
	    
	    Image scaledImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		
	    ImageIcon icon = new ImageIcon(scaledImg);
	    
		JLabel imgs = new JLabel(icon);
		imgs.setForeground(new Color(255, 255, 255));
		imgs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
			}
		});
		imgs.setBounds(10, 11, 47, 40);
		
		add(imgs);
		
		JLabel lblCerrarSesion = new JLabel("Volver al menu");
		lblCerrarSesion.setForeground(new Color(255, 255, 255));
		lblCerrarSesion.setBackground(new Color(255, 255, 255));
		lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCerrarSesion.setBounds(55, 23, 181, 14);
		lblCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
			}
		});
		add(lblCerrarSesion);

        JLabel lblUsuarios = new JLabel("Usuarios");
        lblUsuarios.setForeground(new Color(255, 255, 255));
        lblUsuarios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        lblUsuarios.setBounds(45, 44, 145, 45);
        add(lblUsuarios);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 100, 808, 540);
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
        btnModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aumentarTamanyo();
    			
        		// Obtener valores de los JTextField y JComboBox
        		int id = Integer.parseInt(textFieldId.getText());
                String nombre = textFieldNombre.getText();
                int telefono = Integer.parseInt(textFieldTelefono.getText());
                String email = textFieldEmail.getText();
                String calle = textFieldCalle.getText();
                int codigoPostal = Integer.parseInt(textFieldPostal.getText());
                String dni = textFieldDni.getText();
                String perfil = (String) comboBoxPerfil.getSelectedItem();
                String password = textFieldPassword.getText();

                // Lógica para insertar el nuevo usuario en la base de datos
                DataMetodos.modificarUsuario(id, nombre, telefono, email, calle, codigoPostal, dni, perfil, password);
        		
        		limpiarTextFields();
                recargarTablaUsuario();
                disminuirTamanyo();
                btnAnyadir.setEnabled(true);
                btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
        	}
        });
        btnModificar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        btnModificar.setBounds(377, 50, 117, 37);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aumentarTamanyo();
        			
        		// Obtener valores de los JTextField y JComboBox
        		int id = Integer.parseInt(textFieldId.getText());
        		String perfil = (String) comboBoxPerfil.getSelectedItem();
        		
                // Lógica para insertar el nuevo usuario en la base de datos
                DataMetodos.eliminarUsuario(id, perfil);
        		
        		limpiarTextFields();
                recargarTablaUsuario();
                disminuirTamanyo();
                btnAnyadir.setEnabled(true);
                btnEliminar.setEnabled(false);
				btnModificar.setEnabled(false);
        	}
        });
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

        textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(1079, 141, 212, 30);
		add(textFieldId);
        
        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(1079, 182, 212, 30);
        add(textFieldNombre);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setColumns(10);
        textFieldTelefono.setBounds(1079, 234, 212, 30);
        add(textFieldTelefono);

        textFieldEmail = new JTextField();
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(1079, 287, 212, 30);
        add(textFieldEmail);

        textFieldCalle = new JTextField();
        textFieldCalle.setColumns(10);
        textFieldCalle.setBounds(1079, 339, 212, 30);
        add(textFieldCalle);

        textFieldPostal = new JTextField();
        textFieldPostal.setColumns(10);
        textFieldPostal.setBounds(1079, 389, 212, 30);
        add(textFieldPostal);

        textFieldDni = new JTextField();
        textFieldDni.setColumns(10);
        textFieldDni.setBounds(1079, 443, 212, 30);
        add(textFieldDni);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(1079, 557, 212, 30);
        add(textFieldPassword);

        String[] Perfiles = { "administrativo", "administrador" };
        comboBoxPerfil = new JComboBox<>(Perfiles); // Corregido el tipo de JComboBox
        comboBoxPerfil.setBounds(1079, 507, 212, 24);
        add(comboBoxPerfil);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(953, 180, 89, 30);
        add(lblNombre);

        JLabel lblTelefono = new JLabel("Telefono");
        lblTelefono.setForeground(new Color(255, 255, 255));
        lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTelefono.setBounds(953, 232, 89, 30);
        add(lblTelefono);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(new Color(255, 255, 255));
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(953, 285, 89, 30);
        add(lblEmail);

        JLabel lblCalle = new JLabel("Calle");
        lblCalle.setForeground(new Color(255, 255, 255));
        lblCalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCalle.setBounds(953, 337, 89, 30);
        add(lblCalle);

        JLabel lblCodigoPostal = new JLabel("Codigo Postal");
        lblCodigoPostal.setForeground(new Color(255, 255, 255));
        lblCodigoPostal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCodigoPostal.setBounds(953, 387, 89, 30);
        add(lblCodigoPostal);

        JLabel lblDni = new JLabel("Dni");
        lblDni.setForeground(new Color(255, 255, 255));
        lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDni.setBounds(953, 441, 89, 30);
        add(lblDni);

        JLabel lblTipoDePerfil = new JLabel("Tipo de Perfil");
        lblTipoDePerfil.setForeground(new Color(255, 255, 255));
        lblTipoDePerfil.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTipoDePerfil.setBounds(953, 502, 89, 30);
        add(lblTipoDePerfil);

        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setForeground(new Color(255, 255, 255));
        lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblContrasea.setBounds(953, 555, 89, 30);
        add(lblContrasea);
        
     // Obtener el modelo de selección de la tabla
 		ListSelectionModel selectionModel = tableUsuario.getSelectionModel();
 		// Agregar un ListSelectionListener al modelo de selección
 		selectionModel.addListSelectionListener(new ListSelectionListener() {
 			@Override
 			public void valueChanged(ListSelectionEvent e) {
 				if (!e.getValueIsAdjusting()) {
					// Habilito estos botones
 					btnEliminar.setEnabled(true);
					btnModificar.setEnabled(true);
					
	 				if (tableUsuario.getSelectedRow() >= 0) {// si tenemos una fila seleccionada
	 				// volcar los campos de la fila en los textfields correspondientes
						String id = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 0).toString();
						String nombre = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 1).toString();
						String telefono = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 2).toString();
						String email = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 3).toString();
						String calle = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 4).toString();
						String codigo_postal = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 5).toString();
						String dni = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 6).toString();
						String tipo_perfil = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 7).toString();
						String password = modeloUsuario.getValueAt(tableUsuario.getSelectedRow(), 8).toString();
	
						textFieldId.setText(id);
						textFieldNombre.setText(nombre);
						textFieldTelefono.setText(telefono);
						textFieldEmail.setText(email);
						textFieldCalle.setText(calle);
						textFieldPostal.setText(codigo_postal);
						textFieldDni.setText(dni);
						comboBoxPerfil.setSelectedItem(tipo_perfil);
						textFieldPassword.setText(password);
						
						// Habilitar campos
						btnEliminar.setEnabled(true);
						btnModificar.setEnabled(true);
						
						//Desabilitar campos
						btnAnyadir.setEnabled(false);
	 				}
 				}
 			}
 		});
        
        recargarTablaUsuario();
        btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		textFieldId.setEnabled(false);
		
		JLabel lblId = new JLabel("Id");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(953, 139, 89, 30);
		add(lblId);

		tableUsuario.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableUsuario.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableUsuario.getColumnModel().getColumn(5).setPreferredWidth(30);
		tableUsuario.getColumnModel().getColumn(6).setPreferredWidth(60);
		
		BufferedImage img1 = null;
		try {
			img1 = ImageIO.read(new File("res\\imagenes\\posibleFondo.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int labelWidth1 = 1424;
	    int labelHeight1 = 825;
	    
	    Image scaledImg1 = img1.getScaledInstance(labelWidth1, labelHeight1, Image.SCALE_SMOOTH);
		
	    ImageIcon icon1 = new ImageIcon(scaledImg1);
	    
		JLabel lblNewLabel = new JLabel(icon1);
		lblNewLabel.setBounds(0, 0, 1434, 825);
		add(lblNewLabel);
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
