package gui.createMenu;

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

import model.Image;


public abstract class CreateImageDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public CreateImageDialog(final Panel panel) {
		
		setTitle("Create degrade");
		setBounds(1, 1, 250, 200);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/3 - getWidth()/3,
		size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBorder(BorderFactory.createTitledBorder("Size"));
		pan1.setBounds(0, 0, 250, 50);

		JLabel altoLabel = new JLabel("Height = ");
		final JTextField alto = new JTextField("300");
		alto.setColumns(3);

		JLabel anchoLabel = new JLabel(", Width = ");
		final JTextField ancho = new JTextField("300");
		ancho.setColumns(3);

		JButton okButton = new JButton("Ok");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				int height;
				int width;
				
				try{
					height = Integer.valueOf(alto.getText().trim());
					width = Integer.valueOf(ancho.getText().trim());					
				} catch(NumberFormatException ex){
					new MessageFrame("Values entered are not valid");
					return;
				}
				
				if( height <= 0 || width <= 0 ){
					new MessageFrame("Image must have at least 1x1 size");
					return;
				}
				
				Image img = createBinaryImage(height, width);
					
				if(img != null){
					panel.loadImage(img);
					panel.repaint();
					dispose();
				} else {
					new MessageFrame("Values entered are not valid");
					return;
				}
				
			}


		});
		
		pan1.add(altoLabel);
		pan1.add(alto);

		pan1.add(anchoLabel);
		pan1.add(ancho);
		
		this.add(pan1);
		this.add(okButton);
		
	};

	protected abstract Image createBinaryImage(int height, int width);
}
