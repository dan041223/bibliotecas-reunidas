package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ConectorBBDD;
import modelo.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.CardLayout;

public class Ventana_Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String[] datos;
	public Usuario usuario;
	private static Ventana_Principal instancia;

	private Ventana_Principal() {
		//Para conectarnos a la bbdd ni bien se inicia la app
		ConectorBBDD con = new ConectorBBDD();
		con.connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		
		setBounds(100, 100, 1119, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new CardLayout(0, 0));
		
		File fileSesionRecordada = new File("Ficheros\\SesionRecordada");
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(fileSesionRecordada);
			br = new BufferedReader(fr);
			if(br.ready()) {
				datos = br.readLine().split(";");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	if(usuario != null) {
			Login login = new Login();
			login.usuario = usuario;
			MenuPanel mp = new MenuPanel();
			cambiarPanel(mp);
		}else {
			Login login = new Login();
			cambiarPanel(login);
		}
		
		/*
		LibroPanel libropanel = new LibroPanel(this);
		cambiarPanel(libropanel);
	
		
		//this indica quien es el frame que contiene el panel
		AutorPanel autorPanel = new AutorPanel(this);
		cambiarPanel(autorPanel);
		
		
		UbicacionPanel ubicacionPanel = new UbicacionPanel(this);
		cambiarPanel(ubicacionPanel);
		
		
		
		BibliotecaPanel bibliotecaPabel = new BibliotecaPanel(this);
		cambiarPanel(bibliotecaPabel);
		*/
		
	}

	/*
	 * Creamos una instancia de la ventana
	 */
	public static Ventana_Principal getInstance() {
		if (instancia == null) {
			instancia = new Ventana_Principal();
		}
		return instancia;
	}

	/*
	 * Metodo que se encarga de reemplazar el panel actual con el que se le pasa por
	 * parametro. Se le llama, por ejemplo, al pulsar un boton.
	 */
	public void cambiarPanel(JPanel panelNuevo) {
		contentPane.removeAll();
		contentPane.add(panelNuevo);
		contentPane.repaint();
		contentPane.revalidate();
	}

}
