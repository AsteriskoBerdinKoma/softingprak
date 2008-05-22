package praktika.bezeroa;

import java.rmi.RemoteException;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import praktika.partekatuak.ErreserbaInterface;
import praktika.partekatuak.ErreserbarenBistaObserver;

/**
 * Aplikazioaren lehen mailaren(Bista) klasea
 */

public class ErreserbarenBista extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Kontrolak
	private DefaultListModel itenak; // new DefaultListModel();

	private JList lista; // new JList(itenak);

	// private JTextArea area = new JTextArea();

	public ErreserbarenBista(ErreserbaInterface erreserbaSistema) {
		try {
			ErreserbarenBistaObserver remErrBis = new ErreserbarenBistaObserver(this);
			erreserbaSistema.addObserver(remErrBis);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itenak = new DefaultListModel();
		lista = new JList(itenak);
		lista.setVisibleRowCount(24);
		JScrollPane scrollPane;
		scrollPane = new JScrollPane(lista);
		final GroupLayout groupLayout = new GroupLayout((JComponent) this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}

	public DefaultListModel getItenak(){
		return itenak;
	}

	public void setItenak(DefaultListModel items) {
		this.itenak = items;	
	}
}
