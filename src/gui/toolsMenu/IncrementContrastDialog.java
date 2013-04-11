package gui.toolsMenu;

import gui.MessageFrame;
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
public class IncrementContrastDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	public IncrementContrastDialog(final Panel panel){

		setTitle("Increment Contrast");
		setBounds(1, 1, 250, 170);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/3 - getWidth()/3,
		size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Original values"));
		pan1.setBounds(0, 0, 250, 50);
		
		JPanel pan2 = new JPanel();
		pan2.setBorder(BorderFactory.createTitledBorder("Final values"));
		pan2.setBounds(0, 50, 250, 50);

		JLabel r1Label = new JLabel("r1 = ");
		final JTextField r1 = new JTextField("0");
		r1.setColumns(3);

		JLabel r2Label = new JLabel(", r2 = ");
		final JTextField r2 = new JTextField("0");
		r2.setColumns(3);
		
		JLabel newR1Label = new JLabel("R1 = ");
		final JTextField newR1 = new JTextField("0");
		newR1.setColumns(3);

		JLabel newR2Label = new JLabel(", R2 = ");
		final JTextField newR2 = new JTextField("0");
		newR2.setColumns(3);

		
		JButton okButton = new JButton("Ok");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				int x1;
				int y1;
				int x2;
				int y2;
				
				try{
					x1 = Integer.valueOf(r1.getText());
					y1 = Integer.valueOf(r2.getText());
					x2 = Integer.valueOf(newR1.getText());
					y2 = Integer.valueOf(newR2.getText());
					
				} catch(NumberFormatException ex){
					new MessageFrame("Values entered are not valid");
					return;
				}
				
				panel.getImage().incrementContrast(x1, y1, x2, y2);
				panel.repaint();
				dispose();
			}
		});
		
		pan1.add(r1Label);
		pan1.add(r1);
		pan1.add(r2Label);
		pan1.add(r2);

		pan2.add(newR1Label);
		pan2.add(newR1);
		pan2.add(newR2Label);
		pan2.add(newR2);
		
		this.add(pan1);
		this.add(pan2);
		this.add(okButton);

		}

}
