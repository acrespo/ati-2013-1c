package gui.toolsMenu;

import gui.Panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Image;

public class ThresholdDialog extends JDialog implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private final Panel panel;
	
	public ThresholdDialog(final Panel panel){
		
		this.panel = panel;
		setTitle("Threshold");
		setBounds(1, 1, 250, 170);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/3 - getWidth()/3,
		size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, 250, 70);
		

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
		
		
		JLabel umbralLabel = new JLabel("Threshold = ");
		JSlider range = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
		range.addChangeListener(this);
		range.setMajorTickSpacing(10);
		range.setMinorTickSpacing(1);
		range.setPaintTicks(true);
		range.setPaintLabels(true);

		JButton okButton = new JButton("Ok");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 90, 250, 40);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setImage(panel.getWorkingImage());
				dispose();
			}
		});

		pan1.add(umbralLabel);
		pan1.add(range);

		this.add(pan1);
		this.add(okButton);

		}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JSlider source = (JSlider)e.getSource();
//	    if (!source.getValueIsAdjusting()) {
	        int value = (int)source.getValue();
	        Image panelImage = panel.getImage();
	        Image modify = (Image) panelImage.clone();
			modify.threshold(value);
			panel.setWorkingImage(modify);
			panel.repaint();
//	    }
	}

}
