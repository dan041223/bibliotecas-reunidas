package vista;

import javax.swing.JPanel;

import modelo.Usuario;
import modelo.Usuario.TIPO_PERFIL;

import javax.swing.JButton;

public class UsuarioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public Usuario usuario = Login.usuario;

	/**
	 * Create the panel.
	 */
	public UsuarioPanel() {
		setLayout(null);
		
		JButton btnAgregarAdministrativo = new JButton("AgregarAdministrativo");
		btnAgregarAdministrativo.setBounds(45, 113, 139, 23);
		add(btnAgregarAdministrativo);
		
		if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRATIVO)) {
			btnAgregarAdministrativo.setEnabled(false);
		}else if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRADOR)) {
			btnAgregarAdministrativo.setEnabled(true);
		}
		
		JButton btnAgregarLibro = new JButton("Agregar libro");
		btnAgregarLibro.setBounds(268, 113, 139, 23);
		add(btnAgregarLibro);
		
		if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRATIVO)) {
			btnAgregarLibro.setEnabled(true);
		}else if(usuario.getTipo_perfil().equals(TIPO_PERFIL.ADMINISTRADOR)) {
			btnAgregarLibro.setEnabled(true);
		}
		
	}
}
