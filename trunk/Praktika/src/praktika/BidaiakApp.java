package praktika;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import praktika.bezeroa.AplikazioNagusia;

public class BidaiakApp {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AplikazioNagusia nagusia = new AplikazioNagusia("Erreserba Sistema");
		nagusia.setLocationRelativeTo(null);
		// nagusia.setExtendedState(JFrame.MAXIMIZED_BOTH);
		nagusia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nagusia.setVisible(true);
	}

}
