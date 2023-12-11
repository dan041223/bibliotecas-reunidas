package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setLayout(null);
		
		JButton btnAutores = new JButton("Autores");
		btnAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new AutorPanel(null));
			}
		});
		btnAutores.setBounds(573, 239, 89, 23);
		add(btnAutores);
		
		JButton btnLibros = new JButton("Libros");
		btnLibros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new LibroPanel(null));
			}
		});
		btnLibros.setBounds(573, 309, 89, 23);
		add(btnLibros);
		
		JButton btnPrestamos = new JButton("Prestamos");
		btnPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new PrestamoPanel());
			}
		});
		btnPrestamos.setBounds(573, 371, 89, 23);
		add(btnPrestamos);
		
		JButton btnSocios = new JButton("Socios");
		btnSocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new SocioPanel());
			}
		});
		btnSocios.setBounds(812, 239, 89, 23);
		add(btnSocios);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new Login());
			}
		});
		btnLogin.setBounds(812, 309, 89, 23);
		add(btnLogin);
		
		JButton btnBiblio = new JButton("Bibliotecas");
		btnBiblio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new BibliotecaPanel(null));
			}
		});
		btnBiblio.setBounds(812, 371, 89, 23);
		add(btnBiblio);
		
		JButton btnIncidencias = new JButton("Incidencias");
		btnIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new TodasIncidenciasPanel());
			}
		});
		btnIncidencias.setBounds(812, 439, 89, 23);
		add(btnIncidencias);
		
		JButton btnUsuario = new JButton("Usuarios");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ventana_Principal.getInstance().cambiarPanel(new UsuarioPanel());
			}
		});
		btnUsuario.setBounds(573, 439, 89, 23);
		add(btnUsuario);

	}
}
