package controlador;

import java.awt.EventQueue;

import javax.swing.JPanel;

import modelo.Autor;
import vista.AutorPanel;
import vista.Ventana_Principal;

public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Principal frame = new Ventana_Principal();
					//panel.setVisible(true);
					//frame.nuevoPanel(panel);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
