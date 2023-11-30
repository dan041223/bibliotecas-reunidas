package controlador;

import java.awt.EventQueue;

import vista.Ventana_Principal;

public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Principal frame = Ventana_Principal.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
