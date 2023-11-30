package controlador;

import java.awt.EventQueue;

import vista.Ventana_Principal;

public class Principal {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*
					 * Es una instancia porque queremos que lo que se cree es la ventana con su estado 
					 * para poder volver a ella en caso de que se quiera volver a iniciar sesion
					 */
					Ventana_Principal frame = Ventana_Principal.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
