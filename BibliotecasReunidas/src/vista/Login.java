package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ConectorBBDD;
import modelo.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfCorreo;
	private JPasswordField tfPassword;
	static public Usuario usuario;
	public Boolean iniciadoSesion = false;

	/**
	 * Crea el panel
	 */
	public Login() {
		setLayout(null);
		
		JLabel lblLoginTitle = new JLabel("INICIO DE SESION");
		lblLoginTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLoginTitle.setBounds(587, 158, 269, 53);
		add(lblLoginTitle);
		
		JLabel lblCorreo = new JLabel("Correo electronico:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorreo.setBounds(622, 249, 177, 25);
		add(lblCorreo);
		
		tfCorreo = new JTextField();
		tfCorreo.setBounds(622, 297, 178, 23);
		add(tfCorreo);
		tfCorreo.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setColumns(10);
		tfPassword.setBounds(622, 390, 177, 25);
		add(tfPassword);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasena.setBounds(622, 351, 114, 25);
		add(lblContrasena);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		/*
		 * Detecta cuando pulsas el boton, haciendo los siguientes casos:
		 * En caso de que encuentre el usuario dice en una ventanita que se ha encontrado y pasa a la ventana principal (Socios por ahora para pruebas)
		 * En caso contrario te dice que no se ha encontrado a un usuario con esos datos y no hace nada
		 */
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectorBBDD con = new ConectorBBDD();
				
				String correo = tfCorreo.getText();
				String password = tfPassword.getText();
				usuario = con.consultarUsuario(correo, password);
				
				//Si el usuario esta vacio es que no se ha pasado ningun usuario por lo que ponemos el booleano iniciadoSesion en true
				if(usuario != null) {
					iniciadoSesion = true;
				}
				
				/*
				 * Si el usuario se ha encontrado en la base de datos el login se completa y, 
				 * en la instancia de la ventana principal cambias el panel hasta el primer panel que quieras que aparezca
				 * al logearte
				 * Para hacer pruebas he puesto SocioPanel pero habra que poner, cuando esté hecho, la ventana principal
				 */
				if(iniciadoSesion == true) {					
					Ventana_Principal.getInstance().cambiarPanel(new MenuPanel());
				}
			}
		});
		btnAcceder.setBounds(662, 459, 89, 23);
		add(btnAcceder);
		
		JLabel lblNewLabel_1 = new JLabel("He olvidado mi contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(622, 508, 191, 14);
		add(lblNewLabel_1);

	}
}
