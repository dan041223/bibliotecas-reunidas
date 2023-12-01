package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ConectorBBDD;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;

public class Ventana_Principal extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Ventana_Principal instancia;

	
	
	private Ventana_Principal() {
		//Para conectarnos a la bbdd ni bien se inicia la app
		ConectorBBDD con = new ConectorBBDD();
		con.connect();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1119, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new CardLayout(0, 0));
		
		Login login = new Login();
		cambiarPanel(login);
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
	 * Metodo que se encarga de reemplazar el panel actual con el que se le pasa por parametro.
	 * Se le llama, por ejemplo, al pulsar un boton.
	 */
	public void cambiarPanel(JPanel panelNuevo) {
		contentPane.removeAll();
		contentPane.add(panelNuevo);
		contentPane.repaint();
		contentPane.revalidate();
	}

}
