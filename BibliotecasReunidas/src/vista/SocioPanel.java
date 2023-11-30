package vista;

import javax.swing.JPanel;

import modelo.Socio;
import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import controlador.ConectorBBDD;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SocioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public Usuario usuario = Login.usuario;
	public JTextField tfEmailSocio;
	private JTable tablaSocios;
	public JRadioButton rdbtnBuscarPorId;
	public JRadioButton rdbtnBuscarPorCorreo;
	public JSpinner spinnerIdSocio;
	public ConectorBBDD con;
	DefaultTableModel modeloTablaSocios = new DefaultTableModel();

	/**
	 * Create the panel.
	 */
	public SocioPanel() {
		setLayout(null);
		
		JButton btnAgregarAdministrativo = new JButton("AgregarAdministrativo");
		btnAgregarAdministrativo.setBounds(268, 10, 139, 23);
		add(btnAgregarAdministrativo);
		
		if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRATIVO)) {
			btnAgregarAdministrativo.setEnabled(false);
		}else if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRADOR)) {
			btnAgregarAdministrativo.setEnabled(true);
		}
		
		JButton btnAgregarLibro = new JButton("Agregar libro");
		btnAgregarLibro.setBounds(439, 10, 139, 23);
		add(btnAgregarLibro);
		
		if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRATIVO)) {
			btnAgregarLibro.setEnabled(true);
		}else if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRADOR)) {
			btnAgregarLibro.setEnabled(true);
		}
		
		try {
			BufferedImage img = ImageIO.read(new File("C:\\Development\\Projects\\bibliotecas-reunidas\\BibliotecasReunidas\\src\\images\\flechita_atras.png"));
			
			int labelWidth = 40;
		    int labelHeight = 40;
		    
		    Image scaledImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
			
		    ImageIcon icon = new ImageIcon(scaledImg);
		    
			JLabel imgs = new JLabel(icon);
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
					Ventana_Principal.getInstance().cambiarPanel(new Login());
				}
			});
			imgs.setBounds(0, 0, 47, 40);
			
			add(imgs);
			
			JLabel lblCerrarSesion = new JLabel("Cerrar sesión");
			lblCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCerrarSesion.setBounds(45, 12, 110, 14);
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
					Ventana_Principal.getInstance().cambiarPanel(new Login());
				}
			});
			add(lblCerrarSesion);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(10, 82, 1102, 591);
			add(tabbedPane);
			
			JPanel panelBuscaSocio = new JPanel();
			tabbedPane.addTab("Buscar socios", null, panelBuscaSocio, null);
			panelBuscaSocio.setLayout(null);
			
			JButton btnFiltrar = new JButton("Filtrar");
			btnFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenarTablaPorFiltro();
				}
			});
			btnFiltrar.setBounds(66, 385, 89, 23);
			panelBuscaSocio.add(btnFiltrar);
			
			rdbtnBuscarPorId = new JRadioButton("Buscar por id");
			rdbtnBuscarPorId.setSelected(true);
			rdbtnBuscarPorId.setBounds(0, 48, 109, 23);
			panelBuscaSocio.add(rdbtnBuscarPorId);
			rdbtnBuscarPorId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarComponente();
				}
			});
			
			rdbtnBuscarPorCorreo = new JRadioButton("Buscar por correo electrónico");
			rdbtnBuscarPorCorreo.setBounds(0, 132, 168, 23);
			panelBuscaSocio.add(rdbtnBuscarPorCorreo);
			rdbtnBuscarPorCorreo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarComponente();
				}
			});
			
			ButtonGroup grupoRadioButtons = new ButtonGroup();
			grupoRadioButtons.add(rdbtnBuscarPorCorreo);
			grupoRadioButtons.add(rdbtnBuscarPorId);
			
			SpinnerNumberModel modeloMinimoSpinner = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
			
			spinnerIdSocio = new JSpinner(modeloMinimoSpinner);
			spinnerIdSocio.setBounds(10, 78, 56, 32);
			panelBuscaSocio.add(spinnerIdSocio);
			
			
			JLabel lblNewLabel = new JLabel("Buscador");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblNewLabel.setBounds(10, 11, 168, 30);
			panelBuscaSocio.add(lblNewLabel);
			
			tfEmailSocio = new JTextField();
			tfEmailSocio.setBounds(10, 162, 210, 20);
			panelBuscaSocio.add(tfEmailSocio);
			tfEmailSocio.setColumns(10);
			tfEmailSocio.setEnabled(false);
						
			JLabel lblEncontrados = new JLabel("Se han encontrado:");
			lblEncontrados.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblEncontrados.setBounds(10, 226, 210, 63);
			panelBuscaSocio.add(lblEncontrados);
			
			JLabel lblSeHanEncontrado = new JLabel("0 resultados");
			lblSeHanEncontrado.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblSeHanEncontrado.setBounds(47, 300, 130, 32);
			panelBuscaSocio.add(lblSeHanEncontrado);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(230, 11, 857, 541);
			panelBuscaSocio.add(scrollPane);
			
			tablaSocios = new JTable();
			scrollPane.setViewportView(tablaSocios);
			
			modeloTablaSocios.setColumnIdentifiers(new Object[]{"Id","Nombre","Email","Telefono","DNI","Calle","Codigo Postal","Fecha de Creación"});
			tablaSocios.setModel(modeloTablaSocios);
			
			modeloTablaSocios.setRowCount(0);
			
			
						
			JPanel panelAgregaSocio = new JPanel();
			tabbedPane.addTab("Agregar socio", null, panelAgregaSocio, null);
			panelAgregaSocio.setLayout(null);
			
			JPanel panelModificaSocio = new JPanel();
			tabbedPane.addTab("Modificar socio", null, panelModificaSocio, null);
			panelModificaSocio.setLayout(null);
			
			JPanel panelEliminaSocio = new JPanel();
			tabbedPane.addTab("Eliminar socio", null, panelEliminaSocio, null);
			panelEliminaSocio.setLayout(null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
    public void addNotify() {
        super.addNotify();
        // Este método se llama cuando el componente es añadido al contenedor
        rellenarTablaAlPrincipio();
    }
	
	public void rellenarTablaAlPrincipio() {
		con = new ConectorBBDD();
		for(Socio s : con.consultarSocios()) {
			modeloTablaSocios.addRow(new Object[] {
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
	
	public void rellenarTablaPorFiltro() {
		modeloTablaSocios.setRowCount(0);
		con = new ConectorBBDD();
		if(rdbtnBuscarPorCorreo.isSelected()) {
			for(Socio s : con.consultarSociosPorEmail(tfEmailSocio.getText())) {
				modeloTablaSocios.addRow(new Object[] {
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
				modeloTablaSocios.addRow(new Object[] {
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
	
	public void activarComponente() {
		if(rdbtnBuscarPorCorreo.isSelected()) {
			System.out.println("a");
			tfEmailSocio.setEnabled(true);
			spinnerIdSocio.setEnabled(false);
		}else if(rdbtnBuscarPorId.isSelected()) {
			System.out.println("e");
			tfEmailSocio.setEnabled(false);
			spinnerIdSocio.setEnabled(true);
		}
		
	}
}
