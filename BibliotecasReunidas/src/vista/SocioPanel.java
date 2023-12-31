package vista;

import modelo.Socio;
import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import controlador.ConectorBBDD;


public class SocioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public Usuario usuario = Login.usuario;
	public JTextField tfEmailSocio;
	private JTable tablaSocios;
	public JRadioButton rdbtnBuscarPorId;
	public JRadioButton rdbtnBuscarPorCorreo;
	public JSpinner spinnerIdSocio;
	public ConectorBBDD con;
	public int filaSeleccionada;
	public int columnaSeleccionada;
	public int filaSeleccionadaBorrar;
	public int columnaSeleccionadaBorrar;
	public int filaSeleccionadaMod;
	public int columnaSeleccionadaMod;
	public static int idSocioSeleccionado;
	public int idSeleccionadoBorrar;
	public String nombreSeleccionadoModificar;
	public int telefonoSeleccionadoModificar;
	public String dniSeleccionadoModificar;
	public String calleSeleccionadoModificar;
	public int codigoPostalSeleccionadoModificar;
	public String emailSeleccionadoModificar;
	public int idSeleccionadoModificar;
	/*
	 * Le vas a decir al modelo de la tabla que las celdas no se pueden editar
	 * Le pasas la fila y columna que estas seleccionando y siempre va a devolver false el isCellEditable
	 */
	DefaultTableModel modeloTablaSociosBuscar = new DefaultTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	DefaultTableModel modeloTablaSociosEliminar = new DefaultTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	DefaultTableModel modeloTablaSociosModificar = new DefaultTableModel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField tfNombre;
	private JTextField tfEmail;
	private JFormattedTextField tfTelefono;
	private JFormattedTextField tfDni;
	private JTextField tfCalle;
	private JFormattedTextField tfCodigo_Postal;
	public MaskFormatter mask;
	public MaskFormatter mask2;
	public MaskFormatter mask3;
	public String[] vacios = new String[6];
	private JTextField tfEmailSocioBorrar;
	private JTable tablaSociosBorrar;
	public JRadioButton rdbtnBuscarSocioPorIdBorrar;
	public JSpinner spinnerIdSocioBorrar;
	public JRadioButton rdbtnBuscarSocioPorCorreoBorrar;
	public JButton btnFiltrarSocioBorrados;
	public JButton btnBorrarSocio;
	private JTextField tfNombreModSocios;
	private JTextField tfEmailModSocios;
	private JFormattedTextField tfDniModSocios;
	private JTextField tfCalleModSocios;
	private JTable tablaSociosMod;
	private JTextField tfFiltrarEmailModSocios;
	public JRadioButton rdbtnBuscarPorIdModSocios;
	public JRadioButton rdbtnBuscarPorCorreoModSocios;
	public JSpinner spinnerIdModSocios;
	public JFormattedTextField tfTelefonoModSocios;
	public JFormattedTextField tfCodigoPostalModSocios;
	public JButton btnModificar;
	public static int idSocio;
	
	private JFrame frame;


	/**
	 * Create the panel.
	 */
	public SocioPanel(JFrame frame) {
		setBackground(new Color(128, 128, 192));
		this.frame = frame;
		setLayout(null);
		
		try {
			BufferedImage img = ImageIO.read(new File("res\\imagenes\\flechita_atras.png"));
			
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
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBackground(new Color(128, 128, 192));
			tabbedPane.setBounds(10, 82, 1320, 718);
			add(tabbedPane);
			
			/*
			 * ==============================================================================================
			 * ==============================================================================================
			 * PANEL BUSCADOR DE SOCIOS
			 * ==============================================================================================
			 * ==============================================================================================
			 */
			
			JPanel panelBuscaSocio = new JPanel();
			panelBuscaSocio.setBackground(new Color(128, 128, 192));
			tabbedPane.addTab("Buscar socios", null, panelBuscaSocio, null);
			panelBuscaSocio.setLayout(null);
			
			JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.setOpaque(false);
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenarTabla1PorFiltro();
				}
			});
			btnFiltrar.setBounds(418, 8, 89, 23);
			panelBuscaSocio.add(btnFiltrar);
			
			rdbtnBuscarPorId = new JRadioButton("Buscar por id");
			rdbtnBuscarPorId.setForeground(new Color(255, 255, 255));
			rdbtnBuscarPorId.setOpaque(false);
			rdbtnBuscarPorId.setSelected(true);
			rdbtnBuscarPorId.setBounds(1013, 82, 109, 23);
			panelBuscaSocio.add(rdbtnBuscarPorId);
			rdbtnBuscarPorId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarONoComponente();
				}
			});
			
			rdbtnBuscarPorCorreo = new JRadioButton("Buscar por correo electrónico");
			rdbtnBuscarPorCorreo.setForeground(new Color(255, 255, 255));
			rdbtnBuscarPorCorreo.setOpaque(false);
			rdbtnBuscarPorCorreo.setBounds(1013, 242, 220, 23);
			panelBuscaSocio.add(rdbtnBuscarPorCorreo);
			rdbtnBuscarPorCorreo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarONoComponente();
				}
			});

			ButtonGroup grupoRadioButtons = new ButtonGroup();
			
			grupoRadioButtons.add(rdbtnBuscarPorCorreo);
			grupoRadioButtons.add(rdbtnBuscarPorId);
			
			
			SpinnerNumberModel modeloMinimoSpinner = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
			
			spinnerIdSocio = new JSpinner(modeloMinimoSpinner);
			spinnerIdSocio.setOpaque(false);
			spinnerIdSocio.setBounds(1023, 154, 56, 32);
			panelBuscaSocio.add(spinnerIdSocio);
			
			try {
				mask = new MaskFormatter("#########");
				mask2 = new MaskFormatter("########" + "A");
				mask3 = new MaskFormatter("#####");
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
			
			JLabel lblNewLabel = new JLabel("Buscar Socios");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setBackground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(10, 11, 210, 30);
			panelBuscaSocio.add(lblNewLabel);
			
			tfEmailSocio = new JTextField();
			tfEmailSocio.setBounds(1023, 324, 210, 20);
			panelBuscaSocio.add(tfEmailSocio);
			tfEmailSocio.setColumns(10);
			tfEmailSocio.setEnabled(false);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBackground(new Color(128, 128, 192));
			scrollPane.setBounds(10, 42, 993, 637);
			panelBuscaSocio.add(scrollPane);
			
			JButton btnConsultaIncidencias = new JButton("Consultar incidencias");
			btnConsultaIncidencias.setEnabled(false);
			btnConsultaIncidencias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ventana_Principal vp = Ventana_Principal.getInstance();
					JDialog ventanaIncidencias = new JDialog(vp, "Incidencias", true);
					ventanaIncidencias.getContentPane().add(new IncidenciasPanel(frame));
					ventanaIncidencias.setSize(1119, 550);
					ventanaIncidencias.setVisible(true);
				}
			});
			btnConsultaIncidencias.setBounds(583, 8, 168, 23);
			panelBuscaSocio.add(btnConsultaIncidencias);
			
			modeloTablaSociosBuscar.setColumnIdentifiers(new Object[]{"Id","Nombre","Email","Telefono","DNI","Calle","Codigo Postal","Fecha de Creación"});
			
			JButton btnVerTodosSociosBuscar = new JButton("Ver todos");
			btnVerTodosSociosBuscar.setOpaque(false);
			btnVerTodosSociosBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloTablaSociosBuscar.setRowCount(0);
					rellenarTabla1AlPrincipio();
				}
			});
			btnVerTodosSociosBuscar.setBounds(248, 8, 89, 23);
			panelBuscaSocio.add(btnVerTodosSociosBuscar);
			
			tablaSocios = new JTable();
			scrollPane.setViewportView(tablaSocios);
			tablaSocios.setModel(modeloTablaSociosBuscar);
			
			ListSelectionModel modeloSeleccionTabla = tablaSocios.getSelectionModel();
			
			modeloSeleccionTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			modeloSeleccionTabla.addListSelectionListener(new ListSelectionListener() {
				/*
				 * Cuando el valor de la tabla cambia, es decir, se selecciona una fila,
				 * se activan los botones para ver los prestamos e incidencias
				 */
				@Override
				public void valueChanged(ListSelectionEvent e) {
					filaSeleccionada = tablaSocios.getSelectedRow();
					columnaSeleccionada = tablaSocios.getSelectedColumn();
					
					if(filaSeleccionada != -1 && columnaSeleccionada != -1) {
						idSocioSeleccionado = (int) tablaSocios.getValueAt(filaSeleccionada, 0);
						btnConsultaIncidencias.setEnabled(true);
						//Le pasas la celda seleccionada y la vuelves no editable
						modeloTablaSociosBuscar.isCellEditable(filaSeleccionada, columnaSeleccionada);
					}
					
				}
			});
			
			modeloSeleccionTabla.addListSelectionListener(new ListSelectionListener() {
				/*
				 * Cuando ningun valor de la tabla está desactivado, los botones se desactivan
				 */
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(modeloSeleccionTabla.isSelectionEmpty()) {
						btnConsultaIncidencias.setEnabled(false);
					}
				}
			});
			
			modeloTablaSociosBuscar.setRowCount(0);
			/*
			 * ==============================================================================================
			 * ==============================================================================================
			 * FIN DE PANEL BUSCADOR DE SOCIOS
			 * ----------------------------------------------------------------------------------------------
			 * PANEL AGREGAR SOCIOS
			 * ==============================================================================================
			 * ==============================================================================================
			 */
			JPanel panelAgregaSocio = new JPanel();
			panelAgregaSocio.setBackground(new Color(128, 128, 192));
			tabbedPane.addTab("Agregar socio", null, panelAgregaSocio, null);
			panelAgregaSocio.setLayout(null);
			
			JLabel lblAgregarSocios = new JLabel("Agregar Socios");
			lblAgregarSocios.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblAgregarSocios.setBounds(10, 11, 210, 30);
			panelAgregaSocio.add(lblAgregarSocios);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNombre.setBounds(248, 153, 210, 30);
			panelAgregaSocio.add(lblNombre);
			
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblEmail.setBounds(248, 229, 210, 30);
			panelAgregaSocio.add(lblEmail);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblTelefono.setBounds(248, 313, 210, 30);
			panelAgregaSocio.add(lblTelefono);
			
			JLabel lblDni = new JLabel("Dni:");
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblDni.setBounds(745, 153, 210, 30);
			panelAgregaSocio.add(lblDni);
			
			JLabel lblCalle = new JLabel("Calle:");
			lblCalle.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblCalle.setBounds(745, 229, 210, 30);
			panelAgregaSocio.add(lblCalle);
			
			JLabel lblCodigoPostal = new JLabel("Codigo postal:");
			lblCodigoPostal.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblCodigoPostal.setBounds(745, 313, 210, 30);
			panelAgregaSocio.add(lblCodigoPostal);
			
			tfNombre = new JTextField();
			tfNombre.setBounds(365, 162, 176, 20);
			panelAgregaSocio.add(tfNombre);
			tfNombre.setColumns(10);
			
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(365, 238, 176, 20);
			panelAgregaSocio.add(tfEmail);
			
			tfTelefono = new JFormattedTextField(mask);	
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(370, 319, 176, 20);
			panelAgregaSocio.add(tfTelefono);
			
			tfDni = new JFormattedTextField(mask2);
			tfDni.setColumns(10);
			tfDni.setBounds(915, 162, 176, 20);
			panelAgregaSocio.add(tfDni);
			
			tfCalle = new JTextField();
			tfCalle.setColumns(10);
			tfCalle.setBounds(915, 238, 176, 20);
			panelAgregaSocio.add(tfCalle);
			
			tfCodigo_Postal = new JFormattedTextField(mask3);
			tfCodigo_Postal.setColumns(10);
			tfCodigo_Postal.setBounds(915, 322, 176, 20);
			panelAgregaSocio.add(tfCodigo_Postal);
			
			JButton btnDarAlta = new JButton("Dar de alta");
			btnDarAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tfNombre.getText().isEmpty() || tfEmail.getText().isEmpty() || tfTelefono.getText().isEmpty()
							|| tfCalle.getText().isEmpty() || tfCodigo_Postal.getText().isEmpty() || tfDni.getText().isEmpty()) {
						JOptionPane.showMessageDialog(panelAgregaSocio, "Debes rellenar todos los campos");
					}else {
						if((tfEmail.getText().contains("@gmail") || tfEmail.getText().contains("@hotmail")
							|| tfEmail.getText().contains("@outlook") || tfEmail.getText().contains("@yahoo")) 
							&& (tfEmail.getText().contains(".com") || tfEmail.getText().contains(".es")
							|| tfEmail.getText().contains(".net"))) {
							ConectorBBDD con = new ConectorBBDD();
							con.agregarSocio(tfNombre.getText(), tfEmail.getText(), Integer.parseInt(tfTelefono.getText()), tfDni.getText(), Integer.parseInt(tfCodigo_Postal.getText()), tfCalle.getText());
							JOptionPane.showMessageDialog(panelAgregaSocio, "Los datos se han introducido correctamente");
							modeloTablaSociosBuscar.setRowCount(0);
							modeloTablaSociosEliminar.setRowCount(0);
							modeloTablaSociosModificar.setRowCount(0);
							rellenarTabla1AlPrincipio();
							rellenarTabla2AlPrincipio();
							rellenarTabla3AlPrincipio();
						}else {
							JOptionPane.showMessageDialog(panelAgregaSocio, "El email introducido no es correcto");
						}
					}
				}
			});
			btnDarAlta.setBounds(344, 444, 100, 23);
			panelAgregaSocio.add(btnDarAlta);
			
			JButton btnLimpiar = new JButton("Limpiar");
			btnLimpiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tfCalle.setText("");
					tfCodigo_Postal.setText("");
					tfDni.setText("");
					tfEmail.setText("");
					tfNombre.setText("");
					tfTelefono.setText("");
				}
			});
			btnLimpiar.setBounds(915, 444, 89, 23);
			panelAgregaSocio.add(btnLimpiar);
			
			
			
			/*
			 * ==============================================================================================
			 * ==============================================================================================
			 * FIN PANEL AGREGAR SOCIOS
			 * ----------------------------------------------------------------------------------------------
			 * PANEL MODIFICA SOCIOS
			 * ==============================================================================================
			 * ==============================================================================================
			 */
			
			JPanel panelModificaSocio = new JPanel();
			panelModificaSocio.setBackground(new Color(128, 128, 192));
			tabbedPane.addTab("Modificar socio", null, panelModificaSocio, null);
			panelModificaSocio.setLayout(null);
			
			JLabel lblModificarSocios = new JLabel("Modificar Socios");
			lblModificarSocios.setForeground(new Color(255, 255, 255));
			lblModificarSocios.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblModificarSocios.setBounds(10, 11, 210, 30);
			panelModificaSocio.add(lblModificarSocios);
			
			JLabel lblNombreModificarSocio = new JLabel("Nombre:");
			lblNombreModificarSocio.setForeground(new Color(255, 255, 255));
			lblNombreModificarSocio.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombreModificarSocio.setBounds(1014, 47, 114, 30);
			panelModificaSocio.add(lblNombreModificarSocio);
			
			tfNombreModSocios = new JTextField();
			tfNombreModSocios.setColumns(10);
			tfNombreModSocios.setBounds(1131, 56, 176, 20);
			panelModificaSocio.add(tfNombreModSocios);
			
			tfEmailModSocios = new JTextField();
			tfEmailModSocios.setColumns(10);
			tfEmailModSocios.setBounds(1131, 97, 176, 20);
			panelModificaSocio.add(tfEmailModSocios);
			
			JLabel lblEmailModificarSocios = new JLabel("Email:");
			lblEmailModificarSocios.setForeground(new Color(255, 255, 255));
			lblEmailModificarSocios.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblEmailModificarSocios.setBounds(1014, 88, 114, 30);
			panelModificaSocio.add(lblEmailModificarSocios);
			
			tfTelefonoModSocios = new JFormattedTextField(mask);
			tfTelefonoModSocios.setColumns(10);
			tfTelefonoModSocios.setBounds(1131, 137, 176, 20);
			panelModificaSocio.add(tfTelefonoModSocios);
			
			JLabel lblTelefonoModificarSocios = new JLabel("Telefono:");
			lblTelefonoModificarSocios.setForeground(new Color(255, 255, 255));
			lblTelefonoModificarSocios.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblTelefonoModificarSocios.setBounds(1014, 128, 114, 30);
			panelModificaSocio.add(lblTelefonoModificarSocios);
			
			JLabel lblDniModificarSocios = new JLabel("DNI:");
			lblDniModificarSocios.setForeground(new Color(255, 255, 255));
			lblDniModificarSocios.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblDniModificarSocios.setBounds(1014, 165, 114, 30);
			panelModificaSocio.add(lblDniModificarSocios);
			
			tfDniModSocios = new JFormattedTextField(mask2);
			tfDniModSocios.setColumns(10);
			tfDniModSocios.setBounds(1131, 174, 176, 20);
			panelModificaSocio.add(tfDniModSocios);
			
			tfCalleModSocios = new JTextField();
			tfCalleModSocios.setColumns(10);
			tfCalleModSocios.setBounds(1131, 215, 176, 20);
			panelModificaSocio.add(tfCalleModSocios);
			
			JLabel lblCalleModificarSocios = new JLabel("Calle:");
			lblCalleModificarSocios.setForeground(new Color(255, 255, 255));
			lblCalleModificarSocios.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCalleModificarSocios.setBounds(1014, 206, 114, 30);
			panelModificaSocio.add(lblCalleModificarSocios);
			
			tfCodigoPostalModSocios = new JFormattedTextField(mask3);
			tfCodigoPostalModSocios.setColumns(10);
			tfCodigoPostalModSocios.setBounds(1131, 256, 176, 20);
			panelModificaSocio.add(tfCodigoPostalModSocios);
			
			JLabel lblCodigoPostalModificarSocios = new JLabel("Codigo postal:");
			lblCodigoPostalModificarSocios.setForeground(new Color(255, 255, 255));
			lblCodigoPostalModificarSocios.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCodigoPostalModificarSocios.setBounds(1014, 247, 114, 30);
			panelModificaSocio.add(lblCodigoPostalModificarSocios);
			
			btnModificar = new JButton("Modificar");
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConectorBBDD con = new ConectorBBDD();
					if (tfCalleModSocios.getText().isEmpty() 
							|| tfCodigoPostalModSocios.getText().isEmpty() 
							|| tfDniModSocios.getText().isEmpty() 
							|| tfEmailModSocios.getText().isEmpty() 
							|| tfNombreModSocios.getText().isEmpty() 
							|| tfTelefonoModSocios.getText().isEmpty()) {
						Ventana_Principal vp = Ventana_Principal.getInstance();
						JOptionPane.showMessageDialog(vp ,"Debes rellenar todos los campos");
					}else { 
						con.cambiarSocio(idSeleccionadoModificar, tfNombreModSocios.getText(), tfDniModSocios.getText(),
								Integer.parseInt(tfTelefonoModSocios.getText()), tfCalleModSocios.getText(),
								Integer.parseInt(tfCodigoPostalModSocios.getText()), tfEmailModSocios.getText());
						tfCalleModSocios.setText("");
						tfCodigoPostalModSocios.setText("");
						tfDniModSocios.setText("");
						tfEmailModSocios.setText("");
						tfNombreModSocios.setText("");
						tfTelefonoModSocios.setText("");
						modeloTablaSociosBuscar.setRowCount(0);
						modeloTablaSociosEliminar.setRowCount(0);
						modeloTablaSociosModificar.setRowCount(0);
						rellenarTabla1AlPrincipio();
						rellenarTabla2AlPrincipio();
						rellenarTabla3AlPrincipio();
					}
				}
			});
			btnModificar.setBounds(1211, 312, 89, 23);
			panelModificaSocio.add(btnModificar);
			
			JButton btnNewButton = new JButton("Limpiar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tfCodigoPostalModSocios.setText("");
					tfTelefonoModSocios.setText("");
					tfCalleModSocios.setText("");
					tfDniModSocios.setText("");
					tfEmailModSocios.setText("");
					tfNombreModSocios.setText("");	
					btnModificar.setEnabled(false);
				}
			});
			btnNewButton.setBounds(1019, 312, 89, 23);
			panelModificaSocio.add(btnNewButton);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 52, 991, 542);
			panelModificaSocio.add(scrollPane_1);
			
			tablaSociosMod = new JTable();
			scrollPane_1.setViewportView(tablaSociosMod);
			modeloTablaSociosModificar.setColumnIdentifiers(new Object[]{"Id","Nombre","Email","Telefono","DNI","Calle","Codigo Postal","Fecha de Creación"});
			tablaSociosMod.setModel(modeloTablaSociosModificar);
			
			ListSelectionModel modeloSeleccionTablaModificar = tablaSociosMod.getSelectionModel();
			
			modeloSeleccionTablaModificar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			modeloSeleccionTablaModificar.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					filaSeleccionadaMod = tablaSociosMod.getSelectedRow();
					columnaSeleccionadaMod = tablaSociosMod.getSelectedColumn();
					
					if(filaSeleccionadaMod != -1 && columnaSeleccionadaMod != -1) {
						modeloTablaSociosModificar.isCellEditable(filaSeleccionadaMod, columnaSeleccionadaMod);
						idSeleccionadoModificar = (int) tablaSociosMod.getValueAt(filaSeleccionadaMod, 0);
						nombreSeleccionadoModificar = (String) tablaSociosMod.getValueAt(filaSeleccionadaMod, 1);
						emailSeleccionadoModificar = (String) tablaSociosMod.getValueAt(filaSeleccionadaMod, 2);
						telefonoSeleccionadoModificar = (int) tablaSociosMod.getValueAt(filaSeleccionadaMod, 3);
						dniSeleccionadoModificar = (String) tablaSociosMod.getValueAt(filaSeleccionadaMod, 4);
						calleSeleccionadoModificar = (String) tablaSociosMod.getValueAt(filaSeleccionadaMod, 5);
						codigoPostalSeleccionadoModificar = (int) tablaSociosMod.getValueAt(filaSeleccionadaMod, 6);
						
						tfNombreModSocios.setText(nombreSeleccionadoModificar);
						tfEmailModSocios.setText(emailSeleccionadoModificar);
						tfDniModSocios.setText(dniSeleccionadoModificar);
						tfTelefonoModSocios.setText(Integer.toString(telefonoSeleccionadoModificar));
						tfCalleModSocios.setText(calleSeleccionadoModificar);
						tfCodigoPostalModSocios.setText(Integer.toString(codigoPostalSeleccionadoModificar));
						btnModificar.setEnabled(true);
					}
					
				}
			});
			
			modeloSeleccionTablaModificar.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(modeloSeleccionTablaModificar.isSelectionEmpty()) {
						btnModificar.setEnabled(false);
					}
					
				}
			});
			
			JButton btnFiltrarModSocios = new JButton("Filtrar");
			btnFiltrarModSocios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenarTabla3PorFiltro();
				}
			});
			btnFiltrarModSocios.setBounds(1026, 524, 89, 23);
			panelModificaSocio.add(btnFiltrarModSocios);
			
			rdbtnBuscarPorIdModSocios = new JRadioButton("Buscar por id");
			rdbtnBuscarPorIdModSocios.setForeground(new Color(255, 255, 255));
			rdbtnBuscarPorIdModSocios.setOpaque(false);
			rdbtnBuscarPorIdModSocios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarONoComponenteModififcar();
				}
			});
			rdbtnBuscarPorIdModSocios.setSelected(true);
			rdbtnBuscarPorIdModSocios.setBounds(1016, 364, 109, 23);
			panelModificaSocio.add(rdbtnBuscarPorIdModSocios);
			
			SpinnerNumberModel modeloMinimoSpinnerMod = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
			
			spinnerIdModSocios = new JSpinner(modeloMinimoSpinnerMod);
			spinnerIdModSocios.setBounds(1026, 394, 56, 32);
			panelModificaSocio.add(spinnerIdModSocios);
			
			rdbtnBuscarPorCorreoModSocios = new JRadioButton("Buscar por correo electrónico");
			rdbtnBuscarPorCorreoModSocios.setForeground(new Color(255, 255, 255));
			rdbtnBuscarPorCorreoModSocios.setOpaque(false);
			rdbtnBuscarPorCorreoModSocios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarONoComponenteModififcar();
				}
			});
			rdbtnBuscarPorCorreoModSocios.setBounds(1016, 448, 220, 23);
			panelModificaSocio.add(rdbtnBuscarPorCorreoModSocios);
			
			ButtonGroup grupoRadioButtons3 = new ButtonGroup();
			
			grupoRadioButtons3.add(rdbtnBuscarPorCorreoModSocios);
			grupoRadioButtons3.add(rdbtnBuscarPorIdModSocios);
			
			tfFiltrarEmailModSocios = new JTextField();
			tfFiltrarEmailModSocios.setEnabled(false);
			tfFiltrarEmailModSocios.setColumns(10);
			tfFiltrarEmailModSocios.setBounds(1026, 478, 210, 20);
			panelModificaSocio.add(tfFiltrarEmailModSocios);
			
			JButton btnVerTodosSociosMod = new JButton("Ver todos");
			btnVerTodosSociosMod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloTablaSociosModificar.setRowCount(0);
					rellenarTabla3AlPrincipio();
				}
			});
			btnVerTodosSociosMod.setBounds(1211, 524, 89, 23);
			panelModificaSocio.add(btnVerTodosSociosMod);
			
			/*
			 * ==============================================================================================
			 * ==============================================================================================
			 * FIN PANEL MODIFICA SOCIOS
			 * ----------------------------------------------------------------------------------------------
			 * PANEL ELIMINA SOCIOS
			 * ==============================================================================================
			 * ==============================================================================================
			 */
			JPanel panelEliminaSocio = new JPanel();
			panelEliminaSocio.setBackground(new Color(128, 128, 192));
			tabbedPane.addTab("Eliminar socio", null, panelEliminaSocio, null);
			panelEliminaSocio.setLayout(null);

			SpinnerNumberModel modeloMinimoSpinnerBorrar = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
			
			rdbtnBuscarSocioPorIdBorrar = new JRadioButton("Buscar por id");
			rdbtnBuscarSocioPorIdBorrar.setOpaque(false);
			rdbtnBuscarSocioPorIdBorrar.setForeground(new Color(255, 255, 255));
			rdbtnBuscarSocioPorIdBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarONoComponenteBorrar();
				}
			});
			rdbtnBuscarSocioPorIdBorrar.setSelected(true);
			rdbtnBuscarSocioPorIdBorrar.setBounds(1099, 89, 109, 23);
			panelEliminaSocio.add(rdbtnBuscarSocioPorIdBorrar);
			
			rdbtnBuscarSocioPorCorreoBorrar = new JRadioButton("Buscar por correo electrónico");
			rdbtnBuscarSocioPorCorreoBorrar.setOpaque(false);
			rdbtnBuscarSocioPorCorreoBorrar.setForeground(new Color(255, 255, 255));
			rdbtnBuscarSocioPorCorreoBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarONoComponenteBorrar();
				}
			});
			rdbtnBuscarSocioPorCorreoBorrar.setBounds(1095, 252, 210, 23);
			panelEliminaSocio.add(rdbtnBuscarSocioPorCorreoBorrar);

			ButtonGroup grupoRadioButtonsElimSocios = new ButtonGroup();
			
			grupoRadioButtonsElimSocios.add(rdbtnBuscarSocioPorCorreoBorrar);
			grupoRadioButtonsElimSocios.add(rdbtnBuscarSocioPorIdBorrar);
			
			JLabel lblBorrarSocios = new JLabel("Borrar Socios");
			lblBorrarSocios.setForeground(new Color(255, 255, 255));
			lblBorrarSocios.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblBorrarSocios.setBounds(10, 11, 210, 30);
			panelEliminaSocio.add(lblBorrarSocios);
			
			JScrollPane scrollPaneTablaBorrar = new JScrollPane();
			scrollPaneTablaBorrar.setBounds(10, 55, 1047, 539);
			panelEliminaSocio.add(scrollPaneTablaBorrar);
			
			tablaSociosBorrar = new JTable();
			scrollPaneTablaBorrar.setViewportView(tablaSociosBorrar);
			modeloTablaSociosEliminar.setColumnIdentifiers(new Object[]{"Id","Nombre","Email","Telefono","DNI","Calle","Codigo Postal","Fecha de Creación"});
			tablaSociosBorrar.setModel(modeloTablaSociosEliminar);	
			
			btnBorrarSocio = new JButton("Borrar Seleccionado");
			btnBorrarSocio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Ventana_Principal vp = Ventana_Principal.getInstance();
					
					int opcion = JOptionPane.showConfirmDialog(vp, "¿Quiere borrar el socio?", "Confirmacion", 0);
					if(opcion == 0) {
						ConectorBBDD con = new ConectorBBDD();
						con.eliminarSocio(idSeleccionadoBorrar);
						modeloTablaSociosBuscar.setRowCount(0);
						modeloTablaSociosEliminar.setRowCount(0);
						modeloTablaSociosModificar.setRowCount(0);
						rellenarTabla1AlPrincipio();
						rellenarTabla2AlPrincipio();
						rellenarTabla3AlPrincipio();
					}else if(opcion == 1) {
						
					}
				}
			});
			btnBorrarSocio.setBounds(619, 19, 176, 23);
			panelEliminaSocio.add(btnBorrarSocio);
			btnBorrarSocio.setEnabled(false);
			
			ListSelectionModel modeloSeleccionTabla2 = tablaSociosBorrar.getSelectionModel();
			
			modeloSeleccionTabla2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			modeloSeleccionTabla2.addListSelectionListener(new ListSelectionListener() {
				/*
				 * Cuando el valor de la tabla cambia, es decir, se selecciona una fila,
				 * se activan los botones para ver los prestamos e incidencias
				 */
				@Override
				public void valueChanged(ListSelectionEvent e) {
					filaSeleccionadaBorrar = tablaSociosBorrar.getSelectedRow();
					columnaSeleccionadaBorrar = tablaSociosBorrar.getSelectedColumn();
					
					if(filaSeleccionadaBorrar != -1 && columnaSeleccionadaBorrar != -1) {
						idSeleccionadoBorrar = (int) tablaSociosBorrar.getValueAt(filaSeleccionadaBorrar, 0);
						btnBorrarSocio.setEnabled(true);
						//Le pasas la celda seleccionada y la vuelves no editable
						modeloTablaSociosEliminar.isCellEditable(filaSeleccionadaBorrar, columnaSeleccionadaBorrar);
					}
					
				}
			});
			
			modeloSeleccionTabla2.addListSelectionListener(new ListSelectionListener() {
				/*
				 * Cuando ningun valor de la tabla está desactivado, los botones se desactivan
				 */
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(modeloSeleccionTabla2.isSelectionEmpty()) {
						btnBorrarSocio.setEnabled(false);
					}
				}
			});
			
			spinnerIdSocioBorrar = new JSpinner(modeloMinimoSpinnerBorrar);
			spinnerIdSocioBorrar.setBounds(1109, 155, 56, 32);
			panelEliminaSocio.add(spinnerIdSocioBorrar);
			
			tfEmailSocioBorrar = new JTextField();
			tfEmailSocioBorrar.setEnabled(false);
			tfEmailSocioBorrar.setColumns(10);
			tfEmailSocioBorrar.setBounds(1095, 326, 210, 20);
			panelEliminaSocio.add(tfEmailSocioBorrar);
			
			btnFiltrarSocioBorrados = new JButton("Filtrar");
			btnFiltrarSocioBorrados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenarTabla2PorFiltro();
				}
			});
			btnFiltrarSocioBorrados.setBounds(874, 19, 89, 23);
			panelEliminaSocio.add(btnFiltrarSocioBorrados);
			
			JButton btnVerTodosSociosBorrar = new JButton("Ver todos");
			btnVerTodosSociosBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloTablaSociosEliminar.setRowCount(0);
					rellenarTabla2AlPrincipio();
				}
			});
			btnVerTodosSociosBorrar.setBounds(427, 19, 89, 23);
			panelEliminaSocio.add(btnVerTodosSociosBorrar);
			
			JLabel lblSocios = new JLabel("Socios");
			lblSocios.setForeground(new Color(255, 255, 255));
			lblSocios.setBackground(new Color(255, 255, 255));
			lblSocios.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSocios.setBounds(10, 48, 210, 30);
			add(lblSocios);
			
			/*
			 * ==============================================================================================
			 * ==============================================================================================
			 * FIN PANEL ELIMINA SOCIOS
			 * ==============================================================================================
			 * ==============================================================================================
			 */
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
	
	@Override
    public void addNotify() {
        super.addNotify();
        // Este método se llama cuando el componente es añadido al contenedor
        rellenarTabla1AlPrincipio();
        rellenarTabla2AlPrincipio();
        rellenarTabla3AlPrincipio();
    }
	
	public void rellenarTabla1AlPrincipio() {
		con = new ConectorBBDD();
		for(Socio s : con.consultarSocios()) {
			modeloTablaSociosBuscar.addRow(new Object[] {
					s.getId(),
					s.getNombre(),
					s.getEmail(),
					s.getTelefono(),
					s.getDni(),
					s.getCalle(),
					s.getCodigo_postal(),
					s.getFecha_creacion()
			});
		}
	}
	
	public void rellenarTabla2AlPrincipio() {
		con = new ConectorBBDD();
		for(Socio s : con.consultarSocios()) {
			modeloTablaSociosEliminar.addRow(new Object[] {
					s.getId(),
					s.getNombre(),
					s.getEmail(),
					s.getTelefono(),
					s.getDni(),
					s.getCalle(),
					s.getCodigo_postal(),
					s.getFecha_creacion()
			});
		}
	}
	
	public void rellenarTabla3AlPrincipio() {
		con = new ConectorBBDD();
		for(Socio s : con.consultarSocios()) {
			modeloTablaSociosModificar.addRow(new Object[] {
					s.getId(),
					s.getNombre(),
					s.getEmail(),
					s.getTelefono(),
					s.getDni(),
					s.getCalle(),
					s.getCodigo_postal(),
					s.getFecha_creacion()
			});
		}
	}
	
	public void rellenarTabla1PorFiltro() {
		modeloTablaSociosBuscar.setRowCount(0);
		con = new ConectorBBDD();
		if(rdbtnBuscarPorCorreo.isSelected()) {
			for(Socio s : con.consultarSociosPorEmail(tfEmailSocio.getText())) {
				modeloTablaSociosBuscar.addRow(new Object[] {
						s.getId(),
						s.getNombre(),
						s.getEmail(),
						s.getTelefono(),
						s.getDni(),
						s.getCalle(),
						s.getCodigo_postal(),
						s.getFecha_creacion()
				});
			}
		}else if(rdbtnBuscarPorId.isSelected()) {
			for(Socio s : con.consultarSociosPorId((int) spinnerIdSocio.getValue())) {
				modeloTablaSociosBuscar.addRow(new Object[] {
						s.getId(),
						s.getNombre(),
						s.getEmail(),
						s.getTelefono(),
						s.getDni(),
						s.getCalle(),
						s.getCodigo_postal(),
						s.getFecha_creacion()
				});
			}
		}
	}
	
	public void rellenarTabla2PorFiltro() {
		modeloTablaSociosEliminar.setRowCount(0);
		con = new ConectorBBDD();
		if(rdbtnBuscarSocioPorCorreoBorrar.isSelected()) {
			for(Socio s : con.consultarSociosPorEmail(tfEmailSocioBorrar.getText())) {
				modeloTablaSociosEliminar.addRow(new Object[] {
						s.getId(),
						s.getNombre(),
						s.getEmail(),
						s.getTelefono(),
						s.getDni(),
						s.getCalle(),
						s.getCodigo_postal(),
						s.getFecha_creacion()
				});
			}
		}else if(rdbtnBuscarSocioPorIdBorrar.isSelected()) {
			for(Socio s : con.consultarSociosPorId((int) spinnerIdSocioBorrar.getValue())) {
				modeloTablaSociosEliminar.addRow(new Object[] {
						s.getId(),
						s.getNombre(),
						s.getEmail(),
						s.getTelefono(),
						s.getDni(),
						s.getCalle(),
						s.getCodigo_postal(),
						s.getFecha_creacion()
				});
			}
		}
	}
	
	public void rellenarTabla3PorFiltro() {
		modeloTablaSociosModificar.setRowCount(0);
		con = new ConectorBBDD();
		if(rdbtnBuscarPorCorreoModSocios.isSelected()) {
			for(Socio s : con.consultarSociosPorEmail(tfFiltrarEmailModSocios.getText())) {
				modeloTablaSociosModificar.addRow(new Object[] {
						s.getId(),
						s.getNombre(),
						s.getEmail(),
						s.getTelefono(),
						s.getDni(),
						s.getCalle(),
						s.getCodigo_postal(),
						s.getFecha_creacion()
				});
			}
		}else if(rdbtnBuscarPorIdModSocios.isSelected()) {
			for(Socio s : con.consultarSociosPorId((int) spinnerIdModSocios.getValue())) {
				modeloTablaSociosModificar.addRow(new Object[] {
						s.getId(),
						s.getNombre(),
						s.getEmail(),
						s.getTelefono(),
						s.getDni(),
						s.getCalle(),
						s.getCodigo_postal(),
						s.getFecha_creacion()
				});
			}
		}
	}
	
	public void activarONoComponente() {
		if(rdbtnBuscarPorCorreo.isSelected()) {
			tfEmailSocio.setEnabled(true);
			spinnerIdSocio.setEnabled(false);
		}else if(rdbtnBuscarPorId.isSelected()) {
			tfEmailSocio.setEnabled(false);
			spinnerIdSocio.setEnabled(true);
		}
		
	}
	
	public void activarONoComponenteBorrar() {
		if(rdbtnBuscarSocioPorCorreoBorrar.isSelected()) {
			tfEmailSocioBorrar.setEnabled(true);
			spinnerIdSocioBorrar.setEnabled(false);
		}else if(rdbtnBuscarSocioPorIdBorrar.isSelected()) {
			tfEmailSocioBorrar.setEnabled(false);
			spinnerIdSocioBorrar.setEnabled(true);
		}
		
	}
	
	public void activarONoComponenteModififcar() {
		if(rdbtnBuscarPorCorreoModSocios.isSelected()) {
			tfFiltrarEmailModSocios.setEnabled(true);
			spinnerIdModSocios.setEnabled(false);
		}else if(rdbtnBuscarPorIdModSocios.isSelected()) {
			tfFiltrarEmailModSocios.setEnabled(false);
			spinnerIdModSocios.setEnabled(true);
		}
		
	}
}
