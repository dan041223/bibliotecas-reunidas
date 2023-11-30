package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AutorPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JTextField textField_nombre;
	private JTextField textField_Cod;
	private JTextField textField_Nacionalidad;
	private JTextField textField_Fecha;

	/**
	 * Create the panel.
	 */
	public AutorPanel() {
		setLayout(null);
		
		JLabel codigoID = new JLabel("Codigo ID");
		codigoID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigoID.setBounds(145, 207, 80, 34);
		add(codigoID);
		
		JLabel lblNombre = new JLabel("Nombre ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(145, 258, 60, 34);
		add(lblNombre);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNacionalidad.setBounds(145, 318, 80, 34);
		add(lblNacionalidad);
		
		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(145, 386, 137, 34);
		add(lblFecha);
		
		textField_nombre = new JTextField();
		textField_nombre.setBounds(359, 263, 228, 34);
		add(textField_nombre);
		textField_nombre.setColumns(10);
		
		textField_Cod = new JTextField();
		textField_Cod.setColumns(10);
		textField_Cod.setBounds(359, 212, 228, 34);
		add(textField_Cod);
		
		textField_Nacionalidad = new JTextField();
		textField_Nacionalidad.setColumns(10);
		textField_Nacionalidad.setBounds(359, 315, 228, 34);
		add(textField_Nacionalidad);
		
		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(359, 370, 228, 34);
		add(textField_Fecha);
		
		JLabel lblNewLabel_3 = new JLabel("AUTOR ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(391, 125, 94, 24);
		add(lblNewLabel_3);
		
		JButton btnCrear = new JButton("Crear ");
		btnCrear.setBounds(156, 547, 85, 34);
		add(btnCrear);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(292, 547, 85, 34);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(550, 547, 85, 34);
		add(btnEliminar);
		
		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.setBounds(413, 547, 85, 34);
		add(btnBuscar_1);

	}
}
