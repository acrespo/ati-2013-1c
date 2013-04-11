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

public class RayleighNoiseDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public RayleighNoiseDialog(final Panel panel){
		
		setTitle("Rayleigh Noise");
		setBounds(1, 1, 250, 120);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel paramPanel = new JPanel();
		paramPanel.setBorder(BorderFactory.createTitledBorder("Param."));
		paramPanel.setBounds(0, 0, 250, 50);

		JLabel meanLabel = new JLabel("Media = ");
		final JTextField meanTextField = new JTextField("1");
		meanTextField.setColumns(3);

		JButton okButton = new JButton("Go Back");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 50, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				double mean = 0;
				try {
					mean = Double.valueOf(meanTextField.getText());
				} catch (NumberFormatException ex){
					new MessageFrame("Values entered are not valid");
					return;
				}
	    		panel.getImage().rayleighNoise(mean);
	    		panel.repaint();
	    		dispose();
			}
		});
		
		paramPanel.add(meanLabel);
		paramPanel.add(meanTextField);

		this.add(paramPanel);
		this.add(okButton);
	};

}
