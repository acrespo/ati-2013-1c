package gui.noiseMenu;

import gui.MessageFrame;
import gui.Panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GaussianNoiseDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public GaussianNoiseDialog(final Panel panel){
		
		setTitle("Ruido Gaussiano");
		setBounds(1, 1, 250, 120);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel paramPanel = new JPanel();
		paramPanel.setBorder(BorderFactory.createTitledBorder("Parámetros"));
		paramPanel.setBounds(0, 0, 250, 50);

		JLabel stdDevLabel = new JLabel("Desvío = ");
		final JTextField stdDevTextField = new JTextField("0.1");
		stdDevTextField.setColumns(3);

		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				double stdDev = 0;
				try {
					stdDev = Double.valueOf(stdDevTextField.getText());
				} catch (NumberFormatException ex){
					new MessageFrame("Los valores ingresados son incorrectos");
					return;
				}
	    		panel.getImage().whiteNoise(stdDev);
	    		panel.repaint();
	    		dispose();
			}
		});
		
		paramPanel.add(stdDevLabel);
		paramPanel.add(stdDevTextField);

		this.add(paramPanel);
		this.add(okButton);
	};

}
