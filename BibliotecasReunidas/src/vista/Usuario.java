package vista;

import javax.swing.JPanel;
import javax.swing.JComboBox;

public class Usuario extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Usuario() {
		setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(191, 115, 30, 22);
		add(comboBox);

	}
}
