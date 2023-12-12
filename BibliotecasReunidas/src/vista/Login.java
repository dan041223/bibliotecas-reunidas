package vista;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controlador.ConectorBBDD;
import modelo.Usuario;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfCorreo;
	private JPasswordField tfPassword;
	static public Usuario usuario;
	public JCheckBox chckbxRecordarme;
	public Boolean iniciadoSesion = false;

	private JFrame frame;
	/**
	 * Crea el panel
	 */
	public Login(JFrame frame) {
		this.frame = frame;
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
					if(chckbxRecordarme.isSelected()) {
						File fileSesionRecordada = new File("Ficheros\\SesionRecordada");
						FileWriter fw;
						BufferedWriter bw;
						try {
							fw = new FileWriter(fileSesionRecordada);
							bw = new BufferedWriter(fw);
							
							bw.write(correo + ";" + password);
							
							bw.close();
							fw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Ventana_Principal.getInstance().cambiarPanel(new MenuPanel(frame));
				}
			}
		});
		btnAcceder.setBounds(662, 459, 89, 23);
		add(btnAcceder);
		
		JLabel lblNewLabel_1 = new JLabel("He olvidado mi contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel_1.setBounds(622, 508, 191, 14);
		add(lblNewLabel_1);
		
		chckbxRecordarme = new JCheckBox("Recordarme");
		chckbxRecordarme.setBounds(622, 422, 97, 23);
		add(chckbxRecordarme);

	}
	
	@Override
    public void addNotify() {
        super.addNotify();
        // Este método se llama cuando el componente es añadido al contenedor
        Ventana_Principal vp = Ventana_Principal.getInstance();
        if(vp.datos != null) {
            tfCorreo.setText(vp.datos[0]);
            tfPassword.setText(vp.datos[1]);
            chckbxRecordarme.setSelected(true);
        }
        File flSesionRecordada = new File("Ficheros\\SesionRecordada");
        try {
			FileWriter fw = new FileWriter(flSesionRecordada);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
