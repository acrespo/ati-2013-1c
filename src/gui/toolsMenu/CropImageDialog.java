package gui.toolsMenu;

import gui.MessageFrame;
import gui.Panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Image;
import model.InvalidImageException;

import org.apache.sanselan.ImageWriteException;

import app.ImageSaver;


public class CropImageDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public CropImageDialog(final Panel panel){

	setTitle("Cut image");
	setBounds(1, 1, 250, 170);
	Toolkit toolkit = getToolkit();
	Dimension size = toolkit.getScreenSize();
	setLocation(size.width/3 - getWidth()/3,
	size.height/3 - getHeight()/3);
	this.setResizable(false);
	setLayout(null);

	JPanel pan1 = new JPanel();
	pan1.setBorder(BorderFactory.createTitledBorder("Upper-left corner coordinates"));
	pan1.setBounds(0, 0, 250, 50);
	
	JPanel pan2 = new JPanel();
	pan2.setBorder(BorderFactory.createTitledBorder("Bottom-right corner coordinates"));
	pan2.setBounds(0, 50, 250, 50);

	JLabel coordXLabel1 = new JLabel("X = ");
	final JTextField coordX1 = new JTextField("0");
	coordX1.setColumns(3);

	JLabel coordYLabel1 = new JLabel(", Y = ");
	final JTextField coordY1 = new JTextField("0");
	coordY1.setColumns(3);
	
	JLabel coordXLabel2 = new JLabel("X = ");
	final JTextField coordX2 = new JTextField("0");
	coordX2.setColumns(3);

	JLabel coordYLabel2 = new JLabel(", Y = ");
	final JTextField coordY2 = new JTextField("0");
	coordY2.setColumns(3);

	
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
				x1 = Integer.valueOf(coordX1.getText());
				y1 = Integer.valueOf(coordY1.getText());
				x2 = Integer.valueOf(coordX2.getText());
				y2 = Integer.valueOf(coordY2.getText());
				
			} catch(NumberFormatException ex){
				new MessageFrame("Values entered are not valid");
				return;
			}
			
			Image newImage = null;
			try {
				newImage = panel.getImage().cropImage(x1, y1, x2, y2);
			} catch (InvalidImageException e2) {
				new MessageFrame("Values entered are not valid, coordinates must be inside the image");
			}
			
			if(newImage != null){
				JFileChooser selector = new JFileChooser();
				selector.setApproveButtonText("Save");	
	    		selector.showSaveDialog(CropImageDialog.this);	
	    		
				File arch = selector.getSelectedFile();
				
	    		try {
					ImageSaver.saveImage(arch, newImage);
				} catch (ImageWriteException e1) {
					new MessageFrame("Could not load file");
				} catch (IOException e1) {
					new MessageFrame("Could not load file");
				}
				dispose();
				
			} else {
				new MessageFrame("Values entered are not valid");
			}
			
		}
	});
	
	pan1.add(coordXLabel1);
	pan1.add(coordX1);
	pan1.add(coordYLabel1);
	pan1.add(coordY1);

	pan2.add(coordXLabel2);
	pan2.add(coordX2);
	pan2.add(coordYLabel2);
	pan2.add(coordY2);
	
	this.add(pan1);
	this.add(pan2);
	this.add(okButton);

	}
}
