package gui.toolsMenu;

import gui.Panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PixelModificationDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public PixelModificationDialog(final Panel panel){
		
		setTitle("Modificar Pixel");
		setBounds(1, 1, 250, 170);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/3 - getWidth()/3,
		size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Coordinadas"));
		pan1.setBounds(0, 0, 250, 50);
		
		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Color"));
		pan2.setBounds(0, 50, 250, 50);

		JLabel coordLabel1 = new JLabel("X = ");
		final JTextField coordX = new JTextField("0");
		coordX.setColumns(3);

		JLabel coordLabel2 = new JLabel(", Y = ");
		final JTextField coordY = new JTextField("0");
		coordY.setColumns(3);

		JLabel colorLabel = new JLabel("Valor= ");
		final JTextField color = new JTextField("0");
		color.setColumns(3);
		color.setAlignmentX(LEFT_ALIGNMENT);
		
		JButton okButton = new JButton("OK");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(panel.setPixel(coordX.getText(), coordY.getText(), color.getText()))	{
					dispose();
				}
			}
		});
		
		pan1.add(coordLabel1);
		pan1.add(coordX);

		pan1.add(coordLabel2);
		pan1.add(coordY);

		pan2.add(colorLabel);
		pan2.add(color);
		
		this.add(pan1);
		this.add(pan2);
		this.add(okButton);
		
	};

}
