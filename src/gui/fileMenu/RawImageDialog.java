package gui.fileMenu;

import gui.MessageFrame;
import gui.Panel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Image;

import app.ImageLoader;

public class RawImageDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public RawImageDialog(final Panel panel, final File arch){
		setTitle("Load Raw Image");
		setBounds(1, 1, 250, 170);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/3 - getWidth()/3,
		size.height/3 - getHeight()/3);
		this.setResizable(false);
		setLayout(null);

		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, 250, 50);
		

		JLabel anchoLabel = new JLabel("Width = ");
		final JTextField ancho = new JTextField("0");
		ancho.setColumns(3);
		
		JLabel altoLabel = new JLabel("Height = ");
		final JTextField alto = new JTextField("0");
		alto.setColumns(3);

		JButton okButton = new JButton("Ok");
		okButton.setSize(250, 40);
		okButton.setBounds(0, 100, 250, 40);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				int X, Y;
				Image image = null;
				try{
					X = Integer.valueOf(ancho.getText());
					Y = Integer.valueOf(alto.getText());
				} catch(NumberFormatException ex){
					new MessageFrame("Values entered are not valid");
					return;
				}
				
				try{
					image = ImageLoader.loadRaw(arch, X, Y);
				} catch (IOException ex){
					new MessageFrame("Could not load image");
				}
				
				if(image != null){
					panel.loadImage(image);
					panel.repaint();
				}
				dispose();
			}
		});
		
		pan1.add(anchoLabel);
		pan1.add(ancho);
		pan1.add(altoLabel);
		pan1.add(alto);
		
		this.add(pan1);
		this.add(okButton);
	}

}
