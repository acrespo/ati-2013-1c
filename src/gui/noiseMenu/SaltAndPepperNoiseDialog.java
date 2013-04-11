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

public class SaltAndPepperNoiseDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public SaltAndPepperNoiseDialog(final Panel panel){
		
		setTitle("Salt and Pepper Noise");
		setBounds(1, 1, 250, 120);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel paramPanel = new JPanel();
		paramPanel.setBorder(BorderFactory.createTitledBorder("Params."));
		paramPanel.setBounds(0, 0, 250, 50);

		JLabel minLabel = new JLabel("Min = ");
		final JTextField minTextField = new JTextField("0.1");
		minTextField.setColumns(3);

		JLabel maxLabel = new JLabel("Max = ");
		final JTextField maxTextField = new JTextField("0.9");
		maxTextField.setColumns(3);

		JButton okButton = new JButton("Go Back");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				double min = 0;
				double max = 0;
				try {
					min = Double.valueOf(minTextField.getText());
					max = Double.valueOf(maxTextField.getText());
				} catch (NumberFormatException ex){
					new MessageFrame("Values entered are not valid");
					return;
				}
	    		panel.getImage().saltAndPepperNoise(min, max);
	    		panel.repaint();
	    		dispose();
			}
		});
		
		paramPanel.add(minLabel);
		paramPanel.add(minTextField);

		paramPanel.add(maxLabel);
		paramPanel.add(maxTextField);

		this.add(paramPanel);
		this.add(okButton);
	};

}
