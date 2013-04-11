package gui.noiseMenu;

import gui.Panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Image;

public class GaussianNoiseSlider extends JDialog implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private final Panel panel;

	public GaussianNoiseSlider(final Panel panel){
	
		this.panel = panel;
		setTitle("White Gaussian Noise");
		setBounds(1, 1, 350, 170);
		Dimension size = getToolkit().getScreenSize();
		setLocation(size.width/3 - getWidth()/3, size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				panel.setWorkingImage(panel.getImage());
				panel.repaint();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		JSlider range = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
		range.addChangeListener(this);
		range.setMajorTickSpacing(5);
		range.setMinorTickSpacing(1);
		range.setPaintTicks(true);
		range.setPaintLabels(true);
		range.setSize(350, 120);

		
		JPanel paramPanel = new JPanel();
		paramPanel.setBorder(BorderFactory.createTitledBorder("Param."));
		paramPanel.setBounds(0, 0, 350, 100);

		JLabel meanLabel = new JLabel("Standard Deviation = ");

		JButton okButton = new JButton("Go Back");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 350, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				panel.setImage(panel.getWorkingImage());
				dispose();
			}
		});
		
		paramPanel.add(meanLabel);
		paramPanel.add(range);

		this.add(paramPanel);
		this.add(okButton);
	};
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider source = (JSlider)e.getSource();
//	    if (!source.getValueIsAdjusting()) {
	        int value = (int)source.getValue();
	        Image panelImage = panel.getImage();
	        Image modify = (Image) panelImage.clone();
			modify.whiteNoise(value);
			panel.setWorkingImage(modify);
			panel.repaint();
//	    }
	}
	

}
