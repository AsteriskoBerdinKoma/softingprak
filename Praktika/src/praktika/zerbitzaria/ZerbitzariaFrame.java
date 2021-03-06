package praktika.zerbitzaria;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.border.TitledBorder;

public class ZerbitzariaFrame extends JFrame {

	private JTextArea textArea;
	private JList list;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultListModel model;

	/**
	 * Create the frame
	 */
	public ZerbitzariaFrame() {
		super();
		setTitle("Erreserba Sistema Zerbitzaria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane;
		scrollPane = new JScrollPane();

		list = new JList();
		model = new DefaultListModel();
		list.setModel(model);
		scrollPane.setViewportView(list);

		JLabel konektatutakoBezeroakLabel;
		konektatutakoBezeroakLabel = new JLabel();
		konektatutakoBezeroakLabel.setText("Konektatuta dauden bezeroak:");

		JLabel ekintzakLabel;
		ekintzakLabel = new JLabel();
		ekintzakLabel.setText("Ekintzak:");

		textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		textArea.setLineWrap(true);
		final GroupLayout groupLayout = new GroupLayout(
				(JComponent) getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addComponent(
								scrollPane, GroupLayout.PREFERRED_SIZE, 184,
								GroupLayout.PREFERRED_SIZE).addComponent(
								konektatutakoBezeroakLabel)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addGroup(
								groupLayout.createSequentialGroup()
										.addComponent(ekintzakLabel).addGap(
												335, 335, 335)).addGroup(
								groupLayout.createSequentialGroup()
										.addComponent(textArea,
												GroupLayout.DEFAULT_SIZE, 374,
												Short.MAX_VALUE)
										.addContainerGap()))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap().addGroup(
						groupLayout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								konektatutakoBezeroakLabel).addComponent(
								ekintzakLabel)).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								groupLayout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(textArea,
												GroupLayout.DEFAULT_SIZE, 310,
												Short.MAX_VALUE).addComponent(
												scrollPane,
												GroupLayout.DEFAULT_SIZE, 310,
												Short.MAX_VALUE))
						.addContainerGap()));
		getContentPane().setLayout(groupLayout);
		pack();
		//
	}

	public void gehituBezeroa(String address) {
		model.addElement(address);
	}

	public void kenduBezeroa(String address) {
		model.removeElement(address);
	}

	public void gehituEkintza(String ekintza) {
		textArea.append(ekintza + "\n");
	}

}
