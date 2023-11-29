package vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(new BorderLayout(0, 0));
		GridBagLayout grid = new GridBagLayout();
		add(grid, BorderLayout.NORTH);
	}

}
