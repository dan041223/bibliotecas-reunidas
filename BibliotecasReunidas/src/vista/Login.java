package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ConectorBBDD;
import modelo.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfCorreo;
	private JTextField tfPassword;
	static public Usuario usuario;
	public Boolean iniciadoSesion = false;

	/**
	 * Create the panel.
	 */
	public Login() {
		
		
		setLayout(null);
		
		JLabel lblLoginTitle = new JLabel("INICIO DE SESION");
		lblLoginTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLoginTitle.setBounds(227, 42, 269, 53);
		add(lblLoginTitle);
		
		JLabel lblCorreo = new JLabel("Correo electronico:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorreo.setBounds(262, 133, 177, 25);
		add(lblCorreo);
		
		tfCorreo = new JTextField();
		tfCorreo.setBounds(262, 181, 178, 23);
		add(tfCorreo);
		tfCorreo.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(262, 274, 177, 25);
		add(tfPassword);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasena.setBounds(262, 235, 114, 25);
		add(lblContrasena);
		
		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectorBBDD con = new ConectorBBDD();
				
				String correo = tfCorreo.getText();
				String password = tfPassword.getText();
				usuario = con.consultarUsuario(correo, password);
				
				if(usuario != null) {
					iniciadoSesion = true;
				}
				
				if(iniciadoSesion == true) {					
					Ventana_Principal.getInstance().cambiarPanel(new UsuarioPanel());
				}
			}
		});
		btnNewButton.setBounds(302, 343, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("He olvidado mi contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(262, 392, 191, 14);
		add(lblNewLabel_1);

	}
}
