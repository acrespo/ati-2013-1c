package gui.toolsMenu;

import gui.Panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Utils;

public class HistogramDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1214144658583044333L;

	public HistogramDialog(final Panel panel) {
		
		setTitle("Gray scale histogram");
		setBounds(1, 1, 400, 250);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/3 - getWidth()/3,
				size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);
		
		final BufferedImage histogram = Utils.generateHistogram(panel.getImage());
		
		JPanel p1 = new JPanel();

		p1.setBounds(0, 0, 400, 200);
		p1.add(new JLabel(new ImageIcon(histogram)));
		
		JButton backButton = new JButton("Go Back");
		backButton.setSize(400, 30);
		backButton.setBounds(0, 200, 400, 30);
		backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();				
			}
		});
		
		this.add(p1);
		this.add(backButton);
		
	}
}
